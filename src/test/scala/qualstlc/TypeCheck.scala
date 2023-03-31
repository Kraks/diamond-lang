package diamond.qualstlc

import org.scalatest.funsuite.AnyFunSuite

import diamond._
import Type._
import Expr._
import TypeSyntax._
import ExprSyntax._

import TypeSyntax.given_Conversion_Type_QType
import ExprSyntax.given_Conversion_Int_ENum

class Playground extends AnyFunSuite {
  test("playground") {
  }
}

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

    val Γ10 = TEnv.empty + ("x1" -> (TNum ^ ◆)) + ("x2" -> (TNum ^ ◆)) + ("x3" -> (TNum ^ ◆))
        + ("f" -> ((TNum ~> TNum) ^ ("x1", "x2")))
        + ("g" -> ((TNum ~> TNum) ^ ("x1", "x3")))
    assert(qualExposure(Qual(Set("x1", "x2", "x3", "f", "g")))(using Γ10) == Qual(Set("f", "g")))
    assert(isSubqual(Qual(Set("x1", "x2", "x3", "f", "g")), Qual(Set("f", "g")))(using Γ10))
    assert(!isSubqual(Qual(Set("f")), Qual(Set("g")))(using Γ10))
    assert(!isSubqual(Qual(Set("g")), Qual(Set("f")))(using Γ10))
    assert(isSubqual(Qual(Set("x1", "x3", "g")), Qual(Set("g")))(using Γ10))
    assert(isSubqual(Qual(Set("x1", "x3")), Qual(Set("g")))(using Γ10))
    assert(isSubqual(Qual(Set("x1", "x2")), Qual(Set("f")))(using Γ10))
    assert(isSubqual(Qual(Set("x1", "x2", "x3")), Qual(Set("f", "g")))(using Γ10))
  }

  test("alloc") {
    assert(topTypeCheck(alloc(42)) == (TRef(TNum) ^ ◆))

    val Γ1 = TEnv.empty + ("x" -> TNum)
    assert(typeCheck(alloc(x))(using Γ1) == (TRef(TNum) ^ ◆))

    val Γ2 = TEnv.empty + ("x" -> (TNum ^ ◆))
    val thrown = intercept[QualMismatch] {
      typeCheck(alloc(x))(using Γ2)
    }
    assert(thrown == QualMismatch(Qual(Set("x")), Qual(Set())))
  }

  test("let") {
    val e1 = let("x" ∶ TNum ⇐ 42) { x + 1024 }
    assert(topTypeCheck(e1) == (TNum ^ ()))

    val e2 = let("x" ⇐ 42) { x + 1024 }
    assert(topTypeCheck(e2) == (TNum ^ ()))

    val e3 = let("x" ⇐ alloc(3)) { x }
    assert(topTypeCheck(e3) == (TRef(TNum) ^ ◆))

    val e4 = let("x" ⇐ alloc(3)) { x.deref }
    assert(topTypeCheck(e4) == (TNum ^ ()))

    val e5 =
      let("x" ⇐ alloc(3)) {
        let("_" ⇐ x.assign(4)) {
          x.deref
        }
      }
    assert(topTypeCheck(e5) == (TNum ^ ()))

    val e6 =
      let("x" ⇐ alloc(3)) {
        let("y" ⇐ x) { y }
      }
    assert(topTypeCheck(e6) == (TRef(TNum) ^ ◆))
  }

  test("id") {
    val id0 = λ("x" ∶ (TRef(TNum) ^ ◆)) { x }
    assert(topTypeCheck(id0) == (TFun(None, Some("x"), TRef(TNum)^ ◆, TRef(TNum)^"x") ^ ()))

    val id = λ("id", "x")("id"♯((TRef(TNum) ^ ◆) ~> (TRef(TNum)^"x"))) { x }
    assert(topTypeCheck(id) == (TFun(Some("id"), Some("x"), TRef(TNum)^ ◆, TRef(TNum)^"x") ^ ()))

    val e1 = id(alloc(42))
    assert(topTypeCheck(e1) == (TRef(TNum) ^ ◆))

    val fakeid = λ("fakeid", "x")("fakeid"♯((TRef(TNum) ^ ◆) ~> (TRef(TNum)^"x"))) { alloc(42) }
    val thrown = intercept[NotSubQType] {
      topTypeCheck(fakeid)
    }
    assert(thrown == NotSubQType(TRef(TNum) ^ ◆, TRef(TNum) ^ "x"))

    val Γ1 = TEnv.empty + ("y" -> (TRef(TNum) ^ ◆))
    assert(typeCheck(id(y))(using Γ1) == (TRef(TNum) ^ "y"))

    // TODO: this can be checked in the polymorphic version
    //val e2 = id(42)
    //println(topTypeCheck(e2))
  }

  test("escaping closures") {
    val e1 = 
      let("x" ⇐ alloc(3)) {
        λ("f", "z")("f"♯(TNum ~> TNum)) { x.deref }
      }
    assert(topTypeCheck(e1) == (TFun(Some("f"), Some("z"), TNum^(), TNum^()) ^ ◆))

    val e2 =
      (λ("x" ∶ (TRef(TNum) ^ ◆)) { λ("f", "z")("f"♯(TNum ~> TNum)) { x.deref } })(alloc(3))
    //println(e2)
    assert(topTypeCheck(e2) == (TFun(Some("f"), Some("z"), TNum^(), TNum^()) ^ ◆))

    val e3 =
      (λ("x" ∶ (TRef(TNum) ^ ◆)) { λ("f", "z")("f"♯(TNum ~> (TRef(TNum) ^ "x"))) { x } })(alloc(3))
    assert(intercept[DeepDependency] { topTypeCheck(e3) } ==
      DeepDependency(TFun(Some("f"), Some("z"), TNum^(), TRef(TNum)^"x"), "x"))

    val e4 =
      (λ("x" ∶ (TRef(TNum) ^ ◆)) { λ("f", "z")("f"♯(TNum ~> (TRef(TNum) ^ "f"))) { x } })(alloc(3))
    //println(e4)
    assert(topTypeCheck(e4) == (TFun(Some("f"), Some("z"), TNum^(), TRef(TNum)^"f") ^ ◆))
  }

  test("separation") {
    val c1 = EVar("c1")
    val c2 = EVar("c2")

    // Not allowing argument that has overlapped with c1
    val e1 =
      let("c1" ⇐ alloc(3)) {
        let("f" ⇐ (λ("f", "x")("f"♯((TRef(TNum)^ ◆) ~> TNum)) { x.deref + c1.deref })) {
          EVar("f")(c1)
        }
      }
    assert(intercept[NonOverlap] { topTypeCheck(e1) } ==
      NonOverlap(Qual(Set()), Qual(Set("c1"))))

    // permitted overlap
    val e2 =
      let("c1" ⇐ alloc(3)) {
        let("f" ⇐ (λ("f", "x")("f"♯((TRef(TNum)^(◆, "c1")) ~> TNum)) { x.deref + c1.deref })) {
          EVar("f")(c1)
        }
      }
    assert(topTypeCheck(e2) == (TNum ^ ()))

    // c1, c2 are aliased, therefore f(c2) not allowed
    val e3 =
      let("c1" ⇐ alloc(3)) {
        let("c2" ⇐ c1) {
          let("f" ⇐ (λ("f", "x")("f"♯((TRef(TNum)^(◆)) ~> TNum)) { x.deref + c1.deref })) {
            EVar("f")(c2)
          }
        }
      }
    assert(intercept[NonOverlap] { topTypeCheck(e3) } ==
      NonOverlap(Qual(Set()), Qual(Set("c1"))))

    // permitted overlap with c2, function body captures c1
    // applying with c2, okay
    val e4 =
      let("c1" ⇐ alloc(3)) {
        let("c2" ⇐ c1) {
          let("f" ⇐ (λ("f", "x")("f"♯((TRef(TNum)^(◆, "c2")) ~> TNum)) { x.deref + c1.deref })) {
            EVar("f")(c2)
          }
        }
      }
    assert(topTypeCheck(e4) == (TNum^()))

    // permitted overlap with c2, function body captures c1
    // applying with c1, okay
    val e5 =
      let("c1" ⇐ alloc(3)) {
        let("c2" ⇐ c1) {
          let("f" ⇐ (λ("f", "x")("f"♯((TRef(TNum)^(◆, "c2")) ~> TNum)) { x.deref + c1.deref })) {
            EVar("f")(c1)
          }
        }
      }
    assert(topTypeCheck(e5) == (TNum^()))
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
