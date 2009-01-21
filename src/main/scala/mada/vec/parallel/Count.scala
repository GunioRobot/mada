

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


object Count {
    def apply[A](v: Vector[A], p: A => Boolean, grainSize: Int): Int = {
        v.divide(grainSize).
            parallel(1).map({ w => w.count(p) }).
                reduceLeft(_ + _)
/* maybe faster
        val n = new java.util.concurrent.atomic.AtomicInt(0)
        v.parallel(grainSize).foreach({ e => if (p(e)) n.incrementAndGet })
        n.get
*/
    }
}