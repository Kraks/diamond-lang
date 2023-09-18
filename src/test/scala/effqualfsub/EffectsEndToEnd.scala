package diamond.effqualfsub

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import diamond.effqualfsub.core._
import diamond.effqualfsub.core.given
import diamond._
import Type._
import Expr._
import MemEff._
import TypeSyntax._
import ExprSyntax._
import Parser._
import RunDiamond._

import TypeSyntax.given_Conversion_Type_QType
import ExprSyntax.given_Conversion_Int_ENum

def parseAndCheck(s: String): (QType, Eff) = topTypeCheck(parseToCore(s))
def parseAndEval(s: String): Value = topEval(parseToCore(s))._1

class MemEffectTests extends AnyFunSuite {
  val p1 = """
  val x = Ref 42;
  !x
  """
  assert(parseAndCheck(p1) == (TNum^(), GenEffStore(Map(Set() -> Read)))) // XXX: x is not observable, so there is Set()

  val p2 = """
  topval x = Ref 42; // note the topval
  !x
  """
  assert(parseAndCheck(p2) == (TNum^(), GenEffStore(Map(Set("x") -> Read))))

  val p3 = """
  topval x = Ref 42; // note the topval
  val _ = (x := (! x) + 1);
  !x
  """
  assert(parseAndCheck(p3) == (TNum^(), GenEffStore(Map(Set("x") -> Write))))

  val p4 = """
  Ref 42
  """
  assert(parseAndCheck(p4) == (TRef(TNum^())^ ◆, GenEffStore(Map())))

  val p5 = """
  topval x = Ref 42; // note the topval
  val y = x;
  val _ = (y := (! x) + 1);
  !x
  """
  assert(parseAndCheck(p5) == (TNum^(), GenEffStore(Map(Set("x") -> Write))))

  val p6 = """
  topval x = Ref 42; // note the topval
  topval y = x;
  val _ = (y := (! x) + 1);
  !x
  """
  assert(parseAndCheck(p6) == (TNum^(), GenEffStore(Map(Set("x", "y") -> Write))))
}

class FunEffectTests extends AnyFunSuite {
  val p1 = """
  def f(x: Ref[Int]^<>): Int @read(x) = !x;
  topval y = Ref 42;
  f(y)
  """
  assert(parseAndCheck(p1) == (TNum^(), GenEffStore(Map(Set("y") -> Read))))

  val p2 = """
  topval y = Ref 42;
  topval z = Ref 42;
  def f(x: Ref[Int]^<>): Int @read(y) = !y;
  f(z) // not reading z
  """
  assert(parseAndCheck(p2) == (TNum^(), GenEffStore(Map(Set("y") -> Read))))

  val p3 = """
  topval y = Ref 42;
  def f(x: Ref[Int]^<>): Int @write(x) = {
    val _ = (x := (! x) + 1);
    !x
  };
  f(y)
  """
  assert(parseAndCheck(p3) == (TNum^(), GenEffStore(Map(Set("y") -> Write))))

  val p4 = """
  def f(x: Ref[Int]^<>): Int @read(x) = { // function body can have at most read(x) effect
    val _ = (x := (! x) + 1);
    !x
  };
  topval y = Ref 42;
  f(y)
  """
  intercept[NotSubEffect] { parseAndCheck(p4) }
}
