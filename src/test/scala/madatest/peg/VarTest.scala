

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


// See: http://spirit.sourceforge.net/distrib/spirit_1_8_5/libs/spirit/doc/stored_rule.html


package madatest.pegtest


import mada.peg._
import junit.framework.Assert._
import mada.peg.compatibles._



class VarTest {
    val start = new Var[Char]

    start ::= "a"
    start ::= start.clone | "b"
    start ::= start.clone | "c"
    start ::= start.clone.*

    def testTrivial: Unit = {
        assertTrue(start matches "abcaabbcabc")
        assertFalse(start matches "abcXaabbcabc")
    }
}