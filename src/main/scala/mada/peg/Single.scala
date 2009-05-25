

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


case class Single[A](_1: A) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        if (start == end || _1 != v(start)) {
            FAILURE
        } else {
            start + 1
        }
    }

    override def width = 1
}
