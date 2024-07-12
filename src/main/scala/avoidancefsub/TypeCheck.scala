package diamond.avoidancefsub

import diamond._
import diamond.avoidancefsub.core._

import core.Type._
import core.Expr._

/* Typing environment */

//  m: term variable to type
// tm: type variable to type upper bound
// qm: qualifier variable to qualifier upper bound
case class TEnv(m: AssocList[String, QType], tm: AssocList[TVar, Type], qm: AssocList[String, Qual], observable: Set[String] = Set()):
  def apply(x: String): QType | Qual =
    assert(!(m.contains(x) && qm.contains(x)), s"overlap $x")
    if (m.contains(x)) m(x)
    else qm(x)
  def apply(x: TVar): Type = tm(x)
  def getQualVarBound(q: String): Qual = qm(q)
  def containsQualVar(q: String): Boolean = qm.contains(q)

  def +(xt: (String, (Type | QType))) = xt match {
    case (x, t: Type) => TEnv(m + (x -> QType(t, Qual.untrack)), tm, qm, observable + x)
    case (x, t: QType) => TEnv(m + (x -> t), tm, qm, observable + x)
  }
  def +(tb: TypeBound) = TEnv(m, tm + (TVar(tb.tvar) -> tb.bound.ty), qm + (tb.qvar -> tb.bound.q), observable)
  def filter(q: Set[String]): TEnv = TEnv(m, tm, qm, observable.intersect(q))
  def filter(q: Qual): TEnv = filter(q.varSet)
  def dom: Set[String] = m.dom ++ qm.dom
  def isEmpty(): Boolean = m.isEmpty() && tm.isEmpty() && qm.isEmpty()

object TEnv:
  def empty: TEnv = TEnv(AssocList.empty, AssocList.empty, AssocList.empty)

/* Auxiliary functions for Qualifiers */

def reach(worklist: Set[String], acc: Set[String])(using Γ: TEnv): Set[String] =
  if (worklist.isEmpty) acc
  else {
    val x = worklist.head
    val QType(_, q) = Γ(x)
    val newQual = q.varSet.filter(z => !acc.contains(z))
    reach((worklist ++ newQual) -- Set(x), acc ++ newQual ++ Set(x))
  }

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
  def isEmpty: Boolean = q.set.isEmpty
  def nonEmpty: Boolean = q.set.nonEmpty
  def -(x: QElem): Qual = Qual(q.set - x)
  def --(q2: Qual): Qual = Qual(q.set -- q2.set)
  def \(x: QElem): Qual = Qual(q.set - x)
  def \(q2: Qual): Qual = Qual(q.set -- q2.set)
  def +(x: QElem): Qual = Qual(q.set + x)
  def ++(q2: Set[QElem]): Qual = Qual(q.set ++ q2)
  def ++(q2: Qual): Qual = Qual(q.set ++ q2.set)
  def ∩(x: QElem): Qual = Qual(q.set.intersect(Set(x)))
  def ∩(q2: Qual): Qual = Qual(q.set.intersect(q2.set))
  def ∪(q2: Qual): Qual = Qual(q.set ++ q2.set)
  def satVars(using Γ: TEnv): Set[String] = reach(q.varSet, Set())
  def ⋒(q2: Qual)(using Γ: TEnv): Qual =
    Qual(q.satVars.intersect(q2.satVars).asInstanceOf[Set[QElem]]) + Fresh()
  def ⊆(q2: Qual): Boolean = q.set.subsetOf(q2.set)
  def subsetAt(m: Qual, q2: Qual): Boolean = q.set.intersect(m.set).subsetOf(q2.set.intersect(m.set))
  def ⊆(Γ: TEnv): Boolean = q.set.subsetOf(Γ.dom.asInstanceOf[Set[QElem]] + Fresh())
  def ⊔(q2: Qual): Qual = Qual(q.set ++ q2.set)
  def subst(from: String, to: Qual): Qual =
    if (q.contains(from)) q - from ++ to.set else q
  def rename(from: String, to: String): Qual =
    q.subst(from, Qual.singleton(to))

/* Auxiliary functions for expressions */

