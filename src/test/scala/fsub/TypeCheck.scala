package diamond.fsub

import org.scalatest.funsuite.AnyFunSuite

import Type._
import Expr._
import TypeSyntax._
import ExprSyntax._

class FSubTests extends AnyFunSuite {
  test("eq") {
    given Γ: TEnv = TEnv.empty + ("x" <∶ TTop) + ("y" <∶ TTop)

    assert(typeEq(TVar("x"), TVar("x")))

    assert(!typeEq(TVar("x"), TVar("y")))

    assert(typeEq(TFun(TNum, TNum), TFun(TNum, TNum)))

    assert(typeEq(
      TForall("x", TTop, TFun(TVar("x"), TVar("x"))),
      TForall("y", TTop, TFun(TVar("y"), TVar("y")))))

    assert(typeEq(
      TForall("x", TTop, TFun(TVar("x"), TVar("y"))),
      TForall("x", TTop, TFun(TVar("x"), TVar("y")))))

    assert(typeEq(
      TForall("x", TTop, TForall("y", TTop, TFun(TVar("x"), TVar("y")))),
      TForall("y", TTop, TForall("x", TTop, TFun(TVar("y"), TVar("x"))))))

    assert(!typeEq(
      TForall("x", TTop, TForall("y", TTop, TFun(TVar("y"), TVar("x")))),
      TForall("y", TTop, TForall("x", TTop, TFun(TVar("y"), TVar("x"))))))

    assert(typeEq(
      TForall("x", TTop, TRef(TVar("x"))),
      TForall("z", TTop, TRef(TVar("z")))))
  }

  test("exposure") {
    given Γ: TEnv = TEnv.empty + ("y" <∶ (TNum ~> TNum)) + ("z" <∶ TVar("y")) + ("w" <∶ TVar("z"))

    assert(typeExposure(TVar("z")) == TFun(TNum,TNum))
    assert(typeExposure(TVar("w")) == TFun(TNum,TNum))
  }

  test("lambda") {
    given Γ: TEnv = TEnv.empty
    val e1 = λ("f", "x")(TNum ~> (TNum ~> TNum)) {
      λ("g", "n")(TNum ~> TNum) {
        ite (n === 0) {
          ENum(1)
        }{
          x * f(x)(n - 1)
        }
      }
    }
    assert(typeEq(topTypeCheck(e1), TFun(TNum, TFun(TNum, TNum))))

    val e2 = e1(ENum(5))(ENum(3))
    assert(topTypeCheck(e2) == TNum)

    val e3 = Λ("α"){ λ("id", "x")(α ~> α) { x } }
    assert(typeEq(topTypeCheck(e3), TForall("α", TTop, TFun(TVar("α"), TVar("α")))))

    val e4 = e3(TNum)
    assert(topTypeCheck(e4) == TFun(TNum,TNum))
  }

  test("church bool") {
    given Γ: TEnv = TEnv.empty
    val TBool = ∀("α") { α ~> (α ~> α) }
    val True = Λ("α") { λ("_", "x")(α ~> (α ~> α)) { λ("_", "y")(α ~> α) { x } } }
    val False = Λ("α") { λ("_", "x")(α ~> (α ~> α)) { λ("_", "y")(α ~> α) { y } } }
    val And = λ("_", "x")(TBool ~> (TBool ~> TBool)) {
      λ("_", "y")(TBool ~> TBool) { x(TBool)(y)(False) }
    }
    val Bool2Num = λ("_", "x")(TBool ~> TNum) { x(TNum)(ENum(1))(ENum(0)) }

    assert(typeEq(topTypeCheck(True), TBool))
    assert(typeEq(topTypeCheck(False), TBool))
    assert(typeEq(topTypeCheck(And), TBool ~> (TBool ~> TBool)))
    assert(typeEq(topTypeCheck(Bool2Num), TBool ~> TNum))

    val t = EVar("t")
    val f = EVar("f")
    val and = EVar("and")
    val e1 =
      let("t" ⇐ True) {
        let("f" ⇐ False) {
          let("and" ⇐ And) {
            Bool2Num(and(t)(f))
          }
        }
      }
    assert(topTypeCheck(e1) == TNum)
    val e2 =
      let("t" ⇐ True) {
        let("f" ⇐ False) {
          let("and" ⇐ And) {
            Bool2Num(and(t)(t))
          }
        }
      }
    assert(topTypeCheck(e2) == TNum)
  }

  test("church pair") {
    given Γ: TEnv = TEnv.empty
    def TPair(t1: Type, t2: Type): Type = ∀("γ") { (t1 ~> (t2 ~> γ)) ~> γ }
    val mkPair = Λ("α") { Λ("β") { λ("x" ∶ α) {
      λ("y" ∶ β) { Λ("γ") { λ("f" ∶ (α ~> (β ~> γ))) { f(x)(y) } } } } } }
    val fst = Λ("α") { Λ("β") { λ("x" ∶ TPair(α, β)) { x(α)(λ("a" ∶ α) { λ("b" ∶ β) { EVar("a") } }) } } }
    val snd = Λ("α") { Λ("β") { λ("x" ∶ TPair(α, β)) { x(β)(λ("a" ∶ α) { λ("b" ∶ β) { EVar("b") } }) } } }

    val p1 = mkPair(TNum)(TNum)(1)(2)
    assert(typeEq(
      topTypeCheck(p1),
      TPair(TNum, TNum)))
    assert(typeEq(
      topTypeCheck(p1),
      TForall("α", TTop, TFun(TFun(TNum,TFun(TNum,α)),α))))
    assert(topTypeCheck(p1(TNum)) == TFun(TFun(TNum,TFun(TNum,TNum)),TNum))
    assert(topTypeCheck(fst(TNum)(TNum)(p1)) == TNum)

    val p2 = mkPair(TNum)(TPair(TBool, TBool))(1)(mkPair(TBool)(TBool)(EBool(true))(EBool(false)))
    assert(typeEq(
      topTypeCheck(p2),
      TPair(TNum, TPair(TBool, TBool))))
    assert(topTypeCheck(snd(TNum)(TPair(TBool, TBool))(p2)) == TPair(TBool, TBool))
  }
}
