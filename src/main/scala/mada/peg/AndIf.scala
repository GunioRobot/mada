

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object AndIf {
    def apply[A](p: Peg[A], pred: Vector[A] => Boolean): Peg[A] = apply(p, Vector.triplify(pred))
    def apply[A](p: Peg[A], pred: (Vector[A], Long, Long) => Boolean): Peg[A] = new AndIfPeg(p, pred)
}

class AndIfPeg[A](override val self: Peg[A], pred: (Vector[A], Long, Long) => Boolean) extends PegProxy[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        val cur = self.parse(v, first, last)
        if (cur == FAILURE || !pred(v, first, cur)) {
            FAILURE
        } else {
            cur
        }
    }
}
