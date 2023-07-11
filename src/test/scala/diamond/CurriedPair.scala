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

// This file has test cases of pairs written in curried syntax.

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

  val tyPair = "forall [C^c <: Top^{a, b, <>}] => (((x: A^a) => ((y: B^b) => C^c)^x) => C^c)^{c, a, b}"

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
class CurriedPairTest extends AnyFunSuite {
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
    topval p = makePair[Ref[Int]^u][Ref[Int]^v](u)(v);
    fst[Ref[Int]^u][Ref[Int]^v](p) // Ref[Int]^{p}
    """
    assert(parseAndCheck(p2) == (TRef(TNum^())^("p")))

    // A pair of two references and get the second one:
    val p3 = s"""
    val makePair = ($makePair);
    val fst = ($fst);
    val snd = ($snd);
    topval u = Ref 12;             // use topval to manifest the returned qualifier
    topval v = Ref 34;
    topval p = makePair[Ref[Int]^u][Ref[Int]^v](u)(v);
    snd[Ref[Int]^u][Ref[Int]^v](p) // Ref[Int]^{p}
    """
    assert(parseAndCheck(p3) == (TRef(TNum^())^("p")))

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
