package diamond

// TODO: add F-sub style subtyping
// TODO: finish QType

enum Type:
  case TNum
  case TBool
  case TVar(x: String)
  case TFun(t1: Type, t2: Type)
  case TForall(x: String, t: Type)

case class QType(t: Type, q: Set[String])

enum Expr:
  case ENum(n: Int)
  case EBool(b: Boolean)
  case EVar(x: String)
  case EBinOp(op: String, e1: Expr, e2: Expr)
  case ELam(x: String, t: Type, body: Expr)
  case EApp(e1: Expr, e2: Expr)
  case ELetrec(x: String, t: Type, rhs: Expr, body: Expr)
  case ETyLam(tx: String, body: Expr)
  case ETyApp(e: Expr, t: Type)

case class Env(m: Map[String, Value])

enum Value:
  case VNum(x: Int)
  case VBool(b: Boolean)
  case VFun(x: String, e: Expr, env: Env)
  case VPolyFun(e: Expr, env: Env)

import Type._
import Expr._
import Value._

case class TypeVarNotFound(x: TVar) extends RuntimeException

case class TEnv(m: Map[String, Type], tm: Set[TVar]):
  def apply(x: String): Type = m(x)
  def apply(t: TVar): Type =
    if (tm(t)) t else throw TypeVarNotFound(t)

def typeCheckBinOp(op: String, t1: Type, t2: Type): Type = ???

def typeCheck(e: Expr, tenv: TEnv): Type = e match
  case ENum(_) => TNum
  case EBool(_) => TBool
  case EVar(x) => tenv(x)
  case EBinOp(op, e1, e2) =>
    typeCheckBinOp(op, typeCheck(e1, tenv), typeCheck(e2, tenv))
  case ELam(x, t, e) => ???
  case EApp(e1, e2) => ???
  case ELetrec(x, t, rhs, body) => ???
  case ETyLam(tx, e) => ???
  case ETyApp(e, t) => ???
