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
  test("escaping closures") {
    val e1 =
      let("x" ⇐ alloc(3)) {
        λ("f", "z")("f"♯(TNum ~> TNum)) { x.deref }
      }
    assert(topTypeCheck(e1) == (TFun("f", "z", TNum^(), TNum^()) ^ ◆))

    val e2 =
      (λ("x" ⦂ (TRef(TNum) ^ ◆)) { λ("f", "z")("f"♯(TNum ~> TNum)) { x.deref } })(alloc(3))
    assert(topTypeCheck(e2) == (TFun("f", "z", TNum^(), TNum^()) ^ ◆))

    //    let x = alloc(3) in λf(z).x
    // or ( λ(x). λf(z).x )(alloc(3))
    val e3 =
      (λ("x" ⦂ (TRef(TNum) ^ ◆)) { λ("f", "z")("f"♯(TNum ~> (TRef(TNum) ^ "x"))) { x } })(alloc(3))
    // The type checker should infer the avoidance of `x` and instead use `f` in the return qualifier
    assert(topTypeCheck(e3) == (TFun("f", "z", TNum^(), TRef(TNum^())^"f") ^ ◆))

    val e3_let =
      let("x" ⇐ alloc(0)) {
        λ("f", "z")("f"♯(TNum ~> (TRef(TNum) ^ "x"))) { x }
      }
    assert(topTypeCheck(e3_let) == (TFun("f", "z", TNum^(), TRef(TNum^())^"f") ^ ◆))

    val e3_alias = {
      val f = λ("x" ⦂ (TRef(TNum) ^ ◆)) {
        let ("y" ⇐ x) {
          λ("f", "z")("f"♯(TNum ~> (TRef(TNum) ^ "y"))) { y }
        }
      }
      val arg = alloc(3)
      f(arg)
    }
    assert(topTypeCheck(e3_alias) == (TFun("f", "z", TNum^(), TRef(TNum^())^"f") ^ ◆))

    // Manually annotate the return qualifier to self-ref `f` (not necessary)
    val e4 =
      (λ("x" ⦂ (TRef(TNum) ^ ◆)) { λ("f", "z")("f"♯(TNum ~> (TRef(TNum) ^ "f"))) { x } })(alloc(3))
    assert(topTypeCheck(e4) == (TFun("f", "z", TNum^(), TRef(TNum)^"f") ^ ◆))
    val e4_let =
      let("x" ⇐ alloc(0)) {
        λ("f", "z")("f"♯(TNum ~> (TRef(TNum) ^ "f"))) { x }
      }
    assert(topTypeCheck(e4_let) == (TFun("f", "z", TNum^(), TRef(TNum)^"f") ^ ◆))

    val e5 = {
      val f = λ("x" ⦂ (TRef(TNum) ^ ◆)) {
        let ("y" ⇐ x) {
          λ("f", "z")("f"♯(TNum ~> (TRef(TNum) ^ "f"))) { y }
        }
      }
      val arg = alloc(3)
      f(arg)
    }
    assert(topTypeCheck(e5) == (TFun("f", "z", TNum^(), TRef(TNum)^"f") ^ ◆))
  }
}