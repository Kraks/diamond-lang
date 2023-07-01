package diamond.qualfsub

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import diamond.qualfsub.core._
import diamond._
import Type._
import Expr._
import TypeSyntax._
import ExprSyntax._
import Parser._

import TypeSyntax.given_Conversion_Type_QType
import ExprSyntax.given_Conversion_Int_ENum

class DiamondTest extends AnyFunSuite {
  def parseAndCheck(s: String): QType = topTypeCheck(parseToCore(s))

  test("poly-id") {
    val p1 = """
    def id[T <: Top](x: T^<>): T^x = x
    val x = id[Int](3);              // : Int^∅
    val c = id[Ref[Int]^<>](Ref 42); // : Ref[Int]^◆
    x + (! c)                        // : Int^∅
    """
    assert(parseAndCheck(p1) == (TNum^()))
  }

  test("fake-id") {
    val p1 = """
    def id(x: Ref[Int]^<>): Ref[Int]^x = Ref 42
    id
    """
    assert(intercept[NotSubQType] { parseAndCheck(p1) }
      == NotSubQType(TRef(TNum) ^ ◆, TRef(TNum) ^ "x")())

    val p2 = """
    lam id(x: Ref[Int]^<>): Ref[Int]^x { Ref 42 }
    """
    assert(intercept[NotSubQType] { parseAndCheck(p2) }
      == NotSubQType(TRef(TNum) ^ ◆, TRef(TNum) ^ "x")())
  }

  test("reachability-polymorphism") {
    // paper Sec 2.2.3
    // Note: we use keyword "topval" to declare c1 and c2 as top-level capabilities,
    // which prevents subsitution of the body from c1/c2 to their actual qualifiers.
    // In this way, we can see the precise qualifier at the end.
    // Otherwise, the final result does not have a named qualifier variable.
    val p1 = """
    topval c1 = Ref 42;
    topval c2 = Ref 42;
    def foo(x: Ref[Int]^{<>, c1}): Ref[Int]^x = {
      val _ = (c1 := (! c1) + 1); x
    };
    foo(c1)
    """
    assert(parseAndCheck(p1) == (TRef(TNum) ^ "c1"))

    val p2 = """
    topval c1 = Ref 42;
    topval c2 = Ref 42;
    def foo(x: Ref[Int]^{<>, c1}): Ref[Int]^x = {
      val _ = (c1 := (! c1) + 1); x
    };
    foo(c2)
    """
    assert(parseAndCheck(p2) == (TRef(TNum) ^ "c2"))

    val p3 = """
    topval c = Ref 42;
    def f(x: Ref[Int]^c): (() => Ref[Int]^x)^x = lam () { x };
    f(c)
    """
    assert(parseAndCheck(p3) == (TFun("𝑓#0","𝑥#1",TUnit^(),TRef(TNum)^"c")^"c"))
  }

  test("separation") {
    val p1 = """
    val c1 = Ref 1;
    def f(x: Ref[Int]^<>): Int = { (!x) + (!c1) };
    f@(c1) // enforce using fresh application
    """
    assert(intercept[NonOverlap] { parseAndCheck(p1) } ==
      NonOverlap(Qual(Set()), Qual(Set("c1"))))

    val p2 = """
    val c1 = Ref 1;
    def f(x: Ref[Int]^c1): Int = { (!x) + (!c1) };
    f@(c1) // enforce using fresh application
    """
    assert(parseAndCheck(p2) == (TNum ^ ()))

    val p3 = """
    val c1 = Ref 1;
    val c2 = c1;
    def f(x: Ref[Int]^<>): Int = { (!x) + (!c1) };
    f@(c2)
    """
    assert(intercept[NonOverlap] { parseAndCheck(p3) } ==
      NonOverlap(Qual(Set()), Qual(Set("c1"))))

    val p4 = """
    val c1 = Ref 1;
    val c2 = c1;
    def f(x: Ref[Int]^c2): Int = { (!x) + (!c1) };
    f@(c2)
    """
    assert(parseAndCheck(p4) == (TNum ^ ()))

    val p5 = """
    val c1 = Ref 1;
    val c2 = c1;
    def f(x: Ref[Int]^c2): Int = { (!x) + (!c1) };
    f@(c1)
    """
    assert(parseAndCheck(p5) == (TNum ^ ()))
  }

  test("escape") {
    val p1 = """
    def f1(x: Int) =                                                                            
      val c = Ref x;
      lam g(): Ref[Int]^g { c } 
    f1(0)
    """
    assert(parseAndCheck(p1) == (TFun("g","𝑥#0",TUnit^(),TRef(TNum)^"g")^ ◆))

    val p2 = """
    def f2(x: Int): (g() => Ref[Int]^g)^<> =
      val c = Ref x;
      lam g(): Ref[Int]^g { c } 
    f2(0)
    """
    assert(parseAndCheck(p2) == (TFun("g","𝑥#0",TUnit^(),TRef(TNum)^"g")^ ◆))

    val p3 = """
    def f3(x: Int): (g() => Ref[Int]^g)^<> =
      val c = Ref x;
      def g(): Ref[Int]^g = c;
      g
    f3(0)
    """
    assert(parseAndCheck(p3) == (TFun("g","𝑥#0",TUnit^(),TRef(TNum)^"g")^ ◆))

    val p4 = """
    def f4(x: Int): (g() => Ref[Int]^g)^<> =
      val c = Ref x;
      val g = lam g(): Ref[Int]^g { c };
      g
    f4(0)
    """
    assert(parseAndCheck(p4) == (TFun("g","𝑥#0",TUnit^(),TRef(TNum)^"g")^ ◆))

    val p5 = """
    def f5(x: Int): (g() => Ref[Int]^g)^<> =
      val c = Ref x;
      val g: (g() => Ref[Int]^g)^c = lam g(): Ref[Int]^g { c };
      g
    f5(0)
    """
    assert(parseAndCheck(p5) == (TFun("g","𝑥#0",TUnit^(),TRef(TNum)^"g")^ ◆))
  }
}
