package diamond

object Counter:
  var i: Int = 0
  def reset: Unit = i = 0
  def fresh(prefix: String = ""): String = try { s"$prefix#$i" } finally { i += 1 }

def fix[T](f: T => T): T => T = x =>
  val y = f(x)
  if (x == y) y else fix(f)(y)

object AssocList:
  def empty[T]: AssocList[T] = AssocList[T](List())

case class AssocList[T](m: List[(String, T)]):
  def apply(x: String): T = m.collectFirst({ case (`x`, t) => t }).get
  def +(xt: (String, T)): AssocList[T] = AssocList(xt :: m)
  def filter(q: Set[String]): AssocList[T] = AssocList(m.filter((k, v) => q.contains(k)))
  def dom: Set[String] = m.map(_._1).toSet
  override def toString = s"""[${m.mkString("; ")}]"""
