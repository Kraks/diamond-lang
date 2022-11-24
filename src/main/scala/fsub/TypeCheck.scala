package diamond.fsub

import diamond._
import Type._
import TypeSyntax._
import Expr._
import ExprSyntax._

case class TypeVarNotFound(x: TVar) extends RuntimeException
case class TypeMismatch(e: Expr, actual: Type, expect: Type) extends RuntimeException
case class NotSubtype(t1: Type, t2: Type) extends RuntimeException

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
      typeCheckEq(e1, t1, TNum)
      typeCheckEq(e2, t2, TNum)
      TNum
    case "=" =>
      typeCheckEq(e1, t1, TNum)
      typeCheckEq(e2, t2, TNum)
      TBool

def typeEq(t1: Type, t2: Type)(using Γ: TEnv): Boolean =
  isSubtype(t1, t2) && isSubtype(t2, t1)

def typeCheckEq(e: Expr, actual: Type, exp: Type)(using Γ: TEnv): Type =
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
  case (t1, y@TVar(_)) => isSubtype(t1, Γ(y))
  case (x@TVar(_), t2) => isSubtype(Γ(x), t2)
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
  case (TRef(t1), TRef(t2)) =>
    isSubtype(t1, t2) && isSubtype(t2, t1)
  case _ => false
}

def checkSubtype(t1: Type, t2: Type)(using Γ: TEnv): Unit =
  if (isSubtype(t1, t2)) ()
  else throw NotSubtype(t1, t2)

def typeCheck(e: Expr)(using Γ: TEnv): Type = e match {
  case EUnit => TUnit
  case ENum(_) => TNum
  case EBool(_) => TBool
  case EVar(x) => Γ(x)
  case EBinOp(op, e1, e2) =>
    typeCheckBinOp(e1, e2, op, typeCheck(e1), typeCheck(e2))
  case ELam(f, x, at, e, Some(rt)) => ???
  case ELam(_, x, at, e, None) => ???
  case EApp(e1, e2) => ???
  case ELet(x, xt, rhs, body) => ???
  case ETyLam(tx, ub, e) => ???
  case ETyApp(e, t) => ???
  case EAlloc(e) => ???
  case EAssign(e1, e2) => ???
  case EDeref(e) => ???
  case ECond(cnd, thn, els) => ???
}

def topTypeCheck(e: Expr): Type = {
  Counter.reset
  typeCheck(e)(using TEnv.empty)
}
