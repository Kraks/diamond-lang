package diamond.avoidancefsub
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import diamond._
import diamond.avoidancefsub.core._
import diamond.avoidancefsub.Parser._

import core.Type._
import core.Expr._
import TypeSyntax._
import ExprSyntax._

import TypeSyntax.given_Conversion_Type_QType
import ExprSyntax.given_Conversion_Int_ENum

class AvoidanceFSubTests extends AnyFunSuite {
  def parseAndCheck(s: String): QType = topTypeCheck(parseToCore(s))

  test("type arg infer") {
    val p1 = """
    def id[T <: Top](x: T^<>): T^x = x;
    val x = id(3);                  // : Int^∅
    val c = id(Ref 42);             // : Ref[Int]^◆
    val y = id(x);                  // : Int^∅
    x + y + (! c)                   // : Int^∅
    """
    assert(parseAndCheck(p1) == (TNum^()))
  }

  test("forall type") {
    val p = """
    val p: forall [A^a <: Top^<>] => ((x: A^a) => A^x)^a =
      ([A^a <: Top^<>] => { (x: A^a) => { x } });
    p
    """
    assert(parseAndCheck(p) ==
      (TForall("𝐹#2","A","a",TTop^ ◆,
        TFun("𝑓#3","x",TVar("A")^"a",TVar("A")^"x")^"a")^()))
  }

}