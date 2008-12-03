
package mada.rng


import Pointer._


object Drop extends Drop; trait Drop extends Predefs {
    class MadaRngDrop[A](_1: Expr[Rng[A]]) {
        def rng_drop(_2: Long) = DropExpr(_1, _2).expr
    }
    implicit def toMadaRngDrop[A](_1: Expr[Rng[A]]): MadaRngDrop[A] = new MadaRngDrop[A](_1)
}


case class DropExpr[A](_1: Expr[Rng[A]], _2: Long) extends Expr[Rng[A]] {
    override def _eval = _1 match {
        case DropExpr(x1, x2) => DropImpl(x1.eval, x2 + _2)
        case _ => DropImpl(_1.eval, _2)
    }
}


object DropImpl {
    def apply[A](r: Rng[A], n: Long): Rng[A] = {
        val (p, q) = r.toPair
        r.traversal match {
            case _: RandomAccessTraversal => {
                p += Math.min(q - p, n)
                p <=< q
            }
            case _: SinglePassTraversal => {
                var m = n
                while (m != 0 && p != q) { ++(p); m -= 1 }
                p <=< q
            }
        }
    }
}
