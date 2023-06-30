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

class QualFSubBasicTests extends AnyFunSuite {
  // These are tests copied from qualstlc tests, not involving any FSub feature.

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
    assert(t5 == TFun("AnnoFun#0","Arg#1",QType(TNum,Qual(Set("x", Fresh()))),QType(TNum,Qual(Set(Fresh())))))

    val t6: QType = (𝑓 ♯ t5) ^ ◆
    assert(t6 == QType(TFun("f", "Arg#1",
      QType(TNum,Qual(Set("x", Fresh()))),QType(TNum,Qual(Set(Fresh())))),Qual(Set(Fresh()))))

    val t7: QType = (𝑓 ♯ (TNum ~> TNum)) ^ ◆
    assert(t7 == QType(TFun("f","Arg#3",
      QType(TNum,Qual(Set())),QType(TNum,Qual(Set()))),Qual(Set(Fresh()))))

    val t8: QType = (𝑓 ♯ ((TNum ^ 𝑦) ~> (TNum ^ (𝑦, 𝑧)))) ^ ◆
    assert(t8 == QType(TFun("f", "Arg#5",
      QType(TNum,Qual(Set("y"))),QType(TNum,Qual(Set("y", "z")))),Qual(Set(Fresh()))))

    val t9: QType = (𝑓 ♯ ((𝑥 ⦂ (TNum ^ 𝑦)) ~> (TNum ^ 𝑥))) ^ ◆
    assert(t9 == QType(TFun("f","x",
      QType(TNum,Qual(Set("y"))),QType(TNum,Qual(Set("x")))),Qual(Set(Fresh()))))

