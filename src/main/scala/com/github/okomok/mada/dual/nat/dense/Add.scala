

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package dense


private[mada] final class Add {
     def apply[xs <: Dense, ys <: Dense](xs: xs, ys: ys): apply[xs, ys] =
        Match(xs, ys, const0(Nil), const0(ys), const0(xs),
            ConsMatch(xs, ys, CaseTT(xs, ys), CaseXF(xs, ys), CaseFX(xs, ys), CaseXF(xs, ys))).apply.asInstanceOfNatDense.asInstanceOf[apply[xs, ys]]
    type apply[xs <: Dense, ys <: Dense] =
        Match[xs, ys, const0[Nil], const0[ys], const0[xs],
            ConsMatch[xs, ys, CaseTT[xs, ys], CaseXF[xs, ys], CaseFX[xs, ys], CaseXF[xs, ys]]]#apply#asInstanceOfNatDense

    case class CaseTT[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        override  val self = this
        override type self = CaseTT[xs, ys]
        override  def apply: apply = Cons(`false`, (xs.tail + ys.tail).increment).asInstanceOf[apply]
        override type apply = Cons[`false`, xs#tail# +[ys#tail]#increment]
    }

    case class CaseXF[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        override  val self = this
        override type self = CaseXF[xs, ys]
        private   def xst_add_yst: xst_add_yst = (xs.tail + ys.tail).asInstanceOf[xst_add_yst]
        private  type xst_add_yst = xs#tail# +[ys#tail]
        override  def apply: apply = Cons(xs.head, xst_add_yst)
        override type apply = Cons[xs#head, xst_add_yst]
    }

    case class CaseFX[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        override  val self = this
        override type self = CaseFX[xs, ys]
        private   def xst_add_yst: xst_add_yst = (xs.tail + ys.tail).asInstanceOf[xst_add_yst]
        private  type xst_add_yst = xs#tail# +[ys#tail]
        override  def apply: apply = Cons(ys.head, xst_add_yst)
        override type apply = Cons[ys#head, xst_add_yst]
    }
}
