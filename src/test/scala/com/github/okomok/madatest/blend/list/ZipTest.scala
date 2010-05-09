

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package blendtest; package listtest


import com.github.okomok.mada

import mada.blend._
import mada.meta
import mada.meta.nat.Literal._


class ZipTest extends junit.framework.TestCase {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    def testTrivial: Unit = {
        val i = new java.lang.Integer(10)
        type l1 = Int :: String :: java.lang.Integer :: Nil
        val l1: l1 = 3 :: "hello" :: i :: Nil

        type l2 = java.lang.Integer :: Char :: Int :: Nil
        val l2: l2 = i :: 'a' :: 12 :: Nil

        val _z: l1#zip[l2] = l1.zip(l2)
        val z: (Int, java.lang.Integer) :: (String, Char) :: (java.lang.Integer, Int) :: Nil = _z

        val a = (3, i) :: ("hello", 'a') :: (i, 12) :: Nil
        assertEquals(a, z)
    }

    def testNil: Unit = {
        type l1 = Nil
        val l1: l1 = Nil

        type l2 = Nil
        val l2: l2 = Nil

        val _z: l1#zip[l2] = l1.zip(l2)
        val z: Nil = _z

        val a = Nil
        assertEquals(a, z)
    }

    def testLonger: Unit = {
        val i = new java.lang.Integer(10)
        type l1 = Int :: String :: java.lang.Integer :: Nil
        val l1: l1 = 3 :: "hello" :: i :: Nil

        type l2 = java.lang.Integer :: Char :: Int :: String :: Nil
        val l2: l2 = i :: 'a' :: 12 :: "ignored" :: Nil

        val _z: l1#zip[l2] = l1.zip(l2)
        val z: (Int, java.lang.Integer) :: (String, Char) :: (java.lang.Integer, Int) :: Nil = _z

        val a = (3, i) :: ("hello", 'a') :: (i, 12) :: Nil
        assertEquals(a, z)
    }
}
