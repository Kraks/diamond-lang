package diamond.qualfsub

import diamond._

import Type._
import Expr._

import TypeSyntax._
import TypeSyntax.given_Conversion_Type_QType
import ExprSyntax._

given Conversion[Set[String], Set[QElem]] = _.asInstanceOf[Set[QElem]]

case class QualMismatch(actual: Qual, expect: Qual)
  extends RuntimeException(s"expect qualifier: $expect\n  actual qualifier: $actual")

case class TypeMismatch(e: Expr, actual: Type, expect: Type)
  extends RuntimeException(s"expr:\n  $e\n  expect type: $expect\n  actual type: $actual")

case class QualTypeMismatch(e: Expr, actual: QType, expect: QType)
  extends RuntimeException(s"expr:\n  $e\n  expect type: $expect\n  actual type: $actual")

case class NotSubtype(t1: Type, t2: Type)(Γ: Option[TEnv] = None)
  extends RuntimeException(s"$t1 not subtype of $t2 under $Γ")

case class NotSubQType(t1: QType, t2: QType)(Γ: Option[TEnv] = None)
  extends RuntimeException(s"$t1 not qualified subtype of $t2 under $Γ")

case class NonOverlap(permitted: Qual, overlap: Qual)
  extends RuntimeException(s"permit $permitted, but found overlap $overlap")

case class DeepDependency(t: Type, vbl: String)
  extends RuntimeException(s"$t cannot deeply depend on $vbl")

case class IllFormedQType(t: QType, Γ: TEnv)
  extends RuntimeException(s"ill-formed qualified type $t under $Γ")

case class IllFormedQual(q: Qual)
  extends RuntimeException(s"ill-formed qualifier " + q.set)

case class RequireTypeNonFresh(t: QType)
  extends RuntimeException(s"type $t should be non-fresh")

case class RequireNonFresh(e: Expr, t: QType)
  extends RuntimeException(s"$e: $t should be non-fresh")

case class TEnv(m: AssocList[String, QType], tm: AssocList[TVar, Type], qm: AssocList[String, Qual]):
  def apply(x: String): QType | Qual =
    assert(!(m.contains(x) && qm.contains(x)), "overlap")
    if (m.contains(x)) m(x)
    else qm(x)
  def apply(x: TVar): Type = tm(x)
  def getQualVarBound(q: String): Qual = qm(q)
  def containsQualVar(q: String): Boolean = qm.contains(q)

  def +(xt: (String, (Type|QType))) = xt match {
    case (x, t: Type) => TEnv(m + (x -> t), tm, qm)
    case (x, t: QType) => TEnv(m + (x -> t), tm, qm)
  }
  def +(tb: TypeBound) = TEnv(m, tm + (TVar(tb.tvar) -> tb.bound.ty), qm + (tb.qvar -> tb.bound.q))
  def filter(q: Set[String]) = TEnv(m.filter(q), tm, qm.filter(q))
  def dom: Set[String] = m.dom ++ qm.dom

object TEnv:
  def empty: TEnv = TEnv(AssocList.empty, AssocList.empty, AssocList.empty)

extension (q: Qual)
  def contains(x: QElem): Boolean = q.set.contains(x)
  def varSet: Set[String] = (q.set - Fresh()).asInstanceOf[Set[String]]
  def withVarSet[T](f: Set[String] => Qual): Qual =
    val res = f(varSet)
    if (q.isFresh) res + Fresh() else res
  def size: Int = q.set.size
  def isUntrack: Boolean = q.set.isEmpty
  def isFresh: Boolean = q.set.contains(Fresh())
  def nonFresh: Boolean = !q.set.contains(Fresh())
  def -(x: QElem): Qual = Qual(q.set - x)
  def +(x: QElem): Qual = Qual(q.set + x)
  def ++(q2: Set[QElem]): Qual = Qual(q.set ++ q2)
  def \(q2: Qual): Qual = Qual(q.set -- q2.set)
  def ∪(q2: Qual): Qual = Qual(q.set ++ q2.set)
  def ⊆(q2: Qual): Boolean = q.set.subsetOf(q2.set)
  def ⊆(Γ: TEnv): Boolean = {
    val Qual(s) = q
    s.subsetOf(Γ.dom + ◆)
  }
  // saturated is supposed to be called only within ⋒
  def saturated(using Γ: TEnv): Set[String] = reach(q.varSet, Set())
  def ⋒(q2: Qual)(using Γ: TEnv): Qual =
    //println(s"${q.saturated} ⋒ ${q2.saturated}")
    Qual(q.saturated.intersect(q2.saturated)) + Fresh()

