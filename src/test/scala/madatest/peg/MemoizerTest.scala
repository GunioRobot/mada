

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import junit.framework.Assert._
import mada.Vector

import mada.Peg.Compatibles._
import mada.Peg


class MemoizerTest {
    def testTrivial: Unit = {
        var c = 0
        val v = Vector.from("abcdefghij")
        val m = new Peg.Memoizer(v)
        val q = m(Peg.from("hij"){ _ => c += 1 })
        val p = "abc" >> "defg" >> q
        assertEquals(0, c)
        assertTrue(p matches v)
        assertEquals(1, c)
        assertTrue(p matches v)
        assertEquals(1, c)
        assertTrue(p matches v(v.start, v.end))
        assertEquals(1, c)
        assertTrue(p matches (v(v.start, v.end - 2) ++ "ij"))
        assertEquals(2, c)
    }

    def testRegion: Unit = {
        var c = 0
        val w = Vector.from("abcdefghijklm")
        val v1 = w(w.start, w.end - 3)
        val v2 = w(w.start, w.end - 3)
        assertNotSame(v1, v2)

        val m = new Peg.Memoizer(w) // for w
        val q = m(Peg.from("hij"){ _ => c += 1 })
        val p = "abc" >> "defg" >> q
        assertEquals(0, c)

        assertTrue(p matches v1)
        assertEquals(1, c)
        assertTrue(p matches v1)
        assertEquals(1, c)

        assertTrue(p matches v2)
        assertEquals(1, c)
        assertTrue(p matches v2)
        assertEquals(1, c)
    }
}