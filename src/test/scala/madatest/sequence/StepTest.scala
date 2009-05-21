

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest


import mada.sequence
import junit.framework.Assert._


class StepTest {

    def testStep0: Unit = {
        // Unlike Vector, 0 is allowed.
        val tr = sequence.Of(1,2,3,4,5,6).step(0)
        assertEquals(tr.take(5), sequence.Of(1,1,1,1,1))
        assertEquals(tr.take(5), sequence.Of(1,1,1,1,1))
    }

    def testStep1: Unit = {
        new NotStartable[Int].step(5)
        val tr = sequence.Of(1,2,3,4,5,6).step(1)
        assertEquals(tr, sequence.Of(1,2,3,4,5,6))
        assertEquals(tr, sequence.Of(1,2,3,4,5,6))
    }

    def testStep2: Unit = {
        val tr = sequence.Of(1,2,3,4,5,6).step(2)
        assertEquals(tr, sequence.Of(1,3,5))
    }

    def testStep3: Unit = {
        val tr = sequence.Of(1,2,3,4,5,6).step(3)
        assertEquals(tr, sequence.Of(1,4))
    }

    def testStepFusion: Unit = {
        val tr = sequence.Of(1,2,3,4,5,6,7,8,9,10,11).step(3).step(2)
        assertEquals(tr, sequence.Of(1,7))
    }

    def testStepFusion2: Unit = {
        val tr = sequence.Of(1,2,3,4,5,6,7,8,9,10,11).step(3).step(2)
        assertEquals(tr, sequence.Of(1,2,3,4,5,6,7,8,9,10,11).step(3).seal.step(2))
    }

    def testStepEmpty: Unit = {
        val tr0 = sequence.emptyOf[Int].step(0)
        assertTrue(tr0.isEmpty)
        val tr1 = sequence.emptyOf[Int].step(1)
        assertTrue(tr1.isEmpty)
        val tr2 = sequence.emptyOf[Int].step(2)
        assertTrue(tr2.isEmpty)
    }

}