def reach(worklist: Set[String], acc: Set[String])(using Γ: TEnv): Set[String] =
  if (worklist.isEmpty) acc
  else {
    val x = worklist.head
    val q = Γ(x) match {
      case QType(_, q) => q // if x is a term variable
      case q@Qual(_) => q   // if x is an abstract qualifier
    }
    val newQual = q.varSet.filter(z => !acc.contains(z))
    reach((worklist ++ newQual) -- Set(x), acc ++ newQual ++ Set(x))
  }

def typeCheckBinOp(e1: Expr, e2: Expr, op: String, t1: QType, t2: QType)(using Γ: TEnv): Type =
  op match
    case "+" | "-" | "*" | "/" =>
      checkQTypeEq(e1, t1, TNum)
      checkQTypeEq(e2, t2, TNum)
      TNum
    case "=" =>
      checkQTypeEq(e1, t1, TNum)
      checkQTypeEq(e2, t2, TNum)
      TBool

def qualEq(q1: Qual, q2: Qual)(using Γ: TEnv): Boolean = isSubqual(q1, q2) && isSubqual(q2, q1)
def typeEq(t1: Type, t2: Type)(using Γ: TEnv): Boolean = isSubtype(t1, t2) && isSubtype(t2, t1)
def qtypeEq(t1: QType, t2: QType)(using Γ: TEnv): Boolean = isSubQType(t1, t2) && isSubQType(t2, t1)

def checkQualEq(q1: Qual, q2: Qual)(using Γ: TEnv): Qual =
  if (qualEq(q1, q2)) q1
  else throw QualMismatch(q1, q2)

def checkTypeEq(e: Expr, actual: Type, exp: Type)(using Γ: TEnv): Type =
  if (typeEq(actual, exp)) actual
  else throw TypeMismatch(e, actual, exp)

def checkQTypeEq(e: Expr, actual: QType, exp: QType)(using Γ: TEnv): QType =
  if (qtypeEq(actual, exp)) actual
  else throw QualTypeMismatch(e, actual, exp)

def checkUntrackQual(q: Qual)(using Γ: TEnv): Unit = checkQualEq(q, Qual(Set()))

def expandSelf(q: Set[QElem])(using Γ: TEnv): Set[QElem] =
  q.flatMap {
    case x: String => Γ(x) match
      case QType(TFun(_, _, _, _), q) if !q.isFresh => Set(x) ++ q.set
      case _ => Set(x)
    case d => Set(d)
  }

def boundedBy(e: QElem, b: Set[QElem])(using Γ: TEnv): Boolean =
  b.contains(e) || { e match
    case x: String => Γ(x) match
      case QType(t, q) if !t.isInstanceOf[TFun] && !q.isFresh  =>
        q.set.forall(boundedBy(_, b))
      case q@Qual(_) if !q.isFresh => // Q-QVar
        q.set.forall(boundedBy(_, b))
      case _ => false
    case _ => false
  }

def isSubqual(q1: Qual, q2: Qual)(using Γ: TEnv): Boolean =
  //println(s"$Γ ⊢ $q1 <: $q2")
  // TODO: some well-formedness condition seems missing
  val q2ext = fix(expandSelf)(q2.set)
  q1.set.forall(boundedBy(_, q2ext))

def qualSubst(q: Qual, from: String, to: Qual): Qual =
  if (q.contains(from)) q - from ++ to.set else q

