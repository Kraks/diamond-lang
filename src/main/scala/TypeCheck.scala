package diamond

import Type._
import Expr._

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
  case ELam(f, x, t, e) => ???
  case EApp(e1, e2) => ???
  case ETyLam(tx, e) => ???
  case ETyApp(e, t) => ???
