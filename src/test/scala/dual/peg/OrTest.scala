

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package pegtest


import com.github.okomok.mada

import mada.dual._
import nat.dense.Literal._
import junit.framework.Assert._
import peg.fromList


class OrTest extends org.scalatest.junit.JUnit3Suite {

    def testNilInput {
        type xs    = Nil
        val xs: xs = Nil
        type p   = fromList[_3 :: _5 :: _9 :: Nil]#or[fromList[_4 :: _2 :: Nil]]
        val p: p = fromList(_3 :: _5 :: _9 :: Nil).or(fromList(_4 :: _2 :: Nil))
        type r = p#parse[xs]
        val r: r = p.parse(xs)
        free.assertNot[r#successful]
        assertFalse(r.successful.undual)
        free.assertSame[xs, r#next#force]
        assertEquals(xs, r.next)
    }

    def testOneInput {
        type xs    = _3 :: _8 :: _9 :: Nil
        val xs: xs = _3 :: _8 :: _9 :: Nil
        type p   = fromList[_3 :: _5 :: _9 :: Nil]#or[fromList[_4 :: _2 :: Nil]]
        val p: p = fromList(_3 :: _5 :: _9 :: Nil).or(fromList(_4 :: _2 :: Nil))
        type r = p#parse[xs]
        val r: r = p.parse(xs)
        free.assertNot[r#successful]
        assertFalse(r.successful.undual)
        free.assertSame[xs, r#next#force]
        assertEquals(xs, r.next)
    }

    def testParseLeft {
        type xs    = _3 :: _5 :: _9 :: _0 :: Nil
        val xs: xs = _3 :: _5 :: _9 :: _0 :: Nil
        type p   = fromList[_3 :: _5 :: _9 :: Nil]#or[fromList[_4 :: _2 :: Nil]]
        val p: p = fromList(_3 :: _5 :: _9 :: Nil).or(fromList(_4 :: _2 :: Nil))
        type r = p#parse[xs]
        val r: r = p.parse(xs)
        free.assert[r#successful]
        assertTrue(r.successful.undual)
        type e = r#get#asEither
        val e: e = r.get.asEither
        free.assert[e#isLeft]
        assertTrue(e.isLeft.undual)
        free.assertSame[_3 :: _5 :: _9 :: Nil, e#get]
        assertEquals(_3 :: _5 :: _9 :: Nil, e.get)
        free.assertSame[_0 :: Nil, r#next#force]
        assertEquals(_0 :: Nil, r.next)
    }

    def testParseRight {
        type xs    = _4 :: _2 :: _9 :: _0 :: Nil
        val xs: xs = _4 :: _2 :: _9 :: _0 :: Nil
        type p   = fromList[_3 :: _5 :: _9 :: Nil]#or[fromList[_4 :: _2 :: Nil]]
        val p: p = fromList(_3 :: _5 :: _9 :: Nil).or(fromList(_4 :: _2 :: Nil))
        type r = p#parse[xs]
        val r: r = p.parse(xs)
        free.assert[r#successful]
        assertTrue(r.successful.undual)
        type e = r#get#asEither
        val e: e = r.get.asEither
        free.assert[e#isRight]
        assertTrue(e.isRight.undual)
        free.assertSame[_4 :: _2 :: Nil, e#get]
        assertEquals(_4 :: _2 :: Nil, e.get)
        free.assertSame[_9 :: _0 :: Nil, r#next]
        assertEquals(_9 :: _0 :: Nil, r.next)
    }
}
