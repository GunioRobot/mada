

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


case class Iterate[A](_1: A, _2: A => A) extends Sequence[A] {
    override def begin = new Iterator[A] {
        private var acc = _1

        override def isEnd = false
        override def deref = acc
        override def increment = { acc = _2(acc) }
    }
}