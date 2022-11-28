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
  def filter(q: Qual): TEnv = TEnv(m.filter((k, v) => q.contains(k)))

extension (q: Qual)
  def contains(x: QElem): Boolean = q.q.contains(x)
  def size: Int = q.q.size
  def isUntrack: Boolean = q.q.isEmpty
  def isFresh: Boolean = q.q.contains(Fresh())
  def nonFresh: Boolean = !q.q.contains(Fresh())
  def -(x: QElem): Qual = Qual(q.q - x)
  def +(x: QElem): Qual = Qual(q.q + x)
  def \(q2: Qual): Qual = Qual(q.q -- q2.q)
  def ∪(q2: Qual): Qual = Qual(q.q ++ q2.q)
  def ⊆(q2: Qual): Boolean = q.q.subsetOf(q2.q)
  def ⊆(Γ: TEnv): Boolean = {
    val Qual(s) = q
    val dom: Set[QElem] = Γ.m.keys.toSet
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

// One-step qualifier exposure (i.e. implementing q-self and q-var)
def qualElemExposure(q: Qual, x: String)(using Γ: TEnv): Qual = {
  require(!q.contains(x))
  Γ(x) match
    case QType(TFun(_, _, _, _), r) if r.nonFresh && r ⊆ q => (q \ r) + x
    case QType(t, r) if !t.isInstanceOf[TFun] && r.nonFresh => q ∪ r
    case _ => q + x
}

// One iteration of exposure over the qualifier
def qualExposure0(q: Qual)(using Γ: TEnv): Qual = {
  require(!q.isFresh)
  if (q.isUntrack) q
  else {
    val x = q.q.head.asInstanceOf[String]
    val p = q - x
    val r = qualElemExposure(p, x)
    if (r.contains(x)) qualExposure0(r - x) + x
    else qualExposure0(r)
  }
}

// Iteratively qualifier exposure via fixpoint iteration.
// Compute the maximum upper bound qualifier following the subtyping "lattice" under Γ.
// XXX: is that upper bound unique? eg different traverse order may lead to different result?
def fixQualExposure(q1: Qual, q2: Qual)(using Γ: TEnv): Qual = {
  println(s"fixpoint $q1 $q2")
  if (q1 == q2) q2
  else fixQualExposure(q2, qualExposure0(q2))
}

def qualExposure(q: Qual)(using Γ: TEnv): Qual =
  val p = fixQualExposure(Qual(Set()), q - ◆)
  if (q.isFresh) p + ◆ else p

def isSubqual(q1: Qual, q2: Qual)(using Γ: TEnv): Boolean =
  if (q1 ⊆ q2 && q2 ⊆ Γ) true // Q-Sub
  else isSubqual(qualExposure(q1), qualExposure(q2))

def qtypeRename(tq: QType, from: String, to: String): QType = {
  val QType(t, q) = tq
  val newQual = if (q.contains(from)) q - from + to else q
  QType(typeRename(t, from, to), newQual)
}

// Rename free occurrence of `from` in `t` to `to`
def typeRename(t: Type, from: String, to: String): Type = t match {
  case TUnit | TNum | TBool => t
  case TFun(Some(f), Some(x), t1, t2) =>
    if (f == from || x == from) t
    else if (f == to) {
      val g = Counter.fresh
      val argType = qtypeRename(t1, f, g)
      val retType = qtypeRename(t2, f, g)
      typeRename(TFun(Some(g), Some(x), argType, retType), from, to)
    } else if (x == to) {
      val y = Counter.fresh
      val argType = qtypeRename(t1, x, y)
      val retType = qtypeRename(t2, x, y)
      typeRename(TFun(Some(f), Some(y), argType, retType), from, to)
    } else TFun(Some(f), Some(x), qtypeRename(t1, from, to), qtypeRename(t2, from, to))
  case TFun(Some(f), None, t1, t2) =>
    if (f == from) t
    else if (f == to) {
      val g = Counter.fresh
      val argType = qtypeRename(t1, f, g)
      val retType = qtypeRename(t2, f, g)
      typeRename(TFun(Some(g), None, argType, retType), from, to)
    } else TFun(Some(f), None, qtypeRename(t1, from, to), qtypeRename(t2, from, to))
  case TFun(None, Some(x), t1, t2) =>
    if (x == from) t
    else if (x == to) {
      val y = Counter.fresh
      val argType = qtypeRename(t1, x, y)
      val retType = qtypeRename(t2, x, y)
      typeRename(TFun(None, Some(y), argType, retType), from, to)
    } else TFun(None, Some(x), qtypeRename(t1, from, to), qtypeRename(t2, from, to))
  case TFun(None, None, t1, t2) =>
    TFun(None, None, qtypeRename(t1, from, to), qtypeRename(t2, from, to))
  case TRef(t) =>
    TRef(typeRename(t, from, to))
}

def isSubtype(t1: Type, t2: Type)(using Γ: TEnv): Boolean = (t1, t2) match {
  case (TUnit, TUnit) => true
  case (TNum, TNum) => true
  case (TBool, TBool) => true
  case (tf@TFun(Some(f), Some(x), t1, t2), tg@TFun(Some(g), Some(y), t3, t4)) =>
    if (f == g && x == y) {
      val Γ1 = Γ + (f -> (tf ^ ◆)) + (x -> t3)
      isSubQType(t3, t1) && isSubQType(t2, t4)(using Γ1)
    } else if (f != g && x == y) {
      val f1 = Counter.fresh
      isSubtype(TFun(Some(f1), Some(x), qtypeRename(t1, f, f1), qtypeRename(t2, f, f1)),
        TFun(Some(f1), Some(x), qtypeRename(t3, g, f1), qtypeRename(t4, g, f1)))
    } else if (f == g && x != y) {
      val x1 = Counter.fresh
      isSubtype(TFun(Some(f), Some(x1), qtypeRename(t1, x, x1), qtypeRename(t2, x, x1)),
        TFun(Some(f), Some(x1), qtypeRename(t3, y, x1), qtypeRename(t4, y, x1)))
    } else {
      val f1 = Counter.fresh
      val x1 = Counter.fresh
      val tf1 = TFun(Some(f1), Some(x1),
        qtypeRename(qtypeRename(t1, x, x1), f, f1),
        qtypeRename(qtypeRename(t2, x, x1), f, f1))
      val tg1 = TFun(Some(f1), Some(x1),
        qtypeRename(qtypeRename(t3, y, x1), g, f1),
        qtypeRename(qtypeRename(t4, y, x1), g, f1))
      isSubtype(tf1, tg1)
    }
  case (tf@TFun(Some(f), None, t1, t2), tg@TFun(Some(g), None, t3, t4)) =>
    if (f == g) {
      val Γ1 = Γ + (f -> (tf ^ ◆))
      isSubQType(t3, t1) && isSubQType(t2, t4)(using Γ1)
    } else {
      val f1 = Counter.fresh
      isSubtype(TFun(Some(f1), None, qtypeRename(t1, f, f1), qtypeRename(t2, f, f1)),
        TFun(Some(f1), None, qtypeRename(t3, g, f1), qtypeRename(t4, g, f1)))
    }
  case (TFun(None, Some(x), t1, t2), TFun(None, Some(y), t3, t4)) =>
    if (x == y) {
      val Γ1 = Γ + (x -> t3)
      isSubQType(t3, t1) && isSubQType(t2, t4)(using Γ1)
    } else {
      val x1 = Counter.fresh
      isSubtype(TFun(None, Some(x1), qtypeRename(t1, x, x1), qtypeRename(t2, x, x1)),
        TFun(None, Some(x1), qtypeRename(t3, y, x1), qtypeRename(t4, y, x1)))
    }
  case (TFun(None, None, t1, t2), TFun(None, None, t3, t4)) =>
    isSubQType(t3, t1) && isSubQType(t2, t4)
  case (TRef(t1), TRef(t2)) => typeEq(t1, t2)
  case _ => false
}

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
