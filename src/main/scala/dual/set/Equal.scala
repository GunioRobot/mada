

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package set


private[dual]
object Equal {
     def apply[s <: Set, z <: Set](s: s, z: z): apply[s, z] =
        `if`(s.size.nequal(z.size), const0(`false`), Else(s, z)).apply.asInstanceOfBoolean.asInstanceOf[apply[s, z]]
    type apply[s <: Set, z <: Set] =
        `if`[s#size#nequal[z#size], const0[`false`], Else[s, z]]#apply#asInstanceOfBoolean

    case class Else[s <: Set, z <: Set](s: s, z: z) extends Function0 {
        type self = Else[s, z]
        override  def apply: apply = s.toList.forall(Pred(z))
        override type apply        = s#toList#forall[Pred[z]]
    }

    case class Pred[z <: Set](z: z) extends Function1 {
        type self = Pred[z]
        override  def apply[k <: Any](k: k): apply[k] = z.contains(k)
        override type apply[k <: Any]                 = z#contains[k]
    }
}