package diamond.qualfsub

import diamond._
import diamond.parser._
import org.antlr.v4.runtime._
import scala.collection.JavaConverters._

// Italic style letters are prefixes for anonymous entities generated during parsing

val letPre = "ℓ"
def letPre(n: Int): String = letPre + "#" + n
val tyFunPre = "𝐹"
def tyFunPre(n: Int): String = tyFunPre + "#" + n
val funPre = "𝑓"
def funPre(n: Int): String = funPre + "#" + n
val varPre = "𝑥"
def varPre(n: Int): String = varPre + "#" + n
val tyVarPre = "𝑋"
def tyVarPre(n: Int): String = tyVarPre + "#" + n

package ir {
  abstract class IR
  trait TopLevel
  case class Program(tops: List[Expr]) extends IR {
    def toCore: core.Expr = {
      val (newTops, last) = tops.last match {
        case Expr(e) => (tops.dropRight(1), e)
      }
      newTops.foldRight(last) {
        case (Expr(e), last) =>
          core.Expr.ELet(freshVar(letPre), None, e, last)
      }
    }
  }

  case class Type(t: core.Type) extends IR {
    def toCore = t
  }

  case class Qual(q: core.Qual) extends IR {
    def toCore = q
  }

  case class QType(t: core.Type, q: core.Qual) extends IR {
    def toCore: core.QType = core.QType(t, q)
  }

  case class ParamList(params: List[Param]) extends IR
  case class Param(name: String, qty: core.QType) extends IR

  case class TyParamList(tyParams: List[TyParam]) extends IR
  case class TyParam(tvar: String, qvar: String, bound: core.QType) extends IR

  case class ArgList(args: List[core.Expr]) extends IR
  case class TyArgList(args: List[core.QType]) extends IR

  case class Expr(e: core.Expr) extends IR {
    def toCore: core.Expr = e
  }

  abstract class Def extends IR {
    def toLet(e: core.Expr): core.Expr.ELet
  }
  case class MonoFunDef(name: String, params: List[Param], rt: Option[QType], body: core.Expr) extends Def {
    // TODO: multi-arg functions
    def toLet(e: core.Expr): core.Expr.ELet = {
      val argName =
        if (params.size == 0) freshVar(varPre)
        else params(0).name
      val argTy =
        if (params.size == 0) core.QType(core.Type.TUnit, core.Qual.untrack)
        else params(0).qty
      val rhs = core.Expr.ELam(name, argName, argTy, body, rt.map(_.toCore))
      val rhsTy = None
      core.Expr.ELet(name, rhsTy, rhs, e)
    }
  }
  case class PolyFunDef(name: String, tyParams: List[TyParam], params: List[Param], rt: Option[QType], body: core.Expr) extends Def {
    // TODO: multi-arg functions
    def toLet(e: core.Expr): core.Expr.ELet = {
      val ty = tyParams(0)
      val lamBody = core.Expr.ELam(freshVar(funPre), params(0).name, params(0).qty, body, rt.map(_.toCore))
      val rhs = core.Expr.ETyLam(name, ty.tvar, ty.qvar, ty.bound, lamBody, None)
      val rhsTy = None
      core.Expr.ELet(name, rhsTy, rhs, e)
    }
  }
}

class DiamondVisitor extends DiamondParserBaseVisitor[ir.IR] {
  import DiamondParser._
  import ir._

  val coreTop: core.QType = core.QType(core.Type.TTop, core.Qual.fresh)

  def error = ???

  override def visitIdQty(ctx: IdQtyContext): Param = {
    Param(ctx.ID.getText.toString, visitQty(ctx.qty).toCore)
  }

  override def visitParam(ctx: ParamContext): Param = {
    if (ctx.qty != null) Param(freshVar("Arg"), visitQty(ctx.qty).toCore)
    else if (ctx.idQty != null) visitIdQty(ctx.idQty)
    else error
  }

  override def visitParamList(ctx: ParamListContext): ParamList =
    ParamList(ctx.param.asScala.map(visitParam(_)).toList)

  override def visitFunTy(ctx: FunTyContext): Type = {
    val f = if (ctx.ID != null) ctx.ID.getText.toString else freshVar(funPre)
    val args =
      if (ctx.paramList != null) visitParamList(ctx.paramList).params
      else List()
    val ret = visitQty(ctx.qty).toCore
    // TODO: multi-argument function types
    if (args.size == 0) {
      Type(core.Type.TFun(f, freshVar(varPre), core.QType(core.Type.TUnit), ret))
    } else if (args.size == 1) {
      Type(core.Type.TFun(f, args(0).name, args(0).qty, ret))
    } else error
  }

