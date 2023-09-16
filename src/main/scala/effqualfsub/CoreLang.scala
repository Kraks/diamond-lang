package diamond.effqualfsub.core

import diamond._

/* System F-Sub + Reference + Diamond-flavored reachability types + Move/Flow-sensitive effect system */

/* Types */

enum Type:
  case TUnit
  case TNum
  case TBool
  case TFun(id: String, arg: String, t1: QType, t2: QType, eff: Eff)
  case TRef(t: QType)
  // F◆ new types
  case TTop
  case TVar(x: String)
  case TForall(f: String, tvar: String, qvar: String, bound: QType, res: QType, eff: Eff)

import Type._

/* Qualifiers */

case class Fresh():
  override def toString = "◆"
type QElem = String | Fresh
object Qual:
  def untrack: Qual = Qual(Set())
  def fresh: Qual = Qual(Set(Fresh()))
  def singleton(x: String): Qual = Qual(Set(x))
case class Qual(set: Set[QElem]):
  override def toString =
    if (set.isEmpty) "∅"
    else if (set.size == 1) set.head.toString
    else s"""{${set.mkString(",")}}"""
case class QType(ty: Type, q: Qual = Qual.untrack):
  override def toString = s"$ty^$q"

/* Effects */

trait SemiLattice[T]:
  extension (x: T)
    def ⊔(y: T): T

trait Monoid[T]:
  def id: T
  extension (x: T)
    def ▷(y: T): T

trait EffQuantale[T] extends SemiLattice[T] with Monoid[T]

enum MemEff:
  case Bot, Read, Write, Kill

import MemEff._

case class KillException(rhs: MemEff) extends RuntimeException(s"Kill ▷ ${rhs} is undefined")

given MemEffQuntanleInstance: EffQuantale[MemEff] with
  def id: MemEff = Bot
  extension (e: MemEff)
    def ⊔(f: MemEff): MemEff = (e, f) match {
      case (Bot, f) => f
      case (e, Bot) => e
      case (Read, f) => f
      case (e, Read) => e
      case (Write, f) => f
      case (e, Write) => e
      case (Kill, f) => f
      case (e, Kill) => e
    }
    def ▷(f: MemEff): MemEff = (e, f) match {
      case (Bot, f) => f
      case (Read, Bot) => Read
      case (Read, f) => f
      case (Write, Bot)
         | (Write, Read) => Write
      case (Write, f) => f
      case (Kill, _) => throw KillException(f)
    }

case class GenEffStore[K, E: EffQuantale](m: Map[Set[K], E])

type Eff = GenEffStore[String, MemEff]
val Eff = GenEffStore[String, MemEff]

given EffStoreQuantaleInstance[K, E: EffQuantale]: EffQuantale[GenEffStore[K, E]] with
  def id: GenEffStore[K, E] = GenEffStore(Map())
  private def merge(xs: Map[Set[K], E], ys: Map[Set[K], E])(using merger: (E, E) => E): Map[Set[K], E] =
    xs.foldRight(ys) { case ((k1, e1), ys) =>
      val overlaps = ys.filter((k2, _) => k1.intersect(k2).nonEmpty).toList
      if (overlaps.isEmpty) ys + (k1 -> e1)
      else {
        val keys = overlaps.map(_._1)
        val effs = overlaps.map(_._2)
        ys.removedAll(keys) + (k1 ++ keys.reduce(_ ++ _) -> merger(e1, effs.reduce(merger)))
      }
    }
  extension (s1: GenEffStore[K, E])
    def ⊔(s2: GenEffStore[K, E]) = GenEffStore(merge(s1.m, s2.m)(using _ ⊔ _))
    def ▷(s2: GenEffStore[K, E]) = GenEffStore(merge(s1.m, s2.m)(using _ ▷ _))

/* Expressions */

enum Expr:
  case EUnit
  case ENum(n: Int)
  case EBool(b: Boolean)
  case EVar(x: String)
  case EUnaryOp(op: String, e: Expr)
  case EBinOp(op: String, e1: Expr, e2: Expr)
  // XXX allow annotating observable filter for ELam?
  case ELam(f: String, x: String, at: QType, body: Expr, rt: Option[QType], eff: Option[Eff])
  case EApp(e1: Expr, e2: Expr, fresh: Option[Boolean] = None)
  case ELet(x: String, t: Option[QType], rhs: Expr, body: Expr, global: Boolean = false)
  case EAlloc(init: Expr)
  case EUntrackedAlloc(init: Expr)
  case EAssign(lhs: Expr, rhs: Expr)
  case EDeref(e: Expr)
  case ECond(cnd: Expr, thn: Expr, els: Expr)
  // F◆ new expressions
  case ETyLam(f: String, tvar: String, qvar: String, bound: QType, body: Expr, rt: Option[QType], eff: Option[Eff])
  case ETyApp(t: Expr, q: QType, fresh: Option[Boolean] = None)
  // Move semantics
  case Move(e: Expr)
  case Swap(e1: Expr, e2: Expr)

/* Auxiliary embedded syntax */

import Expr._
import Type._

case class TypeBound(tvar: String, qvar: String, bound: QType = QType(TTop, Qual.fresh)):
  // Attach a new qualified bound
  def <⦂(t: QType): TypeBound = TypeBound(tvar, qvar, t)

