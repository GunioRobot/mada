

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package vector


private[mada] object ToOrdered {
    def apply[A](from: Vector[A], c: compare.GetOrdered[A]): Ordered[Vector[A]] = {
        Vector.forCompare(compare.fromGetOrdered(c)).toGetOrdered.apply(from)
    }
}
