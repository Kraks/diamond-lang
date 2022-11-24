package diamond.fsub.full

import org.scalatest.funsuite.AnyFunSuite

import diamond._
import diamond.fsub._
import Type._
import TypeSyntax._
import Expr._
import ExprSyntax._

class FSubFullTest extends AnyFunSuite {
  test("undecidable") {
    def ¬(T: Type): Type = ∀("α" <∶ T) { α }
    // note that Γ ⊢ ¬S <: ¬ T implies T <: T by contravariance
    val T = ∀("β" <∶ TTop) { ¬(∀("γ" <∶ β) { ¬(γ) }) }

    given Γ: TEnv = TEnv.empty + ("X" <∶ T)
    // Note: this is non-terminating (as it should)
    isSubtypeFull(TVar("X"), ∀("Y" <∶ TVar("X")) { ¬(TVar("Y")) })
  }
}
