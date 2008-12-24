

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Break {
    def apply[A](v: Vector[A], p: A => Boolean): (Vector[A], Vector[A]) = v.span(!p(_: A))
}