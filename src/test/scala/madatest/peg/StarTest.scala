

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import mada.Peg._
import junit.framework.Assert._


class StarTest {
    def testStar: Unit = {
        val sample = mada.Vector.unstringize("aaaaaaa")
        assertTrue((unstringize("a")*).matches(sample))
    }

    def testStar2: Unit = {
        val sample = mada.Vector.unstringize("aaaaaaab")
        assertTrue((unstringize("a").* >> unstringize("b")).matches(sample))
    }

    def testStar3: Unit = {
        val sample = mada.Vector.unstringize("b")
        assertTrue((unstringize("a").* >> unstringize("b")).matches(sample))
    }

    def testStar4: Unit = {
        val sample = mada.Vector.unstringize("Aabababab")
        assertTrue(  (unstringize("A") >> (unstringize("a") >> unstringize("b")).* ).matches(sample)  ) // `.` is needed.
    }

    def testBefore: Unit = {
        val sample = mada.Vector.unstringize("/*hello*/")
        assertTrue((unstringize("/*") >> (any.* >>> ~unstringize("*/")) >> unstringize("*/")).matches(sample))
    }

    def testUntil: Unit = {
        val sample = mada.Vector.unstringize("/*hello*/")
        assertTrue((unstringize("/*") >> (any.* >>> unstringize("*/"))).matches(sample))
    }
}
