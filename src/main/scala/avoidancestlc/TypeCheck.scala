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
  def ++(q2: Qual): Qual = Qual(q.set ++ q2.set)
  def \(q2: Qual): Qual = Qual(q.set -- q2.set)
  def ∪(q2: Qual): Qual = Qual(q.set ++ q2.set)
  def ⊆(q2: Qual): Boolean = q.set.subsetOf(q2.set)
  def ⊆(Γ: TEnv): Boolean = q.set.subsetOf(Γ.dom.asInstanceOf[Set[QElem]] + Fresh())
  def ⊔(q2: Qual): Qual = Qual(q.set ++ q2.set)
  def subst(from: String, to: Qual): Qual =
    if (q.contains(from)) q - from ++ to.set else q
  def rename(from: String, to: String): Qual =
    q.subst(from, Qual.singleton(to))

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