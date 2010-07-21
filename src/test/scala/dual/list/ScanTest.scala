

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._
import nat.peano.Literal._
import junit.framework.Assert._


class ScanTest extends org.scalatest.junit.JUnit3Suite {

    case class Div() extends Function2 {
        override  def self = this
        override type self = Div
        override  def apply[x <: Any, y <: Any](x: x, y: y): apply[x, y] = x.asInstanceOfNat / y.asInstanceOfNat
        override type apply[x <: Any, y <: Any] = x#asInstanceOfNat# /[y#asInstanceOfNat]
    }

    def testLeft {
        type xs = _4 :: _3 :: Nil
        val xs: xs = _4 :: _3 :: Nil
        type u = xs#scanLeft[_15, Div]
        val u: u = xs.scanLeft(_15, Div())
        meta.assertSame[_15 :: _3 :: _1 :: Nil, u]
        assertEquals(_15 :: _3 :: _1 :: Nil, u)
    }

    def testLeftNil {
        type xs = Nil
        val xs: xs = Nil
        type u = xs#scanLeft[_15, Div]
        val u: u = xs.scanLeft(_15, Div())
        meta.assertSame[_15 :: Nil, u]
        assertEquals(_15 :: Nil, u)
    }

    def testRight {
        type xs = _8 :: _12 :: _4 :: Nil
        val xs: xs = _8 :: _12  :: _4 :: Nil
        type u = xs#scanRight[_2, Div]
        val u: u = xs.scanRight(_2, Div())
        meta.assertSame[_1 :: _6 :: _2 :: _2 :: Nil, u]
        assertEquals(_1 :: _6 :: _2 :: _2 :: Nil, u)
    }

    def testRightNil {
        type xs = Nil
        val xs: xs = Nil
        type u = xs#scanRight[_15, Div]
        val u: u = xs.scanRight(_15, Div())
        meta.assertSame[_15 :: Nil, u]
        assertEquals(_15 :: Nil, u)
    }

}
