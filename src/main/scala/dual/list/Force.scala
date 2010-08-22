

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[dual]
object Force {
     def apply[xs <: List](xs: xs): apply[xs] = `if`(xs.isEmpty, const0(Nil), Else(xs)).apply.asList
    type apply[xs <: List]                    = `if`[xs#isEmpty, const0[Nil], Else[xs]]#apply#asList

    case class Else[xs <: List](xs: xs) extends Function0 {
        type self = Else[xs]
        private lazy val r: r = Force.apply(xs.tail).asInstanceOf[r]
        private     type r    = Force.apply[xs#tail]
        override  def apply: apply = Cons(xs.head, r)
        override type apply        = Cons[xs#head, r]
    }
}
