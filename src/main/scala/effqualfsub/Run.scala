package diamond.effqualfsub

import diamond._
import diamond.parser._
import diamond.effqualfsub.core._

object RunDiamond {
  import Value._
  import Type._

  def prettyType(t: Type): String =
    t match {
      case TUnit => "Unit"
      case TNum => "Int"
      case TBool => "Bool"
      case TRef(t) => s"Ref[${prettyQType(t)}]"
      case TFun(f, x, t1, t2, eff) => s"$f($x: ${prettyQType(t1)}) => ${prettyQType(t2)}"
      case TTop => "Top"
      case TVar(x) => x
      case TForall(f, tvar, qvar, bound, res, eff) => s"∀$f($tvar^$qvar <: ${prettyQType(bound)}). ${prettyQType(res)}"
    }

  def prettyQType(tq: QType): String = {
    val QType(t, q) = tq
    val tyStr = prettyType(t)
    t match {
      case t: TFun => s"($tyStr)^$q"
      case t: TForall => s"($tyStr)^$q"
      case _ => s"$tyStr^$q"
    }
  }

  def prettyEff(e: Eff): String = e.m.mkString("↦")

  def prettyPrint(tq: QType, eff: Eff): Unit =
    println(s"Type: ${prettyQType(tq)} | ${prettyEff(eff)}")

  def prettyPrint(v: Value): Unit =
    val str = v match {
      case VUnit => "()"
      case VNum(x) => x.toString
      case VBool(b) => b.toString
      case VLoc(n) => s"loc($n)"
      case VFun(f, _, _, _) => s"fun($f)"
      case VPolyFun(f, _, _) => s"polyFun($f)"
    }
    println(s"Evaluation: $str")

  def main(args: Array[String]): Unit = {
    val res = Parser.parseFileToCore("examples/" + args(0)) //example.dia
    println(s"Parsed core AST: $res")
    val (t, eff) = topTypeCheck(res)
    prettyPrint(t, eff)
    val (v, σ) = topEval(res)
    prettyPrint(v)
  }
}
