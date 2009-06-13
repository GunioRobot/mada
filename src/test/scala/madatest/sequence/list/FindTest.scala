

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.listtest


import mada.sequence.list
import junit.framework.Assert._


class FindTest {
    def testFind: Unit = {
        val tr = list.Of(2,4,6)
        assertEquals(4, tr.find(_ == 4).get)
        assertTrue(tr.find(_ == 9).isEmpty)
    }

    def testContains: Unit = {
        val tr = list.Of(2,4,6)
        assertTrue(tr.contains(4))
    }

    def testForall: Unit = {
        val tr = list.Of(2,4,6)
        assertTrue(tr.forall(_ % 2 == 0))
    }

    def testExists: Unit = {
        val tr = list.Of(2,4,6)
        assertTrue(tr.exists(_ == 6))
    }
}