package diamond.popl24

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import diamond._
import diamond.qualfsub._
import diamond.qualfsub.core._
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

class ExamplesInPaper extends AnyFunSuite {
  test("Sec 2.2.2 - Freshness Marker") {
    val alloc = """
    Ref 0
    """
    assert(prettyQType(parseAndCheck(alloc)) == "Ref[Int^∅]^◆")

    // Note: In paper's Section 2, we have not formally introduced type polymorphism,
    // therefore paper examples omit the "forall" type when explaining.
    // Here, we examine the full type with forall type and ordinary function type.

    val id = """
    def id[T](x: T^<>): T^x = x;
    id
    """
    assert(prettyQType(parseAndCheck(id)) == "(∀id(T^𝑥#0 <: Top^◆). (𝑓#1(x: T^◆) => T^x)^∅)^∅")

    // Note: we use keyword "topval" to declare `z` as a top-level capability,
    // which pretends that we will not leave the scope of `z`.
    // In this way, we can see the precise qualifier at the end.
    // Otherwise, the final result does not have a named qualifier variable.

    val id2 = """
    topval z = Ref 0; // a top-level capability
    def id2[T](x: T^<>): T^x = { val u = z; x };
    id2
    """
    assert(prettyQType(parseAndCheck(id2)) == "(∀id2(T^𝑥#0 <: Top^◆). (𝑓#1(x: T^◆) => T^x)^z)^z")

    val id3 = """
    topval z = Ref 0; // a top-level capability
    def id3[T](x: T^{<>, z}): T^x = { val u = z; x };
    id3
    """
    assert(prettyQType(parseAndCheck(id3)) == "(∀id3(T^𝑥#0 <: Top^◆). (𝑓#1(x: T^{◆,z}) => T^x)^z)^z")

    val id4 = """
    topval z = Ref 0; // a top-level capability
    def id4[T](x: T^{z}): T^x = { val u = z; x };
    id4
    """
    assert(prettyQType(parseAndCheck(id4)) == "(∀id4(T^𝑥#0 <: Top^◆). (𝑓#1(x: T^z) => T^x)^z)^z")
  }

  test("Sec 2.2.3 - Precise Reachability Polymorphism") {
    // Apply id with untracked values:
    val useId1 = """
    def id[T](x: T^<>): T^x = x;
    id(42)
    """
    assert(prettyQType(parseAndCheck(useId1)) == "Int^∅")

    // Apply id with tracked fresh values:
    val useId2 = """
    def id[T](x: T^<>): T^x = x;
    id(Ref 0)
    """
    assert(prettyQType(parseAndCheck(useId2)) == "Ref[Int^∅]^◆")

    // Apply foo with c1 and the result preserves precise reachability:
    val foo_c1 = """
    topval c1 = Ref 42;
    topval c2 = Ref 42;
    def foo(x: Ref[Int]^{c1, <>}): Ref[Int]^x = {
      val _ = (c1 := (! c1) + 1);
      x
    };
    foo(c1)
    """
    assert(prettyQType(parseAndCheck(foo_c1)) == "Ref[Int^∅]^c1")

    // Apply foo with c2 and the result preserves precise reachability:
    val foo_c2 = """
    topval c1 = Ref 42;
    topval c2 = Ref 42;
    def foo(x: Ref[Int]^{c1, <>}): Ref[Int]^x = {
      val _ = (c1 := (! c1) + 1);
      x
    };
    foo(c2)
    """
    assert(prettyQType(parseAndCheck(foo_c2)) == "Ref[Int^∅]^c2")

    // Fake id
    val fakeId = """
    def id(x: Ref[Int]^<>): Ref[Int]^x = Ref 42;
    id
    """
    intercept[NotSubQType] { parseAndCheck(fakeId) }
  }

  test("Sec 2.2.4 - Maybe-Tracked and Subtyping") {
    val idUntrack = """
    topval x = 42;
    def id[T](x: T^<>): T^x = x;
    id(x)
    """
    assert(prettyQType(parseAndCheck(idUntrack)) == "Int^x")

    val untrackUpcast = """
    topval x = 42;
    def id[T](x: T^<>): T^x = x;
    val y: Int = id(x); // use let with type annotation to check subtyping 
    y
    """
    assert(prettyQType(parseAndCheck(untrackUpcast)) == "Int^∅")

    val idTracked = """
    topval y = Ref 42;
    def id[T](x: T^<>): T^x = x;
    id(y)
    """
    assert(prettyQType(parseAndCheck(idTracked)) == "Ref[Int^∅]^y")
  }

  test("Sec 2.2.5 - On-Demand Transitivity") {
    val sep = """
    val c1 = Ref 1;
    def f(x: Ref[Int]^<>): Int = { (!c1) + (!x) };
    val c2 = c1;
    f(c2) // type error
    """
    intercept[NonOverlap] { parseAndCheck(sep) }
  }

  test("Sec 2.2.6 - Qualifier-Dependent Application") {
    val qualifierDep = """
    topval c = Ref 42;
    def f(x: Ref[Int]^c) = () => { x };
    f(c)
    """
    assert(prettyQType(parseAndCheck(qualifierDep)) == "(𝑓#0(𝑥#1: Unit^∅) => Ref[Int^∅]^c)^c")
  }

