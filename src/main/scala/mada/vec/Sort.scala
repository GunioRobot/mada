

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Sort {
    def apply[A](v: Vector[A], lt: (A, A) => Boolean): Vector[A] = new SortVector(v, lt)
}

class SortVector[A](v: Vector[A], lt: (A, A) => Boolean) extends Adapter[A, A] with NotWritable[A] {
    override val * = {
        val w = v.copy
        w.stlSort(0, w.size, lt)
        w
    }
}
