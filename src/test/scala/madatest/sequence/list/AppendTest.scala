

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.listtest


import mada.sequence.list
import junit.framework.Assert._


class AppendTest {

    def testTrivial: Unit = {
        val t = list.Of(4,5,1,3)
        val u = list.Of(9,7,10)
        val v = list.Of(4,5,1,3,9,7,10)
        val k = t ++ u
        assertEquals(v, k)
        assertEquals(v, k)
    }

    def testEmpty: Unit = {
        val k = list.emptyOf[Int] ++ list.emptyOf[Int]
        assertEquals(list.emptyOf[Int], k)
        assertEquals(list.emptyOf[Int], k)
    }

    def testEmpty2: Unit = {
        val t = list.Of(4,5,1,3)
        val t_  = list.Of(4,5,1,3)
        val k = list.emptyOf[Int] ++ t
        assertEquals(t_, k)
        assertEquals(t_, k)
    }

    def testEmpty3: Unit = {
        val t = list.Of(4,5,1,3)
        val t_  = list.Of(4,5,1,3)
        val k = t ++ list.emptyOf[Int]
        assertEquals(t_, k)
        assertEquals(t_, k)
    }

    def testNonTrivial: Unit = {
        val t1 = list.Of(4,5)
        val t2 = list.Of(1)
        val t3 = list.Of(3, 9)
        val t4 = list.emptyOf[Int]
        val t5 = list.Of(7,10,11)
        val v = list.Of(4,5,1,3,9,7,10,11)
        val k = t1 ++ t2 ++ t3 ++ t4 ++ t5
        assertEquals(v, k)
        assertEquals(v, k)
    }

}