extension (e: Expr)
  def freeVars: Set[String] = e match
    case EUnit | ENum(_) | EBool(_) => Set()
    case EVar(x) => Set(x)
    case ELam(f, x, at, e, rt, _) => e.freeVars -- Set(f, x)
    case EApp(e1, e2, _) => e1.freeVars ++ e2.freeVars
    case ELet(x, _, rhs, body, _) => rhs.freeVars ++ (body.freeVars - x)
    case EAlloc(e) => e.freeVars
    case EAssign(e1, e2) => e1.freeVars ++ e2.freeVars
    case EDeref(e) => e.freeVars
    case EAscribe(e, t) => e.freeVars
    case ETyLam(f, tvar, qvar, ub, e, rt, qual) => e.freeVars -- Set(f, qvar)
    case ETyApp(e, qt, _) => e.freeVars ++ qt.freeVars
  def substAnno(x: String, q: Qual): Expr = e match
    case EUnit | ENum(_) | EBool(_) | EVar(_) => e
    case ELam(f, y, at, body, rt, qual) =>
      ELam(f, y, at.substQual(x, q), body.substAnno(x, q), rt.map(_.substQual(x, q)), qual.map(_.subst(x, q)))
    case EApp(e1, e2, fr) => EApp(e1.substAnno(x, q), e2.substAnno(x, q), fr)
    case ELet(x, ty, rhs, body, gl) =>
      ELet(x, ty.map(_.substQual(x, q)), rhs.substAnno(x, q), body.substAnno(x, q), gl)
    case EAlloc(e) =>
      EAlloc(e.substAnno(x, q))
    case EAssign(e1, e2) =>
      EAssign(e1.substAnno(x, q), e2.substAnno(x, q))
    case EDeref(e) =>
      EDeref(e.substAnno(x, q))
    case EAscribe(e, t) =>
      EAscribe(e.substAnno(x, q), t.substQual(x, q))
    case ETyLam(f, tvar, qvar, ub, e, rt, qual) =>
      ETyLam(f, tvar, qvar, ub.substQual(x, q), e.substAnno(x, q), rt.map(_.substQual(x, q)), qual.map(_.subst(x, q)))
    case ETyApp(e, qt, fr) =>
      ETyApp(e.substAnno(x, q), qt.substQual(x, q), fr)

/* Auxiliary functions for types */

extension (t: Type)
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
    // New F◆ types
    case TTop => TTop
    case TVar(x) => TVar(x)
    case TForall(f, tvar, qvar, t1, t2) =>
      if (f == from || qvar == from) t
      else {
        val f1 = if (to.contains(f)) freshVar(f) else f
        val qvar1 = if (to.contains(qvar)) freshVar(qvar) else qvar
        val bound = t1.rename(f, f1)
        val rt = t2.rename(qvar, qvar1).rename(f, f1)
        TForall(f1, tvar, qvar1, bound.substQual(from, to), rt.substQual(from, to))
      }
  def substType(from: String, to: Type): Type = t match
    case TUnit | TNum | TBool => t
    case TFun(f, x, t1, t2) =>
      TFun(f, x, t1.substType(from, to), t2.substType(from, to))
    case TRef(t) => TRef(t.substType(from, to))
    // New F◆ types
    case TTop => t
    case TVar(x) =>
      if (x == from) to
      else TVar(x)
    case TForall(f, tvar, qvar, t1, t2) =>
      if (tvar == from) t
      else TForall(f, tvar, qvar, t1.substType(from, to), t2.substType(from, to))
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
    case TRef(t) => TRef(t.rename(from, to))
    // New F◆ types
    case TTop => TTop
    case TVar(x) => TVar(x)
    case TForall(f, tvar, qvar, t1, t2) =>
      if (f == from || qvar == from) t
      else if (f == to) {
        val g = freshVar(f)
        val bound = t1.rename(f, g)
        val rt = t2.rename(f, g)
        TForall(g, tvar, qvar, bound, rt).rename(from, to)
      } else if (qvar == to) {
        val pvar = freshVar(qvar)
        val rt = t2.rename(qvar, pvar)
        TForall(f, tvar, pvar, t1, rt).rename(from, to)
      } else TForall(f, tvar, qvar, t1.rename(from, to), t2.rename(from, to))
  def renameTVar(from: String, to: String): Type = t match
    case TUnit | TNum | TBool => t
    case TFun(f, x, t1, t2) =>
      TFun(f, x, t1.renameTVar(from, to), t2.renameTVar(from, to))
    case TRef(t) =>
      TRef(t.renameTVar(from, to))
    // New F◆ types
    case TTop => t
    case TVar(x) =>
      if (x == from) TVar(to)
      else TVar(x)
    case TForall(f, tvar, qvar, t1, t2) =>
      if (tvar == to) {
        val tvar1 = freshVar(tvar)
        val bound = t1.renameTVar(tvar, tvar1)
        val rt = t2.renameTVar(tvar, tvar1)
        TForall(f, tvar1, qvar, bound, rt).renameTVar(from, to)
      } else TForall(f, tvar, qvar, t1.renameTVar(from, to), t2.renameTVar(from, to))
  def freeVars: Set[String] = t match
    case TUnit | TNum | TBool => Set()
    case TFun(f, x, t1, t2) =>
      t1.freeVars ++ (t2.freeVars -- Set(x, f))
    case TRef(t) => t.freeVars
    case TTop => Set()
    case TVar(x) => Set()
    case TForall(f, tvar, qvar, bound, rt) =>
      val boundFreeVars = bound.freeVars
      val rtFreeVars = rt.freeVars
      boundFreeVars ++ (rtFreeVars -- Set(f, qvar))
  def expose(Γ: TEnv): Type = t match
    case x@TVar(_) => Γ(x).expose(Γ)
    case _ => t

