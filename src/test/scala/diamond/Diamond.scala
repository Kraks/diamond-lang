package diamond.qualfsub

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import diamond.qualfsub.core._
import diamond._
import Type._
import Expr._
import Value._
import TypeSyntax._
import Parser._

import TypeSyntax.given_Conversion_Type_QType
import ExprSyntax.given_Conversion_Int_ENum

def parseAndCheck(s: String): QType = topTypeCheck(parseToCore(s))
def parseAndEval(s: String): Value = topEval(parseToCore(s))._1

class Playground extends AnyFunSuite {
}

class DiamondTest extends AnyFunSuite {
  test("poly id") {
    val p1 = """
    def id[T <: Top](x: T^<>): T^x = x;
    val x = id[Int](3);              // : Int^∅
    val c = id[Ref[Int]^<>](Ref 42); // : Ref[Int]^◆
    x + (! c)                        // : Int^∅
    """
    assert(parseAndCheck(p1) == (TNum^()))

    val p1_simple = """
    def id[T](x: T^<>) = x;
    val x = id[Int](3);              // : Int^∅
    val c = id[Ref[Int]^<>](Ref 42); // : Ref[Int]^◆
    x + (! c)                        // : Int^∅
    """
    assert(parseAndCheck(p1_simple) == (TNum^()))

    // Sec 2.2.4
    val p2 = """
    val x = 42;
    def id[T <: Top](x: T^<>): T^x = x;
    id[Int](x)
    """
    assert(parseAndCheck(p2) == (TNum^()))

    val p3 = """
    topval y = Ref 42;
    def id[T <: Top](x: T^<>): T^x = x;
    id[Ref[Int]^<>](y)
    """
    assert(parseAndCheck(p3) == (TRef(TNum)^"y"))

    val p4 = """
    def id[T^z <: Top^<>](x: T^z): T^x = x;    // Now x's qualifier is abstract z
    val x = id[Int](3);                        // : Int^∅
    // val c = id[Ref[Int]^<>](Ref 42);        // error because of deep dependency of z when z is fresh
    val c1 = Ref 42;
    val c2 = id[Ref[Int]^c1](c1);
    x + (! c2)                                 // : Int^∅
    """
    assert(parseAndCheck(p4) == (TNum^()))
  }

  test("fake id") {
    val p1 = """
    def id(x: Ref[Int]^<>): Ref[Int]^x = Ref 42;
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


  test("reachability polymorphism") {
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

    // Sec 2.2.6 from the paper:
    // qualifier-dependent application (non-fresh)
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
    def f(x: Ref[Int]^{c1, <>}): Int = { (!x) + (!c1) };
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
    def f(x: Ref[Int]^{c2, <>}): Int = { (!x) + (!c1) };
    f@(c2)
    """
    assert(parseAndCheck(p4) == (TNum ^ ()))

    val p5 = """
    val c1 = Ref 1;
    val c2 = c1;
    def f(x: Ref[Int]^{c2, <>}): Int = { (!x) + (!c1) };
    f@(c1)
    """
    assert(parseAndCheck(p5) == (TNum ^ ()))

    def p6(allow: String, use: String, arg: String) = s"""
    def id(x: Ref[Int]^<>): Ref[Int]^x = x;
    val x = Ref 42;
    val y = id(x);
    def f(z: Ref[Int]^{<>, $allow}): Int = (! $use) + (! z);
    f@($arg)
    """
    assert(parseAndCheck(p6("x", "x", "x")) == (TNum^()))
    assert(parseAndCheck(p6("x", "x", "y")) == (TNum^()))
    assert(parseAndCheck(p6("x", "y", "x")) == (TNum^()))
    assert(parseAndCheck(p6("x", "y", "y")) == (TNum^()))
    assert(parseAndCheck(p6("y", "x", "x")) == (TNum^()))
    assert(parseAndCheck(p6("y", "x", "y")) == (TNum^()))
    assert(parseAndCheck(p6("y", "y", "x")) == (TNum^()))
    assert(parseAndCheck(p6("y", "y", "y")) == (TNum^()))

    def p7(use: String, arg: String) = s"""
    def id(x: Ref[Int]^<>): Ref[Int]^x = x;
    val x = Ref 42;
    val y = id(x);
    def f(z: Ref[Int]^{<>}): Int = (! $use) + (! z);
    f@($arg)
    """
    intercept[NonOverlap] { parseAndCheck(p7("x", "x")) }
    intercept[NonOverlap] { parseAndCheck(p7("x", "y")) }
    intercept[NonOverlap] { parseAndCheck(p7("y", "x")) }
    intercept[NonOverlap] { parseAndCheck(p7("y", "y")) }
  }

  test("free var in type") {
    // Permitting g and f have overlap x
    val p1 = """
    val x = Ref 0;
    def f(g: ((Int) => Ref[Int]^x)^x): Ref[Int]^x = g(0);
    f(lam (y: Int) { x })
    """
    assert(parseAndCheck(p1) == (TRef(TNum)^ ◆))

    // Do not permit any overlap between g and f
    val p2 = """
    val x = Ref 0;
    def f(g: ((Int) => Ref[Int]^x)): Ref[Int]^x = g(0);
    f@(lam (y: Int) { x }) // to enforce checking overlap
    """
    intercept[NonOverlap] { parseAndCheck(p2) }
  }