def typeSubstQual(t: Type, from: String, to: Qual): Type = t match {
  case TUnit | TNum | TBool => t
  case TFun(Some(f), Some(x), t1, t2) =>
    val f1 = if (to.contains(f)) freshVar(f) else f
    val x1 = if (to.contains(x)) freshVar(x) else x
    val at = qtypeRename(t1, f, f1)
    val rt = qtypeRename(qtypeRename(t2, x, x1), f, f1)
    TFun(Some(f1), Some(x1), qtypeSubstQual(at, from, to), qtypeSubstQual(rt, from, to))
  case TFun(Some(f), None, t1, t2) =>
    val f1 = if (to.contains(f)) freshVar(f) else f
    val at = qtypeRename(t1, f, f1)
    val rt = qtypeRename(t2, f, f1)
    TFun(Some(f1), None, qtypeSubstQual(at, from, to), qtypeSubstQual(rt, from, to))
  case TFun(None, Some(x), t1, t2) =>
    val x1 = if (to.contains(x)) freshVar(x) else x
    val at = t1 //qtypeRename(t1, x, x1)
    val rt = qtypeRename(t2, x, x1)
    TFun(None, Some(x1), qtypeSubstQual(at, from, to), qtypeSubstQual(rt, from, to))
  case TFun(f, x, t1, t2) =>
    TFun(f, x, qtypeSubstQual(t1, from, to), qtypeSubstQual(t2, from, to))
  case TRef(t) => TRef(typeSubstQual(t, from, to))
  // New F◆ types
  case TTop => TTop
  case TVar(x) => TVar(x)
  case TForall(Some(f), tvar, qvar, t1, t2) =>
    val f1 = if (to.contains(f)) freshVar(f) else f
    val qvar1 = if (to.contains(qvar)) freshVar(qvar) else qvar
    val bound = qtypeRename(t1, f, f1)
    val rt = qtypeRename(qtypeRename(t2, qvar, qvar1), f, f1)
    TForall(Some(f1), tvar, qvar1, qtypeSubstQual(bound, from, to), qtypeSubstQual(rt, from, to))
  case TForall(None, tvar, qvar, t1, t2) =>
    val qvar1 = if (to.contains(qvar)) freshVar(qvar) else qvar
    val rt = qtypeRename(t2, qvar, qvar1)
    TForall(None, tvar, qvar1, qtypeSubstQual(t1, from, to), qtypeSubstQual(rt, from, to))
}

def qtypeSubstQual(qt: QType, from: String, to: Qual): QType =
  val QType(t, q) = qt
  QType(typeSubstQual(t, from, to), qualSubst(q, from, to))

def qtypeSubstQual(qt: QType, from: Option[String], to: Qual): QType =
  from match {
    case None => qt
    case Some(from) => qtypeSubstQual(qt, from, to)
  }

def typeSubstType(t: Type, from: String, to: Type): Type = t match {
  case TUnit | TNum | TBool => t
  case TFun(f, x, t1, t2) =>
    TFun(f, x, qtypeSubstType(t1, from, to), qtypeSubstType(t2, from, to))
  case TRef(t) => typeSubstType(t, from, to)
  // New F◆ types
  case TTop => t
  case TVar(x) =>
    if (x == from) to
    else TVar(x)
  case TForall(f, tvar, qvar, t1, t2) =>
    if (tvar == from) t
    else TForall(f, tvar, qvar, qtypeSubstType(t1, from, to), qtypeSubstType(t2, from, to))
}

def qtypeSubstType(t: QType, tvar: String, to: Type): QType =
  QType(typeSubstType(t.ty, tvar, to), t.q)

def qtypeSubst(t: QType, tvar: String, qvar: String, to: QType): QType =
  val QType(toType, toQual) = to
  qtypeSubstQual(qtypeSubstType(t, tvar, toType), qvar, toQual)

def qualRename(q: Qual, from: String, to: String): Qual =
  qualSubst(q, from, Qual.singleton(to))

def qtypeRename(tq: QType, from: String, to: String): QType =
  val QType(t, q) = tq
  QType(typeRename(t, from, to), qualRename(q, from, to))

