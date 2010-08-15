

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._
import nat.peano.Literal._
import junit.framework.Assert._


class TakeWhileTest extends org.scalatest.junit.JUnit3Suite {

    type boundsCheck[xs <: List, f <: Function1] = asList[xs#takeWhile[f]]
    type asList[xs <: List] = xs

    case class Lt8() extends Function1 {
        override type self = Lt8
        override  def apply[x <: Any](x: x): apply[x] = x.asNat lt _8
        override type apply[x <: Any] = x#asNat#lt[_8]
    }

    def testTrivial {
        type xs = _5 :: _6 :: _7 :: _8 :: _9 :: Nil
        val xs: xs = _5 :: _6 :: _7 :: _8 :: _9 :: Nil
        val u: xs#takeWhile[Lt8] = xs.takeWhile(Lt8())
        meta.assertSame[_5 :: _6 :: _7 :: Nil, xs#takeWhile[Lt8]#force]
        assertEquals(_5 :: _6 :: _7 :: Nil, u)
    }

    def testTrivialToNil {
        type xs = _9 :: _5 :: _6 :: _7 :: _1 :: _9 :: Nil
        val xs: xs = _9 :: _5 :: _6 :: _7 :: _1 :: _9 :: Nil
        val u: xs#takeWhile[Lt8] = xs.takeWhile(Lt8())
        meta.assertSame[Nil, xs#takeWhile[Lt8]#force]
        assertEquals(Nil, u)
    }

    def testTrivialToAll {
        type xs = _4 :: _5 :: _6 :: _7 :: Nil
        val xs: xs = _4 :: _5 :: _6 :: _7 :: Nil
        val u: xs#takeWhile[Lt8] = xs.takeWhile(Lt8())
        meta.assertSame[_4 :: _5 :: _6 :: _7 :: Nil, xs#takeWhile[Lt8]#force]
        assertEquals(_4 :: _5 :: _6 :: _7 :: Nil, u)
    }

    def testTrivialNil {
        type xs = Nil
        val xs: xs = Nil
        val u: xs#takeWhile[Lt8] = xs.takeWhile(Lt8())
        meta.assertSame[Nil, xs#takeWhile[Lt8]#force]
        assertEquals(Nil, u)
    }

}
