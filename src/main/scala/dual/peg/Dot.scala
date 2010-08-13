

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package peg


private[dual]
object Dot {
     def apply: apply = new Impl
    type apply        =     Impl

    final class Impl extends AbstractPeg with OneWidth {
        type self = Impl

        override  def parse[xs <: List](xs: xs): parse[xs] =
            `if`(xs.isEmpty, const0(Failure(xs)), Else(xs)).apply.asInstanceOfPegResult//.asInstanceOf[parse[xs]]
        override type parse[xs <: List] =
            `if`[xs#isEmpty, const0[Failure[xs]], Else[xs]]#apply#asInstanceOfPegResult
    }

    final case class Else[xs <: List](xs: xs) extends Function0 {
        type self = Else[xs]
        override  def apply: apply = Success(xs.head, xs.tail)
        override type apply        = Success[xs#head, xs#tail]
    }
}
