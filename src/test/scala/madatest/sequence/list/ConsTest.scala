

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package listtest


import mada.sequence._
import junit.framework.Assert._


class ConsTest {

    def testInfer: Unit = {
        val x: list.Type[Int] = 1 :: 3 :: 4 :: Nil
        assertEquals(10, x.nth(1) + 7)
    }

    def testInferStrict: Unit = {
        val x: list.Type[Int] = 1 #:: 3 #:: 4 #:: Nil
        assertEquals(10, x.nth(1) + 7)
    }

}
