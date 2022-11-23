package diamond.fsub

import diamond._
import Type._
import TypeSyntax._
import Expr._
import ExprSyntax._

case class TypeVarNotFound(x: TVar) extends RuntimeException
case class TypeMismatch(e: Expr, actual: Type, expect: Type) extends RuntimeException

object TEnv:
  def empty: TEnv = TEnv(Map(), Map())

case class TEnv(m: Map[String, Type], tm: Map[TVar, Type]):
  def apply(x: String): Type = m(x)
  def apply(t: TVar): Type =
    if (tm.contains(t)) t else throw TypeVarNotFound(t)
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

def isSubtype(t1: Type, t2: Type)(using Γ: TEnv): Boolean = (t1, t2) match {
  case (_, TTop) => true
  case _ => ???
}

def typeCheck(e: Expr)(using Γ: TEnv): Type = e match {
  case EUnit => TUnit
  case ENum(_) => TNum
  case EBool(_) => TBool
  case EVar(x) => Γ(x)
  case EBinOp(op, e1, e2) =>
    typeCheckBinOp(e1, e2, op, typeCheck(e1), typeCheck(e2))
  case ELam(f, x, e, ft@TFun(at, rt)) => ???
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
