package diamond.avoidancefsub
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import diamond._
import diamond.avoidancefsub.core._
import diamond.avoidancefsub.Parser._

import core.Type._
import core.Expr._
import TypeSyntax._
import ExprSyntax._

import TypeSyntax.given_Conversion_Type_QType
import ExprSyntax.given_Conversion_Int_ENum

class AvoidanceFSubTests extends AnyFunSuite {
  def parseAndCheck(s: String): QType = topTypeCheck(parseToCore(s))

  test("type arg infer") {
    val p1 = """
    def id[T <: Top](x: T^<>): T^x = x;
    val x = id(3);                  // : Int^∅
    val c = id(Ref 42);             // : Ref[Int]^◆
    val y = id(x);                  // : Int^∅
    (! c)                           // : Int^∅
    """
    parseToCore(p1)
    //assert(parseAndCheck(p1) == (TNum^()))
  }

  test("forall type") {
    val p = """
    val p: forall [A^a <: Top^<>] => ((x: A^a) => A^x)^a =
      ([A^a <: Top^<>] => { (x: A^a) => { x } });
    p
    """
    parseToCore(p)
    //assert(parseAndCheck(p) ==
    //  (TForall("𝐹#2","A","a",TTop^ ◆,
    //    TFun("𝑓#3","x",TVar("A")^"a",TVar("A")^"x")^"a")^()))
  }

  test("transparent poly pair") {
    def makePair(a: String, b: String) = s"""
      [A <: Top] => { [B <: Top] => {
        [C <: Top] => {
          p(f: (f(x: A^$a) => (g(y: B^$b) => C^g)^f)^{<>, p})^{$a, $b}: C^f => { f($a)($b) }
        }
    } }
    """
    def fstT(a: String)(p: String) = s"""
      [A <: Top] => { [B <: Top] => {
        $p(f(x: A^$a)^$a: (g(y: B^{<>, g}) => A^g)^f => { g(y: B^{<>, g})^f: A^g => { x } })
      } }
    """
    def sndT(b: String)(p: String) = s"""
      [A <: Top] => { [B <: Top] => {
        $p(f(x: A^{<>, f})^$b: (g(y: B^$b) => B^g)^f => { g(y: B^$b)^f: B^g => { y } })
      } }
    """
    //println(parseToCore(makePair("r1", "r2")))
    //println(parseToCore(fstT("r1")("p")))
    //println(parseToCore(sndT("r1")("p")))

    /*
    val prog1 = s"""
      val r1 = Ref 1;
      val r2 = Ref 2;
      val p = ${makePair("Ref[Int]")("r1", "r2")};
      ${fstT("Ref[Int]", "r1")("p")}
    """
    assert(parseAndCheck(prog1) == (TRef(TNum^()) ^ ◆))
    val prog2 = s"""
      val r1 = Ref 1;
      val r2 = Ref 2;
      val p = ${makePair("Ref[Int]")("r1", "r2")};
      ${sndT("Ref[Int]", "r2")("p")}
    """
    assert(parseAndCheck(prog2) == (TRef(TNum^()) ^ ◆))
    */
  }

  test("opaque mono pair") {
    def makePair(a: String, b: String) = s"""
      [A <: Top] => { [B <: Top] => {
        [C <: Top] => {
          p(f: (f(x: A^$a) => (g(y: B^$b) => C^g)^f)^{<>, p})^{$a, $b}: C^f => { f($a)($b) }
        }
    } }
    """
    def fstO(p: String) = s"""
      [A <: Top] => { [B <: Top] => {
        $p(f(x: A^{<>, f}): (g(y: B^{<>,g}) => A^{g,y})^{x, f} => { g(y: B^{<>, g})^{x, f}: A^{g, y} => { x } })
      } }
    """
    def sndO(p: String) = s"""
      [A <: Top] => { [B <: Top] => {
        $p(f(x: A^{<>, f}): (g(y: B^{<>,g}) => B^{g,y})^{x, f} => { g(y: B^{<>, g})^{x, f}: A^{g, y} => { y } })
      } }
    """

    /*
    val prog1 = s"""
      def makePair() = {
        val r1 = Ref 1;
        val r2 = Ref 2;
        val p0 = ${makePair("Ref[Int]")("r1", "r2")};
        p0
      };
      makePair()
    """
    assert(parseAndCheck(prog1) ==
      (TFun("p","f",
        TFun("f","x",TRef(TNum^()) ^ ("f", ◆),
          TFun("g","y",TRef(TNum^()) ^ ("g", ◆),
            TRef(TNum^()) ^ ("g","y")) ^ ("f","x")) ^ ("p", ◆), TRef(TNum^()) ^ ("f", "p")) ^ ◆))

    val prog2 = s"""
      def makePair() = {
        val r1 = Ref 1;
        val r2 = Ref 2;
        val p0 = ${makePair("Ref[Int]")("r1", "r2")};
        p0
      };
      val p1 = makePair();
      ${fstO("Ref[Int]")("p1")}
    """
    assert(parseAndCheck(prog2) == (TRef(TNum^())^ ◆))

    val prog3 = s"""
      def makePair() = {
        val r1 = Ref 1;
        val r2 = Ref 2;
        val p0 = ${makePair("Ref[Int]")("r1", "r2")};
        p0
      };
      val p1 = makePair();
      ${sndO("Ref[Int]")("p1")}
    """
    assert(parseAndCheck(prog3) == (TRef(TNum^())^ ◆))
    */
  }

}