// Rename free occurrence of term variable `from` in `t` to `to`
def typeRename(t: Type, from: String, to: String): Type = t match {
  case TUnit | TNum | TBool => t
  case TFun(Some(f), Some(x), t1, t2) =>
    if (f == from || x == from) t
    else if (f == to) {
      val g = freshVar(f)
      val argType = qtypeRename(t1, f, g)
      val retType = qtypeRename(t2, f, g)
      typeRename(TFun(Some(g), Some(x), argType, retType), from, to)
    } else if (x == to) {
      val y = freshVar(x)
      val argType = t1 //qtypeRename(t1, x, y)
      val retType = qtypeRename(t2, x, y)
      typeRename(TFun(Some(f), Some(y), argType, retType), from, to)
    } else TFun(Some(f), Some(x), qtypeRename(t1, from, to), qtypeRename(t2, from, to))
  case TFun(Some(f), None, t1, t2) =>
    if (f == from) t
    else if (f == to) {
      val g = freshVar(f)
      val argType = qtypeRename(t1, f, g)
      val retType = qtypeRename(t2, f, g)
      typeRename(TFun(Some(g), None, argType, retType), from, to)
    } else TFun(Some(f), None, qtypeRename(t1, from, to), qtypeRename(t2, from, to))
  case TFun(None, Some(x), t1, t2) =>
    if (x == from) t
    else if (x == to) {
      val y = freshVar(x)
      val argType = t1 //qtypeRename(t1, x, y)
      val retType = qtypeRename(t2, x, y)
      typeRename(TFun(None, Some(y), argType, retType), from, to)
    } else TFun(None, Some(x), qtypeRename(t1, from, to), qtypeRename(t2, from, to))
  case TFun(None, None, t1, t2) =>
    TFun(None, None, qtypeRename(t1, from, to), qtypeRename(t2, from, to))
  case TRef(t) =>
    TRef(typeRename(t, from, to))
  // New F◆ types
  case TTop => TTop
  case TVar(x) => TVar(x)
  case TForall(Some(f), tvar, qvar, t1, t2) =>
    if (f == from || qvar == from) t
    else if (f == to) {
      val g = freshVar(f)
      val bound = qtypeRename(t1, f, g)
      val rt = qtypeRename(t2, f, g)
      typeRename(TForall(Some(g), tvar, qvar, bound, rt), from, to)
    } else if (qvar == to) {
      val pvar = freshVar(qvar)
      val rt = qtypeRename(t2, qvar, pvar)
      typeRename(TForall(Some(f), tvar, pvar, t1, rt), from, to)
    } else TForall(Some(f), tvar, qvar, qtypeRename(t1, from, to), qtypeRename(t2, from, to))
  case TForall(None, tvar, qvar, t1, t2) =>
    if (qvar == from) t
    else if (qvar == to) {
      val pvar = freshVar(qvar)
      val rt = qtypeRename(t2, qvar, pvar)
      typeRename(TForall(None, tvar, pvar, t1, rt), from, to)
    } else TForall(None, tvar, qvar, qtypeRename(t1, from, to), qtypeRename(t2, from, to))
}

def qtypeRenameTVar(t: QType, from: String, to: String): QType =
  val QType(ty, q) = t
  QType(typeRenameTVar(ty, from, to), q)

def typeRenameTVar(t: Type, from: String, to: String): Type = t match {
  case TUnit | TNum | TBool => t
  case TFun(f, x, t1, t2) =>
    TFun(f, x, qtypeRenameTVar(t1, from, to), qtypeRenameTVar(t2, from, to))
  case TRef(t) =>
    typeRenameTVar(t, from, to)
  // New F◆ types
  case TTop => t
  case TVar(x) =>
    if (x == from) TVar(to)
    else TVar(x)
  case TForall(f, tvar, qvar, t1, t2) =>
    if (tvar == to) {
      val tvar1 = freshVar(tvar)
      val bound = qtypeRenameTVar(t1, tvar, tvar1)
      val rt = qtypeRenameTVar(t2, tvar, tvar1)
      typeRenameTVar(TForall(f, tvar1, qvar, bound, rt), from, to)
    } else TForall(f, tvar, qvar, qtypeRenameTVar(t1, from, to), qtypeRenameTVar(t2, from, to))
}

