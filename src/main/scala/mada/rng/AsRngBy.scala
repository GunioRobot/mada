
package mada.rng


object AsRngBy extends AsRngBy; trait AsRngBy extends Predefs {
    class MadaRngAsRngBy[A](_1: Expr[Rng[A]]) {
        def rng_asRngBy(_2: Traversal) = AsRngByExpr(_1, _2).expr
    }
    implicit def toMadaRngAsRngBy[A](_1: Expr[Rng[A]]): MadaRngAsRngBy[A] = new MadaRngAsRngBy[A](_1)
}


case class AsRngByExpr[A](_1: Expr[Rng[A]], _2: Traversal) extends Expr[Rng[A]] {
    override def _eval = _1 match {
        case AsRngByExpr(x1, x2) if (x2 == _2) => AsRngByExpr(x1, x2).eval // asRngBy-asRngBy fusion
        case _ => AsRngByImpl(_1.eval, _2)
    }
}


object AsRngByImpl {
    def apply[A](r: Rng[A], t: Traversal): Rng[A] = {
        Assert("requires compatible traversals", r.traversal <:< t)
        if (t <:< r.traversal)
            r
        else
            new AsRngByPointer(r.begin, t) <=< new AsRngByPointer(r.end, t)
    }
}

class AsRngByPointer[A](override val _base: Pointer[A], override val _traversal: Traversal)
        extends PointerAdapter[A, A, AsRngByPointer[A]] {
    override def _copy = new AsRngByPointer(base.copy, traversal)
    override def toString = new StringBuilder().append("AsRngByPointer of ").append(base).toString
}
