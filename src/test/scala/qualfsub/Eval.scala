package diamond.qualfsub

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import diamond.qualfsub.core._
import diamond._
import Type._
import Expr._
import Value._
import TypeSyntax._
import ExprSyntax._

import TypeSyntax.given_Conversion_Type_QType
import ExprSyntax.given_Conversion_Int_ENum

class QualFSubEvalTests extends AnyFunSuite {
  test("poly-id") {
    Counter.reset
    val id = Λ(("T", "z") <⦂ (TTop ^ ◆)) {
      λ("y" ⦂ (TVar("T") ^ ◆)) { EVar("y") }
    }
    val e2 = TRef(TNum)^ ◆
    val e3 = alloc(42)
    val (v, σ) = topEval(id.applyFresh(e2)(e3))
    assert(v == VLoc(2))
    assert(σ ==
      Store(Map(
        VLoc(0) -> VPolyFun(ELam("AnnoFun#1","y",TVar("T")^ ◆,EVar("y"),None),
          Env(Map("AnnoTFun#0" -> VLoc(0)))),
        VLoc(1) -> VFun("AnnoFun#1","y",EVar("y"),
          Env(Map(
            "AnnoTFun#0" -> VLoc(0),
            "AnnoFun#1" -> VLoc(1)))),
        VLoc(2) -> VNum(42),
        VLoc(3) -> VLoc(2))))
  }
}
