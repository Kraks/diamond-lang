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

import RunDiamond.prettyQType

def parseAndCheck(s: String): QType = topTypeCheck(parseToCore(s))
def parseAndEval(s: String): Value = topEval(parseToCore(s))._1

object TransparentPair {
  val makePair = """
    [A^a <: Top^<>] => { [B^b <: Top^{a, <>}] => {
      (x: A^a) => { (y: B^b) => {
        [C^c <: Top^{a, b, <>}] => {
          (f: ((x: A^a) => ((y: B^b) => C^c)^x)): C^c => { f(x)(y) }
        }
      } }
    } }
    """

  //val tyPair = "forall [C^c <: Top^{a, b, <>}] => (((x: A^a) => ((y: B^b) => C^c)^x) => C^c)^{c, a, b}"
  val tyPair = "forall [C^c <: Top^{a, b, <>}] => (((x: A^a) => ((y: B^b) => C^c)^x) => C^c)^{a, b}" // TODO: is c necessary?

  val fst = s"""
    [A^a <: Top^<>] => { [B^b <: Top^{a, <>}] => {
      (p: (${tyPair})^{a, b, <>}) => {
        p[A^a]( (x: A^a) => { (y: B^b) => { x } } )
      }
    } }
    """

  val snd = s"""
    [A^a <: Top^<>] => { [B^b <: Top^{a, <>}] => {
      (p: (${tyPair})^{a, b, <>}) => {
        p[B^b]( (x: A^a) => { (y: B^b) => { y } } )
      }
    } }
    """
}

object OpaquePair {
  def tyOPair(A: String, B: String): String =
    s"forall p[C^c <: Top] => (f(g(x: $A^<>) => ((y: $B^{x, <>}) => C^{x, y})^x) => C^f)^p"

  val makePair = s"""
    [A^a <: Top^<>] => { [B^b <: Top^{a, <>}] => {
      //(x: A^a) => { (y: B^b): (${tyOPair("A", "B")})^{x, y} => {
      (x: A^a) => { (y: B^b) => {
        p[C^c <: Top^{}]: (f(g(x: A^<>) => ((y: B^{x, <>}) => C^{x, y})^x) => C^f)^p => {
          f(h: (g(x: A^<>) => ((y: B^{x, <>}) => C^{x, y})^x)): C^f => { h(x)(y) }
        }
      } }
    } }
    """

  val fst = s"""
    [A^a <: Top^<>] => { [B^b <: Top^{a, <>}] => {
      (p: (${tyOPair("A", "B")})^{a, b}) => {
        p[A]( (x: A^<>) => { (y: B^{x, <>}): A^{x, y} => { x } } )
      }
    } }
    """

  val snd = s"""
    [A^a <: Top^<>] => { [B^b <: Top^{a, <>}] => {
      (p: (${tyOPair("A", "B")})^{a, b}) => {
        p[B]( (x: A^<>) => { (y: B^{x, <>}): B^{x, y} => { y } } )
      }
    } }
    """
}

class Playground extends AnyFunSuite {
}

