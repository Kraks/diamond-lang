package diamond

object Counter:
  var i: Int = 0
  def reset: Unit = i = 0
  def fresh: String = try { "#" + i } finally { i += 1 }

