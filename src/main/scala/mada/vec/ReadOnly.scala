

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object ReadOnly {
    def apply[A](v: Vector[A]): Vector[A] = new ReadOnlyVector(v)
}

class ReadOnlyVector[A](override val self: Vector[A]) extends VectorProxy[A] with NotWritable[A]