/* Auxiliary functions for qualified types */

extension (tq: QType)
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
  def renameTVar(from: String, to: String): QType =
    val QType(ty, q) = tq
    QType(ty.renameTVar(from, to), q)
  def freeVars: Set[String] =
    val QType(t, q) = tq
    t.freeVars ++ q.varSet
  def expose(tenv: TEnv): QType =
    val QType(ty, q) = tq
    //QType(typeExposure(ty), qualExposure(q))
    QType(ty.expose(tenv), q)

/* Qualifier upcasting */

def qualUpcast1(g: List[(String, QType)], p: Qual, r: Qual): (Qual /*filter*/, Qual /*ub*/) = {
  g match
    case (x, QType(t, q))::tail =>
      if (p.contains(x) && !r.contains(x) && q.nonFresh) {
        val (fl, p1) = qualUpcast1(tail, p.subst(x, q), r)
        (fl ++ q, p1) // QA-Head
      } else {
        qualUpcast1(tail, p, r) // QA-Skip
      }
    case _ => (p, p) // QA-Nil
}

def qualUpcast2(g: List[(String, Qual)], p: Qual, r: Qual): (Qual /*filter*/, Qual /*ub*/) = {
  g match
    case (x, q)::tail =>
      if (p.contains(x) && !r.contains(x) && q.nonFresh) {
        val (fl, p1) = qualUpcast2(tail, p.subst(x, q), r)
        (fl ++ q, p1) // QA-Head
      } else {
        qualUpcast2(tail, p, r) // QA-Skip
      }
    case _ => (p, p) // QA-Nil
}

def qualUpcast(g: TEnv, p: Qual, r: Qual): (Qual /*filter*/, Qual /*ub*/) = {
  val (fl1, p1) = qualUpcast1(g.m.m, p, r)
  val (fl2, p2) = qualUpcast2(g.qm.m, p1, r)
  (fl1 ++ fl2, p1 ++ p2)
}

/* Qualifier checking */

def subQualCheck(g: TEnv, p: Qual, r: Qual): Option[Qual] =
  val (fl, p1) = qualUpcast(g, p, r)
  println(s"$g |- p1: $p1 r: $r")
  if (p1 ⊆ r) Some(fl ++ r) else None

/* Subtype checking */

