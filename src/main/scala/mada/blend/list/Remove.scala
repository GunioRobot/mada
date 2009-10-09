

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package blend; package list


@specializer
trait Remove[l <: List, n <: meta.Nat] extends (l => Remove.result[l, n])


object Remove {

    type result[l <: List, n <: meta.Nat] = n#accept[vt[l]]

    sealed trait vt[l <: List] extends meta.nat.Visitor {
        override type Result = List
        override type visitZero = l#tail
        override type visitSucc[n <: meta.Nat] = Cons[l#head, n#accept[vt[l#tail]]]
    }

    implicit def ofZero[h, t <: List] = new Remove[Cons[h, t], meta.Zero] {
        override def apply(_l: Cons[h, t]) = _l.tail
    }

    implicit def ofSucc[h, t <: List, n <: meta.Nat](implicit _remove: Remove[t, n]) = new Remove[Cons[h, t], meta.Succ[n]] {
        override def apply(_l: Cons[h, t]) = Cons(_l.head, _remove(_l.tail))
    }

}
