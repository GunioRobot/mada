

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class SingleTest extends junit.framework.TestCase {
    def testTrivial: Unit = {
        val t = reactive.single(1)
        val s = new java.util.ArrayList[Int]
        t.activate(reactor.make(_ => s.add(99), s.add(_)))
        assertEquals(vector.Of(1, 99), vector.from(s))
    }
}
