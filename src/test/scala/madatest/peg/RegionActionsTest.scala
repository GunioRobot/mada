

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.pegtest


import junit.framework.Assert._
import mada.vector.compatibles._
import mada.{Vector, vector}
import mada.peg.compatibles._
import mada.{Peg, peg}


class RegionActionsTest {
    def testTrivial: Unit = {
        val R = new peg.RegionActions[Char]
        var w: Vector[Char] = null
        val g = R.startAt >> peg.symbol.map.Of("e" --> "z", "ef" --> ( R{v => w = v} >> "g" ), "wx" --> "wy", "wxyz" --> ( R{v => w = v} >> "123" ) )
        assertTrue("abc" >> g >> "LL"  matches "abcefgLL")
        assertEquals(vector.from("ef"), w)
        assertTrue("abc" >> g >> "LL"  matches "abcwxyz123LL")
        assertEquals(vector.from("wxyz"), w)
    }
}
