

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest


import com.github.okomok.mada

import mada.sequence._
import mada.sequence.vector.fromArray
import junit.framework.Assert._
import detail.Example._


class IteratorTest extends junit.framework.TestCase {
    def testTo: Unit = {
        val it = fromArray(example1).toSeq.iterator
        var i = 0
        it.foreach({ (e: Int) =>
            assertEquals(example1(i), e)
            i += 1
        })
    }

    def testFrom: Unit = {
        val ac = iterative.fromSIterable(example1).toVector
        detail.TestVectorReadOnly(example1, ac)
    }
}