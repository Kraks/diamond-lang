package diamond.fsub

import diamond._
import Type._
import TypeSyntax._
import Expr._
import ExprSyntax._

// Algorithmic typing/subtyping following
// http://lampwww.epfl.ch/teaching/archive/type_systems/2004/slides/10handout.pdf

case class TypeVarNotFound(x: TVar)
  extends RuntimeException(s"$x")
case class TypeMismatch(e: Expr, actual: Type, expect: Type)
  extends RuntimeException(s"expr:\n  $e\n  expect type: $expect\n  actual type: $actual")
case class NotSubtype(t1: Type, t2: Type)
  extends RuntimeException(s"$t1 not subtype of $t2")

object TEnv:
  def empty: TEnv = TEnv(Map(), Map())

case class TEnv(m: Map[String, Type], tm: Map[TVar, Type]):
  def apply(x: String): Type = m(x)
  def apply(t: TVar): Type =
    if (tm.contains(t)) tm(t) else throw TypeVarNotFound(t)
  def +(xt: (String, Type)): TEnv = TEnv(m + xt, tm)
  def +(tb: TypeBound): TEnv = TEnv(m, tm + (TVar(tb.id) -> tb.bound))
  def contains(v: TVar): Boolean = tm.contains(v)

def typeCheckBinOp(e1: Expr, e2: Expr, op: String, t1: Type, t2: Type)(using Γ: TEnv): Type =
  op match
    case "+" | "-" | "*" | "/" =>
      checkTypeEq(e1, t1, TNum)
      checkTypeEq(e2, t2, TNum)
      TNum
    case "=" =>
      checkTypeEq(e1, t1, TNum)
      checkTypeEq(e2, t2, TNum)
      TBool

def typeEq(t1: Type, t2: Type)(using Γ: TEnv): Boolean =
  isSubtype(t1, t2) && isSubtype(t2, t1)

def checkTypeEq(e: Expr, actual: Type, exp: Type)(using Γ: TEnv): Type =
  if (typeEq(actual, exp)) actual
  else throw TypeMismatch(e, actual, exp)

def isFreeIn(x: String, t: Type): Boolean = t match {
  case TTop | TUnit | TNum | TBool => false
  case TVar(y) => x == y
  case TFun(t1, t2) => isFreeIn(x, t1) || isFreeIn(x, t2)
  case TForall(y, ub, body) =>
    if (x == y) false
    else isFreeIn(x, ub) && isFreeIn(x, body)
  case TRef(t) => isFreeIn(x, t)
}

def typeSubst(t: Type, from: String, to: Type): Type = t match {
  case TTop | TUnit | TNum | TBool => t
  case TVar(x) =>
    if (x == from) to else t
  case TFun(t1, t2) =>
    TFun(typeSubst(t1, from, to), typeSubst(t2, from, to))
  case TForall(x, ub, body) =>
    if (x == from) t
    else if (isFreeIn(x, to)) {
      val fresh = Counter.fresh
      val newUb = typeSubst(ub, x, TVar(fresh))
      val newBody = typeSubst(body, x, TVar(fresh))
      typeSubst(TForall(fresh, newUb, newBody), from, to)
    } else TForall(x, typeSubst(ub, from, to), typeSubst(body, from, to))
  case TRef(t) => TRef(typeSubst(t, from, to))
}

def isSubtype(t1: Type, t2: Type)(using Γ: TEnv): Boolean = (t1, t2) match {
  case (_, TTop) => true
  case (TUnit, TUnit) => true
  case (TNum, TNum) => true
  case (TBool, TBool) => true
  case (TVar(x), TVar(y)) if x == y => true
  case (x@TVar(_), t) => isSubtype(Γ(x), t)
  case (TFun(t1, t2), TFun(t3, t4)) =>
    isSubtype(t3, t1) && isSubtype(t2, t4)
  case (TForall(x, b1, t1), TForall(y, b2, t2)) =>
    if (x == y) {
      // Note: this is the "kernel" version
      checkSubtype(b1, b2)
      checkSubtype(b2, b1)
      isSubtype(t1, t2)(using Γ + (x <∶ b1))
    } else {
      val z = Counter.fresh
      isSubtype(TForall(z, b1, typeSubst(t1, x, TVar(z))),
        TForall(z, b2, typeSubst(t2, y, TVar(z))))
    }
  case (TRef(t1), TRef(t2)) => typeEq(t1, t2)
  case _ => false
}

