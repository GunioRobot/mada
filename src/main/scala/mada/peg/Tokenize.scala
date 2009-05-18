

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


case class Tokenize[A](_1: Peg[A], _2: Vector[A]) extends Sequence[Vector[A]] {
    override def begin = new sequence.Iterator[Vector[A]] {
        private var (k, l) = Find.impl(_1, _2, _2.start, _2.end)

        override def isEnd = l == FAILURE
        override def deref = {
            preDeref
            new vector.Region(_2, k, l)
        }
        override def increment = {
            preIncrement
            k_l_assign(Find.impl(_1, _2, l, _2.end))
        }

        private def k_l_assign(r: (Int, Int)): Unit = {
            k = r._1; l = r._2
        }
    }
}
