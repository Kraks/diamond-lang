package diamond.qualstlc

import diamond._

import Type._
import Expr._

import TypeSyntax._
import TypeSyntax.given_Conversion_Type_QType
import ExprSyntax._

given Conversion[Set[String], Set[QElem]] = _.asInstanceOf[Set[QElem]]

case class QualMismatch(actual: Qual, expect: Qual)
  extends RuntimeException(s"expect qualifier: $expect\n  actual qualifier: $actual")

case class TypeMismatch(e: Expr, actual: Type, expect: Type)
  extends RuntimeException(s"expr:\n  $e\n  expect type: $expect\n  actual type: $actual")

case class QualTypeMismatch(e: Expr, actual: QType, expect: QType)
  extends RuntimeException(s"expr:\n  $e\n  expect type: $expect\n  actual type: $actual")

case class NotSubtype(t1: Type, t2: Type)(Γ: Option[TEnv] = None)
  extends RuntimeException(s"$t1 not subtype of $t2 under $Γ")

case class NotSubQType(t1: QType, t2: QType)(Γ: Option[TEnv] = None)
  extends RuntimeException(s"$t1 not qualified subtype of $t2 under $Γ")

case class NonOverlap(permitted: Qual, overlap: Qual)
  extends RuntimeException(s"permit $permitted, but found overlap $overlap")

case class DeepDependency(t: Type, vbl: String)
  extends RuntimeException(s"$t cannot deeply depend on $vbl")

case class IllFormedQType(t: QType, Γ: TEnv)
  extends RuntimeException(s"ill-formed qualified type $t under $Γ")

case class IllFormedQual(q: Qual)
  extends RuntimeException(s"ill-formed qualifier " + q.set)

case class RequireTypeNonFresh(t: QType)
  extends RuntimeException(s"type $t should be non-fresh")

case class RequireNonFresh(e: Expr, t: QType)
  extends RuntimeException(s"$e: $t should be non-fresh")

case class TEnv(m: AssocList[String, QType], observable: Set[String] = Set()):
  def apply(x: String): QType = m(x)

  def +(xt: (String, QType)) = xt match {
    case (x, t: QType) => TEnv(m + (x -> t), observable + x)
  }
  def filter(q: Set[String]): TEnv = {
    //println(s"shrink from ${observable} to ${observable.intersect(q)}")
    TEnv(m, observable.intersect(q))
  }
  def filter(q: Qual): TEnv = filter(q.varSet)
  def dom: Set[String] = m.dom

object TEnv:
  def empty: TEnv = TEnv(AssocList.empty)

extension (q: Qual)
  def contains(x: QElem): Boolean = q.set.contains(x)
  def varSet: Set[String] = (q.set - Fresh()).asInstanceOf[Set[String]]
  def withVarSet[T](f: Set[String] => Qual): Qual =
    val res = f(varSet)
    if (q.isFresh) res + Fresh() else res
  def size: Int = q.set.size
  def isUntrack: Boolean = q.set.isEmpty
  def isFresh: Boolean = q.set.contains(Fresh())
  def nonFresh: Boolean = !q.set.contains(Fresh())
  def -(x: QElem): Qual = Qual(q.set - x)
  def +(x: QElem): Qual = Qual(q.set + x)
  def ++(q2: Set[QElem]): Qual = Qual(q.set ++ q2)
  def \(q2: Qual): Qual = Qual(q.set -- q2.set)
  def ∪(q2: Qual): Qual = Qual(q.set ++ q2.set)
  def ⊆(q2: Qual): Boolean = q.set.subsetOf(q2.set)
  def ⊆(Γ: TEnv): Boolean = q.set.subsetOf(Γ.dom + ◆)
  def satVars(using Γ: TEnv): Set[String] = reach(q.varSet, Set())
  def satVarsQual(using Γ: TEnv): Qual = Qual(satVars)
  def sat(using Γ: TEnv): Qual =
    if (!q.isFresh) Qual(satVars) else Qual(satVars) + Fresh()
  def ⋒(q2: Qual)(using Γ: TEnv): Qual =
    Qual(q.satVars.intersect(q2.satVars)) + Fresh()
  def ⊔(q2: Qual): Qual = Qual(q.set ++ q2.set)
  def subst(from: String, to: Qual): Qual =
    if (q.contains(from)) q - from ++ to.set else q
  def rename(from: String, to: String): Qual =
    q.subst(from, Qual.singleton(to))
  def isSubqual(q2: Qual)(using Γ: TEnv): Boolean =
    val q2ext = fix(expandSelf)(q2.set)
    q.set.forall(boundedBy(_, q2ext))

