

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import mada.Peg._
import junit.framework.Assert._


class AnyTest {
    def testTrivial: Unit = {
        val sample = mada.Vector.unstringize("/")
        assertTrue(any[Char].matches(sample))
        assertFalse(any[Char].matches(mada.Vector.empty))
    }
}
