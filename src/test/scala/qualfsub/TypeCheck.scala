package diamond.qualfsub

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import diamond.qualfsub.core._
import diamond._
import Type._
import Expr._
import TypeSyntax._
import ExprSyntax._

import TypeSyntax.given_Conversion_Type_QType
import ExprSyntax.given_Conversion_Int_ENum

class QualFSubTests extends AnyFunSuite {
  test("fsub-syntax") {
    Counter.reset

    // ∀f(X^x <: Top^◆). X^x
    val t1 = ∀("f", ("X", "x") <⦂ (TTop ^ ◆))(TVar("X") ^ "x")
    assert(t1 == TForall("f", "X", "x", TTop^ ◆, TVar("X")^ "x"))

    // Λf(X^x <: Top^◆). λ(y: X^x). y
    val e1 = Λ("f", ("X", "x") <⦂ (TTop ^ ◆)) {
      λ("y" ⦂ (TVar("X") ^ "x")) { EVar("y") }
    }
    assert(e1 == ETyLam("f", "X", "x", TTop^ ◆, ELam("AnnoFun#0", "y", TVar("X")^"x", EVar("y"), None), None))
  }

  test("poly-id") {
    // !! We cannot write ∀(T^z <: Top^◆). (y: T^z) -> T^y
    val e1 = Λ(("T", "z") <⦂ (TTop ^ ◆)) {
      λ("y" ⦂ (TVar("T") ^ ◆)) { EVar("y") }
    }
    val e2 = TRef(TNum)^ ◆
    val e3 = alloc(42)
    assert(topTypeCheck(e1.applyFresh(e2)) == (TFun("AnnoFun#2", "y", TRef(TNum)^ ◆, TRef(TNum)^"y") ^()))
    assert(topTypeCheck(e1.applyFresh(e2).applyFresh(e3)) == (TRef(TNum)^ ◆))

    val e4 = TNum^()
    val e5 = ENum(42)
    assert(topTypeCheck(e1.applyFresh(e4).applyFresh(e5)) == (TNum^ ()))
    // ordinary T-TApp and T-App should apply here:
    assert(topTypeCheck(e1(e4)(e5)) == (TNum^ ()))
  }
}
