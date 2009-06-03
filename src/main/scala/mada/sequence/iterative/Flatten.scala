

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterative


case class Flatten[+A](_1: Iterative[Sequence[A]]) extends Iterative[A] {
    override def begin = new Iterator[A] {
        private val ii = _1.begin
        private var it = ready

        override def isEnd = !it
        override def deref = ~it
        override def increment = {
            it.++
            if (!it) {
                ii.++
                it = ready
            }
        }

        private def ready: Iterator[A] = {
            while (ii) {
                val jt = (~ii).toIterative.begin
                if (jt) {
                    return jt
                }
                ii.++
            }
            iterator.theEnd
        }
    }
}