  override def visitTyParam(ctx: TyParamContext): TyParam = {
    if (ctx.ID.size == 1 && ctx.ty == null) {
      TyParam(ctx.ID(0).getText.toString, freshVar(varPre), coreTop)
    } else if (ctx.ID.size == 1 && ctx.ty != null) {
      TyParam(ctx.ID(0).getText.toString, freshVar(varPre), core.QType(visitTy(ctx.ty).toCore, core.Qual.fresh))
    } else if (ctx.ID.size == 2) {
      TyParam(ctx.ID(0).getText.toString, ctx.ID(1).getText.toString, visitQty(ctx.qty).toCore)
    } else error
  }

  override def visitTyParamList(ctx: TyParamListContext): TyParamList = TyParamList(ctx.tyParam.asScala.map(visitTyParam(_)).toList)

  override def visitTyFunTy(ctx: TyFunTyContext): Type = {
    val f = if (ctx.ID != null) ctx.ID.getText.toString else freshVar(tyFunPre)
    val args = visitTyParamList(ctx.tyParamList).tyParams
    val ret = visitQty(ctx.qty).toCore
    if (args.size == 1) {
      Type(core.Type.TForall(f, args(0).tvar, args(0).qvar, args(0).bound, ret))
    } else error
  }

  override def visitTy(ctx: TyContext): Type = {
    if (ctx.ID != null) {
      val s = ctx.ID.getText.toString
      if (s == "Int") Type(core.Type.TNum)
      else if (s == "Unit") Type(core.Type.TUnit)
      else if (s == "Top") Type(core.Type.TTop)
      else if (s == "Bool") Type(core.Type.TBool)
      else Type(core.Type.TVar(s))
    } else if (ctx.REF != null) {
      Type(core.Type.TRef(visitQty(ctx.qty).toCore))
    }
    else if (ctx.funTy != null) visitFunTy(ctx.funTy)
    else if (ctx.tyFunTy != null) visitTyFunTy(ctx.tyFunTy)
    else visitTy(ctx.ty)
  }

  override def visitQual(ctx: QualContext): Qual = {
    if (ctx.FRESH != null) Qual(core.Qual.fresh)
    else if (ctx.ID != null) Qual(core.Qual.singleton(ctx.ID.getText.toString))
    else {
      val elems: Set[core.QElem] = ctx.qualElems.qualElem.asScala.map( e =>
        if (e.ID != null) e.ID.getText.toString
        else core.Fresh()
      ).toSet
      Qual(core.Qual(elems))
    }
  }

  override def visitQty(ctx: QtyContext): QType = {
    val ty = visitTy(ctx.ty).t
    if (ctx.qual != null) QType(ty, visitQual(ctx.qual).q)
    else QType(ty, core.Qual.untrack)
  }

  override def visitNamedParamList(ctx: NamedParamListContext): ParamList =
    ParamList(ctx.idQty.asScala.map(visitIdQty(_)).toList)

  override def visitMonoFunDef(ctx: MonoFunDefContext): MonoFunDef = {
    val name = ctx.ID.getText.toString
    val args =
      if (ctx.namedParamList != null)
        visitNamedParamList(ctx.namedParamList).params
      else List()
    val rt = if (ctx.qty != null) Some(visitQty(ctx.qty)) else None
    val body = visitExpr(ctx.expr).toCore
    MonoFunDef(name, args, rt, body)
  }

  override def visitPolyFunDef(ctx: PolyFunDefContext): PolyFunDef = {
    val name = ctx.ID.getText.toString
    val tyArgs =
      if (ctx.tyParamList != null)
        visitTyParamList(ctx.tyParamList).tyParams
      else List()
    val args =
      if (ctx.namedParamList != null)
        visitNamedParamList(ctx.namedParamList).params
      else List()
    val rt = if (ctx.qty != null) Some(visitQty(ctx.qty)) else None
    val body = visitExpr(ctx.expr).toCore
    PolyFunDef(name, tyArgs, args, rt, body)
  }

  override def visitLam(ctx: LamContext): Expr = {
    val name = if (ctx.ID != null) ctx.ID.getText.toString else freshVar(funPre)
    val args =
      if (ctx.namedParamList != null)
        visitNamedParamList(ctx.namedParamList).params
      else List()
    val rt = if (ctx.qty != null) Some(visitQty(ctx.qty).toCore) else None
    val body = visitExpr(ctx.expr).toCore
    // TODO: multi-argument lambda functions
    if (args.size == 0) {
      Expr(core.Expr.ELam(name, freshVar(varPre), core.QType(core.Type.TUnit), body, rt))
    } else if (args.size == 1) {
      Expr(core.Expr.ELam(name, args(0).name, args(0).qty, body, rt))
    } else error
  }

  override def visitTyLam(ctx: TyLamContext): Expr = {
    val name = if (ctx.ID != null) ctx.ID.getText.toString else freshVar(tyFunPre)
    val tyArgs =
      if (ctx.tyParamList != null)
        visitTyParamList(ctx.tyParamList).tyParams
      else List()
    val rt = if (ctx.qty != null) Some(visitQty(ctx.qty).toCore) else None
    val body = visitExpr(ctx.expr).toCore
    if (tyArgs.size == 1) {
      Expr(core.Expr.ETyLam(name, tyArgs(0).tvar, tyArgs(0).qvar, tyArgs(0).bound, body, rt))
    } else error
  }

