

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[dual]
object ScanLeft {
     def apply[xs <: List, z <: Any, f <: Function2](xs: xs, z: z, f: f): apply[xs, z, f] = Cons(z, Impl(xs, z, f))
    type apply[xs <: List, z <: Any, f <: Function2]                                      = Cons[z, Impl[xs, z, f]]

    case class Impl[xs <: List, z <: Any, f <: Function2](xs: xs, z: z, f: f) extends AbstractList {
        type self = Impl[xs, z, f]

        override  def isEmpty: isEmpty = xs.isEmpty
        override type isEmpty          = xs#isEmpty

        override lazy val head: head = f.apply(z, xs.head)
        override type head           = f#apply[z, xs#head]

        override  def tail: tail = Impl(xs.tail, head, f)
        override type tail       = Impl[xs#tail, head, f]
    }
}


private[dual]
object ScanRight {
     def apply[xs <: List, z <: Any, f <: Function2](xs: xs, z: z, f: f): apply[xs, z, f] =
        `if`(xs.isEmpty, const0(single(z)), Else(xs, z, f)).apply.asList.asInstanceOf[apply[xs, z, f]]
    type apply[xs <: List, z <: Any, f <: Function2] =
        `if`[xs#isEmpty, const0[single[z]], Else[xs, z, f]]#apply#asList

    case class Else[xs <: List, z <: Any, f <: Function2](xs: xs, z: z, f: f) extends Function0 {
        type self = Else[xs, z, f]
        private lazy val ys: ys = ScanRight.apply(xs.tail, z, f)
        private type ys         = ScanRight.apply[xs#tail, z, f]
        override  def apply: apply = Cons(f.apply(xs.head, ys.head), ys)
        override type apply        = Cons[f#apply[xs#head, ys#head], ys]
    }
}
