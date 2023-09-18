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
  val ⊥ = summon[EffQuantale[Eff]].id

  val p1 = """
  val x = Ref 42;
  !x
  """
  // Note: x is not observable, so no effect manifest
  assert(parseAndCheck(p1) == (TNum^(), ⊥))

  val p1_1 = """
  val x = Ref 42;
  val y = Ref 0;
  val _ = (y := (!x) + 1);
  !y
  """
  // Note: read(x) and write(y) are indistinguishable, since both are not observable
  assert(parseAndCheck(p1_1) == (TNum^(), ⊥))

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
  // Error expected, since the effect signature does not allow write
  intercept[NotSubEffect] { parseAndCheck(p4) }

  val p5 = """
  def id(x: Ref[Int]^<>): Ref[Int]^x @pure = { x };
  topval y = Ref 42;
  id(y)
  """
  // id is a pure function
  assert(parseAndCheck(p5) == (TRef(TNum^())^"y", GenEffStore(Map())))

  val p5_err = """
  def id(x: Ref[Int]^<>): Ref[Int]^x @pure = { val n = !x; x };
  topval y = Ref 42;
  id(y)
  """
  // id is a pure function, so cannot perform effectful operations
  intercept[NotSubEffect] { parseAndCheck(p5_err) }
}

class MoveEffectTests extends AnyFunSuite {
  val p1 = """
  topval x = Ref 42;
  topval y = move x;
  y
  """
  assert(parseAndCheck(p1) == (TRef(TNum^())^"y", GenEffStore(Map(Set("x") -> Kill))))

  val p2 = """
  topval x = Ref 42;
  topval y = x;
  topval z = move x;
  !y
  """
  intercept[KillException] { parseAndCheck(p2) }

  val p3 = """
  topval x = Ref 42;
  topval y = x;
  topval z = move x;
  y
  """
  // Note: because our treatment of variables (no effect at all), it is allowed to return/mention `y`
  // as long as `y` is not used in any effectful way.
  assert(parseAndCheck(p3) == (TRef(TNum^())^"y", GenEffStore(Map(Set("x") -> Kill))))

  val p4 = """
  def free(x: Ref[Int]^<>) = {
    val _ = move(x);
    unit
  };
  topval x = Ref 42;
  topval y = x;
  val _ = free(x);
  !y
  """
  intercept[KillException] { parseAndCheck(p4) }

  val p4_1 = """
  def free(x: Ref[Int]^<>) = { // Note that the effects of `free` are inferred
    val _ = move(x);
    unit
  };
  topval x = Ref 42;
  topval y = x;
  val _ = free(x);
  val _ = (y := 0);
  !x
  """
  intercept[KillException] { parseAndCheck(p4_1) }

  val p5 = """
  topval x = move (Ref 0);
  x
  """
  // Note: the kill of the inner allocation is not observable
  assert(parseAndCheck(p5) == (TRef(TNum^())^"x", ⊥))

  // TODO: parser improvement: allow f: Int => Int @kill(f)
  // TODO: allow only annotate return type or latent effect
  // TODO: omitting "val _ =" has error, toplevel elabration/parser has some error
  // TODO: provide topdef?
  val p6 = """
  def mapOne(f: (Int) => Int @kill(f)): Int @kill(f) = {
    f(0)
  };
  topval g = (x: Int) => { x + 1 };
  mapOne(g)
  """
  assert(parseAndCheck(p6) == (TNum^(),GenEffStore(Map(Set("g") -> Kill))))

  val p6_err1 = """
  def mapOne(f: (Int) => Int @kill(f)): Int @kill(f) = {
    f(0)
  };
  topval g = (x: Int) => { x + 1 };
  val _ = mapOne(g);
  g(1) // error
  """
  intercept[KillException] { parseAndCheck(p6_err1) }

  val p6_err2 = """
  def mapOne(f: (Int) => Int @kill(f)) = {
    f(0)
  };
  topval g = (x: Int) => { x + 1 };
  val _ = mapOne(g);
  topval f = g;
  f(1) // error
  """
  intercept[KillException] { parseAndCheck(p6_err2) }

  val p6_err3 = """
  def mapOne(f: (Int) => Int @kill(f)) = {
    f(0) + f(1)
  };
  mapOne
  """
  intercept[KillException] { parseAndCheck(p6_err3) }

  val p7 = """
  topval r = Ref 42;
  def mapOne(f: ((Int) => Int @kill(f) @read(r))^r ) = {
    f(0)
  };
  topval g = (x: Int) => { (! r) + x };
  mapOne(g)
  """
  // Note: both `r` and `g` are killed, since `r` and `g` are aliased (which seems unavoidable?)
  assert(parseAndCheck(p7) == (TNum^(), GenEffStore(Map(Set("r", "g") -> Kill))))

  val p8 = """
  topval x = Ref 0;     // x: Ref[Int]^<>
  topval y = Ref x;     // y: Ref[Ref[Int]^x]^{x, <>}
  topval z = move(y);   // z: ???
  z
  """
  // XXX: when Ref(x), should we kill x* too? i.e. transfer the ownership into y
  println(parseAndCheck(p8))
}