def subtypeCheck(tenv: TEnv, t1: Type, t2: Type): (Qual /*filter*/, Qual /*growth*/) = {
  (t1, t2) match {
    case (TUnit, TUnit) => (Qual.untrack, Qual.untrack)
    case (TBool, TBool) => (Qual.untrack, Qual.untrack)
    case (TNum, TNum) => (Qual.untrack, Qual.untrack)
    case (TRef(QType(a, _)), TRef(QType(b, _))) =>
      // Note: the formalization does not allow references with deep qualifiers
      val (fl1, q1) = subtypeCheck(tenv, a, b)
      val (fl2, q2) = subtypeCheck(tenv, b, a)
      assert(q1.isUntrack && q2.isUntrack, "must output no growth")
      (fl1 ++ fl2, Qual.untrack)
    case (F@TFun(f, x, qt1@QType(t1, p1), qt2@QType(u1, r1)),
          G@TFun(g, y, qt3@QType(t2, p2), qt4@QType(u2, r2))) =>
      if (f == g && x == y) {
        val (fl1, gr1) = subtypeCheck(tenv, t2, t1)
        val (fl2, gr2) = subtypeCheck(tenv, u1, u2)
        assert(p2.subsetAt(Qual(Set(f, Fresh())), p1), "argument qualifier contravariance")
        println(s"$F <: $G")
        println(s"r1: $r1 r2: $r2")
        assert(r1.subsetAt(Qual(Set(f, Fresh())), r2), "return qualifier covariance")
        val p1_* = (p2 -- Qual(Set(f, Fresh()))) ++ gr1
        val r2_* = (r1 -- Qual(Set(f, x, Fresh()))) ++ gr2
        // S-DEPGR
        val gr1_* = if (r1.contains(x) && gr1.nonEmpty) p1_* else Qual.untrack
        // S-POSX
        val p2_* =
          if (!r1.contains(x) || r2.contains(x)) Qual.untrack
          else if (p2.nonFresh && (!p2.contains(f) || r2.contains(f))) p2 - f
          else throw new RuntimeException("S-POSX")
        val r2_** = r2_* ∪ gr1_* ∪ p2_*
        // S-NEGF
        val fl1_* =
          if (p1.contains(f) && p1.isFresh) Qual.untrack
          else {
            val Some(fl) = subQualCheck(tenv, p1_*, p1 -- Qual(Set(f, Fresh())))
            fl
          }
        // S-GROW
        val (fl2_*, gr) = subQualCheck(tenv, r2_**, r2 -- Qual(Set(f, x, Fresh()))) match {
          case Some(fl2_*) => (Qual.untrack, Qual.untrack)
          case None =>
            if (r2.contains(f) && (!p2.contains(f) || p2.isFresh)) (r2_**, r2_**)
            else throw new RuntimeException("S-GROW")
        }
        val fl = fl1 ∪ fl1_* ∪ fl2 ∪ fl2_*
        (fl, gr)
      } else if (f != g) {
        val f1 = freshVar()
        val F1 = TFun(f1, x, qt1.rename(f, f1), qt2.rename(f, f1))
        val G1 = TFun(f1, y, qt3.rename(g, f1), qt4.rename(g, f1))
        subtypeCheck(tenv, F1, G1)
      } else if (x != y) {
        val x1 = freshVar()
        val F1 = TFun(f, x1, qt1, qt2.rename(x, x1))
        val G1 = TFun(g, x1, qt3, qt4.rename(y, x1))
        subtypeCheck(tenv, F1, G1)
      } else throw new RuntimeException("Impossible")
    // New Fsub types
    case (_, TTop) => (Qual.untrack, Qual.untrack)
    case (TVar(x), TVar(y)) if x == y => (Qual.untrack, Qual.untrack)
    case (x@TVar(_), t) => subtypeCheck(tenv, tenv(x), t)
    case (F@TForall(f1, tvar1, qvar1, bound1@QType(tbound1, qbound1), rt1@QType(t1, q1)),
          G@TForall(f2, tvar2, qvar2, bound2@QType(tbound2, qbound2), rt2@QType(t2, q2))) =>
      if (f1 == f2 && tvar1 == tvar2 && qvar1 == qvar2) {
        //val Γ1 = Γ + (f1 -> (F ^ ◆)) + ((tvar1, qvar1) <⦂ bound2)
        //bound2.isSubQType(bound1) && rt1.isSubQType(rt2)(using Γ1)
        // XXX: assume qvars are never used
        assert(tbound1 == tbound2, "kernel fsub requires same bound")
        val fnfr = Qual(Set(f1, Fresh()))
        assert(q1.subsetAt(fnfr, q2))
        val tenv1 = tenv + TypeBound(tvar1, qvar1, bound1)
        val (fl1, gr1) = subtypeCheck(tenv1, t1, t2)
        subQualCheck(tenv, q1 ++ gr1 -- fnfr, q2 -- fnfr) match {
          case Some(fl2) =>
            (fl1 ++ fl2, Qual.untrack)
          case None =>
            assert(q2.contains(f1), "s-grow")
            val gr = q1 ++ gr1 -- fnfr
            (fl1 ++ gr, gr)
        }
      } else if (f1 != f2) {
        val g = freshVar()
        val F1 = TForall(g, tvar1, qvar1, bound1.rename(f1, g), rt1.rename(f1, g))
        val G1 = TForall(g, tvar2, qvar2, bound2.rename(f2, g), rt2.rename(f2, g))
        subtypeCheck(tenv, F1, G1)
      } else if (tvar1 != tvar2) {
        val tvar3 = freshVar()
        val F1 = TForall(f1, tvar3, qvar1, bound1.renameTVar(tvar1, tvar3), rt1.renameTVar(tvar1, tvar3))
        val G1 = TForall(f2, tvar3, qvar2, bound2.renameTVar(tvar2, tvar3), rt2.renameTVar(tvar2, tvar3))
        subtypeCheck(tenv, F1, G1)
      } else if (qvar1 != qvar2) {
        val qvar3 = freshVar()
        val F1 = TForall(f1, tvar1, qvar3, bound1.rename(qvar1, qvar3), rt1.rename(qvar1, qvar3))
        val G1 = TForall(f2, tvar2, qvar3, bound2.rename(qvar2, qvar3), rt2.rename(qvar2, qvar3))
        subtypeCheck(tenv, F1, G1)
      } else throw new RuntimeException("Impossible")
    case _ => throw new RuntimeException(s"Not subtype $t1 <: $t2")
  }
}

