package diamond.f

import Type._
import Expr._

enum Value:
  case VUnit
  case VNum(x: Int)
  case VBool(b: Boolean)
  case VLoc(n: Int)
  case VFun(f: String, x: String, e: Expr, env: Env)
  case VPolyFun(e: Expr, env: Env)

import Value._

object Env:
  def empty: Env = Env(Map())

case class Env(m: Map[String, VLoc]):
  def apply(x: String): VLoc = m(x)
  def +(xl: (String, VLoc)): Env = Env(m + xl)

object Store:
  def empty: Store = Store(Map())

case class Store(m: Map[VLoc, Value]):
  def apply(ℓ: VLoc): Value = m(ℓ)
  def +(lv: (VLoc, Value)) = Store(m + lv)
  def alloc: (VLoc, Store) =
    val ℓ: VLoc = VLoc(m.size)
    (ℓ, Store(m + (ℓ -> null)))

def evalBinOp(op: String, v1: Value, v2: Value): Value =
  op match {
    case "+" => VNum(v1.asInstanceOf[VNum].x + v2.asInstanceOf[VNum].x)
    case "-" => VNum(v1.asInstanceOf[VNum].x - v2.asInstanceOf[VNum].x)
    case "*" => VNum(v1.asInstanceOf[VNum].x * v2.asInstanceOf[VNum].x)
    case "/" => VNum(v1.asInstanceOf[VNum].x / v2.asInstanceOf[VNum].x)
    case "=" => VBool(v1.asInstanceOf[VNum].x == v2.asInstanceOf[VNum].x)
  }

def eval(e: Expr, ρ: Env, σ: Store): (Value, Store) = e match {
  case EUnit => (VUnit, σ)
  case ENum(n) => (VNum(n), σ)
  case EBool(b) => (VBool(b), σ)
  case EVar(x) => (σ(ρ(x)), σ)
  case EBinOp(op, e1, e2) =>
    val (v1, σ1) = eval(e1, ρ, σ)
    val (v2, σ2) = eval(e2, ρ, σ1)
    (evalBinOp(op, v1, v2), σ2)
  case ELam(f, x, _, e, _) =>
    val (ℓ, σ1) = σ.alloc
    val ρ1 = ρ + (f -> ℓ)
    val fv = VFun(f, x, e, ρ1)
    (fv, σ1 + (ℓ -> fv))
  case EApp(e1, e2) =>
    val (VFun(f, x, body, ρ1), σ1) = eval(e1, ρ, σ)
    val (v, σ2) = eval(e2, ρ, σ1)
    val (ℓ, σ3) = σ2.alloc
    eval(body, ρ1 + (x -> ℓ), σ3 + (ℓ -> v))
  case ELet(x, _, rhs, body) =>
    val (v, σ1) = eval(rhs, ρ, σ)
    val (ℓ, σ2) = σ1.alloc
    eval(body, ρ + (x -> ℓ), σ2 + (ℓ -> v))
  case ETyLam(tx, body) =>
    (VPolyFun(body, ρ), σ)
  case ETyApp(e, t) =>
    val (VPolyFun(body, ρ1), σ1) = eval(e, ρ, σ)
    eval(body, ρ1, σ1)
  case EAlloc(e) =>
    val (v, σ1) = eval(e, ρ, σ)
    val (ℓ, σ2) = σ1.alloc
    (ℓ, σ2 + (ℓ -> v))
  case EAssign(e1, e2) =>
    val (ℓ@VLoc(_), σ1) = eval(e1, ρ, σ)
    val (v, σ2) = eval(e2, ρ, σ1)
    (VUnit, σ2 + (ℓ -> v))
  case EDeref(e: Expr) =>
    val (ℓ@VLoc(_), σ1) = eval(e, ρ, σ)
    (σ1(ℓ), σ1)
  case ECond(cnd, thn, els) =>
    val (VBool(b), σ1) = eval(cnd, ρ, σ)
    if (b) eval(thn, ρ, σ1) else eval(els, ρ, σ1)
}

def topEval(e: Expr): (Value, Store) =
  eval(e, Env(Map()), Store(Map()))
