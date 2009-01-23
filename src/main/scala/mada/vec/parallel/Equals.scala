

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


object EqualsWith {
    def apply[A, B](v: Vector[A], w: Vector[B], p: (A, B) => Boolean, grainSize: Int): Boolean = {
        if (v.size != w.size) {
            false
        } else {
            val bp = new Breakable2(p, false)
            (v.divide(grainSize) zip w.divide(grainSize)).
                parallel(1).map({ case (v1, w1) => breakingEquals(v1, w1, bp) }).
                    unparallel.reduceLeft(_ && _)
        }
    }

    private def breakingEquals[A, B](v: Vector[A], w: Vector[B], p: Breakable2[A, B]): Boolean = {
        val x = v.equalsWith(w)(p)
        if (!x) {
            p.break
        }
        x
    }
}
