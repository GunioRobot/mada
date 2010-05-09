

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package iterativetest


import com.github.okomok.mada

import mada.sequence.iterative
import junit.framework.Assert._


class EmptyTest extends junit.framework.TestCase {
    def testTrivial: Unit = {
        val tr = iterative.empty.of[Int]
        assertTrue(tr.isEmpty)
        assertTrue(tr.isEmpty)
    }
}
