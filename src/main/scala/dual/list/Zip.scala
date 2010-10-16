

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[dual]
object Zip {
     def apply[xs <: List, ys <: List](xs: xs, ys: ys): apply[xs, ys] = Impl(xs, ys)
    type apply[xs <: List, ys <: List]                                = Impl[xs, ys]

    case class Impl[xs <: List, ys <: List](xs: xs, ys: ys) extends AbstractList {
        type self = Impl[xs, ys]

        override  def isEmpty: isEmpty = xs.isEmpty.or(ys.isEmpty)
        override type isEmpty          = xs#isEmpty#or[ys#isEmpty]

        override  def head: head = Tuple2(xs.head, ys.head)
        override type head       = Tuple2[xs#head, ys#head]

        override  def tail: tail = Impl(xs.tail, ys.tail)
        override type tail       = Impl[xs#tail, ys#tail]
    }
}

private[dual]
object ZipBy {
     def apply[xs <: List, ys <: List, f <: Function2](xs: xs, ys: ys, f: f): apply[xs, ys, f] = Impl(xs, ys, f)
    type apply[xs <: List, ys <: List, f <: Function2]                                         = Impl[xs, ys, f]

    case class Impl[xs <: List, ys <: List, f <: Function2](xs: xs, ys: ys, f: f) extends AbstractList {
        type self = Impl[xs, ys, f]

        override  def isEmpty: isEmpty = xs.isEmpty.or(ys.isEmpty)
        override type isEmpty          = xs#isEmpty#or[ys#isEmpty]

        override  def head: head = f.apply(xs.head, ys.head)
        override type head       = f#apply[xs#head, ys#head]

        override  def tail: tail = Impl(xs.tail, ys.tail, f)
        override type tail       = Impl[xs#tail, ys#tail, f]
    }
}