def isSubtype(t1: Type, t2: Type)(using Γ: TEnv): Boolean = (t1, t2) match {
  case (TUnit, TUnit) => true
  case (TNum, TNum) => true
  case (TBool, TBool) => true
  case (F@TFun(f, x, t1, t2), G@TFun(g, y, t3, t4)) =>
    if (f == g && x == y) {
      val Γ1 = (f, x) match
        case (Some(f), Some(x)) => Γ + (f -> (F ^ ◆)) + (x -> t3)
        case (None, Some(x)) => Γ + (x -> t3)
        case (Some(f), None) => Γ + (f -> (F ^ ◆)) 
        case (None, None) => Γ
      isSubQType(t3, t1) && isSubQType(t2, t4)(using Γ1)
    } else if (f != g) {
      val f1 = freshVar()
      val F1 = f match
        case Some(f) => TFun(Some(f1), x, qtypeRename(t1, f, f1), qtypeRename(t2, f, f1))
        case None => TFun(Some(f1), x, t1, t2)
      val G1 = g match
        case Some(g) => TFun(Some(f1), y, qtypeRename(t3, g, f1), qtypeRename(t4, g, f1))
        case None => TFun(Some(f1), y, t3, t4)
      isSubtype(F1, G1)
    } else if (x != y) {
      val x1 = freshVar()
      val F1 = x match
        case Some(x) => TFun(f, Some(x1), t1, qtypeRename(t2, x, x1))
        case None => TFun(f, Some(x1), t1, t2)
      val G1 = y match
        case Some(y) => TFun(g, Some(x1), t3, qtypeRename(t4, y, x1))
        case None => TFun(g, Some(x1), t3, t4)
      isSubtype(F1, G1)
    } else throw new RuntimeException("Impossible")
  case (TRef(t1), TRef(t2)) => typeEq(t1, t2)
  // New F◆ types
  case (_, TTop) => true
  case (TVar(x), TVar(y)) if x == y => true
  case (x@TVar(_), t) => isSubtype(Γ(x), t)
  case (F@TForall(f1, tvar1, qvar1, bound1, rt1), G@TForall(f2, tvar2, qvar2, bound2, rt2)) =>
    if (f1 == f2 && tvar1 == tvar2 && qvar1 == qvar2) {
      val Γ1 = f1 match
        case Some(funName) => Γ + (funName -> (F ^ ◆)) + ((tvar1, qvar1) <⦂ bound2)
        case None => Γ + ((tvar1, qvar1) <⦂ bound2)
      isSubQType(bound2, bound1) && isSubQType(rt1, rt2)(using Γ1)
    } else if (f1 != f2) {
      val g = freshVar()
      val F1 = f1 match
        case Some(f) => TForall(Some(g), tvar1, qvar1, qtypeRename(bound1, f, g), qtypeRename(rt1, f, g))
        case None => TForall(Some(g), tvar1, qvar1, bound1, rt1)
      val G1 = f2 match
        case Some(f) => TForall(Some(g), tvar2, qvar2, qtypeRename(bound2, f, g), qtypeRename(rt2, f, g))
        case None => TForall(Some(g), tvar1, qvar1, bound2, rt2)
      isSubtype(F1, G1)
    } else if (tvar1 != tvar2) {
      val tvar3 = freshVar()
      val F1 = TForall(f1, tvar3, qvar1, qtypeRenameTVar(bound1, tvar1, tvar3), qtypeRenameTVar(rt1, tvar1, tvar3))
      val G1 = TForall(f2, tvar3, qvar2, qtypeRenameTVar(bound2, tvar2, tvar3), qtypeRenameTVar(rt2, tvar2, tvar3))
      isSubtype(F1, G1)
    } else if (qvar1 != qvar2) {
      val qvar3 = freshVar()
      val F1 = TForall(f1, tvar1, qvar3, qtypeRename(bound1, qvar1, qvar3), qtypeRename(rt1, qvar1, qvar3))
      val G1 = TForall(f2, tvar2, qvar3, qtypeRename(bound2, qvar2, qvar3), qtypeRename(rt2, qvar2, qvar3))
      isSubtype(F1, G1)
    } else throw new RuntimeException("Impossible")
  case _ => false
}

def checkSubtype(T: Type, S: Type)(using Γ: TEnv): Unit =
  //println(s"$Γ ⊢ $T <: $S")
  if (isSubtype(T, S)) ()
  else throw NotSubtype(T, S)(Some(Γ))

def isSubQType(T: QType, S: QType)(using Γ: TEnv): Boolean =
  val QType(t1, q1) = T
  val QType(t2, q2) = S
  isSubtype(t1, t2) && isSubqual(q1, q2)

def checkSubQType(T: QType, S: QType)(using Γ: TEnv): Unit =
  //println(s"$Γ ⊢ $T <: $S")
  if (isSubQType(T, S)) ()
  else throw NotSubQType(T, S)(Some(Γ))

