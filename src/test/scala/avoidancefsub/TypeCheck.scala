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

  test("reachability polymorphism") {
    val p1 = """
    def id[T <: Top](x: T^<>): T^x = x;
    val x = id[Int](3);                  // : Int^∅
    val c = id[Ref[Int]](Ref 42);        // : Ref[Int]^◆
    val y = id[Int](x);                  // : Int^∅
    (! c)                                // : Int^∅
    """
    //assert(parseAndCheck(p1) == (TNum^()))
  }

  test("transparent poly pair") {
    def makePair(a: String, b: String) = s"""
      [A <: Top] => { [B <: Top] => {
        (x: A^$a) => { (y: B^$b) => {
          [C <: Top] => {
            p(f: (f(x: A^$a) => (g(y: B^$b) => C^g)^f)^{<>, p})^{$a, $b}: C^f => { f(x)(y) }
          }
        }
      } }
    """
    def fstT(a: String, p: String) = s"""
      [A <: Top] => { [B <: Top] => {
        $p(f(x: A^$a)^$a: (g(y: B^{<>, g}) => A^g)^f => { g(y: B^{<>, g})^f: A^g => { x } })
      } }
    """
    def sndT(b: String, p: String) = s"""
      [A <: Top] => { [B <: Top] => {
        $p(f(x: A^{<>, f})^$b: (g(y: B^$b) => B^g)^f => { g(y: B^$b)^f: B^g => { y } })
      } }
    """

    val prog1 = s"""
      val r1 = Ref 1;
      val r2 = Ref 2;
      val p = ${makePair("r1", "r2")}[Ref[Int], Ref[Int]](r1, r2);
      p
    """
      //${fstT("r1", "p")}[Ref[Int], Ref[Int]]
    println(parseToCore(prog1))
    assert(parseAndCheck(prog1) == (TRef(TNum^()) ^ ◆))
    val prog2 = s"""
      val r1 = Ref 1;
      val r2 = Ref 2;
      val p = ${makePair("r1", "r2")}[Ref[Int], Ref[Int]];
      ${sndT("r1", "p")}[Ref[Int], Ref[Int]]
    """
    //println(parseToCore(prog2))
    //assert(parseAndCheck(prog2) == (TRef(TNum^()) ^ ◆))
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


/*
ELet(r1,None,EAlloc(ENum(1)),ELet(r2,None,EAlloc(ENum(2)),
ELet(p,None,
  ETyApp(ETyApp(
    ETyLam(𝐹#0,A,𝑥#1,TTop^◆,ETyLam(𝐹#2,B,𝑥#3,TTop^◆,ETyLam(𝐹#4,C,𝑥#5,TTop^◆,ELam(p,f,TFun(f,x,TVar(A)^r1,TFun(g,y,TVar(B)^r2,TVar(C)^g)^f)^{◆,p},EApp(EApp(EVar(f),EVar(r1),None),EVar(r2),None),Some(TVar(C)^f),Some({r1,r2})),None,None),None,None),None,None),
    TRef(TNum^∅)^∅,None),
    TRef(TNum^∅)^∅,None),EVar(p),false),false),false)
*/