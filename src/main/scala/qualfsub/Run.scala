package diamond.qualfsub

import diamond._
import diamond.parser._

object RunDiamond {
  def main(args: Array[String]): Unit = {
    val res = Parser.parseFile("examples/" + args(0)).toCore //example.dia
    println(res)
    println(topTypeCheck(res))
    println(topEval(res)._1)
  }
}
