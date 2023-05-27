package diamond

object Counter:
  var i: Int = 0
  def reset: Unit = i = 0
  def fresh(prefix: String = ""): String = try { s"$prefix#$i" } finally { i += 1 }

def fix[T](f: T => T): T => T = x =>
  val y = f(x)
  if (x == y) y else fix(f)(y)
