

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Seek {
    def apply[A](v: Vector[A], p: A => Boolean): Option[A] = v.find(p)
}
