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
    val e = λ("f", "x")(TNum -> (TNum -> TNum)) {
      λ("g", "n")(TNum -> TNum) {
        ite (n === 0) {
          ENum(1)
        }{
          x * f(x)(n - 1)
        }
      }
    }
    assert(typeEq(topTypeCheck(e), TFun(TNum, TFun(TNum, TNum))))
  }
}
