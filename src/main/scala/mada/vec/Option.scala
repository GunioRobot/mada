

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object OptionVector {
    def apply[A](from: Option[A]): Vector[A] = new OptionVector(from)
}

class OptionVector[A](from: Option[A]) extends Vector[A] {
    override def size = if (from.isEmpty) 0 else 1
    override def apply(i: Int) = from.get

    override def firstOption = from // conversion fusion
    override def lastOption = from // conversion fusion
}
