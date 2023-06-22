package diamond

object Counter:
  var i: Int = 0
  def reset: Unit = i = 0
  def fresh(prefix: String = ""): String = try { s"$prefix#$i" } finally { i += 1 }

def freshVar(prefix: String = "x"): String = Counter.fresh(prefix)

def fix[T](f: T => T): T => T = x =>
  val y = f(x)
  if (x == y) y else fix(f)(y)

object AssocList:
  def empty[K, T]: AssocList[K, T] = AssocList[K, T](List())

case class AssocList[K, T](m: List[(K, T)]):
  def apply(x: K): T = m.collectFirst({ case (`x`, t) => t }).get
  def +(xt: (K, T)): AssocList[K, T] = AssocList(xt :: m)
  def filter(q: Set[K]): AssocList[K, T] = AssocList(m.filter((k, v) => q.contains(k)))
  def dom: Set[K] = m.map(_._1).toSet
  def contains(k: K): Boolean = dom.contains(k)
  override def toString = s"""[${m.mkString("; ")}]"""
