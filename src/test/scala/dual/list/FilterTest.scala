

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._
import nat.peano.Literal._


class FilterTest extends org.scalatest.junit.JUnit3Suite {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    class not2 extends Function1 {
        override  def self = this
        override type self = not2
        override  def apply[x <: Any](x: x): apply[x] = x.asInstanceOfNat !== _2
        override type apply[x <: Any] = x#asInstanceOfNat# !==[_2]
    }
    val not2 = new not2

    def testTrivial {
        type xs = _2 :: _3 :: _4 :: _2 :: _5 :: _6 :: _2 :: Nil
        val xs: xs = _2 :: _3 :: _4 :: _2 :: _5 :: _6 :: _2 :: Nil
        val u: xs#filter[not2] = xs.filter(not2)
        val v: _3 :: _4 :: _5 :: _6 :: Nil = u
        assertEquals(_3 :: _4 :: _5 :: _6 :: Nil, v)
    }

    def testTrivialNil {
        type xs = Nil
        val xs: xs = Nil
        val u: xs#filter[not2] = xs.filter(not2)
        val v: Nil = u
        ()
    }
}