object TypeSyntax:
  val ◆ = Fresh()
  extension (t: QType)
    def ~>(s: QType): TFun = TFun(freshVar("AnnoFun"), freshVar("Arg"), t, s, ???)
  extension (id: String)
    def ♯(t: TFun): TFun = TFun(id, t.arg, t.t1, t.t2, ???)
  extension (t: Type)
    def ^(q: Qual): QType = QType(t, q)
    def ^(q: QElem): QType = QType(t, Qual(Set(q)))
    def ^(q: Unit): QType = QType(t, Qual(Set()))
    def ^(q: Tuple): QType = QType(t, Qual(q.toList.asInstanceOf[List[QElem]].toSet))
  // type to qualified type conversion, default is untracked
  given Conversion[Type, QType] = QType(_, Qual.untrack)
  // F◆ new syntax
  extension (id2: (String, String))
    def <⦂(qt: QType): TypeBound = TypeBound(id2._1, id2._2, qt)
  def ∀(f: String, xt: TypeBound)(t: QType) = TForall(f, xt.tvar, xt.qvar, xt.bound, t, ???)
  def ∀(xt: TypeBound)(t: QType) = TForall(freshVar("AnnoTFun"), xt.tvar, xt.qvar, xt.bound, t, ???)

object ExprSyntax:
  import Expr._
  import Type._

  val 𝑥 = "x"
  val x = EVar("x")
  val 𝑦 = "y"
  val y = EVar("y")
  val 𝑧 = "z"
  val z = EVar("z")
  val 𝑛 = "n"
  val n = EVar("n")
  val 𝑓 = "f"
  val f = EVar("f")
  val 𝑔 = "g"
  val g = EVar("g")

  case class BindTy(id: String, ty: QType) {
    def ⇐(e: Expr): Bind = Bind(id, e, Some(ty))
    def ~>(rt: QType): TFun = TFun(freshVar("AnnoFun"), id, ty, rt, ???)
  }
  case class Bind(id: String, rhs: Expr, ty: Option[QType])

  extension (id: String)
    def ⦂(t: QType): BindTy = BindTy(id, t)
    def ⇐(e: Expr): Bind = Bind(id, e, None)

  def λ(f: String, x: String)(ft: TFun)(e: => Expr): ELam = ELam(f, x, ft.t1, e, Some(ft.t2), ???)
  def λ(f: String, xt: BindTy, rt: QType)(e: => Expr): ELam = ELam(f, xt.id, xt.ty, e, Some(rt), ???)
  def λ(xt: BindTy)(e: => Expr): ELam = ELam(freshVar("AnnoFun"), xt.id, xt.ty, e, None, None)
  def ite(c: Expr)(thn: Expr)(els: Expr): Expr = ECond(c, thn, els)
  def let(xv: Bind)(e: Expr): Expr = ELet(xv.id, xv.ty, xv.rhs, e)
  def alloc(e: Expr): Expr = EAlloc(e)
  // F◆ new syntax
  def Λ(f: String, xt: TypeBound, res: QType)(e: => Expr): ETyLam =
    ETyLam(f, xt.tvar, xt.qvar, xt.bound, e, Some(res), ???)
  def Λ(f: String, xt: TypeBound)(e: => Expr): ETyLam =
    ETyLam(f, xt.tvar, xt.qvar, xt.bound, e, None, None)
  def Λ(xt: TypeBound, res: QType)(e: => Expr): ETyLam =
    ETyLam(freshVar("AnnoTFun"), xt.tvar, xt.qvar, xt.bound, e, Some(res), ???)
  def Λ(xt: TypeBound)(e: => Expr): ETyLam =
    ETyLam(freshVar("AnnoTFun"), xt.tvar, xt.qvar, xt.bound, e, None, None)

  extension (e: Expr)
    def apply(a: Expr): Expr = EApp(e, a)
    def applyFresh(a: Expr): Expr = EApp(e, a, Some(true))
    def apply(n: Int): Expr = EApp(e, ENum(n))
    def ===(e0: Expr): Expr = EBinOp("==", e, e0)
    def ===(e0: Int): Expr = EBinOp("==", e, ENum(e0))
    def +(e0: Expr): Expr = EBinOp("+", e, e0)
    def +(e0: Int): Expr = EBinOp("+", e, ENum(e0))
    def -(e0: Expr): Expr = EBinOp("-", e, e0)
    def -(e0: Int): Expr = EBinOp("-", e, ENum(e0))
    def *(e0: Expr): Expr = EBinOp("*", e, e0)
    def *(e0: Int): Expr = EBinOp("*", e, ENum(e0))
    def /(e0: Expr): Expr = EBinOp("/", e, e0)
    def /(e0: Int): Expr = EBinOp("/", e, ENum(e0))
    def deref: Expr = EDeref(e)
    def assign(e0: Expr): Expr = EAssign(e, e0)
    // F◆ new syntax
    def apply(t: QType): Expr = ETyApp(e, t)
    def applyFresh(t: QType): Expr = ETyApp(e, t, Some(true))

  given Conversion[Int, ENum] = ENum(_)
