

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object AsLazy {
    def apply[A](v: => Vector[A]): Vector[A] = new AsLazyVector(v)
}

class AsLazyVector[A](v: => Vector[A]) extends VectorProxy[A] {
    override lazy val self = v
}
