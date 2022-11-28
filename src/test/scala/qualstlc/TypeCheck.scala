package diamond.qualstlc

import org.scalatest.funsuite.AnyFunSuite

import diamond._
import Type._
import Expr._
import TypeSyntax._
import ExprSyntax._

import TypeSyntax.given_Conversion_Type_QType

class Playground extends AnyFunSuite {}

class QualSTLCTests extends AnyFunSuite {
  test("syntax") {
    val t1: QType = TNum ^ ()
    assert(t1 == QType(TNum, Qual.untrack))

    val t2: QType = TNum ^ 𝑥
    assert(t2 == QType(TNum, Qual.singleton("x")))

    val t3: QType = TNum ^ ◆
    assert(t3 == QType(TNum, Qual.fresh))

    val t4: QType = TNum ^ (𝑥, ◆)
    assert(t4 == QType(TNum, Qual(Set("x", Fresh()))))

    val t5: TFun = t4 ~> t3
    assert(t5 == TFun(None,None,QType(TNum,Qual(Set("x", Fresh()))),QType(TNum,Qual(Set(Fresh())))))

    val t6: QType = (𝑓 ♯ t5) ^ ◆
    assert(t6 == QType(TFun(Some("f"), None,
      QType(TNum,Qual(Set("x", Fresh()))),QType(TNum,Qual(Set(Fresh())))),Qual(Set(Fresh()))))

    val t7: QType = (𝑓 ♯ (TNum ~> TNum)) ^ ◆
    assert(t7 == QType(TFun(Some("f"),None,
      QType(TNum,Qual(Set())),QType(TNum,Qual(Set()))),Qual(Set(Fresh()))))

    val t8: QType = (𝑓 ♯ ((TNum ^ 𝑦) ~> (TNum ^ (𝑦, 𝑧)))) ^ ◆
    assert(t8 == QType(TFun(Some("f"), None,
      QType(TNum,Qual(Set("y"))),QType(TNum,Qual(Set("y", "z")))),Qual(Set(Fresh()))))

    val t9: QType = (𝑓 ♯ ((𝑥 ∶ (TNum ^ 𝑦)) ~> (TNum ^ 𝑥))) ^ ◆
    assert(t9 == QType(TFun(Some("f"),Some("x"),
      QType(TNum,Qual(Set("y"))),QType(TNum,Qual(Set("x")))),Qual(Set(Fresh()))))

    val t10: QType = (𝑓 ♯ ((𝑥 ∶ TNum) ~> (TNum ^ 𝑥))) ^ ◆
    assert(t10 == QType(TFun(Some("f"),Some("x"),
      QType(TNum,Qual(Set())),QType(TNum,Qual(Set("x")))),Qual(Set(Fresh()))))
  }

  test("subqual") {
    // x : Int^∅ ⊢ {x} <: ∅
    val Γ1 = TEnv.empty + ("x" -> TNum)
    assert(qualExposure(Qual.singleton("x"))(using Γ1) == Qual.untrack)
    assert(isSubqual(Qual.singleton("x"), Qual.untrack)(using Γ1))

    // y: Int^∅, x : Int^y ⊢ {x} <: ∅
    val Γ2 = TEnv.empty + ("x" -> (TNum ^ "y")) + ("y" -> TNum)
    assert(qualExposure(Qual.singleton("x"))(using Γ2) == Qual.untrack)
    assert(isSubqual(Qual.singleton("x"), Qual.untrack)(using Γ2))

    // y: Int^◆, x : Int^y ⊢ {x} <: {y} but not further
    val Γ3 = TEnv.empty + ("x" -> (TNum ^ "y")) + ("y" -> (TNum ^ ◆))
    assert(qualExposure(Qual(Set("x")))(using Γ3) == Qual.singleton("y"))
    assert(isSubqual(Qual.singleton("x"), Qual.singleton("y"))(using Γ3))

    val Γ4 = TEnv.empty + ("f" -> ((TNum ~> TNum) ^ ("x", "y")))
    assert(qualElemExposure(Qual(Set("z")), "f")(using Γ4) == Qual(Set("f", "z")))

    // f : (Int -> Int)^{x,y} ⊢ {z, x, y, f} <: {f, z}
    val Γ5 = Γ4 + ("z" -> TNum) + ("x" -> TNum) + ("y" -> TNum)
    assert(isSubqual(Qual(Set("z", "x", "y", "f")), Qual(Set("f", "z")))(using Γ5))

    // f : (Int -> Int)^{x,y} ⊢ {z, f, ◆} <: {z, f, ◆}
    assert(qualElemExposure(Qual(Set("z", ◆)), "f")(using Γ4) == Qual(Set("z", "f", ◆)))
    assert(isSubqual(Qual(Set("z", "f", ◆)), Qual(Set("z", "f", ◆)))(using Γ5))
    assert(isSubqual(Qual(Set("z", "x", "y", "f", ◆)), Qual(Set("z", "f", ◆)))(using Γ5))

    // f : (Int -> Int)^{x,y,◆} ⊢ {x, y, f} is not subtype of {f}
    val Γ6 = TEnv.empty + ("f" -> ((TNum ~> TNum) ^ ("x", "y", ◆))) + ("x" -> (TNum ^ ◆)) + ("y" -> (TNum ^ ◆))
    assert(qualExposure(Qual(Set("x", "y", "f")))(using Γ6) == Qual(Set("x", "y", "f")))
    assert(qualExposure(Qual(Set("f")))(using Γ6) == Qual.singleton("f"))
    assert(!isSubqual(Qual(Set("x", "y", "f")), Qual(Set("f")))(using Γ6))
  }

