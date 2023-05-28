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

case class NotSubtype(t1: Type, t2: Type)
  extends RuntimeException(s"$t1 not subtype of $t2")

case class NotSubQType(t1: QType, t2: QType)(Γ: Option[TEnv] = None)
  extends RuntimeException(s"$t1 not qualified subtype of $t2 under $Γ")

case class NonOverlap(permitted: Qual, overlap: Qual)
  extends RuntimeException(s"permit $permitted, but found overlap $overlap")

case class DeepDependency(t: Type, vbl: String)
  extends RuntimeException(s"$t cannot deeply depend on $vbl")

type TEnv = AssocList[QType]
val TEnv = AssocList

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
    Qual(q.saturated.intersect(q2.saturated).asInstanceOf[Set[QElem]]) + Fresh()

def reach(worklist: Set[String], acc: Set[String])(using Γ: TEnv): Set[String] =
  if (worklist.isEmpty) acc
  else {
    val x = worklist.head
    val QType(t, q) = Γ(x)
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
def qualTypeEq(t1: QType, t2: QType)(using Γ: TEnv): Boolean = isSubQType(t1, t2) && isSubQType(t2, t1)

def checkQualEq(q1: Qual, q2: Qual)(using Γ: TEnv): Qual =
  if (qualEq(q1, q2)) q1
  else throw QualMismatch(q1, q2)

def checkTypeEq(e: Expr, actual: Type, exp: Type)(using Γ: TEnv): Type =
  if (typeEq(actual, exp)) actual
  else throw TypeMismatch(e, actual, exp)

def checkQTypeEq(e: Expr, actual: QType, exp: QType)(using Γ: TEnv): QType =
  if (qualTypeEq(actual, exp)) actual
  else throw QualTypeMismatch(e, actual, exp)

def checkUntrackQual(q: Qual)(using Γ: TEnv): Unit = checkQualEq(q, Qual(Set()))

// One-step qualifier exposure (i.e. implementing q-self and q-var)
def qualElemExposure(q: Qual, x: String)(using Γ: TEnv): Qual = {
  require(!q.contains(x))
  Γ(x) match
    case QType(TFun(_, _, _, _), r) if r.nonFresh && r ⊆ Γ =>
      val res = (q \ r) + x
      //println(s"exposing function $Γ ⊢ $x ^ $r -> $res")
      res
    case QType(t, r) if !t.isInstanceOf[TFun] && r.nonFresh =>
      val res = q ∪ r
      //println(s"exposing variable $Γ ⊢ $x ^ $r -> $res")
      res
    case _ =>
      val res = q + x
      //println(s"remaining unchanged $Γ ⊢ $x -> $res")
      res
}

// One iteration of exposure over the qualifier
def qualExposure0(q: Qual)(using Γ: TEnv): Qual = {
  require(!q.isFresh)
  if (q.isUntrack) q
  else {
    val ps: Set[Set[String]] = q.set.asInstanceOf[Set[String]].map { x =>
        qualElemExposure(q - x, x).set.asInstanceOf[Set[String]]
    }
    Qual(ps.tail.foldLeft(ps.head)((a, b) => a.intersect(b)))
  }
}

/*
Note: this one is buggy
def qualExposure0(q: Qual)(using Γ: TEnv): Qual = {
  require(!q.isFresh)
  if (q.isUntrack) q
  else {
    val x = q.set.head.asInstanceOf[String]
    val p = q - x
    val r = qualElemExposure(p, x)
    if (r.contains(x)) qualExposure0(r - x) + x
    else qualExposure0(r)
  }
}
*/

// Iteratively qualifier exposure via fixpoint iteration.
// Compute the maximum upper bound qualifier following the subtyping "lattice" under Γ.
// XXX: is that upper bound unique? eg different traverse order may lead to different result?
def fixQualExposure(q1: Qual, q2: Qual)(using Γ: TEnv): Qual = {
  //println(s"fixpoint $q1 $q2 ${q1 == q2}")
  if (q1 == q2) q2
  else fixQualExposure(q2, qualExposure0(q2))
}

def qualExposure(q: Qual)(using Γ: TEnv): Qual =
  val p = fixQualExposure(Qual(Set()), q - ◆)
  if (q.isFresh) p + ◆ else p

// the Q-Sub rule
def isSubset(q1: Qual, q2: Qual)(using Γ: TEnv): Boolean = q1 ⊆ q2 && q2 ⊆ Γ

//def isSubqual(q1: Qual, q2: Qual)(using Γ: TEnv): Boolean =
  // if (isSubset(q1, q2)) true
  // else if (isSubset(qualExposure(q1), qualExposure(q2))) true
  // else false

def isSubqual(q1: Qual, q2: Qual)(using Γ: TEnv): Boolean =
  //println(s"$Γ ⊢ $q1 <: $q2")
  // TODO: some well-formedness condition seems missing
  def qSelf(q: Set[QElem]): Set[QElem] =
    q.flatMap {
      case x: String => Γ(x) match
        case QType(TFun(_, _, _, _), q) if !q.isFresh => Set(x) ++ q.set
        case _ => Set(x)
      case d => Set(d)
    }
  val q2ext = fix(qSelf)(q2.set)
  def bounded(e: QElem): Boolean =
    q2ext(e) || { e match
      case x: String => Γ(x) match
        case QType(t, q) if !t.isInstanceOf[TFun] && !q.isFresh => q.set.forall(bounded)
        case _ => false
      case _ => false
    }
  q1.set.forall(bounded)

def qualSubst(q: Qual, from: String, to: Qual): Qual =
  if (q.contains(from)) q - from ++ to.set else q

def typeSubst(t: Type, from: String, to: Qual): Type = t match {
  case TUnit | TNum | TBool => t
  case TFun(f, x, t1, t2) =>
    // XXX: need capture-free renaming of f and x?
    TFun(f, x, qtypeSubst(t1, Some(from), to), qtypeSubst(t2, Some(from), to))
  case TRef(t) => TRef(typeSubst(t, from, to))
}

def qtypeSubst(qt: QType, from: Option[String], to: Qual): QType =
  from match {
    case None => qt
    case Some(from) =>
      val QType(t, q) = qt
      QType(typeSubst(t, from, to), qualSubst(q, from, to))
  }

def qtypeRename(tq: QType, from: String, to: String): QType = {
  val QType(t, q) = tq
  val newQual = qualSubst(q, from, Qual.singleton(to))
  QType(typeRename(t, from, to), newQual)
}

// Rename free occurrence of `from` in `t` to `to`
def typeRename(t: Type, from: String, to: String): Type = t match {
  case TUnit | TNum | TBool => t
  case TFun(Some(f), Some(x), t1, t2) =>
    if (f == from || x == from) t
    else if (f == to) {
      val g = Counter.fresh()
      val argType = qtypeRename(t1, f, g)
      val retType = qtypeRename(t2, f, g)
      typeRename(TFun(Some(g), Some(x), argType, retType), from, to)
    } else if (x == to) {
      val y = Counter.fresh()
      val argType = qtypeRename(t1, x, y)
      val retType = qtypeRename(t2, x, y)
      typeRename(TFun(Some(f), Some(y), argType, retType), from, to)
    } else TFun(Some(f), Some(x), qtypeRename(t1, from, to), qtypeRename(t2, from, to))
  case TFun(Some(f), None, t1, t2) =>
    if (f == from) t
    else if (f == to) {
      val g = Counter.fresh()
      val argType = qtypeRename(t1, f, g)
      val retType = qtypeRename(t2, f, g)
      typeRename(TFun(Some(g), None, argType, retType), from, to)
    } else TFun(Some(f), None, qtypeRename(t1, from, to), qtypeRename(t2, from, to))
  case TFun(None, Some(x), t1, t2) =>
    if (x == from) t
    else if (x == to) {
      val y = Counter.fresh()
      val argType = qtypeRename(t1, x, y)
      val retType = qtypeRename(t2, x, y)
      typeRename(TFun(None, Some(y), argType, retType), from, to)
    } else TFun(None, Some(x), qtypeRename(t1, from, to), qtypeRename(t2, from, to))
  case TFun(None, None, t1, t2) =>
    TFun(None, None, qtypeRename(t1, from, to), qtypeRename(t2, from, to))
  case TRef(t) =>
    TRef(typeRename(t, from, to))
}

def isSubtype(t1: Type, t2: Type)(using Γ: TEnv): Boolean = (t1, t2) match {
  case (TUnit, TUnit) => true
  case (TNum, TNum) => true
  case (TBool, TBool) => true
  case (tf@TFun(Some(f), Some(x), t1, t2), tg@TFun(Some(g), Some(y), t3, t4)) =>
    if (f == g && x == y) {
      val Γ1 = Γ + (f -> (tf ^ ◆)) + (x -> t3)
      isSubQType(t3, t1) && isSubQType(t2, t4)(using Γ1)
    } else if (f != g && x == y) {
      val f1 = Counter.fresh()
      isSubtype(TFun(Some(f1), Some(x), qtypeRename(t1, f, f1), qtypeRename(t2, f, f1)),
        TFun(Some(f1), Some(x), qtypeRename(t3, g, f1), qtypeRename(t4, g, f1)))
    } else if (f == g && x != y) {
      val x1 = Counter.fresh()
      isSubtype(TFun(Some(f), Some(x1), qtypeRename(t1, x, x1), qtypeRename(t2, x, x1)),
        TFun(Some(f), Some(x1), qtypeRename(t3, y, x1), qtypeRename(t4, y, x1)))
    } else {
      val f1 = Counter.fresh()
      val x1 = Counter.fresh()
      val tf1 = TFun(Some(f1), Some(x1),
        qtypeRename(qtypeRename(t1, x, x1), f, f1),
        qtypeRename(qtypeRename(t2, x, x1), f, f1))
      val tg1 = TFun(Some(f1), Some(x1),
        qtypeRename(qtypeRename(t3, y, x1), g, f1),
        qtypeRename(qtypeRename(t4, y, x1), g, f1))
      isSubtype(tf1, tg1)
    }
  case (tf@TFun(Some(f), None, t1, t2), tg@TFun(Some(g), None, t3, t4)) =>
    if (f == g) {
      val Γ1 = Γ + (f -> (tf ^ ◆))
      isSubQType(t3, t1) && isSubQType(t2, t4)(using Γ1)
    } else {
      val f1 = Counter.fresh()
      isSubtype(TFun(Some(f1), None, qtypeRename(t1, f, f1), qtypeRename(t2, f, f1)),
        TFun(Some(f1), None, qtypeRename(t3, g, f1), qtypeRename(t4, g, f1)))
    }
  case (TFun(None, Some(x), t1, t2), TFun(None, Some(y), t3, t4)) =>
    if (x == y) {
      val Γ1 = Γ + (x -> t3)
      isSubQType(t3, t1) && isSubQType(t2, t4)(using Γ1)
    } else {
      val x1 = Counter.fresh()
      isSubtype(TFun(None, Some(x1), qtypeRename(t1, x, x1), qtypeRename(t2, x, x1)),
        TFun(None, Some(x1), qtypeRename(t3, y, x1), qtypeRename(t4, y, x1)))
    }
  case (TFun(None, None, t1, t2), TFun(None, None, t3, t4)) =>
    isSubQType(t3, t1) && isSubQType(t2, t4)
  case (TRef(t1), TRef(t2)) => typeEq(t1, t2)
  case _ => false
}

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
    // XXX: why require saturation here?
    val sq1 = Qual(q1.saturated)
    val sq2 = Qual(q2.saturated)
    if (isSubqual(sq1, sq2)) ()
    else throw NonOverlap(sq2, sq1 \ sq2)
  } else throw NotSubtype(t1, t2)

