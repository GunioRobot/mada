

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package reactivetest


import mada.sequence._
import junit.framework.Assert._


class IterativeTest {
    def testTo: Unit = {
        val r = reactive.Of(1,2,3,4,5,6)
        assertEquals(iterative.Of(1,2,3,4,5,6), r.toIterative)
    }

    def testLong: Unit = {
        val r = reactive.fromIterative(vector.range(0,400))
        assertEquals(vector.range(0,400), r.toIterative)
    }
}
