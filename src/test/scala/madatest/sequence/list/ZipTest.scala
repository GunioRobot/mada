

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package listtest


import mada.sequence._
import junit.framework.Assert._


class ZipTest {
    def testFibs: Unit = {
        lazy val fibs: List[Int] = 0 :: 1 :: fibs.zipBy(fibs.tail)(_ + _)
        assertEquals(832040, fibs.nth(30))
    }

    def testUnzipInfinite: Unit = {
        val L = (('a', 1) #:: ('b', 2) #:: ('c', 3) #:: Nil).cycle.unzip
        val A1 = 'a' #:: 'b' #:: 'c' #:: 'a' #:: 'b' #:: Nil
        val A2 = 1 #:: 2 #:: 3 #:: 1 #:: 2 #:: 3 #:: 1 #:: Nil
        assertEquals(A1, L._1.take(5))
        assertEquals(A2, L._2.take(7))
    }
}
