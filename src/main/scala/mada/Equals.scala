

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


// == is not type-safe.

object Equals {
    def apply[A](x: A, y: A): Boolean = x == y
}
