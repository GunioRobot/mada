

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._


class FlatMapTest extends junit.framework.TestCase {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    class oops extends Function1 {
        override  def apply[x <: Any](x: x): apply[x] = x :: Box("oops") :: Nil
        override type apply[x <: Any] = x :: Box[String] :: Nil
    }
    val oops = new oops

    def testTrivial {
        type xs = Box[Int] :: Box[String] :: Box[Char] :: Nil
        val xs: xs = Box(3) :: Box("hello") :: Box('a') :: Nil
        val u: xs#flatMap[oops] = xs.flatMap(oops)
        val v: Box[Int] :: Box[String] :: Box[String] :: Box[String] :: Box[Char] :: Box[String] :: Nil = u
        assertEquals(Box(3) :: Box("oops") :: Box("hello") :: Box("oops") :: Box('a') :: Box("oops")  :: Nil, v)
    }

    def testTrivialNil {
        type xs = Nil
        val xs: xs = Nil
        val u: xs#flatMap[oops] = xs.flatMap(oops)
        val v: Nil = u
        ()
    }
}
