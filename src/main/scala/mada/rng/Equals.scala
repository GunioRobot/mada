
package mada.rng


// equals

object Equals extends Equals

trait Equals {
    class MadaRngEquals[A1](_1: Expr[Rng[A1]]) {
        def equals[A2](_2: Expr[Rng[A2]], _3: Expr[(A1, A2) => Boolean]) = EqualsExpr(_1, _2, _3).expr
        def equals(_2: Expr[Rng[A1]]) = EqualsExpr(_1, _2, Expr((_: A1) == (_: A1))).expr
    }
    implicit def toMadaRngEquals[A1](_1: Expr[Rng[A1]]) = new MadaRngEquals(_1)
}

case class EqualsExpr[A1, A2](_1: Expr[Rng[A1]], _2: Expr[Rng[A2]], _3: Expr[(A1, A2) => Boolean]) extends Expr[Boolean] {
    def eval = {
        val x1 = _1.toLazy
        val x2 = _2.toLazy
        x1.eval.traversal min x2.eval.traversal match {
            case _: RandomAccessTraversal => {
                if (SizeExpr(x1).eval != SizeExpr(x2).eval) false else EqualsToExpr(x1, BeginExpr(x2), _3).eval
            }
            case _: SinglePassTraversal => EqualsImpl(x1.eval, x2.eval, _3.eval)
        }
    }
}

object EqualsImpl {
    def apply[A1, A2](r1: Rng[A1], r2: Rng[A2], f: (A1, A2) => Boolean): Boolean = {
        val (p1, q1) = (r1.begin, r1.end)
        val (p2, q2) = (r2.begin, r2.end)
        while (p1 != q1 && p2 != q2) {
            if (!f(*(p1), *(p2)))
                return false
            ++(p1); ++(p2)
        }
        (p2 == q2) && (p1 == q1)
    }
}


// equalsTo

object EqualsTo extends EqualsTo

trait EqualsTo {
    class MadaRngEqualsTo[A1](_1: Expr[Rng[A1]]) {
        def equals[A2](_2: Expr[Pointer[A2]], _3: Expr[(A1, A2) => Boolean]) = EqualsToExpr(_1, _2, _3).expr
        def equals(_2: Expr[Pointer[A1]]) = EqualsToExpr(_1, _2, Expr((_: A1) == (_: A1))).expr
    }
    implicit def toMadaRngEqualsTo[A1](_1: Expr[Rng[A1]]) = new MadaRngEqualsTo(_1)
}

case class EqualsToExpr[A1, A2](_1: Expr[Rng[A1]], _2: Expr[Pointer[A2]], _3: Expr[(A1, A2) => Boolean]) extends Expr[Boolean] {
    def eval = {
        val x1 = _1.toLazy
        MismatchExpr(x1, _2, _3).eval._1 == EndExpr(x1).eval
    }
}
