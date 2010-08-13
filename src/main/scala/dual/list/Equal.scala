

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[dual]
object Equal {
     def apply[xs <: List, ys <: List, ee <: Option](xs: xs, ys: ys, ee: ee): apply[xs, ys, ee] =
        `if`(xs.isEmpty.and(ys.isEmpty), const0(`true`), `if`(xs.isEmpty.nequal(ys.isEmpty), const0(`false`), Else(xs, ys, ee))).apply.asInstanceOfBoolean.asInstanceOf[apply[xs, ys, ee]]
    type apply[xs <: List, ys <: List, ee <: Option] =
        `if`[xs#isEmpty#and[ys#isEmpty], const0[`true`], `if`[xs#isEmpty#nequal[ys#isEmpty], const0[`false`], Else[xs, ys, ee]]]#apply#asInstanceOfBoolean

    case class Else[xs <: List, ys <: List, ee <: Option](xs: xs, ys: ys, ee: ee) extends Function0 {
        type self = Else[xs, ys, ee]
        private lazy val _ee: _ee = ee.getOrNaturalEquiv(xs.head)
        private type _ee          = ee#getOrNaturalEquiv[xs#head]
        override  def apply: apply = `if`(_ee.equiv(xs.head, ys.head), Then(xs, ys, ee), const0(`false`)).apply.asInstanceOf[apply]
        override type apply        = `if`[_ee#equiv[xs#head, ys#head], Then[xs, ys, ee], const0[`false`]]#apply
    }

    case class Then[xs <: List, ys <: List, ee <: Option](xs: xs, ys: ys, ee: ee) extends Function0 {
        type self = Then[xs, ys, ee]
        override  def apply: apply = Equal.apply(xs.tail, ys.tail, ee)//.asInstanceOf[apply]
        override type apply        = Equal.apply[xs#tail, ys#tail, ee]
    }
}