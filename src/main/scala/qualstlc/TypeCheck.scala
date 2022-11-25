package diamond.qualstlc

import diamond._

import Type._
import Expr._

import TypeSyntax._
import TypeSyntax.given_Conversion_Type_QType
import ExprSyntax._

case class QualMismatch(actual: Qual, expect: Qual)
  extends RuntimeException(s"expect qualifier: $expect\n  actual qualifier: $actual")
case class TypeMismatch(e: Expr, actual: Type, expect: Type)
  extends RuntimeException(s"expr:\n  $e\n  expect type: $expect\n  actual type: $actual")
case class QualTypeMismatch(e: Expr, actual: QType, expect: QType)
  extends RuntimeException(s"expr:\n  $e\n  expect type: $expect\n  actual type: $actual")
case class NotSubtype(t1: QType, t2: QType)
  extends RuntimeException(s"$t1 not subtype of $t2")

object TEnv:
  def empty: TEnv = TEnv(Map())

case class TEnv(m: Map[String, QType]):
  def apply(x: String): QType = m(x)
  def +(xt: (String, QType)): TEnv = TEnv(m + xt)
  def ⊇(q: Qual): Boolean = {
    val Qual(s) = q
    val dom: Set[QElem] = m.keys.toSet
    s.subsetOf(dom + ◆)
  }

def typeCheckBinOp(e1: Expr, e2: Expr, op: String, t1: QType, t2: QType)(using Γ: TEnv): Type =
  op match
    case "+" | "-" | "*" | "/" =>
      checkQTypeEq(e1, t1, TNum)
      checkQTypeEq(e2, t2, TNum)
      TNum
    case "=" =>
      checkQTypeEq(e1, t1, TNum)
      checkQTypeEq(e2, t2, TNum)
      TBool

def qualEq(q1: Qual, q2: Qual)(using Γ: TEnv): Boolean = ???
def typeEq(t1: Type, t2: Type)(using Γ: TEnv): Boolean = ???
def qualTypeEq(t1: QType, t2: QType)(using Γ: TEnv): Boolean = ???

def checkQualEq(q1: Qual, q2: Qual)(using Γ: TEnv): Qual =
  if (qualEq(q1, q2)) q1
  else throw QualMismatch(q1, q2)

def checkTypeEq(e: Expr, actual: Type, exp: Type)(using Γ: TEnv): Type =
  if (typeEq(actual, exp)) actual
  else throw TypeMismatch(e, actual, exp)

def checkQTypeEq(e: Expr, actual: QType, exp: QType)(using Γ: TEnv): QType =
  if (qualTypeEq(actual, exp)) actual
  else throw QualTypeMismatch(e, actual, exp)

def checkUntrackQual(q: Qual): Unit =
  assert(q.isUntrack, "Not support nested reference")

def qualVarExposure0(x: String)(using Γ: TEnv): Qual = {
  // XXX: should use a worklist
  Γ(x) match {
    case QType(TFun(_, _, _, _), r) if r.nonFresh => ???
    case QType(_, r) if r.nonFresh => ???
  }
}

def qualExposure(q: Qual)(using Γ: TEnv): Qual = {
  // compute the maximum upper bound qualifier under Γ,
  // super inefficient via a fixpoint itervation
  // XXX: is that upper bound unique?
  var p: Set[QElem] = Set()
  for (x <- q.q if !x.isInstanceOf[Fresh]) {
  }
  Qual(p)
}

def isSubqual(q1: Qual, q2: Qual)(using Γ: TEnv): Boolean = {
  if (q1 ≤ q2 && Γ ⊇ q2) true // Q-Sub
  else ???
}

def isSubtype(t1: Type, t2: Type)(using Γ: TEnv): Boolean = ???

def isSubQType(T: QType, S: QType)(using Γ: TEnv): Boolean =
  val QType(t1, q1) = T
  val QType(t2, q2) = S
  isSubtype(t1, t2) && isSubqual(q1, q2)

def typeCheck(e: Expr)(using Γ: TEnv): QType = e match {
  case EUnit => TUnit
  case ENum(_) => TNum
  case EBool(_) => TBool
  case EVar(x) =>
    val QType(t, _) = Γ(x)
    t ^ x
  case EBinOp(op, e1, e2) =>
    typeCheckBinOp(e1, e2, op, typeCheck(e1), typeCheck(e2))
  case ELam(f, x, at, e, Some(rt)) => ???
  case ELam(_, x, at, e, None) => ???
  case EApp(e1, e2) => ???
  case ELet(x, Some(t), rhs, body) => ???
  case ELet(x, None, rhs, body) => ???
  case EAlloc(e) =>
    val QType(t, q) = typeCheck(e)
    checkUntrackQual(q)
    TRef(t) ^ ◆
  case EAssign(e1, e2) =>
    val QType(TRef(t1), q1) = typeCheck(e1)
    val QType(t2, q2) = typeCheck(e2)
    checkUntrackQual(q2)
    checkTypeEq(e2, t2, t1)
    TUnit
  case EDeref(e) =>
    val QType(TRef(t), q) = typeCheck(e)
    t
  case ECond(cnd, thn, els) => ???
}

def topTypeCheck(e: Expr): QType = {
  Counter.reset
  typeCheck(e)(using TEnv.empty)
}