  test("Sec 2.3 - Type-and-Qualifier Abstractions in F◆-Sub") {
    // Various ways of writing the polymorphic identity function:

    val id1 = """
    def id[T <: Top](x: T^<>): T^x = x;
    id
    """

    val id2 = """
    def id[T^z <: Top^<>](x: T^<>): T^x = x;
    id
    """

    val id3 = """
    def id[T](x: T^<>) = x;
    id
    """

    // They are all morally the same:
    assert(prettyQType(parseAndCheck(id1)) == "(∀id(T^𝑥#0 <: Top^◆). (𝑓#1(x: T^◆) => T^x)^∅)^∅")
    assert(prettyQType(parseAndCheck(id2)) == "(∀id(T^z <: Top^◆). (𝑓#0(x: T^◆) => T^x)^∅)^∅")
    assert(prettyQType(parseAndCheck(id3)) == "(∀id(T^𝑥#0 <: Top^◆). (𝑓#1(x: T^◆) => T^x)^∅)^∅")
  }

  object TransparentPair {
    def defMakePairWithName(f: String) = s"""
    def $f[A^a <: Top^<>, B^b <: Top^{a, <>}](x: A^a, y: B^b) = {
      [C^c <: Top^{a, b, <>}] => {
        (f: (A^a, B^b) => C^c): C^c => { f(x, y) }
      }
    };
    """
    val makePair = defMakePairWithName("makePair")

    val tyPair = "forall [C^c <: Top^{a, b, <>}] => (((A^a, B^b) => C^c) => C^c)^{a, b}"

    def defFstWithName(f: String) = s"""
    def $f[A^a <: Top^<>, B^b <: Top^{a, <>}](p: (${tyPair})^{a, b, <>}) = {
      p[A^a]( (x: A^a, y: B^b) => { x } )
    };
    """
    val fst = defFstWithName("fst")

    def defSndWithName(f: String) = s"""
    def $f[A^a <: Top^<>, B^b <: Top^{a, <>}](p: (${tyPair})^{a, b, <>}) = {
      p[B^b]( (x: A^a, y: B^b) => { y } )
    };
    """
    val snd = defSndWithName("snd")
  }

  test("Sec 2.4.1 - Transparent Pairs") {
    import TransparentPair._

    val pairUse1 = s"""
    $makePair
    $fst
    $snd
    topval u = Ref 12;             // use topval to manifest the returned qualifier
    topval v = Ref 34;
    val p = makePair[Ref[Int]^u, Ref[Int]^v](u, v);
    fst[Ref[Int]^u, Ref[Int]^v](p) // precise qualifier: Ref[Int]^{u}
    """
    assert(prettyQType(parseAndCheck(pairUse1)) == "Ref[Int^∅]^u")

    val pairUse2 = s"""
    $makePair
    $fst
    $snd
    topval u = Ref 12;             // use topval to manifest the returned qualifier
    topval v = Ref 34;
    val p = makePair[Ref[Int]^u, Ref[Int]^v](u, v);
    snd[Ref[Int]^u, Ref[Int]^v](p) // precise qualifier: Ref[Int]^{v}
    """
    assert(prettyQType(parseAndCheck(pairUse2)) == "Ref[Int^∅]^v")
  }

  object OpaquePair {
    def defMakePairWithName(f: String) = s"""
    def $f[A^a <: Top^<>, B^b <: Top^{a, <>}](x: A^a, y: B^b) = {
      p[C^c <: Top]: (f((x: A^<>, y: B^{x, <>}) => C^{x, y}) => C^f)^p => {
        f(h: (x: A^<>, y: B^{x, <>}) => C^{x, y}): C^f => { h(x, y) }
      }
    };
    """
    val makePair = defMakePairWithName("makePair")

    val tyPair = "forall p[C^c <: Top] => (f((x: A^<>, y: B^{x, <>}) => C^{x, y}) => C^f)^p"

    def defFstWithName(f: String) = s"""
    def $f[A^a <: Top^<>, B^b <: Top^{a, <>}](p: ($tyPair)^{a, b}) = {
      p[A]((x: A^<>, y: B^{x, <>}) => { x })
    };
    """
    val fst = defFstWithName("fst")

    def defSndWithName(f: String) = s"""
    def $f[A^a <: Top^<>, B^b <: Top^{a, <>}](p: ($tyPair)^{a, b}) = {
      p[B]((x: A^<>, y: B^{x, <>}) => { y })
    };
    """
    val snd = defSndWithName("snd")
  }

