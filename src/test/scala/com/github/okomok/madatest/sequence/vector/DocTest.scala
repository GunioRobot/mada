

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest


import com.github.okomok.mada

    import mada.sequence._
    import junit.framework.Assert._

    class DocTest extends junit.framework.TestCase {
        def testTrivial: Unit = {
            val v = Vector(0,1,2,3,4).parallelize
            v.map(_ + 10).seek(_ == 13) match {
                case Some(e) => assertEquals(13, e)
                case None => fail("doh")
            }

            val i = new java.util.concurrent.atomic.AtomicInteger(0)
            v.each {
                _ => i.incrementAndGet
            }
            assertEquals(5, i.get)
        }
    }