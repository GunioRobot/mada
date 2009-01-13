

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Tokens {
    def apply[A](p: Peg[A], v: Vector[A]): Iterator[Vector[A]] = {
        p.tokenize(v).map({ (i_j: (Long, Long)) => v.window(i_j._1, i_j._2) })
    }
}