class DiamondPairTest extends AnyFunSuite {
  test("transparent pairs") {
    import TransparentPair._

    assert(parseAndCheck(makePair) ==
      (TForall("𝐹#0","A","a",TTop^ ◆,
        TForall("𝐹#1","B","b",TTop^("a",◆),
          TFun("𝑓#2","x",TVar("A")^"a",
            TFun("𝑓#3","y",TVar("B")^"b",
              TForall("𝐹#4","C","c",TTop^("a","b",◆),
                TFun("𝑓#5","f",
                  TFun("𝑓#6","x",TVar("A")^"a",
                    TFun("𝑓#7","y",TVar("B")^"b",
                      TVar("C")^"c")^"x")^(),
                  TVar("C")^"c")^("x","y"))^("x","y"))^("x"))^())^())^()))
    //println(RunDiamond.prettyQType(parseAndCheck(makePair)))

    //println(parseToCore(fst))
    //println(parseAndCheck(fst))

    //println(parseToCore(snd))
    //println(parseAndCheck(snd))

    // A pair of two integers:
    val p1 = s"""
    val makePair = ($makePair);
    val p = makePair[Int][Int](1)(2);
    val fst = ($fst);
    val n = fst[Int][Int](p);
    val snd = ($snd);
    val m = snd[Int][Int](p);
    n + m
    """
    assert(parseAndCheck(p1) == (TNum^()))
    assert(parseAndEval(p1) == VNum(3))

    // A pair of two references and get the first one:
    val p2 = s"""
    val makePair = ($makePair);
    val fst = ($fst);
    val snd = ($snd);
    topval u = Ref 12;             // use topval to manifest the returned qualifier
    topval v = Ref 34;
    val p = makePair[Ref[Int]^u][Ref[Int]^v](u)(v);
    fst[Ref[Int]^u][Ref[Int]^v](p) // precise qualifier: Ref[Int]^{u}
    """
    assert(parseAndCheck(p2) == (TRef(TNum^())^"u"))

    // A pair of two references and get the second one:
    val p3 = s"""
    val makePair = ($makePair);
    val fst = ($fst);
    val snd = ($snd);
    topval u = Ref 12;             // use topval to manifest the returned qualifier
    topval v = Ref 34;
    val p = makePair[Ref[Int]^u][Ref[Int]^v](u)(v);
    snd[Ref[Int]^u][Ref[Int]^v](p) // precise qualifier: Ref[Int]^{v}
    """
    assert(parseAndCheck(p3) == (TRef(TNum^())^"v"))

    // It is not allowed to put fresh entities:
    val p4 = s"""
    val makePair = ($makePair);
    val fst = ($fst);
    val snd = ($snd);
    val p = makePair[Ref[Int]^<>][Ref[Int]^<>](Ref 1)(Ref 2);
    snd[Ref[Int]^<>][Ref[Int]^<>](p)
    """
    intercept[DeepDependency] { parseAndCheck(p4) }

    // A pair of two same elements:
    val dup = s"""
    val makePair = ($makePair);
    val fst = ($fst);
    val snd = ($snd);
    topval u = Ref 12;
    val p = makePair[Ref[Int]^u][Ref[Int]^u](u)(u);
    """

    // Check the type of the first component of the pair of two same elements:
    val p5 = s"""
    $dup
    fst[Ref[Int]^u][Ref[Int]^u](p)
    """
    assert(parseAndCheck(p5) == (TRef(TNum^())^"u"))

    // Check the type of the second component of the pair of two same elements:
    val p6 = s"""
    $dup
    snd[Ref[Int]^u][Ref[Int]^u](p)
    """
    assert(parseAndCheck(p6) == (TRef(TNum^())^"u"))

    // Transparent pairs cannot be used for escaping (should use opaque pairs):
    val p7 = s"""
    val makePair = ($makePair);
    val fst = ($fst);
    val snd = ($snd);
    def f(x: Int) = {
      val c1 = Ref x;
      val c2 = Ref (x+1);
      makePair[Ref[Int]^c1][Ref[Int]^c2](c1)(c2)
    };
    f(1)
    """
    intercept[DeepDependency] { parseAndCheck(p7) }
  }

