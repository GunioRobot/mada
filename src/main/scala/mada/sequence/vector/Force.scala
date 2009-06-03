

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class Force[A](_1: Vector[A]) extends Adapter.Transform[A] with Adapter.NotWritable[A] {
    override val underlying = vector.fromArray(_1.toArray)
    override def force = this // force-force fusion
}