

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package dense


private[mada] final class BitAnd {
     def apply[xs <: Dense, ys <: Dense](xs: xs, ys: ys): apply[xs, ys] =
        Match(xs, ys, Const0(Nil), Const0(Nil), Const0(Nil),
            ConsMatch(xs, ys, CaseTT(xs, ys), Else(xs, ys), Else(xs, ys), Else(xs, ys))).apply.asInstanceOfNatDense.asInstanceOf[apply[xs, ys]]
    type apply[xs <: Dense, ys <: Dense] =
        Match[xs, ys, Const0[Nil], Const0[Nil], Const0[Nil],
            ConsMatch[xs, ys, CaseTT[xs, ys], Else[xs, ys], Else[xs, ys], Else[xs, ys]]]#apply#asInstanceOfNatDense

    case class CaseTT[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        type self = CaseTT[xs, ys]
        private   def xst_and_yst: xst_and_yst = (xs.tail & ys.tail).asInstanceOf[xst_and_yst]
        private  type xst_and_yst = xs#tail# &[ys#tail]
        override  def apply: apply = Cons(`true`, xst_and_yst)
        override type apply = Cons[`true`, xst_and_yst]
    }

    case class Else[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        type self = Else[xs, ys]
        override  def apply: apply = new ConsFalse().apply(xs.tail & ys.tail).asInstanceOf[apply]
        override type apply = ConsFalse#apply[xs#tail# &[ys#tail]]
    }
}


private[mada] final class BitOr {
     def apply[xs <: Dense, ys <: Dense](xs: xs, ys: ys): apply[xs, ys] =
        Match(xs, ys, Const0(Nil), Const0(ys), Const0(xs),
            ConsMatch(xs, ys, Else(xs, ys), Else(xs, ys), Else(xs, ys), CaseFF(xs, ys))).apply.asInstanceOfNatDense.asInstanceOf[apply[xs, ys]]
    type apply[xs <: Dense, ys <: Dense] =
        Match[xs, ys, Const0[Nil], Const0[ys], Const0[xs],
            ConsMatch[xs, ys, Else[xs, ys], Else[xs, ys], Else[xs, ys], CaseFF[xs, ys]]]#apply#asInstanceOfNatDense

    case class CaseFF[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        type self = CaseFF[xs, ys]
        private   def xst_or_yst: xst_or_yst = (xs.tail | ys.tail).asInstanceOf[xst_or_yst]
        private  type xst_or_yst = xs#tail# |[ys#tail]
        override  def apply: apply = Cons(`false`, xst_or_yst)
        override type apply = Cons[`false`, xst_or_yst]
    }

    case class Else[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        type self = Else[xs, ys]
        private   def xst_or_yst: xst_or_yst = (xs.tail | ys.tail).asInstanceOf[xst_or_yst]
        private  type xst_or_yst = xs#tail# |[ys#tail]
        override  def apply: apply = Cons(`true`, xst_or_yst)
        override type apply = Cons[`true`, xst_or_yst]
    }
}