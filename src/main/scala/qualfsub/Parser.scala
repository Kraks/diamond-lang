package diamond.qualfsub

import diamond.parser._
import org.antlr.v4.runtime._
import scala.collection.JavaConverters._

package ir {
  abstract class IR
  trait TopLevel
  case class Program(top: List[TopLevel]) extends IR

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
  case class Param(name: Option[String], qty: core.QType) extends IR

  case class TyParamList(tyParams: List[TyParam]) extends IR
  case class TyParam(tvar: String, qvar: Option[String], bound: core.QType) extends IR

  case class Expr(e: core.Expr) extends IR with TopLevel {
    def toCore: core.Expr = e
  }

  abstract class Def extends IR with TopLevel
  case class MonoFunDef(name: String, params: List[Param], rt: Option[QType], body: Expr) extends Def
  case class PolyFunDef(name: String, tyParams: List[TyParam], params: List[Param], rt: Option[QType], body: Expr) extends Def
}

class DiamondVisitor extends DiamondParserBaseVisitor[ir.IR] {
  import DiamondParser._
  import ir._

  val coreTop: core.QType = core.QType(core.Type.TTop, core.Qual.fresh)

  def error = ???

  override def visitIdQty(ctx: IdQtyContext): Param = {
    Param(Some(ctx.ID.getText.toString), visitQty(ctx.qty).toCore)
  }

  override def visitParam(ctx: ParamContext): Param = {
    if (ctx.qty != null) Param(None, visitQty(ctx.qty).toCore)
    else if (ctx.idQty != null) visitIdQty(ctx.idQty)
    else error
  }

  override def visitParamList(ctx: ParamListContext): ParamList = ParamList(ctx.param.asScala.map(visitParam(_)).toList)

  override def visitFunTy(ctx: FunTyContext): Type = {
    val f = if (ctx.ID != null) Some(ctx.ID.getText.toString) else None
    val args = visitParamList(ctx.paramList).params
    val ret = visitQty(ctx.qty).toCore
    // TODO: multi-argument function types
    if (args.size == 0) {
      Type(core.Type.TFun(f, None, core.QType(core.Type.TUnit), ret))
    } else if (args.size == 1) {
      Type(core.Type.TFun(f, args(0).name, args(0).qty, ret))
    } else error
  }

  override def visitTyParam(ctx: TyParamContext): TyParam = {
    if (ctx.ID.size == 1 && ctx.ty == null) {
      TyParam(ctx.ID(0).getText.toString, None, coreTop)
    } else if (ctx.ID.size == 1 && ctx.ty != null) {
      TyParam(ctx.ID(0).getText.toString, None, core.QType(visitTy(ctx.ty).toCore, core.Qual.fresh))
    } else if (ctx.ID.size == 2) {
      TyParam(ctx.ID(0).getText.toString, Some(ctx.ID(1).getText.toString), visitQty(ctx.qty).toCore)
    } else error
  }

  override def visitTyParamList(ctx: TyParamListContext): TyParamList = TyParamList(ctx.tyParam.asScala.map(visitTyParam(_)).toList)

  override def visitTyFunTy(ctx: TyFunTyContext): Type = {
    val f = if (ctx.ID != null) Some(ctx.ID.getText.toString) else None
    val args = visitTyParamList(ctx.tyParamList).tyParams
    val ret = visitQty(ctx.qty).toCore
    if (args.size == 1) {
      Type(core.Type.TForall(f, args(0).tvar, args(0).qvar.getOrElse(""), args(0).bound, ret))
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
      val qty = visitQty(ctx.qty)
      // TODO: nested ref types
      Type(core.Type.TRef(qty.t))
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

  override def visitNamedParamList(ctx: NamedParamListContext): ParamList = ParamList(ctx.idQty.asScala.map(visitIdQty(_)).toList)

  override def visitMonoFunDef(ctx: MonoFunDefContext): MonoFunDef = {
    val name = ctx.ID.getText.toString
    val args = visitNamedParamList(ctx.namedParamList).params
    val rt = if (ctx.qty != null) Some(visitQty(ctx.qty)) else None
    val body = visitExpr(ctx.expr)
    MonoFunDef(name, args, rt, body)
  }

  override def visitPolyFunDef(ctx: PolyFunDefContext): PolyFunDef = {
    val name = ctx.ID.getText.toString
    val tyArgs = visitTyParamList(ctx.tyParamList).tyParams
    val args = visitNamedParamList(ctx.namedParamList).params
    val rt = if (ctx.qty != null) Some(visitQty(ctx.qty)) else None
    val body = visitExpr(ctx.expr)
    PolyFunDef(name, tyArgs, args, rt, body)
  }

  override def visitLam(ctx: LamContext): Expr = {
    val name = if (ctx.ID != null) ctx.ID.getText.toString else "_"
    val args = visitNamedParamList(ctx.namedParamList).params
    val rt = if (ctx.qty != null) Some(visitQty(ctx.qty).toCore) else None
    val body = visitExpr(ctx.expr)
    // TODO: multi-argument lambda functions
    if (args.size == 0) {
      Expr(core.Expr.ELam(name, "_", core.QType(core.Type.TUnit), body.toCore, rt))
    } else if (args.size == 1) {
      Expr(core.Expr.ELam(name, args(0).name.get, args(0).qty, body.toCore, rt))
    } else error
  }

  override def visitTyLam(ctx: TyLamContext): Expr = {
    val name = if (ctx.ID != null) Some(ctx.ID.getText.toString) else None
    val tyArgs = visitTyParamList(ctx.tyParamList).tyParams
    val rt = if (ctx.qty != null) Some(visitQty(ctx.qty).toCore) else None
    val body = visitExpr(ctx.expr)
    if (tyArgs.size == 1) {
      Expr(core.Expr.ETyLam(name, tyArgs(0).tvar, tyArgs(0).qvar.getOrElse(""), tyArgs(0).bound, body.toCore, rt))
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

  override def visitExpr(ctx: ExprContext): Expr = {
    val e: core.Expr =
      if (ctx.ID != null) core.Expr.EVar(ctx.ID.getText.toString)
      else if (ctx.op1 != null) ???
      else if (ctx.op2 != null) {
        val arg1 = visitExpr(ctx.expr(0)).toCore
        val arg2 = visitExpr(ctx.expr(1)).toCore
        core.Expr.EBinOp(ctx.op2.getText.toString, arg1, arg2)
      } else if (ctx.COLONEQ != null) {
        val lhs = visitExpr(ctx.expr(0)).toCore
        val rhs = visitExpr(ctx.expr(1)).toCore
        core.Expr.EAssign(lhs, rhs)
      } else if (ctx.AT != null) {
        // TODO
        ???
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
    val topLevels  = ctx.funDefOrExpr.asScala.map(visit(_)).toList
    Program(topLevels.asInstanceOf[List[TopLevel]])
  }
}

object Parser {
  def parse(input: String): ir.IR = {
    val charStream = new ANTLRInputStream(input)
    val lexer = new DiamondLexer(charStream)
    val tokens = new CommonTokenStream(lexer)
    val parser = new DiamondParser(tokens)
    val visitor = new DiamondVisitor()
    val res: ir.IR = visitor.visit(parser.program).asInstanceOf[ir.IR]
    res
  }

  def parseFile(filepath: String): ir.IR = parse(scala.io.Source.fromFile(filepath).mkString)

  def main(args: Array[String]): Unit = {
    parseFile("grammar/example.dia")
  }
}
