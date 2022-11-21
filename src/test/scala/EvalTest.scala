package diamond

import org.scalatest.funsuite.AnyFunSuite

import Type._
import Expr._
import Value._

class TestBase extends AnyFunSuite {
  val ρ0 = Env(Map())
  val σ0 = Store(Map())
  val x = EVar("x")
  val y = EVar("y")
  val z = EVar("z")
  val n = EVar("n")
  val f = EVar("f")
  val g = EVar("g")
}

class EvalTests extends TestBase {
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
    val e1 = ELam("f", "x", TNum, EBinOp("+", x, ENum(3)))
    val e2 = EBinOp("+", ENum(4), ENum(5))
    // (λf(x).x + 3)(4 + 5)
    val (v, σ) = topEval(EApp(e1, e2))
    assert(v == VNum(12))
  }

  test("recursive function - power") {
    // λf(x).λg(n). if (n == 0) 1 else x * f(x)(n-1)
    val e1 =
      ELam("f", "x", TNum,
        ELam("g", "n", TNum,
          ECond(EBinOp("=", n, ENum(0)),
            ENum(1),
            EBinOp("*", x, EApp(EApp(f, x), EBinOp("-", n, ENum(1)))))))
    // power(2, 5)
    val app = EApp(EApp(e1, ENum(2)), ENum(5))
    val (v, σ) = topEval(app)
    assert(v == VNum(32))
  }
}
