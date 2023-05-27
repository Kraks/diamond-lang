package diamond.fsub.full

import diamond._
import diamond.fsub._
import Type._
import TypeSyntax._
import Expr._
import ExprSyntax._

def checkSubtypeFull(t1: Type, t2: Type)(using Γ: TEnv): Unit =
  if (isSubtypeFull(t1, t2)) ()
  else throw NotSubtype(t1, t2)

def isSubtypeFull(t1: Type, t2: Type)(using Γ: TEnv): Boolean =
  println(s"$Γ ⊢ $t1 <: $t2")
  (t1, t2) match {
    case (_, TTop) => true
    case (TUnit, TUnit) => true
    case (TNum, TNum) => true
    case (TBool, TBool) => true
    case (TVar(x), TVar(y)) if x == y => true
    case (x@TVar(_), t) => isSubtypeFull(Γ(x), t)
    case (TFun(t1, t2), TFun(t3, t4)) =>
      isSubtypeFull(t3, t1) && isSubtypeFull(t2, t4)
    case (TForall(x, b1, t1), TForall(y, b2, t2)) =>
      if (x == y) {
        checkSubtypeFull(b2, b1)
        isSubtypeFull(t1, t2)(using Γ + (x <∶ b2))
      } else {
        val z = Counter.fresh()
        isSubtypeFull(TForall(z, b1, typeSubst(t1, x, TVar(z))),
            TForall(z, b2, typeSubst(t2, y, TVar(z))))
      }
    case (TRef(t1), TRef(t2)) => typeEq(t1, t2)
    case _ => false
  }
