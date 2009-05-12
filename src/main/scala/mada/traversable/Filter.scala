

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class Filter[A](that: Traversable[A], predicate: A => Boolean) extends Traversable[A] { ^ =>
    override def start = new Traverser[A] {
        private val t = ^.that.start
        _ready
        override def isEnd = t.isEnd
        override def deref = t.deref
        override def increment = _ready

        private def _ready: Unit = {
            while (!t.isEnd && !(^.predicate(t.deref))) {
                t.increment
            }
        }
    }
}
