package diamond

// TODO: add F-sub style subtyping
// TODO: add qualified types (QType)
// TODO: add algebraic data types
// TODO: type lambda self-reference?
// TODO: multi-argument lambdas
// TODO: add effect system

enum Type:
  case TUnit
  case TNum
  case TBool
  case TVar(x: String)
  case TFun(t1: Type, t2: Type)
  case TForall(x: String, t: Type)
  case TRef(t: Type)

import Type._

case class QType(t: Type, q: Set[String])

enum Expr:
  case EUnit
  case ENum(n: Int)
  case EBool(b: Boolean)
  case EVar(x: String)
  case EBinOp(op: String, e1: Expr, e2: Expr)
  case ELam(f: String, x: String, body: Expr, ft: TFun)
  case EApp(e1: Expr, e2: Expr)
  case ELet(x: String, t: Option[Type], rhs: Expr, body: Expr)
  case ETyLam(tx: String, body: Expr)
  case ETyApp(e: Expr, t: Type)
  case EAlloc(init: Expr)
  case EAssign(lhs: Expr, rhs: Expr)
  case EDeref(e: Expr)
  case ECond(cnd: Expr, thn: Expr, els: Expr)

object TypeSyntax:
  import Type._
  def ∀(x: String)(t: Type) = TForall(x, t)
  extension (t: Type)
    def ->(s: Type): TFun = TFun(t, s)

object ExprSyntax:
  import Expr._
  import Type._
  def λ(f: String, x: String)(ft: TFun)(e: => Expr): ELam = ELam(f, x, e, ft)
  def Λ(x: String)(e: => Expr): ETyLam = ETyLam(x, e)
  def ite(c: Expr)(thn: Expr)(els: Expr): Expr = ECond(c, thn, els)

  extension (e: Expr)
    def apply(a: Expr): Expr = EApp(e, a)
    def ===(e0: Expr): Expr = EBinOp("=", e, e0)
    def ===(e0: Int): Expr = EBinOp("=", e, ENum(e0))
    def +(e0: Expr): Expr = EBinOp("+", e, e0)
    def +(e0: Int): Expr = EBinOp("+", e, ENum(e0))
    def -(e0: Expr): Expr = EBinOp("-", e, e0)
    def -(e0: Int): Expr = EBinOp("-", e, ENum(e0))
    def *(e0: Expr): Expr = EBinOp("*", e, e0)
    def *(e0: Int): Expr = EBinOp("*", e, ENum(e0))
    def /(e0: Expr): Expr = EBinOp("/", e, e0)
    def /(e0: Int): Expr = EBinOp("/", e, ENum(e0))
