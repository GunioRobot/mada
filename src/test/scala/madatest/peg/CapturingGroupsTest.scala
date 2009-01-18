

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import junit.framework.Assert._
import mada.Vector.Compatibles._
import mada.Peg.Compatibles._
import mada.Peg._


class CapturingGroupsTest {
    def testTrivial: Unit = {
        val c = new CapturingGroups[String, Char]
        val p = ("abcd" >> c("name", "EFG") >> "hi" >> c("name"))
        assertTrue(p matches "abcdEFGhiEFG")
    }
}
