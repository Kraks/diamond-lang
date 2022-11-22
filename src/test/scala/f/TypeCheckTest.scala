package diamond.f

import org.scalatest.funsuite.AnyFunSuite

import Type._
import Expr._
import Value._

import TypeSyntax._
import ExprSyntax._

class TypeCheckTests extends TestBase {
  test("type eq") {
    assert(typeEq(TVar("x"), TVar("x")))
    assert(!typeEq(TVar("x"), TVar("y")))

    assert(typeEq(TFun(TNum, TNum), TFun(TNum, TNum)))

    assert(typeEq(
      TForall("x", TFun(TVar("x"), TVar("x"))),
      TForall("y", TFun(TVar("y"), TVar("y")))))

    assert(typeEq(
      TForall("x", TFun(TVar("x"), TVar("y"))),
      TForall("x", TFun(TVar("x"), TVar("y")))))

    assert(typeEq(
      TForall("x", TForall("y", TFun(TVar("x"), TVar("y")))),
      TForall("y", TForall("x", TFun(TVar("y"), TVar("x"))))))

    assert(!typeEq(
      TForall("x", TForall("y", TFun(TVar("y"), TVar("x")))),
      TForall("y", TForall("x", TFun(TVar("y"), TVar("x"))))))

    assert(typeEq(
      TForall("x", TRef(TVar("x"))),
      TForall("z", TRef(TVar("z")))))
  }

  test("typing lambda") {
    val e1 = λ("f", "x")(TNum -> (TNum -> TNum)) {
      λ("g", "n")(TNum -> TNum) {
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

    val e3 = Λ("α"){ λ("id", "x")(α -> α) { x } }
    assert(typeEq(topTypeCheck(e3), TForall("α", TFun(TVar("α"), TVar("α")))))

    val e4 = e3(TNum)
    assert(topTypeCheck(e4) == TFun(TNum,TNum))
  }

  test("typing let") {
    val e1 = let("x" ∶ TNum ⇐ ENum(42)) { x + 1024 }
    assert(topTypeCheck(e1) == TNum)

    val e2 = let("x" ⇐ ENum(42)) { x + 1024 }
    assert(topTypeCheck(e2) == TNum)
  }

  test("typing church-bool") {
    val TBool = ∀("α") { α -> (α -> α) }
    val True = Λ("α") { λ("_", "x")(α -> (α -> α)) { λ("_", "y")(α -> α) { x } } }
    val False = Λ("α") { λ("_", "x")(α -> (α -> α)) { λ("_", "y")(α -> α) { y } } }
    val And = λ("_", "x")(TBool -> (TBool -> TBool)) {
      λ("_", "y")(TBool -> TBool) { x(TBool)(y)(False) }
    }
    val Bool2Num = λ("_", "x")(TBool -> TNum) { x(TNum)(ENum(1))(ENum(0)) }

    assert(typeEq(topTypeCheck(True), TBool))
    assert(typeEq(topTypeCheck(False), TBool))
    assert(typeEq(topTypeCheck(And), TBool -> (TBool -> TBool)))
    assert(typeEq(topTypeCheck(Bool2Num), TBool -> TNum))

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
}