def checkSubtypeOverlap(T: QType, S: QType)(using Γ: TEnv): Unit =
  val QType(t1, q1) = T
  val QType(t2, q2) = S
  if (isSubtype(t1, t2)) {
    val sq1 = Qual(q1.saturated)
    val sq2 = Qual(q2.saturated)
    if (isSubqual(sq1, sq2)) ()
    else throw NonOverlap(sq2, sq1 \ sq2)
  } else throw NotSubtype(t1, t2)()

def checkDeepDep(t: Type, x: String): Unit =
  if (!typeFreeVars(t).contains(x)) ()
  else throw DeepDependency(t, x)

def checkDeepDep(t: Type, x: Option[String]): Unit =
  x match
    case Some(x) => checkDeepDep(t, x)
    case None => ()

def qtypeFreeVars(qt: QType): Set[String] =
  val QType(t, q) = qt
  typeFreeVars(t) ++ q.varSet

def typeFreeVars(t: Type): Set[String] = t match
  case TUnit | TNum | TBool => Set()
  case TFun(f, x, t1, t2) =>
    (qtypeFreeVars(t1) ++ qtypeFreeVars(t2)) -- (f.toSet ++ x.toSet)
  case TRef(t) => typeFreeVars(t)
  case TTop => Set()
  case TVar(x) => Set()
  case TForall(f, tvar, qvar, bound, rt) =>
    val boundFreeVars = qtypeFreeVars(bound)
    val rtFreeVars = qtypeFreeVars(rt)
    boundFreeVars ++ (rtFreeVars -- (f.toSet ++ Set(qvar)))

def freeVars(e: Expr): Set[String] = e match {
  case EUnit | ENum(_) | EBool(_) => Set()
  case EVar(x) => Set(x)
  case EBinOp(op, e1, e2) => freeVars(e1) ++ freeVars(e2)
  case ELam(f, x, _, e, _) => freeVars(e) -- Set(f, x)
  case EApp(e1, e2, _) => freeVars(e1) ++ freeVars(e2)
  case ELet(x, _, rhs, body) => freeVars(rhs) ++ (freeVars(body) - x)
  case EAlloc(e) => freeVars(e)
  case EUntrackedAlloc(e) => freeVars(e)
  case EAssign(e1, e2) => freeVars(e1) ++ freeVars(e2)
  case EDeref(e) => freeVars(e)
  case ECond(cnd, thn, els) => freeVars(cnd) ++ freeVars(thn) ++ freeVars(els)
  case ETyLam(f, tvar, qvar, ub, e, _) =>
    freeVars(e) -- (f.toSet ++ Set(qvar))
  case ETyApp(e, qt, _) =>
    freeVars(e) ++ qtypeFreeVars(qt)
}

def typeExposure(t: Type)(using Γ: TEnv): Type = t match {
  case x@TVar(_) => typeExposure(Γ(x))
  case _ => t
}

// XXX: exposure across fresh??
// XXX: this is not used currently
def qualExposure(qual: Qual)(using Γ: TEnv): Qual =
  val vars = qual.varSet
  val (abs, conc) = vars.partition(Γ.containsQualVar(_))
  if (abs.isEmpty) qual
  else qualExposure(Qual(abs.flatMap(Γ.getQualVarBound(_).set))) ++ conc

def qtypeExposure(t: QType)(using Γ: TEnv): QType =
  val QType(ty, q) = t
  //QType(typeExposure(ty), qualExposure(q))
  QType(typeExposure(ty), q)

def qtypeWFCheck(t: QType)(using Γ: TEnv): Unit =
  if (qtypeFreeVars(t).subsetOf(Γ.dom)) ()
  else throw new IllFormedQType(t, Γ)