  test("Sec 2.4.2 - Opauqe Pairs") {
    import OpaquePair._

    val inScopeUse = s"""
    $makePair
    $fst
    $snd
    val c1 = Ref 42; 
    val c2 = Ref 100;
    topval p = makePair[Ref[Int]^c1, Ref[Int]^c2](c1, c2);
    fst[Ref[Int]^c1, Ref[Int]^c2](p)
    """
    assert(prettyQType(parseAndCheck(inScopeUse)) == "Ref[Int^∅]^p")

    val outScopeUse = s"""
    $makePair
    $fst
    $snd
    def f(x: Int) = {                                                                                                                           
      val c1 = Ref x;
      val c2 = Ref (x+1);
      makePair[Ref[Int]^c1, Ref[Int]^c2](c1, c2) 
    };
    topval p = f(1);
    snd[Ref[Int]^p, Ref[Int]^p](p)
    """
    assert(prettyQType(parseAndCheck(outScopeUse)) == "Ref[Int^∅]^p")
  }

  test("Sec 2.4.2 - Transparent to Opaque Pairs") {
    // Defining the coercion function via eta-expansion
    val convPrelude = s"""
    ${OpaquePair.defMakePairWithName("makeOPair")}
    ${TransparentPair.defFstWithName("tfst")}
    ${TransparentPair.defSndWithName("tsnd")}
    def conv[A^a <: Top^<>, B^b <: Top^{a, <>}](p: (${TransparentPair.tyPair})^{a, b, <>}) = {
      makeOPair[A^a, B^b](tfst[A^a, B^b](p), tsnd[A^a, B^b](p))
    };
    """

    // A locally defined transparent pair is converted to opaque pair before returning:
    val example = s"""
    $convPrelude
    ${TransparentPair.defMakePairWithName("makeTPair")}
    ${OpaquePair.defFstWithName("ofst")}
    ${OpaquePair.defSndWithName("osnd")}
    def f(x: Int) = {
      val c1 = Ref x;
      val c2 = Ref (x+1);
      val p = makeTPair[Ref[Int]^c1, Ref[Int]^c2](c1, c2);
      // conv[Ref[Int]^p, Ref[Int]^p](p)   // works too
      conv[Ref[Int]^c1, Ref[Int]^c2](p)
    };
    topval p = f(1);
    ofst[Ref[Int]^p, Ref[Int]^p](p)
    """
    assert(prettyQType(parseAndCheck(example)) == "Ref[Int^∅]^p")

    // try the other direction?
    val example2 = s"""
    $convPrelude
    ${TransparentPair.defMakePairWithName("makeTPair")}
    ${OpaquePair.defFstWithName("ofst")}
    ${OpaquePair.defSndWithName("osnd")}
    //def conv2[A^a <: Top^<>, B^b <: Top^{a, <>}](p: (${OpaquePair.tyPair})^{a, b}) = {
    //  makeTPair[A^a, B^b](ofst[A^a, B^b](p), osnd[A^a, B^b](p))
    //}
    topval c1 = Ref 1;
    topval c2 = Ref 2;
    topval p = makeTPair[Ref[Int]^c1, Ref[Int]^c2](c1, c2);
    topval p2 = conv[Ref[Int]^c1, Ref[Int]^c2](p);
    //val p3 = makeTPair[Ref[Int]^c1, Ref[Int]^c2](ofst[Ref[Int]^c1, Ref[Int]^c2](p2), osnd[Ref[Int]^c1, Ref[Int]^c2](p2))
    ofst[Ref[Int]^c1, Ref[Int]^c2](p2)
    """
    println(prettyQType(parseAndCheck(example2)))
  }

  test("Fig 1 and Sec 2.5 - counter example and neste mutable references") {
    // Here we use opaque pairs
    import OpaquePair._

    // A pair of thunk types
    val tyThunk = "(() => Unit)"

    val counterPrelude = s"""
    $makePair
    $fst
    $snd
    def counter(n: Int) = {
      val x = Ref n;
      val inc = () => { x := (! x) + 1 };
      val dec = () => { x := (! x) - 1 };
      makePair[${tyThunk}^x, ${tyThunk}^x](inc, dec)
    };
    topval p = counter(0);
    val inc = fst[${tyThunk}^p, ${tyThunk}^p](p);
    val dec = snd[${tyThunk}^p, ${tyThunk}^p](p);
    """

    // Check type of the increse function
    val inc = s"""
    $counterPrelude
    inc
    """
    assert(prettyQType(parseAndCheck(inc)) == "(𝑓#0(𝑥#1: Unit^∅) => Unit^∅)^p")

    // Check type of the decrease function
    val dec = s"""
    $counterPrelude
    dec
    """
    assert(prettyQType(parseAndCheck(dec)) == "(𝑓#6(𝑥#7: Unit^∅) => Unit^∅)^p")

    // Nested references (Sec 2.5)
    val nested1 = s"""
    $counterPrelude
    topval cf = Ref inc;
    cf
    """
    assert(prettyQType(parseAndCheck(nested1)) == "Ref[(𝑓#0(𝑥#1: Unit^∅) => Unit^∅)^p]^cf")

    val nested2 = s"""
    $counterPrelude
    topval cf = Ref inc;
    val _ = cf := dec;   // storing the dec function
    !cf
    """
    assert(prettyQType(parseAndCheck(nested2)) == "(𝑓#0(𝑥#1: Unit^∅) => Unit^∅)^p")
  }
}