extension (t: Type)
  def ⊔(t2: Type)(using Γ: TEnv): Type =
    if (t.isSubtype(t2)) t2
    else if (t2.isSubtype(t)) t
    else throw RuntimeException(s"No least upper bound between $t and $t2") 
  def substQual(from: String, to: Qual): Type = t match
    case TUnit | TNum | TBool => t
    case TFun(f, x, t1, t2) =>
      if (f == from || x == from) t
      else {
        val f1 = if (to.contains(f)) freshVar(f) else f
        val x1 = if (to.contains(x)) freshVar(x) else x
        val at = t1.rename(f, f1)
        val rt = t2.rename(x, x1).rename(f, f1)
        TFun(f1, x1, at.substQual(from, to), rt.substQual(from, to))
      }
    case TRef(t) => TRef(t.substQual(from, to))
  def substType(from: String, to: Type): Type = t match
    case TUnit | TNum | TBool => t
    case TFun(f, x, t1, t2) =>
      TFun(f, x, t1.substType(from, to), t2.substType(from, to))
    case TRef(t) => TRef(t.substType(from, to))
  // Rename free occurrence of term variable `from` in `t` to `to`
  def rename(from: String, to: String): Type = t match
    case TUnit | TNum | TBool => t
    case TFun(f, x, t1, t2) =>
      if (f == from || x == from) t
      else if (f == to) {
        val g = freshVar(f)
        val argType = t1.rename(f, g)
        val retType = t2.rename(f, g)
        TFun(g, x, argType, retType).rename(from, to)
      } else if (x == to) {
        val y = freshVar(x)
        val argType = t1 //rename(t1, x, y)
        val retType = t2.rename(x, y)
        TFun(f, y, argType, retType).rename(from, to)
      } else TFun(f, x, t1.rename(from, to), t2.rename(from, to))
    case TRef(t) =>
      TRef(t.rename(from, to))
  def isSubtype(t2: Type)(using Γ: TEnv): Boolean = (t, t2) match
    case (TUnit, TUnit) => true
    case (TNum, TNum) => true
    case (TBool, TBool) => true
    case (F@TFun(f, x, t1, t2), G@TFun(g, y, t3, t4)) =>
      if (f == g && x == y) {
        val Γ1 = Γ + (f -> (F ^ ◆)) + (x -> t3)
        t3.isSubQType(t1) && t2.isSubQType(t4)(using Γ1)
      } else if (f != g) {
        val f1 = freshVar()
        val F1 = TFun(f1, x, t1.rename(f, f1), t2.rename(f, f1))
        val G1 = TFun(f1, y, t3.rename(g, f1), t4.rename(g, f1))
        F1.isSubtype(G1)
      } else if (x != y) {
        val x1 = freshVar()
        val F1 = TFun(f, x1, t1, t2.rename(x, x1))
        val G1 = TFun(g, x1, t3, t4.rename(y, x1))
        F1.isSubtype(G1)
      } else throw new RuntimeException("Impossible")
    case (TRef(t1), TRef(t2)) => qtypeEq(t1, t2)
    case _ => false
  def freeVars: Set[String] = t match
    case TUnit | TNum | TBool => Set()
    case TFun(f, x, t1, t2) =>
      t1.freeVars ++ (t2.freeVars -- Set(x, f))
    case TRef(t) => t.freeVars

