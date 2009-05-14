

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


class Flatten[A](val _1: Traversable[Traversable[A]]) extends Traversable[A] { self =>
    override def start = new Traverser[A] {
        private val tt = self._1.start
        private var t = ready
        override def isEnd = !t
        override def deref = ~t
        override def increment = {
            t.++
            if (!t) {
                tt.++
                t = ready
            }
        }

        private def ready: Traverser[A] = {
            while (tt) {
                val u = (~tt).start
                if (u) {
                    return u
                }
                tt.++
            }
            traverser.theEnd
        }
    }
}