  test("opaque pairs") {
    import OpaquePair._

    assert(parseAndCheck(makePair) ==
      (TForall("𝐹#0","A","a",TTop^ ◆,
        TForall("𝐹#1","B","b",TTop^("a", ◆),
          TFun("𝑓#2","x",TVar("A")^"a",
            TFun("𝑓#3","y",TVar("B")^"b",
              TForall("p","C","c",TTop^(),
                TFun("f","Arg#4",
                  TFun("g","x",TVar("A")^ ◆,
                    TFun("𝑓#5","y",TVar("B")^("x", ◆),
                      TVar("C")^("x","y"))^"x")^(),
                  TVar("C")^"f")^"p")^("x","y"))^"x")^())^())^()))
    assert(prettyQType(parseAndCheck(makePair)) ==
      """(∀𝐹#0(A^a <: Top^◆). (∀𝐹#1(B^b <: Top^{a,◆}). (𝑓#2(x: A^a) => (𝑓#3(y: B^b) => (∀p(C^c <: Top^∅). (f(Arg#4: (g(x: A^◆) => (𝑓#5(y: B^{x,◆}) => C^{x,y})^x)^∅) => C^f)^p)^{x,y})^x)^∅)^∅)^∅""")

    assert(prettyQType(parseAndCheck(fst)) ==
      """(∀𝐹#0(A^a <: Top^◆). (∀𝐹#1(B^b <: Top^{a,◆}). (𝑓#2(p: (∀p(C^c <: Top^∅). (f(Arg#3: (g(x: A^◆) => (𝑓#4(y: B^{x,◆}) => C^{x,y})^x)^∅) => C^f)^p)^{a,b}) => A^p)^∅)^∅)^∅""")

    assert(prettyQType(parseAndCheck(snd)) ==
      """(∀𝐹#0(A^a <: Top^◆). (∀𝐹#1(B^b <: Top^{a,◆}). (𝑓#2(p: (∀p(C^c <: Top^∅). (f(Arg#3: (g(x: A^◆) => (𝑓#4(y: B^{x,◆}) => C^{x,y})^x)^∅) => C^f)^p)^{a,b}) => B^p)^∅)^∅)^∅""")

    // A pair of two integers:
    val p1 = s"""
    val makePair = ($makePair);
    val p = makePair[Int][Int](1)(2);
    val fst = ($fst);
    val n = fst[Int][Int](p);
    val snd = ($snd);
    val m = snd[Int][Int](p);
    n + m
    """
    assert(parseAndCheck(p1) == (TNum^()))

    // A pair of two references and get the first one:
    val p2 = s"""
    val makePair = ($makePair);
    val fst = ($fst);
    val snd = ($snd);
    topval u = Ref 12;             // use topval to manifest the returned qualifier
    topval v = Ref 34;
    val p = makePair[Ref[Int]^u][Ref[Int]^v](u)(v);
    fst[Ref[Int]^u][Ref[Int]^v](p) // Ref[Int]^{u, v}
    """
    assert(parseAndCheck(p2) == (TRef(TNum^())^("u", "v")))

    // A pair of two references and get the second one:
    val p3 = s"""
    val makePair = ($makePair);
    val fst = ($fst);
    val snd = ($snd);
    topval u = Ref 12;             // use topval to manifest the returned qualifier
    topval v = Ref 34;
    val p = makePair[Ref[Int]^u][Ref[Int]^v](u)(v);
    snd[Ref[Int]^u][Ref[Int]^v](p) // Ref[Int]^{u, v}
    """
    assert(parseAndCheck(p3) == (TRef(TNum^())^("u", "v")))

    // Construct an escaped pair:
    val p4 = s"""
    val makePair = ($makePair);
    val fst = ($fst);
    val snd = ($snd);
    def f(x: Int): (${tyOPair("Ref[Int]", "Ref[Int]")})^<> = {
      val c1 = Ref x;
      val c2 = Ref (x+1);
      makePair[Ref[Int]^c1][Ref[Int]^c2](c1)(c2)
    };
    f(1)
    """
    assert(prettyQType(parseAndCheck(p4)) ==
      "(∀p(C^c <: Top^∅). (f(Arg#21: (g(x: Ref[Int^∅]^◆) => (𝑓#22(y: Ref[Int^∅]^{◆,x}) => C^{x,y})^x)^∅) => C^f)^p)^◆")

    // Construct an escaped pair and get its first component:
    val p5 = s"""
    val makePair = ($makePair);
    val fst = ($fst);
    val snd = ($snd);
    def f(x: Int): (${tyOPair("Ref[Int]", "Ref[Int]")})^<> = {
      val c1 = Ref x;
      val c2 = Ref (x+1);
      makePair[Ref[Int]^c1][Ref[Int]^c2](c1)(c2)
    };
    topval p = f(1);
    fst[Ref[Int]^p][Ref[Int]^p](p)
    """
    assert(prettyQType(parseAndCheck(p5)) == "Ref[Int^∅]^p")

    // Construct an escaped pair and get its second component:
    val p6 = s"""
    val makePair = ($makePair);
    val fst = ($fst);
    val snd = ($snd);
    def f(x: Int): (${tyOPair("Ref[Int]", "Ref[Int]")})^<> = {
      val c1 = Ref x;
      val c2 = Ref (x+1);
      makePair[Ref[Int]^c1][Ref[Int]^c2](c1)(c2)
    };
    topval p = f(1);
    snd[Ref[Int]^p][Ref[Int]^p](p)
    """
    assert(prettyQType(parseAndCheck(p6)) == "Ref[Int^∅]^p")

    // Compute something:
    val p7 = s"""
    val makePair = ($makePair);
    val fst = ($fst);
    val snd = ($snd);
    def f(x: Int): (${tyOPair("Ref[Int]", "Ref[Int]")})^<> = {
      val c1 = Ref x;
      val c2 = Ref (x+1);
      makePair[Ref[Int]^c1][Ref[Int]^c2](c1)(c2)
    };
    val p = f(1);
    val two = (! snd[Ref[Int]^p][Ref[Int]^p](p));
    val c1 = fst[Ref[Int]^p][Ref[Int]^p](p);
    val _ = c1 := ((! c1) + 5);
    two + (! c1)  // 2 + 6
    """
    assert(parseAndCheck(p7) == (TNum^()))
    assert(parseAndEval(p7) == VNum(8))
  }

