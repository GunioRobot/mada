

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.listtest


import mada.sequence._
import junit.framework.Assert._


class SpanTest {
    def testTrivial: Unit = {
        val t = list.Of(3,3,3,3,3,9,7,10)
        val (fst, snd) = t.span(_ == 3)
        assertEquals(list.Of(3,3,3,3,3), fst)
        assertEquals(list.Of(9,7,10), snd)
    }

    def testEmpty: Unit = {
        val (fst, snd) = list.emptyOf[Int].span(_ == 3)
        assertTrue(fst.isEmpty)
        assertTrue(snd.isEmpty)
    }

    def testBound1: Unit = {
        val t = list.Of(3,3,3,3,9,3,3,3,3)
        val (fst, snd) = t.span(_ == 4)
        assertTrue(fst.isEmpty)
        assertEquals(list.Of(3,3,3,3,9,3,3,3,3), snd)
    }

    def testBound2: Unit = {
        val t = list.Of(3,3,3,3,9,3,3,3,3)
        val (fst, snd) = t.span(_ != 99)
        assertEquals(list.Of(3,3,3,3,9,3,3,3,3), fst)
        assertTrue(snd.isEmpty)
    }
}
