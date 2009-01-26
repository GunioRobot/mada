

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Eps {
    def apply[A]: Peg[A] = new EpsPeg[A]
}

private[mada] class EpsPeg[A] extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = start
    override def length = 0
}
