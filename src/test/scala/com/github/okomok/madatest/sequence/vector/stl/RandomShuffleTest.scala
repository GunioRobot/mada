

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest; package stltest


import com.github.okomok.mada

// import mada.Compare.madaCompareFromGetOrdered


import mada.sequence.vector._
import mada.sequence.{Vector, vector}
import junit.framework.Assert._
import com.github.okomok.madatest.sequencetest.vectortest.detail.Example._
import com.github.okomok.madatest.sequencetest.vectortest.detail._


class RandomShuffleTest extends junit.framework.TestCase {
    def testTrivial: Unit = {
        val v = fromArray(example1)
        mada.sequence.vector.stl.randomShuffle(v, 0, v.size)
//        println(v.toString)
        mada.sequence.vector.stl.sort(v, 0, v.size)
        TeztVectorReadOnly(example1Sorted, v)
    }
}