  override def visitValue(ctx: ValueContext): Expr = {
    if (ctx.TRUE != null) Expr(core.Expr.EBool(true))
    else if (ctx.FALSE != null) Expr(core.Expr.EBool(false))
    else if (ctx.UNIT != null) Expr(core.Expr.EUnit)
    else if (ctx.INT != null) Expr(core.Expr.ENum(ctx.INT.getText.toInt))
    else if (ctx.lam != null) visitLam(ctx.lam)
    else if (ctx.tyLam != null) visitTyLam(ctx.tyLam)
    else error
  }

  override def visitAlloc(ctx: AllocContext): Expr = Expr(core.Expr.EAlloc(visitExpr(ctx.expr).toCore))

  override def visitDeref(ctx: DerefContext): Expr = Expr(core.Expr.EDeref(visitExpr(ctx.expr).toCore))

  override def visitLet(ctx: LetContext): Expr = {
    val rhs = visitExpr(ctx.expr(0)).toCore
    val body = visitExpr(ctx.expr(1)).toCore
    val isGlobal = (ctx.valDecl.TOPVAL != null)
    if (ctx.ID != null) {
      val x = ctx.ID.getText.toString
      Expr(core.Expr.ELet(x, None, rhs, body, isGlobal))
    } else {
      val Param(x, qty) = visitIdQty(ctx.idQty)
      Expr(core.Expr.ELet(x, Some(qty), rhs, body, isGlobal))
    }
  }

  override def visitArgs(ctx: ArgsContext): ArgList =
    ArgList(ctx.expr.asScala.map(visitExpr(_).toCore).toList)

  override def visitTyArgs(ctx: TyArgsContext): TyArgList =
    TyArgList(ctx.qty.asScala.map(visitQty(_).toCore).toList)

  override def visitExpr(ctx: ExprContext): Expr = {
    val e: core.Expr =
      if (ctx.ID != null) core.Expr.EVar(ctx.ID.getText.toString)
      else if (ctx.op1 != null) {
        val e = visitExpr(ctx.expr(0)).toCore
        core.Expr.EUnaryOp(ctx.op1.getText.toString, e)
      } else if (ctx.op2 != null) {
        val arg1 = visitExpr(ctx.expr(0)).toCore
        val arg2 = visitExpr(ctx.expr(1)).toCore
        core.Expr.EBinOp(ctx.op2.getText.toString, arg1, arg2)
      } else if (ctx.COLONEQ != null) {
        val lhs = visitExpr(ctx.expr(0)).toCore
        val rhs = visitExpr(ctx.expr(1)).toCore
        core.Expr.EAssign(lhs, rhs)
      } else if (ctx.funDef != null) {
        val body = visitExpr(ctx.expr(0)).toCore
        super.visit(ctx.funDef).asInstanceOf[Def].toLet(body)
      } else if (ctx.args != null) {
        // TODO: multi-argument application
        val e = visitExpr(ctx.expr(0)).toCore
        val args = visitArgs(ctx.args).args
        if (ctx.AT != null) core.Expr.EApp(e, args(0), Some(true))
        else core.Expr.EApp(e, args(0), None)
      } else if (ctx.tyArgs != null) {
        // TODO: multi-argument application
        val e = visitExpr(ctx.expr(0)).toCore
        val tyArgs = visitTyArgs(ctx.tyArgs).args
        if (ctx.AT != null) core.Expr.ETyApp(e, tyArgs(0), Some(true))
        else core.Expr.ETyApp(e, tyArgs(0), None)
      } else if (ctx.LPAREN != null && ctx.RPAREN != null) {
        visitExpr(ctx.expr(0)).toCore
      } else if (ctx.LCURLY != null && ctx.RCURLY != null) {
        visitExpr(ctx.expr(0)).toCore
      } else {
        super.visitExpr(ctx).asInstanceOf[Expr].toCore // value, alloc, deref, let
      }
    Expr(e)
  }

  override def visitProgram(ctx: ProgramContext): IR = {
    val exprs = ctx.expr.asScala.map(visit(_)).toList
    Program(exprs.asInstanceOf[List[Expr]])
  }
}

object Parser {
  def parse(input: String): ir.Program = {
    Counter.reset
    val charStream = new ANTLRInputStream(input)
    val lexer = new DiamondLexer(charStream)
    val tokens = new CommonTokenStream(lexer)
    val parser = new DiamondParser(tokens)
    val visitor = new DiamondVisitor()
    val res = visitor.visit(parser.program).asInstanceOf[ir.Program]
    res
  }

  def parseFile(filepath: String): ir.Program = parse(scala.io.Source.fromFile(filepath).mkString)

  def parseToCore(input: String) = parse(input).toCore

  def parseFileToCore(filepath: String) = parseFile(filepath).toCore
}
