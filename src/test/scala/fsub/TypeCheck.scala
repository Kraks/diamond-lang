package diamond.fsub

import org.scalatest.funsuite.AnyFunSuite

import Type._
import Expr._
import TypeSyntax._
import ExprSyntax._

class FSubTest extends AnyFunSuite {
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
}
