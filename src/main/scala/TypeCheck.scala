package diamond

import Type._
import Expr._

case class TypeVarNotFound(x: TVar) extends RuntimeException
case class TypeMismatch(e: Expr, actual: Type, expect: Type) extends RuntimeException

case class TEnv(m: Map[String, Type], tm: Set[TVar]):
  def apply(x: String): Type = m(x)
  def apply(t: TVar): Type =
    if (tm(t)) t else throw TypeVarNotFound(t)
  def +(xt: (String, Type)): TEnv = TEnv(m + xt, tm)
  def +(t: String): TEnv = TEnv(m, tm + TVar(t))

def typeCheckBinOp(e1: Expr, e2: Expr, op: String, t1: Type, t2: Type): Type =
  op match
    case "+" | "-" | "*" | "/" =>
      typeCheckEq(e1, t1, TNum)
      typeCheckEq(e2, t2, TNum)
      TNum
    case "=" =>
      typeCheckEq(e1, t1, TNum)
      typeCheckEq(e2, t2, TNum)
      TBool

def typeEq(t1: Type, t2: Type): Boolean = ???

def typeCheckEq(e: Expr, actual: Type, exp: Type): Type =
  if (typeEq(actual, exp)) actual
  else throw TypeMismatch(e, actual, exp)

def typeWFCheck(t: Type, Γ: TEnv): Type = ???

def typeCheck(e: Expr, Γ: TEnv): Type = e match {
  case EUnit => TUnit
  case ENum(_) => TNum
  case EBool(_) => TBool
  case EVar(x) => Γ(x)
  case EBinOp(op, e1, e2) =>
    typeCheckBinOp(e1, e2, op, typeCheck(e1, Γ), typeCheck(e2, Γ))
  case ELam(f, x, e, ft@TFun(at, rt)) =>
    typeWFCheck(ft, Γ)
    val t = typeCheck(e, Γ + (x -> at) + (f -> ft))
    typeCheckEq(e, t, rt)
    ft
  case EApp(e1, e2) =>
    val TFun(at, rt) = typeCheck(e1, Γ)
    val t2 = typeCheck(e2, Γ)
    typeCheckEq(e2, t2, at)
    rt
  case ELet(x, xt, rhs, body) =>
    val t1 = typeCheck(rhs, Γ)
    val t1c = xt match
      case Some(t) =>
        typeWFCheck(t, Γ)
        typeCheckEq(rhs, t1, t)
      case None => t1
    typeCheck(body, Γ + (x -> t1c))
  case ETyLam(tx, e) =>
    TForall(tx, typeCheck(e, Γ + tx))
  case ETyApp(e, t) =>
    typeWFCheck(t, Γ)
    val TForall(x, t0) = typeCheck(e, Γ)
    // TODO: need type substitution
    ???
  case EAlloc(e) =>
    TRef(typeCheck(e, Γ))
  case EAssign(e1, e2) =>
    val TRef(t) = typeCheck(e1, Γ)
    val t2 = typeCheck(e2, Γ)
    typeCheckEq(e2, t2, t)
    TUnit
  case EDeref(e) =>
    val TRef(t) = typeCheck(e, Γ)
    t
  case ECond(cnd, thn, els) =>
    val t1 = typeCheck(cnd, Γ)
    typeCheckEq(cnd, t1, TBool)
    val t2 = typeCheck(thn, Γ)
    val t3 = typeCheck(els, Γ)
    typeCheckEq(thn, t2, t3)
}