def checkDeepDep(t: Type, x: Option[String]): Unit =
  if (typeFreeVars(t).intersect(x.toSet).isEmpty) ()
  else throw DeepDependency(t, x.get)

def qtypeFreeVars(qt: QType): Set[String] = {
  val QType(t, q) = qt
  typeFreeVars(t) ++ q.varSet
}

def typeFreeVars(t: Type): Set[String] = t match
  case TUnit | TNum | TBool => Set()
  case TFun(f, x, t1, t2) =>
    (qtypeFreeVars(t1) ++ qtypeFreeVars(t2)) -- (f.toSet ++ x.toSet)
  case TRef(t) => typeFreeVars(t)

def freeVars(e: Expr): Set[String] = e match {
  case EUnit | ENum(_) | EBool(_) => Set()
  case EVar(x) => Set(x)
  case EBinOp(op, e1, e2) => freeVars(e1) ++ freeVars(e2)
  case ELam(f, x, _, e, _) => freeVars(e) -- Set(f, x)
  case EApp(e1, e2) => freeVars(e1) ++ freeVars(e2)
  case ELet(x, _, rhs, body) => freeVars(rhs) ++ (freeVars(body) - x)
  case EAlloc(e) => freeVars(e)
  case EUntrackedAlloc(e) => freeVars(e)
  case EAssign(e1, e2) => freeVars(e1) ++ freeVars(e2)
  case EDeref(e) => freeVars(e)
  case ECond(cnd, thn, els) => freeVars(cnd) ++ freeVars(thn) ++ freeVars(els)
}

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
    // XXX well-formedness check? at/rt qualifier should be smaller than ctx? or check at call-site?
    val fv = freeVars(e) -- Set(f, x)
    val Γ1 = Γ.filter(fv) + (x -> at) + (f -> (ft ^ Qual(fv.asInstanceOf[Set[QElem]])))
    val t = typeCheck(e)(using Γ1)
    checkSubQType(t, rt)(using Γ1)
    ft ^ Qual(fv.asInstanceOf[Set[QElem]])
  case ELam(_, x, at, e, None) =>
    val fv = freeVars(e) - x
    val t = typeCheck(e)(using Γ.filter(fv) + (x -> at))
    TFun(None, Some(x), at, t) ^ Qual(fv.asInstanceOf[Set[QElem]])
  case EApp(e1, e2) =>
    val t1@QType(TFun(f, x, atq@QType(at, aq), rtq@QType(rt, rq)), qf) = typeCheck(e1)
    //println(t1)
    // FIXME: function type's qualifier needs to include free variables of types (not only terms)
    val codomBound: Qual =
      Qual(Γ.dom.asInstanceOf[Set[QElem]]) ++ f.toSet ++ x.toSet ++ Set(◆)
    //println(codomBound)
    if (!(rq ⊆ codomBound)) throw RuntimeException("ill-formed qualifier " + rq)
    val tq2@QType(t2, q2) = typeCheck(e2)
    if (!aq.isFresh) {
      // T-App
      checkSubQType(tq2, atq)
      if (q2.contains(◆)) throw RuntimeException(s"${tq2} is possibly fresh")
      qtypeSubst(qtypeSubst(rtq, x, q2), f, qf)
    } else {
      // println(s"tq2: $tq2 rt: $rt fv(rt): ${typeFreeVars(rt)}")
      // T-App◆
      // ◆ ∈ q2 ⇒ x ∉ fv(rt)
      if (q2.isFresh) checkDeepDep(rt, x)
      // ◆ ∈ qf ⇒ f ∉ fv(rt)
      if (qf.isFresh) checkDeepDep(rt, f)
      checkSubtypeOverlap(t2 ^ (q2 ⋒ qf), atq)
      qtypeSubst(qtypeSubst(rtq, x, q2), f, qf)
    }
  case ELet(x, Some(qt1), rhs, body) =>
    val QType(t1, q1) = qt1
    val qt2 = typeCheck(rhs)
    checkSubQType(qt2, qt1)
    val rt = typeCheck(body)(using Γ + (x -> qt1))
    // Note: here we are not using the more precise qualifier for substitution
    if (q1.isFresh) checkDeepDep(rt.ty, Some(x))
    qtypeSubst(rt, Some(x), q1)
  case ELet(x, None, rhs, body) =>
    val qt@QType(t, q) = typeCheck(rhs)
    val rt = typeCheck(body)(using Γ + (x -> qt))
    if (q.isFresh) checkDeepDep(rt.ty, Some(x))
    qtypeSubst(rt, Some(x), q)
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
}

def topTypeCheck(e: Expr): QType = {
  Counter.reset
  typeCheck(e)(using TEnv.empty)
}
