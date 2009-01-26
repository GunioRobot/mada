

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object CopyTo {
    def apply[A, B >: A](v: Vector[A], w: Vector[B]): Vector[A] = {
        ThrowIf.differentSize(v, w, "copyTo")
        val (x, i, j) = v.triple
        val (y, k, _) = w.triple
        stl.Copy(x, i, j, y, k)
        v
    }
}
