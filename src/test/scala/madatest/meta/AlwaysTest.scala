

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package metatest


import mada.meta._
// import junit.framework.Assert._


class AlwaysTest {

    trait Strong
    final class so extends Strong

    def testTrivial: Unit = {
        type k = always[String]#apply1[so]
        assertSame[String, k]
        assertSame[String, Always[String, String]#apply0]
        assertSame[String, always[String]#apply0]
        assertSame[String, always[String]#apply1[so]]
        assertSame[String, always[String]#apply2[scala.Int, so]]
        assertSame[String, always[String]#apply3[scala.Int, so, so]]
        ()
    }
}