extension (tq: QType)
  def ⊔(tq2: QType)(using Γ: TEnv): QType =
    val QType(t1, q1) = tq
    val QType(t2, q2) = tq2
    QType(t1 ⊔ t2, q1 ⊔ q2)
  def substQual(from: String, to: Qual): QType =
    val QType(t, q) = tq
    QType(t.substQual(from, to), q.subst(from, to))
  def substQual(from: Option[String], to: Qual): QType = from match
    case None => tq
    case Some(from) => tq.substQual(from, to)
  def substType(tvar: String, to: Type): QType =
    QType(tq.ty.substType(tvar, to), tq.q)
  def qtypeSubst(tvar: String, qvar: String, to: QType): QType =
    val QType(toType, toQual) = to
    tq.substType(tvar, toType).substQual(qvar, toQual)
  def rename(from: String, to: String): QType =
    val QType(t, q) = tq
    QType(t.rename(from, to), q.rename(from, to))
  def isSubQType(tq2: QType)(using Γ: TEnv): Boolean =
    //println(s"$Γ ⊢ $T <: $S")
    val QType(t1, q1) = tq
    val QType(t2, q2) = tq2
    t1.isSubtype(t2) && q1.sat.isSubqual(q2.sat)
  def freeVars: Set[String] =
    val QType(t, q) = tq
    t.freeVars ++ q.varSet

extension (e: Expr)
  def freeVars: Set[String] = e match
    case EUnit | ENum(_) | EBool(_) => Set()
    case EVar(x) => Set(x)
    case EUnaryOp(op, e) => e.freeVars
    case EBinOp(op, e1, e2) => e1.freeVars ++ e2.freeVars
    case ELam(f, x, at, e, rt) => e.freeVars -- Set(f, x)
    case EApp(e1, e2, _) => e1.freeVars ++ e2.freeVars
    case ELet(x, _, rhs, body, _) => rhs.freeVars ++ (body.freeVars - x)
    case EAlloc(e) => e.freeVars
    case EUntrackedAlloc(e) => e.freeVars
    case EAssign(e1, e2) => e1.freeVars ++ e2.freeVars
    case EDeref(e) => e.freeVars
    case ECond(cnd, thn, els) => cnd.freeVars ++ thn.freeVars ++ els.freeVars

def qualEq(q1: Qual, q2: Qual)(using Γ: TEnv): Boolean = q1.isSubqual(q2) && q2.isSubqual(q1)
def typeEq(t1: Type, t2: Type)(using Γ: TEnv): Boolean = t1.isSubtype(t2) && t2.isSubtype(t1)
def qtypeEq(t1: QType, t2: QType)(using Γ: TEnv): Boolean = t1.isSubQType(t2) && t2.isSubQType(t1)

def reach(worklist: Set[String], acc: Set[String])(using Γ: TEnv): Set[String] =
  if (worklist.isEmpty) acc
  else {
    val x = worklist.head
    val QType(_, q) = Γ(x)
    val newQual = q.varSet.filter(z => !acc.contains(z))
    reach((worklist ++ newQual) -- Set(x), acc ++ newQual ++ Set(x))
  }

def expandSelf(q: Set[QElem])(using Γ: TEnv): Set[QElem] =
  q.flatMap {
    case x: String => Γ(x) match
      case QType(TFun(_, _, _, _), q) if !q.isFresh => Set(x) ++ q.set
      case _ => Set(x)
    case d => Set(d)
  }

def boundedBy(e: QElem, b: Set[QElem])(using Γ: TEnv): Boolean =
  b.contains(e) || { e match
    case x: String => Γ(x) match
      case QType(t, q) if !q.isFresh => // Q-Var
        q.set.forall(boundedBy(_, b))
      case _ => false
    case _ => false
  }

/* Checking helper functions */

def checkQualEq(q1: Qual, q2: Qual)(using Γ: TEnv): Qual =
  if (qualEq(q1.sat, q2.sat)) q1
  else throw QualMismatch(q1.sat, q2.sat)

def checkTypeEq(e: Expr, actual: Type, exp: Type)(using Γ: TEnv): Type =
  if (typeEq(actual, exp)) actual
  else throw TypeMismatch(e, actual, exp)

def checkQTypeEq(e: Expr, actual: QType, exp: QType)(using Γ: TEnv): QType =
  if (qtypeEq(actual, exp)) actual
  else throw QualTypeMismatch(e, actual, exp)