/* Avoidance */

// Different from the Coq implementation, here we implement the variants
// of the avoidance judgment separately for clarity, at the cost of some
// code duplication.

val mt = Qual.untrack

// φ ⊢ T1 ≤_a^δ T2
def avoidancePos(t: Type, a: String): (Qual /*filter*/, Qual /*growth*/, Type) = {
  t match {
    case TBool => (mt, mt, t)
    case TNum => (mt, mt, t)
    case TUnit => (mt, mt, t)
    case TRef(QType(t, q)) =>
      assert(q.isUntrack, "must be untrack in this system")
      val (fl, gr, t1) = avoidancePos(t, a)
      assert(t == t1, s"$t and $t1 should be equivalent")
      (mt, mt, TRef(QType(t, mt)))
    case F@TFun(f, x, QType(t, p), QType(u, r)) => // AV-POSF
      // S-NEGF
      val (fl1, gr1, t1) =
        if (p.contains(f) && p.isFresh) avoidanceNeg(t, a)
        else {
          val (fl1, t1) = avoidanceNegNG(t, a)
          (fl1, mt, t1)
        }
      val (fl2, gr2, u1) = avoidancePos(u, a)
      // S-DEPGR
      val gr =
        if (gr1.nonEmpty && r.contains(x))
          gr2 ++ (r ∩ a) ++ (p \ (Qual(Set(f, Fresh())))) ++ gr1
        else
          gr2 ++ (r ∩ a)
      // S-GROW
      val p1 =
        if (gr.nonEmpty && p.nonFresh) p \ (Qual(Set(f, a)))
        else p \ Qual(Set(a))
      // S-GROW
      val r1 = if (gr.nonEmpty) (r \ a) + f else r
      val fl = fl1 ++ fl2 ++ gr
      (fl, gr, TFun(f, x, QType(t1, p1), QType(u1, r1)))
    // F-sub new types
    // XXX
    case TTop => (mt, mt, t)
    case TVar(x) => (mt, mt, t)
    case TForall(f, tvar, qvar, bound@QType(tbound, qbound), QType(u, r)) =>
      val (fl1, tbound1) = avoidanceNegNG(tbound, a)
      assert(tbound == tbound1, "kernel fsub")
      val (fl2, gr2, u1) = avoidancePos(u, a)
      val gr = gr2 ++ (r ∩ a)
      val r1 = if (gr.nonEmpty) (r \ a) + f else r
      val fl = fl2 ++ gr
      (fl, gr, TForall(f, tvar, qvar, bound, QType(u1, r1)))
  }
}

