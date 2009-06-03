

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class Memoize[A](_1: Vector[A]) extends Adapter.Transform[A] with Adapter.NotWritable[A] {
    override val underlying = _1

    private val table = new java.util.concurrent.ConcurrentHashMap[Int, () => A]
    override def apply(i: Int) = assoc.lazyGet(table)(i){ underlying(i) }
    override def memoize = this // memoize-memoize fusion
}