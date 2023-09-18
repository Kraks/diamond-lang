package diamond.effqualfsub

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import diamond.effqualfsub.core._
import diamond.effqualfsub.core.given
import diamond._
import Type._
import Expr._
import MemEff._
import TypeSyntax._
import ExprSyntax._

import TypeSyntax.given_Conversion_Type_QType
import ExprSyntax.given_Conversion_Int_ENum

class QuantaleTests extends AnyFunSuite {
  val e1 = Eff(Map(Set("a", "b") -> Read, Set("c", "d") -> Write, Set("f") -> Bot))
  val e2 = Eff(Map(Set("a", "e") -> Write, Set("d") -> Kill, Set("x") -> Kill))
  val e3 = Eff(Map(Set("e") -> Read, Set("x", "f") -> Bot))
  val id = summon[EffQuantale[Eff]].id

  test("join") {
    assert(e1 ⊔ e2 ==
      Eff(Map(Set("x") -> Kill, Set("f") -> Bot, Set("c", "d") -> Kill, Set("a", "b", "e") -> Write)))
    assert(e1 ⊔ e2 == e2 ⊔ e1)

    assert(e1 ⊔ id == e1)
    assert(id ⊔ e1 == e1)
  }

  test("seq") {
    assert(e1 ▷ e2 ==
      Eff(Map(Set("x") -> Kill, Set("f") -> Bot, Set("c", "d") -> Kill, Set("a", "b", "e") -> Write)))
    intercept[KillException] { e2 ▷ e1 } match { case KillException(Write) => }

    assert(e1 ▷ id == e1)
    assert(id ▷ e1 == e1)
  }

  test("dist") {
    assert(e1 ▷ (e2 ⊔ e3) == (e1 ▷ e2) ⊔ (e1 ▷ e3))
    assert((e1 ⊔ e3) ▷ e2 == (e1 ▷ e2) ⊔ (e1 ▷ e3))

    intercept[KillException] { (e1 ⊔ e2) ▷ e3 }
    intercept[KillException] { (e1 ▷ e3) ⊔ (e2 ▷ e3) }
  }
}