def checkUntrackQual(q: Qual)(using Γ: TEnv): Unit = checkQualEq(q, Qual(Set()))

def checkSubtype(T: Type, S: Type)(using Γ: TEnv): Unit =
  if (T.isSubtype(S)) ()
  else throw NotSubtype(T, S)(Some(Γ))

def checkSubQType(T: QType, S: QType)(using Γ: TEnv): Unit =
  if (T.isSubQType(S)) ()
  else throw NotSubQType(T, S)(Some(Γ))

def checkSubtypeOverlap(T: QType, S: QType)(using Γ: TEnv): Unit =
  val QType(t1, q1) = T
  val QType(t2, q2) = S
  if (t1.isSubtype(t2)) {
    val sq1 = q1.sat
    val sq2 = q2.sat
    if (sq1.isSubqual(sq2)) ()
    else throw NonOverlap(sq2 - Fresh(), sq1 \ sq2)
  } else throw NotSubtype(t1, t2)()

def checkDeepDep(t: Type, x: String): Unit =
  if (!t.freeVars.contains(x)) ()
  else throw DeepDependency(t, x)

def checkQTypeWF(t: QType)(using Γ: TEnv): Unit =
  if (t.freeVars.subsetOf(Γ.dom)) ()
  else throw new IllFormedQType(t, Γ)

/* Main type check functions */

def typeCheckUnaryOp(e: Expr, op: String, t: QType)(using Γ: TEnv): Type =
  op match
    case "~" =>
      checkQTypeEq(e, t, TBool)
      TBool

def typeCheckBinOp(e1: Expr, e2: Expr, op: String, t1: QType, t2: QType)(using Γ: TEnv): Type =
  op match
    case "+" | "-" | "*" | "/" =>
      checkQTypeEq(e1, t1, TNum)
      checkQTypeEq(e2, t2, TNum)
      TNum
    case "&&" | "||" =>
      checkQTypeEq(e1, t1, TBool)
      checkQTypeEq(e2, t2, TBool)
      TBool
    case "==" =>
      checkQTypeEq(e1, t1, TNum)
      checkQTypeEq(e2, t2, TNum)
      TBool

