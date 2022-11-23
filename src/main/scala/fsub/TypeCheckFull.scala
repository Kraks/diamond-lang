package diamond.fsub.full

import diamond._
import diamond.fsub._
import Type._
import TypeSyntax._
import Expr._
import ExprSyntax._

def isSubtypeFull(t1: Type, t2: Type)(using Γ: TEnv): Boolean = ???

def checkSubtypeFull(t1: Type, t2: Type)(using Γ: TEnv): Unit =
  if (isSubtypeFull(t1, t2)) ()
  else throw NotSubtype(t1, t2)
