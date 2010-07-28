

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class TailTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val s = new java.util.ArrayList[Int]
        reactive.Of(0,1,2,3,4).tail.activate(reactor.make(_ => s.add(99), s.add(_)))
        assertEquals(vector.Of(1,2,3,4, 99), vector.from(s))
    }

    def testEmpty: Unit = {
        val s = new java.util.ArrayList[Int]
        reactive.empty.of[Int].tail.activate(reactor.make(_ => s.add(99), s.add(_)))
        assertEquals(vector.Of(99), vector.from(s))
    }
}