

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Span {
    def apply[A](v: Vector[A], p: A => Boolean): (Vector[A], Vector[A]) = {
        val (first, last) = v.toPair
        val middle = stl.FindIf(v, first, last, !p(_: A))
        (v.window(first, middle), v.window(middle, last))
    }
}