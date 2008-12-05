
package mada.rng.jcl


// ArrayList[A] <-> Expr[Rng[A]]

object ArrayListCompatible extends ArrayListCompatible; trait ArrayListCompatible {
    implicit def madaRngJcl_ArrayList2RngExpr[A](from: java.util.ArrayList[A]): Expr[Rng[A]] = FromArrayListExpr(Expr(from)).expr
    implicit def madaRngJcl_RngExpr2ArrayList[A](from: Expr[Rng[A]]): java.util.ArrayList[A] = ToArrayListExpr(from).eval
}


// toRng

object ArrayListToRng extends ArrayListToRng; trait ArrayListToRng extends Predefs {
    class MadaRngArrayListToRng[A](_1: Expr[java.util.ArrayList[A]]) {
        def toRng = FromArrayListExpr(_1).expr
    }
    implicit def toMadaRngArrayListToRng[A](_1: Expr[java.util.ArrayList[A]]): MadaRngArrayListToRng[A] = new MadaRngArrayListToRng[A](_1)
}

case class FromArrayListExpr[A](_1: Expr[java.util.ArrayList[A]]) extends Expr[Rng[A]] {
    override def _eval[U](c: Context[Rng[A], U]): U = c match {
        case DefaultContext => _1 match {
            case ToArrayListExpr(x1) => x1.eval
            case _ => delegate.eval
        }
        case _ => delegate.eval(c)
    }

    private def delegate = IndexAccessRngExpr(new ArrayListIndexAccess(_1.eval))
}

class ArrayListIndexAccess[A](val base: java.util.ArrayList[A]) extends IndexAccess[A] {
    override def _set(i: Long, e: A) = { base.set(i.toInt, e) }
    override def _get(i: Long) = base.get(i.toInt)
    override def _size = base.size
}


// toArrayList

object ToArrayList extends ToArrayList; trait ToArrayList extends Predefs {
    class MadaRngToArrayList[A](_1: Expr[Rng[A]]) {
        def rng_jcl_toArrayList = ToArrayListExpr(_1).expr
    }
    implicit def toMadaRngToArrayList[A](_1: Expr[Rng[A]]): MadaRngToArrayList[A] = new MadaRngToArrayList[A](_1)
}

case class ToArrayListExpr[A](_1: Expr[Rng[A]]) extends Expr[java.util.ArrayList[A]] {
    override def _eval = _1 match {
        case FromArrayListExpr(x1) => x1.eval
        case _ => ToArrayListImpl(_1.Lazy)
    }
}

object ToArrayListImpl {
    def apply[A](x: Expr[Rng[A]]): java.util.ArrayList[A] = {
        var a = newArrayList(x)
        ForeachExpr(x, a.add(_: A)).eval
        a
    }

    private def newArrayList[A](x: Expr[Rng[A]]) = x.eval.traversal match {
        case _: RandomAccessTraversal => new java.util.ArrayList[A](SizeExpr(x).eval.toInt)
        case _: SinglePassTraversal => new java.util.ArrayList[A]
    }
}


// ArrayList utilities

object ArrayList {
    def apply[A](es: A*): java.util.ArrayList[A] = {
        val a = new java.util.ArrayList[A](es.length)
        for (e <- es.elements) a.add(e)
        a
    }
}