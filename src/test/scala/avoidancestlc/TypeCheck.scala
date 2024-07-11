package diamond.avoidancestlc
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import diamond._
import diamond.avoidancestlc.core._
import diamond.avoidancestlc.Parser._

import core.Type._
import core.Expr._
import TypeSyntax._
import ExprSyntax._

import TypeSyntax.given_Conversion_Type_QType
import ExprSyntax.given_Conversion_Int_ENum

class AvoidanceSTLCTests extends AnyFunSuite {
  def parseAndCheck(s: String): QType = topTypeCheck(parseToCore(s))

  /*
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
    // The type checker should infer the avoidance of `x` and instead use `f` in the return qualifier
    assert(topTypeCheck(e3) == (TFun("f", "z", TNum^(), TRef(TNum^())^"f") ^ ◆))

    val e3_let =
      let("x" ⇐ alloc(0)) {
        λ("f", "z")("f"♯(TNum ~> (TRef(TNum) ^ "x"))) { x }
      }
    assert(topTypeCheck(e3_let) == (TFun("f", "z", TNum^(), TRef(TNum^())^"f") ^ ◆))

    val e3_alias = {
      val f = λ("x" ⦂ (TRef(TNum) ^ ◆)) {
        let ("y" ⇐ x) {
          λ("f", "z")("f"♯(TNum ~> (TRef(TNum) ^ "y"))) { y }
        }
      }
      val arg = alloc(3)
      f(arg)
    }
    assert(topTypeCheck(e3_alias) == (TFun("f", "z", TNum^(), TRef(TNum^())^"f") ^ ◆))

    // Manually annotate the return qualifier to self-ref `f` (not necessary)
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

  test("escaping closures -- surface syntax") {
    val p0 = """
    def f0(x: Int) =
      val c = Ref x;
      g() => { c };
    f0(0)
    """
    assert(parseAndCheck(p0) == (TFun("g","𝑥#0",TUnit^(),TRef(TNum)^"g")^ ◆))

    val p1 = """
    def f1(x: Int) =
      val c = Ref x;
      g(): Ref[Int]^g => { c };
    f1(0)
    """
    assert(parseAndCheck(p1) == (TFun("g","𝑥#0",TUnit^(),TRef(TNum)^"g")^ ◆))

    val p2 = """
    def f2(x: Int): (g() => Ref[Int]^g)^<> =
      val c = Ref x;
      g(): Ref[Int]^g => { c };
    f2(0)
    """
    assert(parseAndCheck(p2) == (TFun("g","𝑥#0",TUnit^(),TRef(TNum)^"g")^ ◆))

    val p3 = """
    def f3(x: Int): (g() => Ref[Int]^g)^<> =
      val c = Ref x;
      def g(): Ref[Int]^g = c;
      g;
    f3(0)
    """
    assert(parseAndCheck(p3) == (TFun("g","𝑥#0",TUnit^(),TRef(TNum)^"g")^ ◆))

    val p4 = """
    def f4(x: Int): (g() => Ref[Int]^g)^<> =
      val c = Ref x;
      val g = g(): Ref[Int]^g => { c };
      g;
    f4(0)
    """
    assert(parseAndCheck(p4) == (TFun("g","𝑥#0",TUnit^(),TRef(TNum)^"g")^ ◆))

    val p5 = """
    def f5(x: Int): (g() => Ref[Int]^g)^<> =
      val c = Ref x;
      val g: (g() => Ref[Int]^g)^c = g(): Ref[Int]^g => { c };
      g;
    f5(0)
    """
    assert(parseAndCheck(p5) == (TFun("g","𝑥#0",TUnit^(),TRef(TNum)^"g")^ ◆))

    // written as anonymous functions:
    val p6 = """
    def f6(x: Int): (g() => Ref[Int]^g)^<> =
      ((c: Ref[Int]^<>) => {
         g(): Ref[Int]^g => { c }
      })(Ref x);
    f6(0)
    """
    assert(parseAndCheck(p6) == (TFun("g","𝑥#0",TUnit^(),TRef(TNum)^"g")^ ◆))
  }
  */

  test("mono pair") {
    def makePair(t: String)(a: String, b: String) = s"""
      p(f: (f(x: $t^$a) => (g(y: $t^$b) => $t^g)^f)^{<>, p})^{$a, $b}: $t^f => { f($a)($b) }
    """
    def fstT(t: String, a: String)(p: String) = s"""
      $p(f(x: $t^$a)^$a: (g(y: $t^{<>, g}) => $t^g)^f => { g(y: $t^{<>, g})^f: $t^g => { x } })
      //$p(f(x: $t^$a)^$a => { g(y: $t^{<>, g})^{x}: $t^g => { x } })
    """
    def sndT(t: String, b: String)(p: String) = s"""
      $p(f(x: $t^{<>, f})^$b => { g(y: $t^$b)^y: $t^g => { y } })
    """
    val p0 = s"""
      val r1 = Ref 1;
      val r2 = Ref 2;
      val p = ${makePair("Ref[Int]")("r1", "r2")};
      ${fstT("Ref[Int]", "r1")("p")}
    """
    //println(parseToCore(fstT("Ref[Int]", "r1")("p")))
    println(parseToCore(p0))
    println(parseAndCheck(p0))
  }
}

