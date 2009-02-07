

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object FilterFrom {
    def apply[A](p: Peg[A], v: Vector[A]): Iterator[A] = {
        Iterator.flatten(p.tokenize(v).map({ v => v.toIterator }))
    }
}
