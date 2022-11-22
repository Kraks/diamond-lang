package diamond

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
}