// φ ⊢ T1 ≥_a^δ T2
def avoidanceNeg(t: Type, a: String): (Qual /*filter*/, Qual /*growth*/, Type) = {
  t match {
    case TBool => (mt, mt, t)
    case TNum => (mt, mt, t)
    case TUnit => (mt, mt, t)
    case TRef(QType(t, q)) =>
      assert(q.isUntrack, "must be untrack in this system")
      val (fl, gr, t1) = avoidancePos(t, a)
      assert(t == t1, "must be equivalent")
      (mt, mt, TRef(QType(t, mt)))
    case F@TFun(f, x, QType(t, p), QType(u, r))
      if r.contains(f) && (!p.contains(f) || p.isFresh) => // AV-NEGF-GR
      // S-GROW
      val (fl1, gr1, t1) = avoidancePos(t, a)
      val (fl2, gr2, u1) = avoidanceNeg(u, a)
      // S-DEPGR
      val gr1_* =
        if (gr1.nonEmpty || p.contains(a))
          (p -- Qual(Set(f, Fresh()))) ++ gr1
        else mt
      // S-NEGF
      val p1 =
        if (gr1_*.nonEmpty) (p - a) ++ Qual(Set(f, Fresh()))
        else p
      // S-DEPGR/S-POSX
      val r1 =
        if (gr1_*.nonEmpty && p.nonFresh) (r - a) + x
        else r - a
      // S-DEPGR/S-GROW
      val gr =
        if (r1.contains(x)) gr1_* ++ gr2
        else gr2
      val fl = fl1 ++ fl2 ++ gr
      (fl, gr, TFun(f, x, QType(t1, p1), QType(u1, r1)))
    case F@TFun(f, x, QType(t, p), QType(u, r)) =>
      val (fl, ty) = avoidanceNegNG(F, a)
      (fl, mt, ty)
    // F-sub new types
    // XXX
    case TTop => (mt, mt, t)
    case TVar(x) => (mt, mt, t)
    case TForall(f, tvar, qvar, bound@QType(tbound, qbound), QType(u, r))
      if r.contains(f) =>
      val (fl1, gr1, tbound1) = avoidancePos(tbound, a)
      assert(tbound == tbound1, "kernel fsub")
      val (fl2, gr2, u1) = avoidanceNeg(u, a)
      val r1 = r \ a
      (fl2 ++ gr2, gr2, TForall(f, tvar, qvar, bound, QType(u1, r1)))
    case TForall(f, tvar, qvar, bound, res) =>
      val (fl, t1) = avoidanceNegNG(t, a)
      (fl, mt, t1)
  }
}

// φ ⊢ T1 ≥_a^⊥ T2
def avoidanceNegNG(t: Type, a: String): (Qual /*filter*/, Type) = {
  t match {
    case TBool => (mt, t)
    case TNum => (mt, t)
    case TUnit => (mt, t)
    case TRef(QType(t, q)) =>
      assert(q.isUntrack, "must be untrack in this system")
      val (fl, gr, t1) = avoidancePos(t, a)
      assert(t == t1, "must be equivalent")
      (mt, TRef(QType(t, mt)))
    case F@TFun(f, x, QType(t, p), QType(u, r)) => // AV-NEGF-NG
      val (fl1, gr1, t1) = avoidancePos(t, a)
      val (fl2, u1) = avoidanceNegNG(u, a)
      // S-NEGF
      val p1 =
        if (gr1.nonEmpty || p.contains(a))
          (p - a) ++ Set(f, Fresh())
        else p
      // !S-DEPGR
      val r1 =
        if (gr1.nonEmpty || p.contains(a)) r -- Qual(Set(x, a))
        else r - a
      val fl = fl1 ++ fl2
      (fl, TFun(f, x, QType(t1, p1), QType(u1, r1)))
    // F-sub new types
    // XXX
    case TTop => (mt, t)
    case TVar(x) => (mt, t)
    case TForall(f, tvar, qvar, bound@QType(tbound, qbound), QType(u, r)) =>
      val (fl1, gr1, tbound1) = avoidancePos(tbound, a)
      assert(tbound == tbound1, "kernel fsub")
      val (fl2, u1) = avoidanceNegNG(u, a)
      val r1 = r \ a
      (fl2, TForall(f, tvar, qvar, bound, QType(u1, r1)))
  }
}

/* Bidirectional Typing */

