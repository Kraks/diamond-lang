package diamond.effqualfsub

import diamond._
import diamond.parser._

import core.MemEff._
import core.given

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
      if (tops.size > 0) {
        val (newTops, last) = tops.last match {
          case Expr(e) => (tops.dropRight(1), e)
        }
        newTops.foldRight(last) {
          case (Expr(e), last) =>
            core.Expr.ELet(freshVar(letPre), None, e, last)
        }
      } else core.Expr.EUnit
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

  case class EffList(effs: List[Eff]) extends IR {
    def toCore: core.Eff = effs.map(_.toCore).reduce(_ ⊔ _)
  }
  case class Eff(eff: core.Eff) extends IR {
    def toCore: core.Eff = eff
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
  case class MonoFunDef(name: String, params: List[Param], rt: Option[QType], eff: Option[core.Eff], body: core.Expr) extends Def {
    def toLet(e: core.Expr): core.Expr.ELet = {
      val realParams: List[Param] =
        if (params.size == 0) List(Param(freshVar(varPre), core.QType(core.Type.TUnit, core.Qual.untrack)))
        else params
      val rhs = realParams.zipWithIndex.foldRight(body) {
        case ((param, idx), body) =>
          val funName = if (idx == 0) name else freshVar(funPre)
          val realRt = if (idx == realParams.size-1) rt.map(_.toCore) else None
          core.Expr.ELam(funName, param.name, param.qty, body, realRt, eff)
      }
      val rhsTy = None
      core.Expr.ELet(name, rhsTy, rhs, e)
    }
  }
  case class PolyFunDef(name: String, tyParams: List[TyParam], params: List[Param], rt: Option[QType], body: core.Expr) extends Def {
    def toLet(e: core.Expr): core.Expr.ELet = {
      val realParams: List[Param] =
        if (params.size == 0) List(Param(freshVar(varPre), core.QType(core.Type.TUnit, core.Qual.untrack)))
        else params
      val lam = realParams.zipWithIndex.foldRight(body) {
        case ((param, idx), body) =>
          val realRt = if (idx == realParams.size-1) rt.map(_.toCore) else None
          core.Expr.ELam(freshVar(funPre), param.name, param.qty, body, realRt, ???)
      }
      val rhs = tyParams.zipWithIndex.foldRight(lam) {
        case ((ty, idx), body) =>
          val funName = if (idx == 0) name else freshVar(tyFunPre)
          core.Expr.ETyLam(funName, ty.tvar, ty.qvar, ty.bound, body, None, ???)
      }
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
      else List(Param(freshVar(varPre), core.QType(core.Type.TUnit, core.Qual.untrack)))
    val ret = visitQty(ctx.qty).toCore
    val eff = visitEffs(ctx.effs).toCore
    val rest = args.zipWithIndex.drop(1).foldRight(ret) {
      case ((arg, idx), rt) =>
        val q = core.Qual(args.take(idx).map(_.name).toSet)
        // XXX: here we use `eff` for every curried function's effect, is that right?
        core.QType(core.Type.TFun(freshVar(funPre), arg.name, arg.qty, rt, eff), q)
    }
    val fty = core.Type.TFun(f, args(0).name, args(0).qty, rest, eff)
    Type(fty)
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
    //Note: we have not supported multi-argument forall types, they can only be curried
    if (args.size == 1) {
      Type(core.Type.TForall(f, args(0).tvar, args(0).qvar, args(0).bound, ret, ???))
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
    else if (ctx.qualElems != null) {
      val elems: Set[core.QElem] = ctx.qualElems.qualElem.asScala.map( e =>
        if (e.ID != null) e.ID.getText.toString
        else core.Fresh()
      ).toSet
      Qual(core.Qual(elems))
    } else Qual(core.Qual.untrack)
  }

  override def visitQty(ctx: QtyContext): QType = {
    val ty = visitTy(ctx.ty).t
    if (ctx.qual != null) QType(ty, visitQual(ctx.qual).q)
    else QType(ty, core.Qual.untrack)
  }

  override def visitNamedParamList(ctx: NamedParamListContext): ParamList =
    ParamList(ctx.idQty.asScala.map(visitIdQty(_)).toList)

  override def visitEff(ctx: EffContext): Eff = {
    val ids = ctx.ID.asScala
    val lab = ids(0).getText.toString match {
      case "pure" => Bot
      case "rd" | "read" => Read
      case "wr" | "write" => Write
      case "kl" | "kill" => Kill
      case e => throw new RuntimeException(s"Unknown effect $e")
    }
    val vars = ids.tail.map(_.getText.toString).toSet
    Eff(core.Eff(Map(vars -> lab)))
  }

  override def visitEffs(ctx: EffsContext): EffList = {
    if (ctx.ID != null) {
      ctx.ID.getText.toString match {
        case "pure" => EffList(List(Eff(core.Eff(Map()))))
        case e => throw new RuntimeException(s"Unknown effect $e")
      }
    } else EffList(ctx.eff.asScala.toList.map(visitEff(_)))
  }

  override def visitMonoFunDef(ctx: MonoFunDefContext): MonoFunDef = {
    val name = ctx.ID.getText.toString
    val args =
      if (ctx.namedParamList != null)
        visitNamedParamList(ctx.namedParamList).params
      else List()
    val rt = if (ctx.qty != null) Some(visitQty(ctx.qty)) else None
    val eff = if (ctx.effs != null) Some(visitEffs(ctx.effs).toCore) else None
    val body = visitExpr(ctx.expr).toCore
    MonoFunDef(name, args, rt, eff, body)
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
      else List(Param(freshVar(varPre), core.QType(core.Type.TUnit, core.Qual.untrack)))
    val rt = if (ctx.qty != null) Some(visitQty(ctx.qty).toCore) else None
    val eff = if (ctx.effs != null) Some(visitEffs(ctx.effs).toCore) else None
    val body = visitExpr(ctx.expr).toCore
    val ret = args.zipWithIndex.foldRight(body) {
      case ((arg, idx), body) =>
        val realName = if (idx == 0) name else freshVar(funPre)
        val realRt = if (idx == args.size-1) rt else None
        val realEff = if (idx == args.size-1) eff else None
        core.Expr.ELam(realName, arg.name, arg.qty, body, realRt, realEff)
    }
    Expr(ret)
  }

  override def visitTyLam(ctx: TyLamContext): Expr = {
    val name = if (ctx.ID != null) ctx.ID.getText.toString else freshVar(tyFunPre)
    val tyArgs =
      if (ctx.tyParamList != null)
        visitTyParamList(ctx.tyParamList).tyParams
      else List()
    val rt = if (ctx.qty != null) Some(visitQty(ctx.qty).toCore) else None
    val body = visitExpr(ctx.expr).toCore
    //Note: we have not supported multi-argument type lambdas, they can only be curried
    if (tyArgs.size == 1) {
      Expr(core.Expr.ETyLam(name, tyArgs(0).tvar, tyArgs(0).qvar, tyArgs(0).bound, body, rt, ???))
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
      } else if (ctx.boolOp2 != null) {
        val arg1 = visitExpr(ctx.expr(0)).toCore
        val arg2 = visitExpr(ctx.expr(1)).toCore
        core.Expr.EBinOp(ctx.boolOp2.getText.toString, arg1, arg2)
      } else if (ctx.ADD != null) {
        val arg1 = visitExpr(ctx.expr(0)).toCore
        val arg2 = visitExpr(ctx.expr(1)).toCore
        core.Expr.EBinOp("+", arg1, arg2)
      } else if (ctx.MINUS != null) {
        val arg1 = visitExpr(ctx.expr(0)).toCore
        val arg2 = visitExpr(ctx.expr(1)).toCore
        core.Expr.EBinOp("-", arg1, arg2)
      } else if (ctx.MULT != null) {
        val arg1 = visitExpr(ctx.expr(0)).toCore
        val arg2 = visitExpr(ctx.expr(1)).toCore
        core.Expr.EBinOp("*", arg1, arg2)
      } else if (ctx.DIV != null) {
        val arg1 = visitExpr(ctx.expr(0)).toCore
        val arg2 = visitExpr(ctx.expr(1)).toCore
        core.Expr.EBinOp("/", arg1, arg2)
      } else if (ctx.COLONEQ != null) {
        val lhs = visitExpr(ctx.expr(0)).toCore
        val rhs = visitExpr(ctx.expr(1)).toCore
        core.Expr.EAssign(lhs, rhs)
      } else if (ctx.funDef != null) {
        val body = visitExpr(ctx.expr(0)).toCore
        super.visit(ctx.funDef).asInstanceOf[Def].toLet(body)
      } else if (ctx.args != null) {
        val f = visitExpr(ctx.expr(0)).toCore
        val args = visitArgs(ctx.args).args
        val fresh = if (ctx.AT != null) Some(true) else None
        args.foldLeft(f) { case (f, arg) => core.Expr.EApp(f, arg, fresh) }
      } else if (ctx.tyArgs != null) {
        // there is at least one type in tyArgs
        val fresh = if (ctx.AT != null) Some(true) else None
        val f = visitExpr(ctx.expr(0)).toCore
        val tyArgs = visitTyArgs(ctx.tyArgs).args
        tyArgs.foldLeft(f) { case (f, tyArg) => core.Expr.ETyApp(f, tyArg, fresh) }
      } else if (ctx.IF != null && ctx.ELSE != null) {
        val cnd = visitExpr(ctx.expr(0)).toCore
        val thn = visitExpr(ctx.expr(1)).toCore
        val els = visitExpr(ctx.expr(2)).toCore
        core.Expr.ECond(cnd, thn, els)
      } else if (ctx.LPAREN != null && ctx.RPAREN != null) {
        // term application without argument
        val f = visitExpr(ctx.expr(0)).toCore
        val arg = core.Expr.EUnit
        core.Expr.EApp(f, arg, None)
      } else {
        super.visitExpr(ctx).asInstanceOf[Expr].toCore // value, alloc, deref, let, wrapped expr
      }
    Expr(e)
  }

  override def visitWrapExpr(ctx: WrapExprContext): Expr = {
    Expr(visitExpr(ctx.expr).toCore)
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
