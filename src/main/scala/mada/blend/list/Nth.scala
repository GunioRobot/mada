

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package blend; package list


// drop#head can't replace this. The delayed #head destroys type identity.


@specializer
trait Nth[l <: List, n <: meta.Nat] extends (l => Nth.result[l, n])


object Nth {

    type result[l <: List, n <: meta.Nat] = n#accept[vt[l]]

    sealed trait vt[l <: List] extends meta.nat.Visitor {
        override type Result = Any
        override type visitZero = l#head
        override type visitSucc[n <: meta.Nat] = n#accept[vt[l#tail]]
    }

    implicit def ofZero[h, t <: List] = new Nth[Cons[h, t], meta.Zero] {
        override def apply(_l: Cons[h, t]) = _l.head
    }

    implicit def ofSucc[h, t <: List, n <: meta.Nat](implicit _nth: Nth[t, n]) = new Nth[Cons[h, t], meta.Succ[n]] {
        override def apply(_l: Cons[h, t]) = _nth(_l.tail)
    }

}