

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._
import nat.peano.Literal._
import junit.framework.Assert._


class SpanTest extends org.scalatest.junit.JUnit3Suite {

    case class Lt8() extends Function1 {
        override type self = Lt8
        override  def apply[x <: Any](x: x): apply[x] = x.asInstanceOfNat < _8
        override type apply[x <: Any] = x#asInstanceOfNat# <[_8]
    }

    def testTrivial {
        type xs = _5 :: _6 :: _7 :: _8 :: _9 :: Nil
        val xs: xs = _5 :: _6 :: _7 :: _8 :: _9 :: Nil
        type u = xs#span[Lt8]
        val u: u = xs.span(Lt8())
        meta.assertSame[Tuple2[_5 :: _6 :: _7 :: Nil, _8 :: _9 :: Nil], u]
        assertEquals(Tuple2(_5 :: _6 :: _7 :: Nil, _8 :: _9 :: Nil), u)
    }

    def testTrivialNil {
        type xs = Nil
        val xs: xs = Nil
        type u = xs#span[Lt8]
        val u: u = xs.span(Lt8())
        meta.assertSame[Tuple2[Nil, Nil], u]
        assertEquals(Tuple2(Nil, Nil), u)
    }

    def testTakeAll {
        type xs = _4 :: _5 :: _6 :: _7 :: Nil
        val xs: xs = _4 :: _5 :: _6 :: _7 :: Nil
        type u = xs#span[Lt8]
        val u: u = xs.span(Lt8())
        meta.assertSame[Tuple2[xs, Nil], u]
        assertEquals(Tuple2(xs, Nil), u)
    }

    def testTakeNothing {
        type xs = _9 :: _5 :: _6 :: _7 :: _1 :: _9 :: Nil
        val xs: xs = _9 :: _5 :: _6 :: _7 :: _1 :: _9 :: Nil
        type u = xs#span[Lt8]
        val u: u = xs.span(Lt8())
        meta.assertSame[Tuple2[Nil, xs], u]
        assertEquals(Tuple2(Nil, xs), u)
    }

}