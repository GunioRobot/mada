

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package pegtest


import com.github.okomok.mada

import mada.dual._
import nat.dense.Literal._
import junit.framework.Assert._
import peg.term


class TermTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        type xs    = _3 :: Nil
        val xs: xs = _3 :: Nil
        type r = term[_3]#matches[xs]
        val r: r = term(_3).matches(xs)
        meta.assert[r]
        assertTrue(r.undual)
    }

    def testTrivial2 {
        type xs    = _7 :: Nil
        val xs: xs = _7 :: Nil
        type r = term[_3]#matches[xs]
        val r: r = term(_3).matches(xs)
        meta.assertNot[r]
        assertFalse(r.undual)
    }

    def testFail {
        type xs    = Nil
        val xs: xs = Nil
        type r = term[_5]#matches[xs]
        val r: r = term(_5).matches(xs)
        meta.assertNot[r]
        assertFalse(r.undual)
    }

    def testParse {
        type xs    = _3 :: _5 :: _6 :: Nil
        val xs: xs = _3 :: _5 :: _6 :: Nil
        type r = term[_3]#parse[xs]
        val r: r = term(_3).parse(xs)
        meta.assert[r#successful]
        meta.assertSame[_3, r#get]
        meta.assertSame[_5 :: _6 :: Nil, r#next#force]
        assertEquals(_3, r.get)
        assertEquals(_5 :: _6 :: Nil, r.next)
    }

    def testParseFail {
        type xs    = _3 :: _5 :: _6 :: Nil
        val xs: xs = _3 :: _5 :: _6 :: Nil
        type r = term[_2]#parse[xs]
        val r: r = term(_2).parse(xs)
        meta.assertNot[r#successful]
        meta.assertSame[_3 :: _5 :: _6 :: Nil, r#next#force]
        assertEquals(_3 :: _5 :: _6 :: Nil, r.next)
    }

}
