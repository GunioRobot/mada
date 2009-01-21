

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec.parallel


import mada._
import junit.framework.Assert._


class MapTest {
    def testTrivial: Unit = {
        val v = Vector.range(0, 10)
        val e = Vector.range(1, 11)
        assertEquals(e, v.parallel.map(_ + 1))
        assertEquals(e, v.parallel(4).map(_ + 1))
        assertEquals(e, v.parallel(500).map(_ + 1))
    }

    def testFor: Unit = {
        val v = Vector.range(0, 10)
        val e = Vector.range(1, 11)
        val w = for (a <- v.parallel) yield a + 1
        assertEquals(e, w)
    }

    def testFusion: Unit = {
        val v = Vector.range(0, 10)
        val e = Vector.range(2, 12)
        assertEquals(e, v.parallel.map(_ + 1).map(_ + 1))
        assertEquals(e, v.parallel(4).map(_ + 1).map(_ + 1))
        assertEquals(e, v.parallel(500).map(_ + 1).map(_ + 1))
    }
}