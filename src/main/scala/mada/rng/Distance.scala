
package mada.rng


import FoldLeft._
import Outdirect._
import Size._


object Distance extends Distance; trait Distance extends Predefs {
    class MadaRngDistance[A](_1: Expr[Rng[A]]) {
        def distance = DistanceExpr(_1).expr
    }
    implicit def toMadaRngDistance[A](_1: Expr[Rng[A]]): MadaRngDistance[A] = new MadaRngDistance[A](_1)
}


case class DistanceExpr[A](_1: Expr[Rng[A]]) extends Expr[Long] {
    override def _eval = {
        val z1 = _1.xlazy
        z1.eval.traversal match {
            case _: RandomAccessTraversal => z1.size.eval
            case _: SinglePassTraversal => z1.outdirect.foldLeft(0L, { (b: Long, a: Pointer[A]) => b + 1 }).eval
        }
    }
}
