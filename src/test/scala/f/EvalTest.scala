package diamond.f

import org.scalatest.funsuite.AnyFunSuite

import Type._
import Expr._
import Value._

class TestBase extends AnyFunSuite {
  val ρ0 = Env.empty
  val σ0 = Store.empty
  val Γ0 = TEnv.empty
}

class EvalTests extends TestBase {
  import ExprSyntax._
  import TypeSyntax._

  test("primitive unit value") {
    val (v, σ) = topEval(EUnit)
    assert(v == VUnit)
  }

  test("primitive num value") {
    val (v, σ) = topEval(ENum(1024))
    assert(v == VNum(1024))
  }

  test("primitive bool value") {
    val (v, σ) = topEval(EBool(false))
    assert(v == VBool(false))
  }

  test("binary op") {
    // ((16/2 - 3) * 5) + 100
    val (v, σ) = topEval(
      EBinOp("+",
        EBinOp("*",
          EBinOp("-",
            EBinOp("/", ENum(16), ENum(2)),
            ENum(3)),
          ENum(5)),
        ENum(100)))
    assert(v == VNum(125))
  }

  test("application") {
    // λf(x).x + 3
    val e1 = ELam("f", "x", EBinOp("+", x, ENum(3)), TFun(TNum, TNum))
    val e2 = EBinOp("+", ENum(4), ENum(5))
    // (λf(x).x + 3)(4 + 5)
    val (v, σ) = topEval(EApp(e1, e2))
    assert(v == VNum(12))
  }

  test("recursive function - power") {
    // λf(x).λg(n). if (n == 0) 1 else x * f(x)(n-1)
    val e1 =
      ELam("f", "x",
        ELam("g", "n",
          ECond(EBinOp("=", n, ENum(0)),
            ENum(1),
            EBinOp("*", x, EApp(EApp(f, x), EBinOp("-", n, ENum(1))))),
          TFun(TNum, TNum)),
        TFun(TNum, TFun(TNum, TNum)))
    // power(2, 5)
    val app = EApp(EApp(e1, ENum(2)), ENum(5))
    val (v, σ) = topEval(app)
    assert(v == VNum(32))
  }

  test("church bool") {
    val TBool = ∀("α") { α -> (α -> α) }
    val True = Λ("α") { λ("_", "x")(α -> (α -> α)) { λ("_", "y")(α -> α) { x } } }
    val False = Λ("α") { λ("_", "x")(α -> (α -> α)) { λ("_", "y")(α -> α) { y } } }
    val And = λ("_", "x")(TBool -> (TBool -> TBool)) {
      λ("_", "y")(TBool -> TBool) { x(TBool)(y)(False) }
    }
    val Bool2Num = λ("_", "x")(TBool -> TNum) { x(TNum)(ENum(1))(ENum(0)) }

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
    val (v1, _) = topEval(e1)
    assert(v1 == VNum(0))

    val e2 =
      let("t" ⇐ True) {
        let("f" ⇐ False) {
          let("and" ⇐ And) {
            Bool2Num(and(t)(t))
          }
        }
      }
    val (v2, _) = topEval(e2)
    assert(v2 == VNum(1))

  }
}
