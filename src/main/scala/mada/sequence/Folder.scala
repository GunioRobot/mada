

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


case class FolderLeft[A, B](_1: Sequence[A], _2: B, _3: (B, A) => B) extends Sequence[B] {
    override def begin = (single(_2) ++ new _FolderLeft(_1, _2, _3)).begin
}

private class _FolderLeft[A, B](_1: Sequence[A], _2: B, _3: (B, A) => B) extends Sequence[B] {
    override def begin = new Iterator[B] {
        private val it = _1.begin
        private var z = _2

        override def isEnd = !it
        override def deref = _3(z, ~it)
        override def increment = {
            z = deref
            it.++
        }
    }
}


case class ReducerLeft[A, B >: A](_1: Sequence[A], _2: (B, A) => B) extends Sequence[B] {
    override def begin = {
        val it = _1.begin
        if (!it) {
            throw new UnsupportedOperationException("reducerLeft on empty sequence")
        }
        val e = ~it
        it.++
        bind(it).folderLeft[B](e)(_2).begin
    }
/*
    override protected val delegate = {
        val it = _1.begin
        if (!it) {
            throw new UnsupportedOperationException("reducerLeft on empty sequence")
        }
        val e = ~it // too early?, but lazyness can'it always be feasible. (BTW, Vector is usually infeasible.)
        it.++
        bind(it).folderLeft[B](e)(_2)
    }
*/
}
