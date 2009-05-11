

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


/**
 * Contains eligibles around <code>Vector</code>.
 */
trait Eligibles {
    // Hmm, Ordering should have taken [-A]?
    implicit def forOrdering[A](implicit c: Ordering[A]): Ordering[Vector[A]] = {
        compare.toOrdering(forCompare(compare.fromOrdering(c)))
    }

    /*implicit*/ def forOrdering_[A](implicit c: compare.GetOrdered[A]): Ordering[Vector[A]] = {
        compare.toOrdering(forCompare(compare.fromGetOrdered(c)))
    }

    // For unambiguous overload resolution, `implicit` is facing the alternative
    // of `madaVectorToOrdered` or ...
    /*implicit*/ def forCompare[A](implicit c: Compare[A]): Compare[Vector[A]] = new Compare[Vector[A]] {
        override def apply(v: Vector[A], w: Vector[A]) = {
            stl.LexicographicalCompare(v, v.start, v.end, w, w.start, w.end, c)
        }
        override def threeWay(v: Vector[A], w: Vector[A]) = {
            stl.LexicographicalCompare3way(v, v.start, v.end, w, w.start, w.end, c)
        }
    }
}