  test("escape") {
    // No annotation is required for this case, since we infer the type
    // of the returned literal lambda using self-reference.
    val p0 = """
    def f0(x: Int) =
      val c = Ref x;
      lam g() { c };
    f0(0)
    """
    assert(parseAndCheck(p0) == (TFun("g","𝑥#0",TUnit^(),TRef(TNum)^"g")^ ◆))

    val p1 = """
    def f1(x: Int) =                                                                            
      val c = Ref x;
      lam g(): Ref[Int]^g { c };
    f1(0)
    """
    assert(parseAndCheck(p1) == (TFun("g","𝑥#0",TUnit^(),TRef(TNum)^"g")^ ◆))

    val p2 = """
    def f2(x: Int): (g() => Ref[Int]^g)^<> =
      val c = Ref x;
      lam g(): Ref[Int]^g { c };
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
      val g = lam g(): Ref[Int]^g { c };
      g;
    f4(0)
    """
    assert(parseAndCheck(p4) == (TFun("g","𝑥#0",TUnit^(),TRef(TNum)^"g")^ ◆))

    val p5 = """
    def f5(x: Int): (g() => Ref[Int]^g)^<> =
      val c = Ref x;
      val g: (g() => Ref[Int]^g)^c = lam g(): Ref[Int]^g { c };
      g;
    f5(0)
    """
    assert(parseAndCheck(p5) == (TFun("g","𝑥#0",TUnit^(),TRef(TNum)^"g")^ ◆))

    // written as anonymous functions:
    val p6 = """
    def f6(x: Int): (g() => Ref[Int]^g)^<> =
      (lam (c: Ref[Int]^<>) {
         lam g(): Ref[Int]^g { c }
      })(Ref x);
    f6(0)
    """
    // TODO: also make this case work without Ref[Int]^g?
    assert(parseAndCheck(p6) == (TFun("g","𝑥#0",TUnit^(),TRef(TNum)^"g")^ ◆))
  }

  test("cannot escape") {
    val p1 = """
    def f1(x: Int): (g() => Ref[Int]^g)^<> =
      val c = Ref x;
      val g: (g() => Ref[Int]^g)^c = lam g() { c };
      g;
    f1(0)
    """
    // Not okay: `lam g() { c };` is first typed as `(g() => Ref(Int)^c)^c`,
    // but it is not a subtype of `(g() => Ref(Int)^g)^c`.
    // Because S-Fun rule assign fresh as the qualifier to the function type of `g`,
    // which prevents from using Q-Self to upcast `c` to `g`.
    intercept[NotSubQType] { parseAndCheck(p1) }
  }

  test("nested references") {
    val p1 = """
    val x = 42;                                                                          
    val c1 = Ref x;
    val c2 = Ref c1; 
    val c3 = ! c2; // that is c1
    c3
    """
    assert(parseAndCheck(p1) == (TRef(TNum^())^ ◆))

    val p2 = """
    topval x = 42;                                                                          
    topval c1 = Ref x;
    val c2 = Ref c1; 
    val c3 = ! c2; // that is c1
    c3
    """
    assert(parseAndCheck(p2) == (TRef(TNum^"x")^"c1"))

    val p3 = """
    topval c = Ref 100;
    val f1 = lam (): Int { !c };
    val hc = Ref f1;
    hc
    """
    assert(parseAndCheck(p3) == (TRef(TFun("𝑓#0","𝑥#1",TUnit^(),TNum^())^"c")^(◆,"c")))

    val p4 = """
    topval c = Ref 100;
    val f1 = lam (): Int { !c };
    val f2 = lam (): Int { (!c) + (!c) };
    val hc = Ref f1;
    val _ = hc := f2;
    val x: Int = (! hc)(unit);
    val _ = hc := f1;
    val y: Int = (! hc)(unit);
    hc
    """
    assert(parseAndCheck(p4) == (TRef(TFun("𝑓#0","𝑥#1",TUnit^(),TNum^())^"c")^(◆,"c")))

    val p5 = """
    topval c = Ref 100;
    val f1 = lam (): Int { !c };
    val f2 = lam (): Int { (!c) + (!c) };
    val hc = Ref f1;
    val _ = hc := f2;
    val x: Int = (! hc)(unit); // 200
    val _ = hc := f1;
    val y: Int = (! hc)(unit); // 100
    x + y
    """
    assert(parseAndCheck(p5) == (TNum^()))
    assert(parseAndEval(p5) == VNum(300))
  }

  test("type arg infer") {
    val p1 = """
    def id[T <: Top](x: T^<>): T^x = x;
    val x = id(3);                  // : Int^∅
    val c = id(Ref 42);             // : Ref[Int]^◆
    val y = id(x);                  // : Int^∅
    x + y + (! c)                   // : Int^∅
    """
    assert(parseAndCheck(p1) == (TNum^()))
  }
}
