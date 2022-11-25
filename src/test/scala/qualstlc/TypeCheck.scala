package diamond.qualstlc

import org.scalatest.funsuite.AnyFunSuite

import Type._
import Expr._
import TypeSyntax._
import ExprSyntax._

import TypeSyntax.given_Conversion_Type_QType

class QualSTLCTests extends AnyFunSuite {
  test("syntax") {
    val t1: QType = TNum ^ ()
    assert(t1 == QType(TNum, Qual.untrack))

    val t2: QType = TNum ^ 𝑥
    assert(t2 == QType(TNum, Qual.singleton("x")))

    val t3: QType = TNum ^ ◆
    assert(t3 == QType(TNum, Qual.fresh))

    val t4: QType = TNum ^ (𝑥, ◆)
    assert(t4 == QType(TNum, Qual(Set("x", Fresh()))))

    val t5: TFun = t4 ~> t3
    assert(t5 == TFun(None,QType(TNum,Qual(Set("x", Fresh()))),QType(TNum,Qual(Set(Fresh())))))

    val t6: QType = (𝑓 ♯ t5) ^ ◆
    assert(t6 == QType(TFun(Some("f"),
      QType(TNum,Qual(Set("x", Fresh()))),QType(TNum,Qual(Set(Fresh())))),Qual(Set(Fresh()))))

    val t7: QType = (𝑓 ♯ (TNum ~> TNum)) ^ ◆
    assert(t7 == QType(TFun(Some("f"),QType(TNum,Qual(Set())),QType(TNum,Qual(Set()))),Qual(Set(Fresh()))))

    val t8: QType = (𝑓 ♯ ((TNum ^ 𝑦) ~> (TNum ^ (𝑦, 𝑧)))) ^ ◆
    assert(t8 == QType(TFun(Some("f"),
      QType(TNum,Qual(Set("y"))),QType(TNum,Qual(Set("y", "z")))),Qual(Set(Fresh()))))
  }
}
