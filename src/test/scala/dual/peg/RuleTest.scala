

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package pegtest


import com.github.okomok.mada

import mada.dual._
import nat.dense.Literal._
import junit.framework.Assert._
import peg.term


class RuleTest extends org.scalatest.junit.JUnit3Suite {


    val MyRule = new MyRule
    final class MyRule extends peg.Rule {
        type self = MyRule
        override  def rule: rule = term(_1).seq(self.opt).seq(term(_2))
        override type rule       = term[_1]#seq[self#opt]#seq[term[_2]]
    }

    def testTrivial {
        type xs    = _1 :: _1 :: _2 :: _2 :: Nil
        val xs: xs = _1 :: _1 :: _2 :: _2 :: Nil
        type r   = MyRule#matches[xs]
        val r: r = MyRule.matches(xs)
        meta.assert[r]
        assertTrue(r.undual)
    }

    def testTrivialFail {
        type xs    = _1 :: _1 :: _1 :: _2 :: _2 :: Nil
        val xs: xs = _1 :: _1 :: _1 :: _2 :: _2 :: Nil
        type r   = MyRule#matches[xs]
        val r: r = MyRule.matches(xs)
        meta.assertNot[r]
        assertFalse(r.undual)
    }

}



/*
object ArithmeticRules {

     val `(`: `(` = _10
    type `(`      = _10
     val `)`: `)` = _11
    type `)`      = _11
     val `*`: `*` = _12
    type `*`      = _12
     val `+`: `+` = _13
    type `+`      = _13
     val `-`: `-` = _14
    type `-`      = _14
     val `/`: `/` = _15
    type `/`      = _15

    val group = new group
    final class group extends Rule {
        type self = group
        override  def rule: rule = term(`(`).seq(expr).seq(`)`)
        override type rule       = term[`(`]#seq[expr]#seq[`)`]
    }

    val factor = new factor
    final class factor extends Rule {
        type self = factor
        override  def rule: rule = integer.or(group)
        override type rule       = integer#or[group]
    }

    val factor = new factor
    final class factor extends Rule {
        type self = factor
        override  def rule: rule = integer.or(group)
        override type rule       = integer#or[group]
    }

}
*/