def basicType(t: Type): Boolean = t match
  case TUnit | TNum | TBool => true
  case _ => false

def isVar(e: Expr): Boolean = e match
  case EVar(_) => true
  case _ => false

def wellFormed(tenv: TEnv, t: QType): Boolean =
  t.freeVars.subsetOf(tenv.dom)

// check returns the filter
def check(tenv: TEnv, e: Expr, tq: QType): Qual = e match {
  case ELam(f, x, at@QType(t, p), body, rt@Some(QType(u, r)), qual) =>
    // Note: assume that at/rt is consistent with the provided ft
    val QType(ft, q) = tq
    val r1 = if (p.contains(f)) r else r.subst(f, q)
    val x1 = if (!p.isFresh && p ⊆ r1) Qual.singleton(x) else Qual.untrack
    val fl = check(tenv + (x -> QType(t, p.subst(f, q))), body, QType(u, x1 ++ r.subst(f, q)))
    assert(fl ⊆ (q + x), s"filter must be a subset of the provided qualifier: $fl ⊆ ${q + x}")
    (p ++ q) -- Qual(Set(Fresh(), f))
  case ETyLam(f, tvar, qvar, bound@QType(t, p), body, rt@Some(QType(u, r)), qual) =>
    // XXX: Need double check
    val QType(ft, q) = tq
    val r1 = if (p.contains(f)) r else r.subst(f, q)
    val tenv1 = tenv + TypeBound(tvar, qvar, QType(t, p.subst(f, q)))
    val fl = check(tenv1, body, QType(u, r.subst(f, q)))
    assert(fl ⊆ (q + qvar), s"filter must be a subset of the provided qualifier: $fl ⊆ ${q + qvar}")
    (p ++ q) -- Qual(Set(Fresh(), f))
  case _ =>
    //println(s"check $e $tq")
    val QType(t, q) = tq
    val (fl1, q1) = checkInfer(tenv, e, t)
    //println(s"$e $q1 $q")
    val Some(fl2) = subQualCheck(tenv, q1, q)
    (fl1 ++ fl2) - Fresh()
}

