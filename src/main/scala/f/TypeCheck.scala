package diamond.f

import diamond._
import Type._
import Expr._

case class TypeVarNotFound(x: TVar) extends RuntimeException
case class TypeMismatch(e: Expr, actual: Type, expect: Type)
  extends RuntimeException(s"expr:\n  $e\n  expect type: $expect\n  actual type: $actual")

object TEnv:
  def empty: TEnv = TEnv(Map(), Set())

case class TEnv(m: Map[String, Type], tm: Set[TVar]):
  def apply(x: String): Type = m(x)
  def apply(t: TVar): Type =
    if (tm(t)) t else throw TypeVarNotFound(t)
  def +(xt: (String, Type)): TEnv = TEnv(m + xt, tm)
  def +(t: String): TEnv = TEnv(m, tm + TVar(t))
  def contains(v: TVar): Boolean = tm.contains(v)

def typeCheckBinOp(e1: Expr, e2: Expr, op: String, t1: Type, t2: Type): Type =
  op match
    case "+" | "-" | "*" | "/" =>
      checkTypeEq(e1, t1, TNum)
      checkTypeEq(e2, t2, TNum)
      TNum
    case "=" =>
      checkTypeEq(e1, t1, TNum)
      checkTypeEq(e2, t2, TNum)
      TBool

def typeEq(t1: Type, t2: Type): Boolean = (t1, t2) match {
  case (TUnit, TUnit) => true
  case (TNum, TNum) => true
  case (TBool, TBool) => true
  case (TVar(x), TVar(y)) if x == y => true
  case (TFun(t1, t2), TFun(t3, t4)) if
    typeEq(t1, t3) && typeEq(t2, t4) => true
  case (TForall(x, t1), TForall(y, t2)) =>
    if (x == y) typeEq(t1, t2)
    else {
      val z = Counter.fresh
      typeEq(TForall(z, typeSubst(t1, x, TVar(z))), TForall(z, typeSubst(t2, y, TVar(z))))
    }
  case (TRef(t1), TRef(t2)) =>
    typeEq(t1, t2)
  case _ => false
}

def isFreeIn(x: String, t: Type): Boolean = t match {
  case TUnit | TNum | TBool => false
  case TVar(y) => x == y
  case TFun(t1, t2) => isFreeIn(x, t1) || isFreeIn(x, t2)
  case TForall(y, body) =>
    if (x == y) false
    else isFreeIn(x, body)
  case TRef(t) => isFreeIn(x, t)
}

def typeSubst(t: Type, from: String, to: Type): Type = t match {
  case TUnit | TNum | TBool => t
  case TVar(x) =>
    if (x == from) to else t
  case TFun(t1, t2) =>
    TFun(typeSubst(t1, from, to), typeSubst(t2, from, to))
  case TForall(x, body) =>
    if (x == from) t
    else if (isFreeIn(x, to)) {
      val fresh = Counter.fresh
      val newBody = typeSubst(body, x, TVar(fresh))
      typeSubst(TForall(fresh, newBody), from, to)
    } else TForall(x, typeSubst(body, from, to))
  case TRef(t) => TRef(typeSubst(t, from, to))
}

def typeApp(t: Type, a: Type): Type =
  val TForall(x, body) = t
  typeSubst(body, x, a)

def checkTypeEq(e: Expr, actual: Type, exp: Type): Type =
  if (typeEq(actual, exp)) actual
  else throw TypeMismatch(e, actual, exp)

def typeWFCheck(t: Type, Γ: TEnv): Type = t match {
  case TUnit | TNum | TBool => t
  case t@TVar(x) =>
    if (Γ.contains(t)) t
    else throw TypeVarNotFound(t)
  case TFun(t1, t2) =>
    typeWFCheck(t1, Γ)
    typeWFCheck(t2, Γ)
    t
  case TForall(x, t) =>
    typeWFCheck(t, Γ + x)
  case TRef(t) =>
    typeWFCheck(t, Γ)
    t
}

def typeCheck(e: Expr, Γ: TEnv): Type = e match {
  case EUnit => TUnit
  case ENum(_) => TNum
  case EBool(_) => TBool
  case EVar(x) => Γ(x)
  case EBinOp(op, e1, e2) =>
    typeCheckBinOp(e1, e2, op, typeCheck(e1, Γ), typeCheck(e2, Γ))
  case ELam(f, x, at, e, Some(rt)) =>
    // recursive function requires full annotation
    val ft = TFun(at, rt)
    typeWFCheck(ft, Γ)
    val t = typeCheck(e, Γ + (x -> at) + (f -> ft))
    checkTypeEq(e, t, rt)
    ft
  case ELam(_, x, at, e, None) =>
    typeWFCheck(at, Γ)
    val rt = typeCheck(e, Γ + (x -> at))
    TFun(at, rt)
  case EApp(e1, e2) =>
    val TFun(at, rt) = typeCheck(e1, Γ)
    val t2 = typeCheck(e2, Γ)
    checkTypeEq(e2, t2, at)
    rt
  case ELet(x, Some(t), rhs, body) =>
    val t1 = typeCheck(rhs, Γ)
    typeWFCheck(t, Γ)
    checkTypeEq(rhs, t1, t)
    typeCheck(body, Γ + (x -> t1))
  case ELet(x, None, rhs, body) =>
    typeCheck(body, Γ + (x -> typeCheck(rhs, Γ)))
  case ETyLam(tx, e) =>
    TForall(tx, typeCheck(e, Γ + tx))
  case ETyApp(e, t) =>
    typeWFCheck(t, Γ)
    val TForall(x, t0) = typeCheck(e, Γ)
    typeSubst(t0, x, t)
  case EAlloc(e) =>
    TRef(typeCheck(e, Γ))
  case EAssign(e1, e2) =>
    val TRef(t) = typeCheck(e1, Γ)
    val t2 = typeCheck(e2, Γ)
    checkTypeEq(e2, t2, t)
    TUnit
  case EDeref(e) =>
    val TRef(t) = typeCheck(e, Γ)
    t
  case ECond(cnd, thn, els) =>
    val t1 = typeCheck(cnd, Γ)
    checkTypeEq(cnd, t1, TBool)
    val t2 = typeCheck(thn, Γ)
    val t3 = typeCheck(els, Γ)
    checkTypeEq(thn, t2, t3)
}

def topTypeCheck(e: Expr): Type = {
  Counter.reset
  typeCheck(e, TEnv.empty)
}

