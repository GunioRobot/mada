

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


object Map {
    def apply[Z, A](v: Vector[Z], f: Z => A, grainSize: Int): Vector[A] = new MapVector(v, f, grainSize)
}

class MapVector[Z, A](v: Vector[Z], f: Z => A, grainSize: Int) extends VectorProxy[A] with NotWritable[A] {
    Assert(!v.isParallel)

    override lazy val self = unparallel.parallel(grainSize)
    override lazy val unparallel = {
        if (grainSize == 1) {
            v.map({ e => Future(f(e)) }).force.map({ u => u() })
        } else {
            Vector.undivide(
                v.divide(grainSize).map({ w => Future(w.map(f)) }).force.map({ u => u() })
            )
        }
    }

    override def force = _wait(self) // force-map fusion
    override def lazyValues = self // lazyValues-map fusion
    // `unparallel` is not so cheap to short-cut.
    // override def map[B](_f: A => B) = v.parallel(grainSize).map(_f compose f) // map-map fusion
    // override def seek(p: A => Boolean) = v.parallel(grainSize).seek(p compose f).map(f) // seek-map fusion

    private def _wait(v: Vector[A]): Vector[A] = {
        Assert(v.isParallel)
        v.foreach({ e => () })
        v
    }
}
