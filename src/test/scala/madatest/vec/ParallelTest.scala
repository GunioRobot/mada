

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada._
import junit.framework.Assert._


class ParallelTest {
    def testFusion: Unit = {
        val v = Vector.range(0, 10)
        val e = Vector.range(1, 11)
        assertEquals(e, v.parallel(4).parallel(3).parallel(1).map(_ + 1))
    }

    def testFor: Unit = {
        val v = Vector.range(0, 10)
        val e = Vector.range(1, 11)
        val w = for (a <- v.parallel) yield a + 1
        assertEquals(e, w)
    }
}