  test("counter") {
    import OpaquePair._

    // Paper Fig.1 counter example

    val tyThunk = "(() => Unit)"
    val counterPrelude = s"""
    val makePair = $makePair;
    val fst = $fst;
    val snd = $snd;
    def counter(n: Int) = {
      val x = Ref n;
      val inc = () => { x := (! x) + 1 };
      val dec = () => { x := (! x) - 1 };
      makePair[${tyThunk}^x][${tyThunk}^x](inc)(dec)
    };
    topval p = counter(0);
    val inc = fst[${tyThunk}^p][${tyThunk}^p](p);
    val dec = snd[${tyThunk}^p][${tyThunk}^p](p);
    """

    val inc = s"""
    $counterPrelude
    inc
    """
    assert(prettyQType(parseAndCheck(inc)) == "(𝑓#21(𝑥#22: Unit^∅) => Unit^∅)^p")
    
    val callInc = s"""
    $counterPrelude
    inc(unit)
    """
    assert(prettyQType(parseAndCheck(callInc)) == "Unit^∅")

    val dec = s"""
    $counterPrelude
    dec
    """
    assert(prettyQType(parseAndCheck(dec)) == "(𝑓#27(𝑥#28: Unit^∅) => Unit^∅)^p")

    val callDec = s"""
    $counterPrelude
    dec(unit)
    """
    assert(prettyQType(parseAndCheck(callDec)) == "Unit^∅")
  }

  test("transparent pair to opaque pair") {
    val convPrelude = s"""
      val makeOPair = ${OpaquePair.makePair};
      val fst = ${TransparentPair.fst};
      val snd = ${TransparentPair.snd};
      val conv = [A^a <: Top^<>] => { [B^b <: Top^{a, <>}] => {
        // return type is optional:
        // (p: (${TransparentPair.tyPair})^{a, b, <>}) => {
        (p: (${TransparentPair.tyPair})^{a, b, <>}): (${OpaquePair.tyOPair("A", "B")})^{a, b} => {
          makeOPair[A^a][B^b](fst[A^a][B^b](p))(snd[A^a][B^b](p))
        }
      } };
    """

    val conv = s"""
    $convPrelude
    conv
    """
    //println(prettyQType(parseAndCheck(conv)))

    val example = s"""
    $convPrelude
    val makeTPair = ${TransparentPair.makePair};
    val opairFst = ${OpaquePair.fst};
    val opairSnd = ${OpaquePair.snd};
    def f(x: Int) = {
      val c1 = Ref x;
      val c2 = Ref (x+1);
      val p = makeTPair[Ref[Int]^c1][Ref[Int]^c2](c1)(c2);
      // conv[Ref[Int]^p][Ref[Int]^p](p)   // works too
      conv[Ref[Int]^c1][Ref[Int]^c2](p)
    };
    topval p = f(1);
    opairFst[Ref[Int]^p][Ref[Int]^p](p)
    """
    assert(prettyQType(parseAndCheck(example)) == "Ref[Int^∅]^p")
  }
}

