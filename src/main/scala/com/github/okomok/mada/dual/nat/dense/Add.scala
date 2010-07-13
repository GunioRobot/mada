

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package dense


private[mada] final case class Add[xs <: Dense, ys <: Dense](xs: xs, ys: ys) {
     def apply: apply = Match(xs, ys, always0(Nil), always0(ys), always0(xs), ConsMatch(xs, ys, TT(), XF(), FX(), XF())).apply.asInstanceOfNatDense.asInstanceOf[apply]
    type apply = Match[xs, ys, always0[Nil], always0[ys], always0[xs], ConsMatch[xs, ys, TT, XF, FX, XF]]#apply#asInstanceOfNatDense

    final case class TT() extends Function0 {
        override  val self = this
        override type self = TT
        override  def apply: apply = Cons(`false`, (xs.tail + ys.tail).increment).asInstanceOf[apply]
        override type apply = Cons[`false`, xs#tail# +[ys#tail]#increment]
    }

    final case class XF() extends Function0 {
        override  val self = this
        override type self = XF
        override  def apply: apply = Cons(xs.head, (xs.tail + ys.tail))
        override type apply = Cons[xs#head, xs#tail# +[ys#tail]]
    }

    final case class FX() extends Function0 {
        override  val self = this
        override type self = FX
        override  def apply: apply = Cons(ys.head, xs.tail + ys.tail)
        override type apply = Cons[ys#head, xs#tail# +[ys#tail]]
    }
}
