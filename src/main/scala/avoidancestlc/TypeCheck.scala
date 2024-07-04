package diamond.avoidancestlc

import diamond._
import Type._
import Expr._

/* Typing environment */

case class TEnv(m: AssocList[String, QType], observable: Set[String] = Set()):
  def apply(x: String): QType = m(x)
  def +(xt: (String, QType)) = xt match {
    case (x, t: QType) => TEnv(m + (x -> t), observable + x)
  }
  def filter(q: Set[String]): TEnv = TEnv(m, observable.intersect(q))
  def filter(q: Qual): TEnv = filter(q.varSet)
  def dom: Set[String] = m.dom
  def isEmpty(): Boolean = m.isEmpty()

object TEnv:
  def empty: TEnv = TEnv(AssocList.empty)
  def unapply(g: TEnv): Option[((String, QType), TEnv)] =
    if (g.isEmpty()) None else Some((g.m.m.head, TEnv(AssocList(g.m.m.tail))))

/* Auxiliary functions for Qualifiers */

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
  def nonEmpty: Boolean = q.set.nonEmpty
  def -(x: QElem): Qual = Qual(q.set - x)
  def --(q2: Qual): Qual = Qual(q.set -- q2.set)
  def +(x: QElem): Qual = Qual(q.set + x)
  def ++(q2: Set[QElem]): Qual = Qual(q.set ++ q2)
  def ++(q2: Qual): Qual = Qual(q.set ++ q2.set)
  def \(q2: Qual): Qual = Qual(q.set -- q2.set)
  def ∪(q2: Qual): Qual = Qual(q.set ++ q2.set)
  def ⊆(q2: Qual): Boolean = q.set.subsetOf(q2.set)
  def subsetAt(m: Qual, q2: Qual): Boolean = q.set.intersect(m.set).subsetOf(q2.set.intersect(m.set))
  def ⊆(Γ: TEnv): Boolean = q.set.subsetOf(Γ.dom.asInstanceOf[Set[QElem]] + Fresh())
  def ⊔(q2: Qual): Qual = Qual(q.set ++ q2.set)
  def subst(from: String, to: Qual): Qual =
    if (q.contains(from)) q - from ++ to.set else q
  def rename(from: String, to: String): Qual =
    q.subst(from, Qual.singleton(to))

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
  def freeVars: Set[String] = t match
    case TUnit | TNum | TBool => Set()
    case TFun(f, x, t1, t2) =>
      t1.freeVars ++ (t2.freeVars -- Set(x, f))
    case TRef(t) => t.freeVars

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
  def freeVars: Set[String] =
    val QType(t, q) = tq
    t.freeVars ++ q.varSet

/* Qualifier upcasting */

def qualUpcast(g: TEnv, p: Qual, r: Qual): (Qual, Qual) = {
  g match {
    case TEnv((x, QType(t, q)), tail) =>
      if (p.contains(x) && !r.contains(x) && q.nonFresh) {
        val (fl, p1) = qualUpcast(tail, p.subst(x, q), r)
        (fl ++ q, p1) // QA-Head
      } else {
        qualUpcast(tail, p, r) // QA-Skip
      }
    case _ => (p, p) // QA-Nil
  }
}

/* Qualifier checking */

def subQualCheck(g: TEnv, p: Qual, r: Qual): Option[Qual] =
  val (fl, p1) = qualUpcast(g, p, r)
  if (p1 ⊆ r) Some(fl ++ r) else None

/* Subtype checking */

def subtypeCheck(tenv: TEnv, t1: Type, t2: Type): (Qual, Qual) = {
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
  }
}

/* Avoidance */
