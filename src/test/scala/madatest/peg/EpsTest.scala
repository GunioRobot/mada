

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package pegtest


import mada.peg._
import junit.framework.Assert._


class EpsTest {
    def testTrivial: Unit = {
        assertTrue(eps[Int].matches(mada.sequence.vector.empty[Int]))
        assertFalse(eps[Int].matches(mada.sequence.vector.Of(1,2,3)))
    }

    def testCompile(v: mada.sequence.Vector[Char]): Unit = {
        (unstringize("abcd") >> eps).parse(v, 0, 10)
    }
}
