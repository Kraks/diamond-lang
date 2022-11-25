package diamond.qualstlc

enum Type:
  case TUnit
  case TNum
  case TBool
  case TFun(t1: QType, t2: QType)
  case TRef(t: Type)

import Type._

case class Fresh()
type QElem = String | Fresh
object Qualifier:
  def untrack: Qualifier = Qualifier(Set())
case class Qualifier(q: Set[QElem]):
  def isUntrack: Boolean = q.isEmpty
  def isFresh: Boolean = q.contains(Fresh())

case class QType(t: Type, q: Qualifier)

enum Expr:
  case EUnit
  case ENum(n: Int)
  case EBool(b: Boolean)
  case EVar(x: String)
  case EBinOp(op: String, e1: Expr, e2: Expr)
  case ELam(f: String, x: String, at: QType, body: Expr, rt: Option[QType])
  case EApp(e1: Expr, e2: Expr)
  case ELet(x: String, t: Option[QType], rhs: Expr, body: Expr)
  case EAlloc(init: Expr)
  case EAssign(lhs: Expr, rhs: Expr)
  case EDeref(e: Expr)
  case ECond(cnd: Expr, thn: Expr, els: Expr)

import Expr._

object TypeSyntax:
  import Type._
  val ◆ = Fresh()
  extension (t: QType)
    def ~>(s: QType): TFun = TFun(t, s)
  extension (t: Type)
    def ^(q: Qualifier): QType = QType(t, q)
    def ^(q: QElem): QType = QType(t, Qualifier(Set(q)))
  // type to qualified type conversion, default is untracked
  given Conversion[Type, QType] = QType(_, Qualifier.untrack)

object ExprSyntax:
  import Expr._
  import Type._

  val x = EVar("x")
  val y = EVar("y")
  val z = EVar("z")
  val n = EVar("n")
  val f = EVar("f")
  val g = EVar("g")

  case class BindTy(id: String, ty: QType) {
    def ⇐(e: Expr): Bind = Bind(id, e, Some(ty))
  }
  case class Bind(id: String, rhs: Expr, ty: Option[QType])

  extension (id: String)
    def ∶(t: QType): BindTy = BindTy(id, t)
    def ⇐(e: Expr): Bind = Bind(id, e, None)

  def λ(f: String, x: String)(ft: TFun)(e: => Expr): ELam = ELam(f, x, ft.t1, e, Some(ft.t2))
  def λ(xt: BindTy)(e: => Expr): ELam = ELam("_", xt.id, xt.ty, e, None)
  def ite(c: Expr)(thn: Expr)(els: Expr): Expr = ECond(c, thn, els)
  def let(xv: Bind)(e: Expr): Expr = ELet(xv.id, xv.ty, xv.rhs, e)

  extension (e: Expr)
    def apply(a: Expr): Expr = EApp(e, a)
    def apply(n: Int): Expr = EApp(e, ENum(n))
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
