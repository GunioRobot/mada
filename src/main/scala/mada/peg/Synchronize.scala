

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Synchronize {
    def apply[A](p: Peg[A]): Peg[A] = new SynchronizePeg(p)
}

private[mada] class SynchronizePeg[A](override val self: Peg[A]) extends PegProxy[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = synchronized { self.parse(v, start, end) }
    override def width = synchronized { self.width }
}