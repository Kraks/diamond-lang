package diamond.avoidancestlc
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import diamond._
import diamond.avoidancestlc.core._

import core.Type._
import core.Expr._
import TypeSyntax._
import ExprSyntax._

import TypeSyntax.given_Conversion_Type_QType
import ExprSyntax.given_Conversion_Int_ENum

class AvoidanceSTLCTests extends AnyFunSuite {
  /*
  test("escaping closures") {
    val e1 =
      let("x" ⇐ alloc(3)) {
        λ("f", "z")("f"♯(TNum ~> TNum)) { x.deref }
      }
    assert(topTypeCheck(e1) == (TFun("f", "z", TNum^(), TNum^()) ^ ◆))
  }
    */
}