def typeCheck(e: Expr)(using Γ: TEnv): QType = e match {
  case EUnit => TUnit
  case ENum(_) => TNum
  case EBool(_) => TBool
  case EVar(x) =>
    val QType(t, _) = Γ(x)
    t ^ x
  case EBinOp(op, e1, e2) =>
    typeCheckBinOp(e1, e2, op, typeCheck(e1), typeCheck(e2))
  case ELam(f, x, at, e, Some(rt)) =>
    // XXX allow annotating observable filter?
    val ft = TFun(Some(f), Some(x), at, rt)
    qtypeWFCheck(ft)
    val fv = qtypeFreeVars(at) ++ qtypeFreeVars(rt) ++ freeVars(e) -- Set(f, x)
    val Γ1 = Γ.filter(fv) + (x -> at) + (f -> (ft ^ Qual(fv)))
    val t = typeCheck(e)(using Γ1)
    checkSubQType(t, rt)(using Γ1)
    ft ^ Qual(fv)
  case ELam(_, x, at, e, None) =>
    qtypeWFCheck(at)
    val fv = qtypeFreeVars(at) ++ freeVars(e) - x
    val t = typeCheck(e)(using Γ.filter(fv) + (x -> at))
    TFun(None, Some(x), at, t) ^ Qual(fv)
  case EApp(e1, e2, Some(true)) =>
    // FIXME: function type's qualifier needs to include free variables of types (not only terms)
    // T-App◆
    val t1@QType(TFun(f, x, atq@QType(at, aq), rtq@QType(rt, rq)), qf) = typeCheck(e1)
    val codomBound: Qual = Qual(Γ.dom) ++ f.toSet ++ x.toSet ++ Set(◆)
    if (!(rq ⊆ codomBound)) throw IllFormedQual(rq)
    val tq2@QType(t2, q2) = typeCheck(e2)
    // ◆ ∈ q2 ⇒ x ∉ fv(rt)
    if (q2.isFresh) checkDeepDep(rt, x)
    // ◆ ∈ qf ⇒ f ∉ fv(rt)
    if (qf.isFresh) checkDeepDep(rt, f)
    checkSubtypeOverlap(t2 ^ (q2 ⋒ qf), atq)
    qtypeSubstQual(qtypeSubstQual(rtq, x, q2), f, qf)
  case EApp(e1, e2, Some(false)) =>
    // T-App
    val t1@QType(TFun(f, x, atq@QType(at, aq), rtq@QType(rt, rq)), qf) = typeCheck(e1)
    val codomBound: Qual = Qual(Γ.dom) ++ f.toSet ++ x.toSet ++ Set(◆)
    if (!(rq ⊆ codomBound)) throw IllFormedQual(rq)
    val tq2@QType(t2, q2) = typeCheck(e2)
    checkSubQType(tq2, atq)
    if (q2.isFresh) throw RequireNonFresh(e2, tq2)
    qtypeSubstQual(qtypeSubstQual(rtq, x, q2), f, qf)
  case EApp(e1, e2, None) =>
    // Not specified which application rule to use, try heuristically
    val t1@QType(TFun(f, x, atq@QType(at, aq), rtq@QType(rt, rq)), qf) = typeCheck(e1)
    val codomBound: Qual = Qual(Γ.dom) ++ f.toSet ++ x.toSet ++ Set(◆)
    if (!(rq ⊆ codomBound)) throw IllFormedQual(rq)
    val tq2@QType(t2, q2) = typeCheck(e2)
    if (q2.isFresh) typeCheck(EApp(e1, e2, Some(true)))
    else {
      // When q2 is not fresh, both T-App◆ and T-App are applicable
      try typeCheck(EApp(e1, e2, Some(true)))
      catch case ex: RuntimeException => typeCheck(EApp(e1, e2, Some(false)))
    }
  case ELet(x, Some(qt1), rhs, body) =>
    qtypeWFCheck(qt1)
    val QType(t1, q1) = qt1
    val qt2 = typeCheck(rhs)
    checkSubQType(qt2, qt1)
    val rt = typeCheck(body)(using Γ + (x -> qt1))
    // Note: here we are not using the more precise qualifier for substitution
    if (q1.isFresh) checkDeepDep(rt.ty, Some(x))
    qtypeSubstQual(rt, x, q1)
  case ELet(x, None, rhs, body) =>
    val qt@QType(t, q) = typeCheck(rhs)
    val rt = typeCheck(body)(using Γ + (x -> qt))
    if (q.isFresh) checkDeepDep(rt.ty, Some(x))
    qtypeSubstQual(rt, x, q)
  case EAlloc(e) =>
    val QType(t, q) = typeCheck(e)
    checkUntrackQual(q)
    TRef(t) ^ ◆
  case EUntrackedAlloc(e) =>
    val QType(t, q) = typeCheck(e)
    checkUntrackQual(q)
    TRef(t) ^ ()
  case EAssign(e1, e2) =>
    val QType(TRef(t1), q1) = typeCheck(e1)
    val QType(t2, q2) = typeCheck(e2)
    checkUntrackQual(q2)
    checkTypeEq(e2, t2, t1)
    TUnit
  case EDeref(e) =>
    val QType(TRef(t), q) = typeCheck(e)
    t
  case ECond(cnd, thn, els) =>
    // XXX: instead of requiring the same type, could compute their join
    val t1 = typeCheck(cnd)
    checkQTypeEq(cnd, t1, TBool)
    val t2 = typeCheck(thn)
    val t3 = typeCheck(els)
    checkQTypeEq(thn, t2, t3)
  // New F◆ terms
  case ETyLam(Some(f), tvar, qvar, ub, e, Some(rt)) =>
    val ft = TForall(Some(f), tvar, qvar, ub, rt)
    val fv = qtypeFreeVars(ub) ++ qtypeFreeVars(rt) ++ freeVars(e) -- Set(f, qvar)
    val Γ1 = Γ.filter(fv) + ((tvar, qvar) <⦂ ub) + (f -> (ft ^ Qual(fv)))
    val t = typeCheck(e)(using Γ1)
    checkSubQType(t, rt)(using Γ1)
    ft ^ Qual(fv)
  case ETyLam(_, tvar, qvar, ub, e, None) =>
    val fv = qtypeFreeVars(ub) ++ freeVars(e) -- Set(qvar)
    val Γ1 = Γ.filter(fv) + ((tvar, qvar) <⦂ ub)
    val t = typeCheck(e)(using Γ1)
    TForall(None, tvar, qvar, ub, t) ^ Qual(fv)
  case ETyApp(e, arg@QType(tyArg, qArg), Some(true)) =>
    // T-TApp◆
    qtypeWFCheck(arg)
    val t1 = typeCheck(e)
    val QType(TForall(f, tvar, qvar, ub, rt), qf) = qtypeExposure(t1)
    // qf may contain abstract qualifier variables (which seems fine?)
    val codomBound: Qual = Qual(Γ.dom) ++ f.toSet ++ Set(qvar, ◆)
    if (!(rt.q ⊆ codomBound)) throw IllFormedQual(rt.q)
    if (!(qArg ⊆ Γ)) throw IllFormedQual(qArg)
    if (qArg.isFresh) checkDeepDep(rt.ty, qvar)
    if (qf.isFresh) checkDeepDep(rt.ty, f)
    checkSubtypeOverlap(tyArg ^ (qArg ⋒ qf), ub)
    qtypeSubst(qtypeSubstQual(rt, f, qf), tvar, qvar, arg)
  case ETyApp(e, arg@QType(tyArg, qArg), Some(false)) =>
    // T-TApp
    qtypeWFCheck(arg)
    val t1 = typeCheck(e)
    val QType(TForall(f, tvar, qvar, ub, rt), qf) = qtypeExposure(t1)
    // qf may contain abstract qualifier variables (which seems fine?)
    val codomBound: Qual = Qual(Γ.dom) ++ f.toSet ++ Set(qvar, ◆)
    if (!(rt.q ⊆ codomBound)) throw IllFormedQual(rt.q)
    if (!(qArg ⊆ Γ)) throw IllFormedQual(qArg)
    if (qArg.isFresh) throw RequireTypeNonFresh(arg)
    checkSubQType(arg, ub)
    qtypeSubst(qtypeSubstQual(rt, f, qf), tvar, qvar, arg)
  case ETyApp(e, arg@QType(tyArg, qArg), _) =>
    // Not specified which application rule to use, try heuristically
    qtypeWFCheck(arg)
    val t1 = typeCheck(e)
    val QType(TForall(f, tvar, qvar, ub, rt), qf) = qtypeExposure(t1)
    // qf may contain abstract qualifier variables (which seems fine?)
    val codomBound: Qual = Qual(Γ.dom) ++ f.toSet ++ Set(qvar, ◆)
    if (!(rt.q ⊆ codomBound)) throw IllFormedQual(rt.q)
    if (!(qArg ⊆ Γ)) throw IllFormedQual(qArg)
    if (qArg.isFresh) typeCheck(ETyApp(e, arg, Some(true)))
    else {
      try typeCheck(ETyApp(e, arg, Some(true)))
      catch case ex: RuntimeException => typeCheck(ETyApp(e, arg, Some(false)))
    }
}

def topTypeCheck(e: Expr): QType = {
  Counter.reset
  typeCheck(e)(using TEnv.empty)
}