def typeCheck(e: Expr)(using Γ: TEnv): QType = e match {
  case EUnit => TUnit
  case ENum(_) => TNum
  case EBool(_) => TBool
  case EVar(x) =>
    val QType(t, _) = Γ(x)
    assert(Γ.observable.contains(x), s"$x is not observable")
    t ^ x
  case EUnaryOp(op, e) =>
    typeCheckUnaryOp(e, op, typeCheck(e))
  case EBinOp(op, e1, e2) =>
    typeCheckBinOp(e1, e2, op, typeCheck(e1), typeCheck(e2))
  case ELam(f, x, at, body, Some(rt)) =>
    // XXX allow annotating observable filter?
    val ft = TFun(f, x, at, rt)
    checkQTypeWF(ft)
    //val fv = Qual((freeVars(rt) ++ freeVars(body)) -- Set(f, x))
    val fv = Qual(body.freeVars -- Set(f, x))
    val Γ1 = (Γ + (x -> at) + (f -> (ft ^ fv))).filter(fv ++ Set(x, f))
    val t = typeCheck(body)(using Γ1)
    checkSubQType(t, rt)(using Γ1)
    ft ^ fv
  case ELam(f, x, at, body, None) =>
    checkQTypeWF(at)
    val fv = Qual(body.freeVars - x)
    val Γ1 = (Γ + (x -> at)).filter(fv ++ Set(x))
    val tq@QType(t, q) = typeCheck(body)(using Γ1)
    val ft = TFun(f, x, at, tq)
    ft ^ fv
  case EApp(e1, e2, Some(true)) => // T-App◆
    val t1@QType(TFun(f, x, atq@QType(at, aq), rtq@QType(rt, rq)), qf) = typeCheck(e1)
    val codomBound: Qual = Qual(Γ.dom) ++ Set(◆, f, x)
    if (!(rq ⊆ codomBound)) throw IllFormedQual(rq)
    val tq2@QType(t2, q2) = typeCheck(e2)
    // ◆ ∈ q2 ⇒ x ∉ fv(rt)
    if (q2.isFresh) checkDeepDep(rt, x)
    // ◆ ∈ qf ⇒ f ∉ fv(rt)
    if (qf.isFresh) checkDeepDep(rt, f)
    checkSubtypeOverlap(t2 ^ (q2 ⋒ qf), atq)
    rtq.substQual(x, q2).substQual(f, qf)
  case EApp(e1, e2, Some(false)) => // T-App
    val t1@QType(TFun(f, x, atq@QType(at, aq), rtq@QType(rt, rq)), qf) = typeCheck(e1)
    val codomBound: Qual = Qual(Γ.dom) ++ Set(◆, f, x)
    if (!(rq ⊆ codomBound)) throw IllFormedQual(rq)
    val tq2@QType(t2, q2) = typeCheck(e2)
    checkSubQType(tq2, atq)
    if (q2.isFresh) throw RequireNonFresh(e2, tq2)
    rtq.substQual(x, q2).substQual(f, qf)
  case EApp(e1, e2, None) =>
    val t1@QType(TFun(f, x, atq@QType(at, aq), rtq@QType(rt, rq)), qf) = typeCheck(e1)
    // Not specified which application rule to use, try heuristically
    if (aq.isFresh) typeCheck(EApp(e1, e2, Some(true)))
    else typeCheck(EApp(e1, e2, Some(false)))
  case ELet(x, Some(qt1), rhs, body, isGlobal) =>
    checkQTypeWF(qt1)
    val QType(t1, q1) = qt1
    val qt2 = typeCheck(rhs)
    checkSubQType(qt2, qt1)
    val rt = typeCheck(body)(using Γ + (x -> qt1))
    if (isGlobal) rt
    else {
      if (q1.isFresh) checkDeepDep(rt.ty, x)
      // Note: here we are not using the more precise qualifier (qt2) for substitution,
      // since it has been up-cast to q1 explicitly.
      rt.substQual(x, q1)
    }
  case ELet(x, None, rhs, body, isGlobal) =>
    val qt@QType(t, q) = typeCheck(rhs)
    val rt = typeCheck(body)(using Γ + (x -> qt))
    if (isGlobal) rt
    else {
      try
        if (q.isFresh) checkDeepDep(rt.ty, x)
        rt.substQual(x, q)
      catch case ex@DeepDependency(_, `x`) =>
        // If returning a literal lambda term without return type annotation,
        // try upcast the codomain type using the self-ref.
        body match {
          case ELam(f, a, at, fbody, None) =>
            val newRt = Some(QType(rt.ty.asInstanceOf[TFun].t2.ty, Qual.singleton(f)))
            typeCheck(ELet(x, None, rhs, ELam(f, a, at, fbody, newRt), isGlobal))
          case _ => throw ex
        }
    }
  case EAlloc(e) =>
    val tq@QType(t, q) = typeCheck(e)
    if (q.isFresh) throw RequireNonFresh(e, tq)
    TRef(QType(t, q)) ^ (q ++ Set(◆))
  case EUntrackedAlloc(e) =>
    // Only for testing
    val QType(t, q) = typeCheck(e)
    checkUntrackQual(q)
    TRef(t) ^ ()
  case EAssign(e1, e2) =>
    val QType(TRef(QType(t1, q1)), p) = typeCheck(e1)
    val tq2@QType(t2, q2) = typeCheck(e2)
    if (q1.isFresh || q2.isFresh) throw RequireNonFresh(e2, tq2)
    checkQualEq(q1, q2)
    checkTypeEq(e2, t2, t1)
    TUnit
  case EDeref(e) =>
    val QType(TRef(tq@QType(t, p)), q) = typeCheck(e)
    if (p.isFresh) throw RequireNonFresh(e, tq)
    t ^ p
  case ECond(cnd, thn, els) =>
    val t1 = typeCheck(cnd)
    checkQTypeEq(cnd, t1, TBool)
    val t2 = typeCheck(thn)
    val t3 = typeCheck(els)
    t2 ⊔ t3
}

def topTypeCheck(e: Expr): QType = {
  Counter.reset
  typeCheck(e)(using TEnv.empty)
}
