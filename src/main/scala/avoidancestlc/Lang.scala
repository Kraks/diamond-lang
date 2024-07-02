package diamond.avoidancestlc

import diamond._

/* STLC + Subtyping + Reference + Diamond-flavored reachability types */

/* Types */

enum Type:
  case TUnit
  case TNum
  case TBool
  case TFun(id: String, arg: String, t1: QType, t2: QType)
  case TRef(t: QType)

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

/* Expressions */

enum Expr:
  case EUnit
  case ENum(n: Int)
  case EBool(b: Boolean)
  case EVar(x: String)
  case ELam(f: String, x: String, at: QType, body: Expr, rt: Option[QType])
  case EApp(e1: Expr, e2: Expr, fresh: Option[Boolean] = None)
  case ELet(x: String, t: Option[QType], rhs: Expr, body: Expr, global: Boolean = false)
  case EAlloc(init: Expr)
  case EAssign(lhs: Expr, rhs: Expr)
  case EDeref(e: Expr)
  case EAscribe(e: Expr, t: QType)