class DiamondTest extends AnyFunSuite {

  test("simple expression") {
    val p1 = """
    1
    2
    3 + 4 * 5
    """
    assert(parseAndCheck(p1) == (TNum^()))

    val p2 = """
    true
    false
    true || true || (true && false)
    """
    assert(parseAndCheck(p2) == (TBool^()))

    val p3 = """ """
    assert(parseAndCheck(p3) == (TUnit^()))

    val p4 = """ unit """
    assert(parseAndCheck(p4) == (TUnit^()))
  }

  test("precedence") {
    val p1 = """ 3 + 4 * 5 """
    assert(parseAndEval(p1) == VNum(23))

    val p2 = "(3 + 4) * 5"
    assert(parseAndEval(p2) == VNum(35))

    val p3 = "3 * 4 / 2 + 1"
    assert(parseAndEval(p3) == VNum(7))
  }

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
    (id(x: Ref[Int]^<>): Ref[Int]^x => { Ref 42 })
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
    def f(x: Ref[Int]^c): (() => Ref[Int]^x)^x = () => { x };
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
    def f(g: ((Int) => Ref[Int]^x)^{x, <>}): Ref[Int]^x = g(0);
    f((y: Int) => { x })
    """
    assert(parseAndCheck(p1) == (TRef(TNum)^ ◆))

    // Do not permit any overlap between g and f
    val p2 = """
    val x = Ref 0;
    def f(g: ((Int) => Ref[Int]^x)^<>): Ref[Int]^x = g(0);
    f((y: Int) => { x })
    """
    // FIXME: intercept[NonOverlap] { parseAndCheck(p2) }

    // FIXME: What's the expected qualifier of f?
    // Should we include `x`?
    // This currently implementation does not.
    val p3 = """
    topval x = Ref 0;
    def f(g: ((Int) => Ref[Int]^x)^{x, <>}): Ref[Int]^x = g(0);
    f
    """
    assert(parseAndCheck(p3) ==
      (TFun("f","g",
        TFun("𝑓#0","Arg#1",TNum^(),TRef(TNum^())^"x")^("x", ◆),
        TRef(TNum^())^"x")^()))
  }

  test("escape") {
    // No annotation is required for this case, since we infer the type
    // of the returned literal lambda using self-reference.
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
    // TODO: also make this case work without Ref[Int]^g?
    assert(parseAndCheck(p6) == (TFun("g","𝑥#0",TUnit^(),TRef(TNum)^"g")^ ◆))
  }

  test("cannot escape") {
    val p1 = """
    def f1(x: Int): (g() => Ref[Int]^g)^<> =
      val c = Ref x;
      val g: (g() => Ref[Int]^g)^c = g() => { c };
      g;
    f1(0)
    """
    // Not okay: `() => { c };` is first typed as `(g() => Ref(Int)^c)^c`,
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
    val f1 = (): Int => { !c };
    val hc = Ref f1;
    hc
    """
    assert(parseAndCheck(p3) == (TRef(TFun("𝑓#0","𝑥#1",TUnit^(),TNum^())^"c")^(◆,"c")))

    val p4 = """
    topval c = Ref 100;
    val f1 = (): Int => { !c };
    val f2 = (): Int => { (!c) + (!c) };
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
    val f1 = (): Int => { !c };
    val f2 = (): Int => { (!c) + (!c) };
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

  test("forall type") {
    val p = """
    val p: forall [A^a <: Top^<>] => ((x: A^a) => A^x)^a =
      ([A^a <: Top^<>] => { (x: A^a) => { x } });
    p
    """
    assert(parseAndCheck(p) ==
      (TForall("𝐹#2","A","a",TTop^ ◆,
        TFun("𝑓#3","x",TVar("A")^"a",TVar("A")^"x")^"a")^()))
  }
}
