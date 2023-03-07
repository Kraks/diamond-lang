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

    // a: Int, z: Int^a, x: Int^z, y: Int^x
    val Γ7 = TEnv.empty + ("a" -> (TNum ^ ())) + ("z" -> (TNum ^ "a")) + ("x" -> (TNum ^ "z")) + ("y" -> (TNum ^ "x"))
    assert(isSubqual(Qual(Set("y")), Qual(Set("x")))(using Γ7))
    assert(isSubqual(Qual(Set("y")), Qual(Set("z")))(using Γ7))
    assert(isSubqual(Qual(Set("y")), Qual(Set("a")))(using Γ7))
    assert(isSubqual(Qual(Set("y")), Qual(Set()))(using Γ7))
    assert(isSubqual(Qual(Set("x")), Qual(Set("z")))(using Γ7))
    assert(isSubqual(Qual(Set("x")), Qual(Set("a")))(using Γ7))
    assert(isSubqual(Qual(Set("x")), Qual(Set()))(using Γ7))
    assert(isSubqual(Qual(Set("z")), Qual(Set("a")))(using Γ7))
    assert(isSubqual(Qual(Set("z")), Qual(Set()))(using Γ7))

    val Γ8 = TEnv.empty + ("a" -> (TNum ^ ("b", ◆))) + ("b" -> TNum) + ("c" -> (TNum ^ "d")) + ("d" -> TNum)
    // a: Int^{b, ◆}, b: Int^∅, c: Int^d, d: Int^∅ ⊢ {a, c} <: {a}
    assert(isSubqual(Qual(Set("a", "c")), Qual(Set("a")))(using Γ8))
    // a: Int^{b, ◆}, b: Int^∅, c: Int^d, d: Int^∅ ⊢ {a, c} ¬<: ∅
    assert(!isSubqual(Qual(Set("a", "c")), Qual(Set()))(using Γ8))
    // a: Int^{b, ◆}, b: Int^∅, c: Int^d, d: Int^∅ ⊢ {a, c} ¬<: {◆}
    assert(!isSubqual(Qual(Set("a", "c")), Qual(Set(◆)))(using Γ8))

    val Γ9 = TEnv.empty + ("a" -> TNum) + ("b" -> TNum)
    /*
     Γ = a: Int^∅, b: Int^∅
     a: Int^∅ ∈ Γ
     ------------- [Q-Var]     ------------- [Q-Sub]
     Γ ⊢ {a} <: ∅             Γ ⊢ ∅ <: {b}
     ------------------------------------------- [Q-Trans]
                  Γ ⊢ {a} <: {b}
    */
    assert(isSubqual(Qual(Set("a")), Qual(Set("b")))(using Γ9))
    // a: Int^∅, b: Int^∅ ⊢ {b} <: {a}
    assert(isSubqual(Qual(Set("b")), Qual(Set("a")))(using Γ9))

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
    // x : Int^∅ ⊢ Int^x <: Int^∅
    val Γ1 = TEnv.empty + ("x" -> TNum)
    assert(isSubQType(TNum ^ 𝑥, TNum)(using Γ1))

    val Γ2 = TEnv.empty + ("y" -> (TRef(TNum) ^ ◆))
    // y : Ref[Int]^◆ ⊢ Ref[Int]^y <: Ref[Int]^y
    assert(isSubQType(TRef(TNum) ^ 𝑦, TRef(TNum) ^ 𝑦)(using Γ2))
    // y : Ref[Int]^◆ ⊢ Ref[Int]^y is not subtype of Ref[Int]^◆
    assert(!isSubQType(TRef(TNum) ^ 𝑦, TRef(TNum) ^ ◆)(using Γ2))
  }

  test("saturation") {
    val Γ1 = TEnv.empty + ("f" -> (TNum ^ ("x", "y"))) + ("x" -> (TNum ^ ◆)) + ("y" -> (TNum ^ ◆))
    assert(Qual.singleton("f").saturated(using Γ1) == Set("x", "y", "f"))

    val Γ2 = TEnv.empty + ("f" -> (TNum ^ ("x", "y"))) + ("x" -> (TNum ^ "y")) + ("y" -> (TNum ^ ◆))
    assert(Qual.singleton("f").saturated(using Γ2) == Set("x", "y", "f"))

    val Γ3 = TEnv.empty + ("a" -> (TNum ^ ())) + ("z" -> (TNum ^ "a")) + ("x" -> (TNum ^ "z")) + ("y" -> (TNum ^ "x"))
    assert(Qual.singleton("x").saturated(using Γ3) == Set("x", "z", "a"))
  }
}