// infer returns the filter and the inferred type
def infer(tenv: TEnv, e: Expr): (Qual, QType) = {
  e match {
    case EUnit => (Qual.untrack, QType(TUnit, Qual.untrack))
    case ENum(_) => (Qual.untrack, QType(TNum, Qual.untrack))
    case EBool(_) => (Qual.untrack, QType(TBool, Qual.untrack))
    case EVar(x) =>
      val QType(t, q) = tenv(x)
      (Qual.singleton(x), QType(t, Qual.singleton(x)))
    case EAlloc(e0) =>
      val (fl, QType(b, q)) = infer(tenv, e0)
      assert(basicType(b), "must be a basic type")
      (fl, QType(TRef(QType(b, mt)), q + Fresh()))
    case EDeref(e0) =>
      val (fl, QType(TRef(QType(b, _)), q)) = infer(tenv, e0)
      assert(basicType(b), "must be a basic type")
      (fl, QType(b, Qual.untrack))
    case EAssign(lhs, rhs) =>
      val (fl1, QType(TRef(QType(b1, _)), q)) = infer(tenv, lhs)
      assert(basicType(b1), "must be a basic type")
      val (fl2, QType(b2, p)) = infer(tenv, rhs)
      assert(b1 == b2, "must have the same type")
      (fl1 ++ fl2, QType(b1, Qual.untrack))
    case EAscribe(e, qt) =>
      assert(wellFormed(tenv, qt), "must be well-formed")
      val fl = check(tenv, e, qt)
      (fl, qt)
    case ELet(x, _, rhs, body, _) =>
      val (fl2, QType(t, p)) = infer(tenv, rhs)
      val (fl1, QType(u, r)) = infer(tenv + (x -> QType(t, p)), body)
      val (fl3, gr, v) = avoidancePos(u, x)
      val fl = (fl2 ++ fl1 ++ fl3 ++ p ++ r ++ gr) -- Qual(Set(x, Fresh()))
      val rq = (r ++ gr).subst(x, p)
      (fl, QType(v, rq))
    case EApp(ELam(f, x, at, body, rt, _), e2, _) =>
      // If an application's left-hand side is a literal lambda term,
      // we delegate the inference using TA-LET rule
      infer(tenv, ELet(x, Some(at), e2, body, false))
    case EApp(e1, e2, _) =>
      val (fl1, QType(TFun(f, x, QType(t, p), QType(u, r)), q)) = infer(tenv, e1)
      val (fl2, p1) = checkInfer(tenv, e2, t)
      val fl3 =
        if (p.nonFresh && !isVar(e1)) {
          val Some(fl) = subQualCheck(tenv, p1, p)
          fl
        } else if (p.nonFresh && isVar(e1)) {
          val Some(fl) = subQualCheck(tenv, p1, p.subst(f, Qual.singleton(e1.asInstanceOf[EVar].x)))
          fl
        } else if (p.isFresh && !p.contains(f)) {
          val (fl, p2) = qualUpcast(tenv, p1, p)
          given TEnv = tenv
          assert(((p2 \ p) ⋒ q) ⊆ Qual.fresh, s"qualifiers not separate: ${p2 \ p} and $q")
          fl
        } else Qual.untrack
      val fl = fl1 ++ fl2 ++ fl3 ++ (r \ Qual(Set(f, x, Fresh())))
      (fl, QType(u, r.subst(x, p1).subst(f, q)))
    case ELam(f, x, at, body, Some(rt), qual) =>
      // We consider a lambda term with full type annotation as "ascription"
      val q = qual.getOrElse(Qual((body.freeVars -- Set(f, x)).asInstanceOf[Set[QElem]]))
      val tq = QType(TFun(f, x, at, rt), q)
      val fl = check(tenv, e, tq)
      (fl, tq)
    case ELam(f, x, at, body, None, qual) =>
      // If there is only argument type, we infer the return type and
      // check the whole lambda term again
      val q = qual.getOrElse(Qual((body.freeVars -- Set(f, x)).asInstanceOf[Set[QElem]]))
      val (bodyFl, rt) = infer(tenv + (x -> at), body)
      val tq = QType(TFun(f, x, at, rt), q)
      val fl = check(tenv, ELam(f, x, at, body, Some(rt), Some(q)), tq)
      (fl, tq)
    /** F-sub new terms **/
    case ETyLam(f, tvar, qvar, bound, body, Some(rt), qual) =>
      // We consider a type lambda term with full type annotation as "ascription"
      val q = qual.getOrElse(Qual((body.freeVars -- Set(f, qvar)).asInstanceOf[Set[QElem]]))
      val tq = QType(TForall(f, tvar, qvar, bound, rt), q)
      val fl = check(tenv, e, tq)
      (fl, tq)
    case ETyLam(f, tvar, qvar, bound, body, None, qual) =>
      // If there is only bound type, we infer the return type and
      // check the whole type lambda term again
      val q = qual.getOrElse(Qual((body.freeVars -- Set(f, qvar)).asInstanceOf[Set[QElem]]))
      val (bodyFl, rt) = infer(tenv + TypeBound(tvar, qvar, bound), body)
      val tq = QType(TForall(f, tvar, qvar, bound, rt), q)
      val fl = check(tenv, ETyLam(f, tvar, qvar, bound, body, Some(rt), Some(q)), tq)
      (fl, tq)
    case ETyApp(e, tq, _) =>
      val (fl1, t1) = infer(tenv, e)
      val QType(TForall(f, tvar, qvar, bound@QType(t, p), rt@QType(u, r)), q) = t1.expose
      val (fl2, gr) = subtypeCheck(tenv, tq.ty, u)
      assert(wellFormed(tenv, tq), "must be well-formed")
      val fl = (fl1 ++ fl2 ++ p ++ r) -- Qual(Set(f, qvar, Fresh()))
      (fl -- Qual(Set(f, qvar, Fresh())), QType(u, r.subst(qvar, tq.q)))
  }
}

def checkInfer(tenv: TEnv, e: Expr, t: Type): (Qual/*filter*/, Qual/*qual*/) = {
  val (fl1, QType(t1, q)) = infer(tenv, e)
  println(s"checkInfer $t1 <: $t")
  val (fl2, gr) = subtypeCheck(tenv, t1, t)
  (fl1 ++ fl2, q ++ gr)
}

def topTypeCheck(e: Expr): QType = {
  Counter.reset
  val (fl, qt) = infer(TEnv.empty, e)
  qt
}