

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package nattest


import com.github.okomok.mada

import mada.dual._
import junit.framework.Assert._


class StrongTest extends org.scalatest.junit.JUnit3Suite {

    def testDense {
        import nat.dense.StrongLiteral._
        free.assert(_5 plus _4 equal _9)
        assertEquals(8, _2 plus _6 undual)
    }

    def testPeano {
        import nat.peano.StrongLiteral._
        free.assert(_5 plus _4 equal _9)
        assertEquals(8, _2 plus _6 undual)
    }

}