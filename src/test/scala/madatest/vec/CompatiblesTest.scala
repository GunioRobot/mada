

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada.Vector
import mada.Vector.arrayVector
import junit.framework.Assert._
import madatest.vec.detail.Example._


class CompatiblesTest {
    import Vector.compatibles._

    def testCompile(x: Int): Unit = {
        takeIterator(makeVector)
        takeVector(Array(1,2,3))
    }

    def takeVector[A](v: Vector[A]): Unit = { }
    def takeIterator[A](it: Iterator[A]): Unit = { }
    def makeVector: Vector[Char] = throw new Error()
}