    val t10: QType = (𝑓 ♯ ((𝑥 ⦂ TNum) ~> (TNum ^ 𝑥))) ^ ◆
    assert(t10 == QType(TFun("f","x",
      QType(TNum,Qual(Set())),QType(TNum,Qual(Set("x")))),Qual(Set(Fresh()))))
  }

  test("subqual") {
    import TypeSyntax.given_Conversion_Type_QType
    // x : Int^∅ ⊢ {x} <: ∅
    val Γ1: TEnv = TEnv.empty + ("x" -> TNum)
    assert(isSubqual(Qual.singleton("x"), Qual.untrack)(using Γ1))

    // y: Int^∅, x : Int^y ⊢ {x} <: ∅
    val Γ2: TEnv = TEnv.empty + ("x" -> (TNum ^ "y")) + ("y" -> TNum)
    assert(isSubqual(Qual.singleton("x"), Qual.untrack)(using Γ2))

    // y: Int^◆, x : Int^y ⊢ {x} <: {y} but not further
    val Γ3 = TEnv.empty + ("x" -> (TNum ^ "y")) + ("y" -> (TNum ^ ◆))
    assert(isSubqual(Qual.singleton("x"), Qual.singleton("y"))(using Γ3))

    val Γ4 = TEnv.empty + ("f" -> ((TNum ~> TNum) ^ ("x", "y")))

    // f : (Int -> Int)^{x,y} ⊢ {z, x, y, f} <: {f, z}
    val Γ5 = Γ4 + ("z" -> TNum) + ("x" -> TNum) + ("y" -> TNum)
    assert(isSubqual(Qual(Set("z", "x", "y", "f")), Qual(Set("f", "z")))(using Γ5))

    // f : (Int -> Int)^{x,y} ⊢ {z, f, ◆} <: {z, f, ◆}
    assert(isSubqual(Qual(Set("z", "f", ◆)), Qual(Set("z", "f", ◆)))(using Γ5))
    assert(isSubqual(Qual(Set("z", "x", "y", "f", ◆)), Qual(Set("z", "f", ◆)))(using Γ5))

    // f : (Int -> Int)^{x,y,◆} ⊢ {x, y, f} is not subtype of {f}
    val Γ6 = TEnv.empty + ("f" -> ((TNum ~> TNum) ^ ("x", "y", ◆))) + ("x" -> (TNum ^ ◆)) + ("y" -> (TNum ^ ◆))
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
    val e1 = let("x" ⦂ TNum ⇐ 42) { x + 1024 }
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

  test("polymorphic reachability") {
    val id0 = λ("x" ⦂ (TRef(TNum) ^ ◆)) { x }
    assert(topTypeCheck(id0) == (TFun("AnnoFun#0", "x", TRef(TNum)^ ◆, TRef(TNum)^"x") ^ ()))

    val id = λ("id", "x")("id"♯((TRef(TNum) ^ ◆) ~> (TRef(TNum)^"x"))) { x }
    assert(topTypeCheck(id) == (TFun("id", "x", TRef(TNum)^ ◆, TRef(TNum)^"x") ^ ()))

    assert(topTypeCheck(id(alloc(42))) == (TRef(TNum) ^ ◆))
    assert(topTypeCheck(id(EUntrackedAlloc(0))) == (TRef(TNum) ^ ()))

    val fakeid = λ("fakeid", "x")("fakeid"♯((TRef(TNum) ^ ◆) ~> (TRef(TNum)^"x"))) { alloc(42) }
    assert(intercept[NotSubQType] { topTypeCheck(fakeid) }
           == NotSubQType(TRef(TNum) ^ ◆, TRef(TNum) ^ "x")())

    val Γ1 = TEnv.empty + ("y" -> (TRef(TNum) ^ ◆))
    assert(typeCheck(id(y))(using Γ1) == (TRef(TNum) ^ "y"))

    val c1 = EVar("c1")
    val c2 = EVar("c2")
    val foo = EVar("foo")
    // Sec 2.2.3 from the paper
    def mkFoo(e: Expr): Expr = {
      let("foo" ⇐ (λ("foo", "x")("foo"♯((TRef(TNum)^(◆, "c1")) ~> (TRef(TNum)^"x"))) {
        let("_" ⇐ c1.assign(c1.deref + 1)) {
          x
        }
      })) {
        foo(e)
      }
    }

    val Γ2 = TEnv.empty + ("c1" -> (TRef(TNum)^ ◆)) + ("c2" -> (TRef(TNum)^ ◆))
    assert(typeCheck(mkFoo(c1))(using Γ2) == (TRef(TNum)^"c1"))
    assert(typeCheck(mkFoo(c2))(using Γ2) == (TRef(TNum)^"c2"))

    // Sec 2.2.6 from the paper:
    // qualifier-dependent application (non-fresh)
    val Γ3 = TEnv.empty + ("c" -> (TRef(TNum)^ ◆))
    val e2 =
      let("f" ⇐ (λ("f", "x")("f"♯((TRef(TNum)^"c") ~> ((("_" ⦂ TUnit) ~> (TRef(TNum)^"x"))^"x"))) {
        λ("_" ⦂ TUnit) { x }
      })) {
        EVar("f")(EVar("c"))
      }
    assert(typeCheck(e2)(using Γ3) == (TFun("AnnoFun#4", "_", TUnit, (TRef(TNum)^"c"))^"c"))
  }

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
    assert(intercept[DeepDependency] { topTypeCheck(e3) } ==
      DeepDependency(TFun("f", "z", TNum^(), TRef(TNum)^"x"), "x"))

    val e3_let =
      let("x" ⇐ alloc(0)) {
        λ("f", "z")("f"♯(TNum ~> (TRef(TNum) ^ "x"))) { x }
      }
    assert(intercept[DeepDependency] { topTypeCheck(e3_let) } ==
      DeepDependency(TFun("f", "z", TNum, TRef(TNum)^"x"), "x"))

    val e3_alias = {
      val f = λ("x" ⦂ (TRef(TNum) ^ ◆)) {
        let ("y" ⇐ x) {
          λ("f", "z")("f"♯(TNum ~> (TRef(TNum) ^ "y"))) { y }
        }
      }
      val arg = alloc(3)
      f(arg)
    }
    assert(intercept[DeepDependency] { topTypeCheck(e3_alias) } ==
      DeepDependency(TFun("f", "z", TNum^(), TRef(TNum)^"x"), "x"))

    // must upcast return qualifier to self-ref `f`
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

  test("separation") {
    val c1 = EVar("c1")
    val c2 = EVar("c2")

    // Not allowing argument that has overlapped with c1
    val e1 =
      let("c1" ⇐ alloc(3)) {
        let("f" ⇐ (λ("f", "x")("f"♯((TRef(TNum)^ ◆) ~> TNum)) { x.deref + c1.deref })) {
          EVar("f").applyFresh(c1)
        }
      }
    assert(intercept[NonOverlap] { topTypeCheck(e1) } ==
      NonOverlap(Qual(Set()), Qual(Set("c1"))))

    // permitted overlap
    val e2 =
      let("c1" ⇐ alloc(3)) {
        let("f" ⇐ (λ("f", "x")("f"♯((TRef(TNum)^(◆, "c1")) ~> TNum)) { x.deref + c1.deref })) {
          EVar("f").applyFresh(c1)
        }
      }
    assert(topTypeCheck(e2) == (TNum ^ ()))

    // Sec 2.2.5 from the paper:
    // c1, c2 are aliased, therefore f(c2) not allowed
    val e3 =
      let("c1" ⇐ alloc(3)) {
        let("c2" ⇐ c1) {
          let("f" ⇐ (λ("f", "x")("f"♯((TRef(TNum)^(◆)) ~> TNum)) { x.deref + c1.deref })) {
            EVar("f").applyFresh(c2)
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
            EVar("f").applyFresh(c2)
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
            EVar("f").applyFresh(c1)
          }
        }
      }
    assert(topTypeCheck(e5) == (TNum^()))

    val id = λ("id", "x")("id"♯((TRef(TNum) ^ ◆) ~> (TRef(TNum)^"x"))) { x }
    def make_e6(allow: String, use: String, arg: String) =
      let("id" ⇐ id) {
        let("x" ⇐ alloc(0)) {
          let("y" ⇐ EVar("id")(EVar("x"))) {
            let("f" ⇐ (λ("f", "z")("f"♯((TRef(TNum)^(◆, allow)) ~> TNum)) {
              EVar(use).deref + EVar("z").deref
            })) {
              EVar("f").applyFresh(EVar(arg))
            }
          }
        }
      }
    assert(topTypeCheck(make_e6("x", "x", "x")) == (TNum^()))
    assert(topTypeCheck(make_e6("x", "x", "y")) == (TNum^()))
    assert(topTypeCheck(make_e6("x", "y", "x")) == (TNum^()))
    assert(topTypeCheck(make_e6("x", "y", "y")) == (TNum^()))
    assert(topTypeCheck(make_e6("y", "x", "x")) == (TNum^()))
    assert(topTypeCheck(make_e6("y", "x", "y")) == (TNum^()))
    assert(topTypeCheck(make_e6("y", "y", "x")) == (TNum^()))
    assert(topTypeCheck(make_e6("y", "y", "y")) == (TNum^()))

    def make_e7(use: String, arg: String) =
      let("id" ⇐ id) {
        let("x" ⇐ alloc(0)) {
          let("y" ⇐ EVar("id")(EVar("x"))) {
            let("f" ⇐ (λ("f", "z")("f"♯((TRef(TNum)^(◆)) ~> TNum)) {
              EVar(use).deref + EVar("z").deref
            })) {
              EVar("f").applyFresh(EVar(arg))
            }
          }
        }
      }
    intercept[NonOverlap] { topTypeCheck(make_e7("x", "x")) }
    intercept[NonOverlap] { topTypeCheck(make_e7("x", "y")) }
    intercept[NonOverlap] { topTypeCheck(make_e7("y", "x")) }
    // TODO: what's the right error message here?
    intercept[NonOverlap] { topTypeCheck(make_e7("y", "y")) }
  }

  test("var rename") {
    Counter.reset
    val t1: QType = (𝑓 ♯ ((𝑥 ⦂ TNum) ~> (TNum ^ 𝑥))) ^ ◆
    assert(qtypeRename(t1, "f", "g") == t1)
    assert(qtypeRename(t1, "g", "h") == t1)
    assert(qtypeRename(t1, "x", "y") == t1)
    assert(qtypeRename(t1, "z", "y") == t1)

    //                         this f is free ↓
    val t2: QType = (𝑔 ♯ (t1 ~> (TRef(TNum) ^ 𝑓))) ^ (𝑦)
    Counter.reset
    assert(qtypeRename(t2, "f", "g") ==
      QType(TFun("g#0","Arg#2",
        QType(TFun("f","x",
          QType(TNum,Qual(Set())),
          QType(TNum,Qual(Set("x")))),
          Qual(Set(Fresh()))),
        QType(TRef(TNum),Qual(Set("g")))),Qual(Set("y"))))

    //                              this y is free ↓
    val t3: QType = (𝑓 ♯ ((𝑥 ⦂ TNum) ~> (TNum ^ (𝑥, 𝑦)))) ^ ◆
    Counter.reset
    assert(qtypeRename(t3, "y", "x") ==
      QType(TFun("f","x#0",QType(TNum,Qual(Set())),QType(TNum,Qual(Set("x#0", "x")))),Qual(Set(Fresh()))))

    //      (𝑔 ♯ ((𝑓 ♯ ((𝑥 ∶ TNum) ~> (TNum ^ 𝑥))) ^ ◆ ~> (TRef(TNum) ^ 𝑓))) ^ (𝑦)
    // then (𝑔' ♯ ((𝑓 ♯ ((𝑥 ∶ TNum) ~> (TNum ^ 𝑥))) ^ ◆ ~> (TRef(TNum) ^ 𝑔))) ^ (𝑦)
    // then (𝑔' ♯ ((𝑓' ♯ ((𝑥 ∶ TNum) ~> (TNum ^ 𝑥))) ^ ◆ ~> (TRef(TNum) ^ 𝑓))) ^ (𝑦)
    Counter.reset
    assert(qtypeRename(qtypeRename(t2, "f", "g"), "g", "f") ==
      QType(TFun("g#0","Arg#2",
        QType(TFun("f#1","x",
          QType(TNum,Qual(Set())),
          QType(TNum,Qual(Set("x")))),
          Qual(Set(Fresh()))),
        QType(TRef(TNum),Qual(Set("f")))),Qual(Set("y"))))

    /*
     Env = x: Ref^◆
     def f(y: Ref^x): (x: Ref) => Ref^y = \z.x
     f(x) // (x1: Ref) => Ref^x = \z.x
     */
    val Γ = TEnv.empty + ("x" -> (TRef(TNum) ^ ◆))
    val g_type = "g"♯( ("x" ⦂ (TRef(TNum) ^ ◆)) ~> (TRef(TNum)^"y") )
    val g_body = EVar("y")
    val f_type = "f"♯( ("y" ⦂ (TRef(TNum) ^ "x")) ~> ( g_type ^ "y" ) )
    val f_body = λ("g", "z")(g_type) { g_body }
    val f_def = λ("f", "y")(f_type) { f_body }

    assert(typeCheck(f_def)(using Γ) ==
      (TFun("f", "y",
        TRef(TNum)^"x",
        TFun("g", "x", TRef(TNum) ^ ◆, TRef(TNum)^"y")^"y") ^ "x"))

    val e = let ("f" ⇐ f_def) {
      EVar("f")(EVar("x"))
    }
    Counter.reset
    // Check capture-free substitution of qualifiers
    assert(typeCheck(e)(using Γ) ==
      (TFun("g", "x#1", TRef(TNum) ^ ◆,TRef(TNum)^"x") ^ "x"))
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

    Counter.reset
    val t1: QType = (𝑓 ♯ ((𝑥 ⦂ TNum) ~> (TNum ^ 𝑓))) ^ ◆
    val t2: QType = (𝑔 ♯ ((𝑥 ⦂ TNum) ~> (TNum ^ 𝑔))) ^ ◆
    assert(isSubQType(t1, t2)(using TEnv.empty))

    Counter.reset
    // let f: g(y: Ref(Num)◆ => Ref(Num)^{y, g}) =
    //   (λf(x).x): f(x: Ref(Num)◆ => Ref(Num)^{x, f})
    // in f
    val e = let("f" ⦂ ("g"♯( ("y" ⦂ (TRef(TNum)^(◆))) ~> (TRef(TNum)^("y", "g"))))  ⇐
      (λ("f", "x")("f"♯((TRef(TNum)^(◆)) ~> (TRef(TNum)^("x", "f")))) { EVar("x") })) { EVar("f") }
    assert(topTypeCheck(e) == (("g"♯( ("y" ⦂ (TRef(TNum)^(◆))) ~> (TRef(TNum)^("y", "g"))))^()))
  }

  test("saturation") {
    val Γ1 = TEnv.empty + ("f" -> (TNum ^ ("x", "y"))) + ("x" -> (TNum ^ ◆)) + ("y" -> (TNum ^ ◆))
    assert(Qual.singleton("f").saturated(using Γ1) == Set("x", "y", "f"))

    val Γ2 = TEnv.empty + ("f" -> (TNum ^ ("x", "y"))) + ("x" -> (TNum ^ "y")) + ("y" -> (TNum ^ ◆))
    assert(Qual.singleton("f").saturated(using Γ2) == Set("x", "y", "f"))

    val Γ3 = TEnv.empty + ("a" -> (TNum ^ ())) + ("z" -> (TNum ^ "a")) + ("x" -> (TNum ^ "z")) + ("y" -> (TNum ^ "x"))
    assert(Qual.singleton("x").saturated(using Γ3) == Set("x", "z", "a"))
  }

  test("free-var-in-type") {
    /* val x = alloc(0)
     * def f(g: (Int -> Ref[Int]^x)^x): Ref[Int]^x = g(0)
     * f(λ(y).x)
     */
    // Permitting g and f have overlap x
    val let0 =
      let("x" ⇐ alloc(0)) {
        let("f" ⇐ λ("f", ("g" ⦂ ((TNum ~> (TRef(TNum) ^ "x")) ^ "x")), (TRef(TNum) ^ "x")) { EVar("g")(0) }) {
          EVar("f")( λ("y" ⦂ TNum) { EVar("x") } )
        }
      }
    println(topTypeCheck(let0))

    /* val x = alloc(0)
     * def f(g: (Int -> Ref[Int]^x)^∅): Ref[Int]^x = g(0)
     * f(λ(y).x)
     */
    // Do not permit any overlap between g and f
    val let1 =
      let("x" ⇐ alloc(0)) {
        let("f" ⇐ λ("f", ("g" ⦂ ((TNum ~> (TRef(TNum) ^ "x")) ^ ())), (TRef(TNum) ^ "x")) { EVar("g")(0) }) {
          EVar("f").applyFresh( λ("y" ⦂ TNum) { EVar("x") } )
        }
      }
    intercept[NonOverlap] { topTypeCheck(let1) } // errors as expected (do we want to allow it?)
  }
}

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