def checkSubtype(t1: Type, t2: Type)(using Γ: TEnv): Unit =
  if (isSubtype(t1, t2)) ()
  else throw NotSubtype(t1, t2)

def typeExposure(t: Type)(using Γ: TEnv): Type = t match {
  case x@TVar(_) => typeExposure(Γ(x))
  case _ => t
}

def typeWFCheck(t: Type)(using Γ: TEnv): Type = t match {
  case TTop | TUnit | TNum | TBool => t
  case t@TVar(x) =>
    if (Γ.contains(t)) t
    else throw TypeVarNotFound(t)
  case TFun(t1, t2) =>
    typeWFCheck(t1)
    typeWFCheck(t2)
    t
  case TForall(x, b, t) =>
    typeWFCheck(b)
    typeWFCheck(t)(using Γ + (x <∶ b))
  case TRef(t) =>
    typeWFCheck(t)
    t
}

def typeCheck(e: Expr)(using Γ: TEnv): Type = e match {
  case EUnit => TUnit
  case ENum(_) => TNum
  case EBool(_) => TBool
  case EVar(x) => Γ(x)
  case EBinOp(op, e1, e2) =>
    typeCheckBinOp(e1, e2, op, typeCheck(e1), typeCheck(e2))
  case ELam(f, x, at, e, Some(rt)) =>
    // recursive function requires full annotation
    val ft = TFun(at, rt)
    typeWFCheck(ft)
    val t = typeCheck(e)(using Γ + (x -> at) + (f -> ft))
    checkTypeEq(e, t, rt)
    ft
  case ELam(_, x, at, e, None) =>
    typeWFCheck(at)
    val rt = typeCheck(e)(using Γ + (x -> at))
    TFun(at, rt)
  case EApp(e1, e2) =>
    val t1 = typeCheck(e1)
    val t2 = typeCheck(e2)
    val TFun(t3, t4) = typeExposure(t1)
    checkSubtype(t2, t3)
    t4
  case ELet(x, Some(t), rhs, body) =>
    val t1 = typeCheck(rhs)
    typeWFCheck(t)
    checkSubtype(t1, t)
    typeCheck(body)(using Γ + (x -> t1))
  case ELet(x, None, rhs, body) =>
    typeCheck(body)(using Γ + (x -> typeCheck(rhs)))
  case ETyLam(tx, ub, e) =>
    TForall(tx, ub, typeCheck(e)(using Γ + (tx <∶ ub)))
  case ETyApp(e, t) =>
    val t1 = typeCheck(e)
    val TForall(x, b, body) = typeExposure(t1)
    checkSubtype(t, b)
    typeSubst(body, x, t)
  case EAlloc(e) =>
    TRef(typeCheck(e))
  case EAssign(e1, e2) =>
    val t1 = typeCheck(e1)
    val TRef(t) = typeExposure(t1)
    val t2 = typeCheck(e2)
    checkTypeEq(e2, t2, t)
    TUnit
  case EDeref(e) =>
    val TRef(t) = typeExposure(typeCheck(e))
    t
  case ECond(cnd, thn, els) =>
    // XXX: instead of requiring the same type, could compute their join
    val t1 = typeCheck(cnd)
    checkTypeEq(cnd, t1, TBool)
    val t2 = typeCheck(thn)
    val t3 = typeCheck(els)
    checkTypeEq(thn, t2, t3)
}

def topTypeCheck(e: Expr): Type = {
  Counter.reset
  typeCheck(e)(using TEnv.empty)
}