  test("var rename") {
    Counter.reset
    val t1: QType = (𝑓 ♯ ((𝑥 ∶ TNum) ~> (TNum ^ 𝑥))) ^ ◆
    assert(qtypeRename(t1, "f", "g") == t1)
    assert(qtypeRename(t1, "g", "h") == t1)
    assert(qtypeRename(t1, "x", "y") == t1)
    assert(qtypeRename(t1, "z", "y") == t1)

    //                         this f is free ↓ 
    val t2: QType = (𝑔 ♯ (t1 ~> (TRef(TNum) ^ 𝑓))) ^ (𝑦)
    Counter.reset
    assert(qtypeRename(t2, "f", "g") ==
      QType(TFun(Some("#0"),None,
        QType(TFun(Some("f"),Some("x"),
          QType(TNum,Qual(Set())),
          QType(TNum,Qual(Set("x")))),
          Qual(Set(Fresh()))),
        QType(TRef(TNum),Qual(Set("g")))),Qual(Set("y"))))

    //                              this y is free ↓
    val t3: QType = (𝑓 ♯ ((𝑥 ∶ TNum) ~> (TNum ^ (𝑥, 𝑦)))) ^ ◆
    Counter.reset
    assert(qtypeRename(t3, "y", "x") ==
      QType(TFun(Some("f"),Some("#0"),QType(TNum,Qual(Set())),QType(TNum,Qual(Set("#0", "x")))),Qual(Set(Fresh()))))

    //      (𝑔 ♯ ((𝑓 ♯ ((𝑥 ∶ TNum) ~> (TNum ^ 𝑥))) ^ ◆ ~> (TRef(TNum) ^ 𝑓))) ^ (𝑦)
    // then (𝑔' ♯ ((𝑓 ♯ ((𝑥 ∶ TNum) ~> (TNum ^ 𝑥))) ^ ◆ ~> (TRef(TNum) ^ 𝑔))) ^ (𝑦)
    // then (𝑔' ♯ ((𝑓' ♯ ((𝑥 ∶ TNum) ~> (TNum ^ 𝑥))) ^ ◆ ~> (TRef(TNum) ^ 𝑓))) ^ (𝑦)
    Counter.reset
    assert(qtypeRename(qtypeRename(t2, "f", "g"), "g", "f") ==
      QType(TFun(Some("#0"),None,
        QType(TFun(Some("#1"),Some("x"),
          QType(TNum,Qual(Set())),
          QType(TNum,Qual(Set("x")))),
          Qual(Set(Fresh()))),
        QType(TRef(TNum),Qual(Set("f")))),Qual(Set("y"))))
  }

  test("subtype") {
    // x : Int^∅ ⊢ Int^{x} <: Int^∅
    val Γ1 = TEnv.empty + ("x" -> TNum)
    assert(isSubQType(TNum ^ 𝑥, TNum)(using Γ1))


  }
}
