package diamond.f

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
  val α = TVar("α")
  val β = TVar("β")
  val γ = TVar("γ")
  val τ = TVar("τ")
  def ∀(x: String)(t: Type) = TForall(x, t)
  extension (t: Type)
    def ->(s: Type): TFun = TFun(t, s)

object ExprSyntax:
  import Expr._
  import Type._

  val x = EVar("x")
  val y = EVar("y")
  val z = EVar("z")
  val n = EVar("n")
  val f = EVar("f")
  val g = EVar("g")

  case class BindTy(id: String, ty: Type) {
    def ⇐(e: Expr): Bind = Bind(id, e, Some(ty))
  }
  case class Bind(id: String, rhs: Expr, ty: Option[Type])

  extension (id: String)
    def ∶(t: Type): BindTy = BindTy(id, t)
    def ⇐(e: Expr): Bind = Bind(id, e, None)

  def λ(f: String, x: String)(ft: TFun)(e: => Expr): ELam = ELam(f, x, e, ft)
  def Λ(x: String)(e: => Expr): ETyLam = ETyLam(x, e)
  def ite(c: Expr)(thn: Expr)(els: Expr): Expr = ECond(c, thn, els)
  def let(xv: Bind)(e: Expr): Expr = ELet(xv.id, xv.ty, xv.rhs, e)

  extension (e: Expr)
    def apply(a: Expr): Expr = EApp(e, a)
    def apply(t: Type): Expr = ETyApp(e, t)
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
