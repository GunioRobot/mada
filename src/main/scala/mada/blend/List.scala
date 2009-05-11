

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.blend


// See: HList.scala
//      at http://www.assembla.com/wiki/show/scala.result


import list._


sealed trait List { // this: `this` =>

    private val _this = this.asInstanceOf[`this`]
    private[mada] type `this` <: List

    /**
     * Returns the first element.
     */
    def head: head
    type head

    /**
     * Returns the trailing list.
     */
    def tail: tail
    type tail <: List

    /**
     * Is this list nil?
     */
    type isEmpty <: meta.Boolean
    final def isEmpty(implicit _unmeta: meta.Unmeta[isEmpty, scala.Boolean]): scala.Boolean = _unmeta() // just for convenience.

    /**
     * Supports visitor iteration.
     */
    type accept[v <: Visitor] <: v#Result

    /**
     * Returns the <code>n</code>-th element.
     */
    final def at[n <: meta.Nat](implicit _at: At[`this`, n]): at[n] = _at(_this)
    final type at[n <: meta.Nat] = At.result[`this`, n]

    /**
     * Drops EXACTLY <code>n</code> elements.
     */
    final def drop[n <: meta.Nat](implicit _drop: Drop[`this`, n]): drop[n] = _drop(_this)
    final type drop[n <: meta.Nat] = Drop.result[`this`, n]

    /**
     * Takes EXACTLY <code>n</code> elements.
     */
    final def take[n <: meta.Nat](implicit _take: Take[`this`, n]): take[n] = _take(_this)
    final type take[n <: meta.Nat] = Take.result[`this`, n]

    /**
     * @return <code>drop[n].take[m - n]</code>.
     */
    final def slice[n <: meta.Nat, m <: meta.Nat](implicit _slice: Slice[`this`, n, m]): slice[n, m] = _slice(_this)
    final type slice[n <: meta.Nat, m <: meta.Nat] = Slice.result[`this`, n, m]

    /**
     * Returns the last element.
     */
    final def last(implicit _last: Last[`this`, meta.error]): last = _last(_this, meta.nullOf[meta.error])
    final type last = Last.result[`this`, meta.error]

    /**
     * Returns the length.
     */
    final type length = Length.result[`this`]
    final def length(implicit _unmeta: meta.Unmeta[length, scala.Int]): scala.Int = _unmeta() // just for convenience.

    /**
     * Prepends <code>that</code>.
     */
    final def :::[that <: List](_that: that)(implicit _prepend: Prepend[`this`, that]): prepend[that] = _prepend(_this, _that)
    final type prepend[that <: List] = Prepend.result[`this`, that]

    /**
     * Prepends reversed <code>that</code>.
     */
    final def reverse_:::[that <: List](_that: that)(implicit _reversePrepend: ReversePrepend[`this`, that]): reversePrepend[that] = _reversePrepend(_this, _that)
    final type reversePrepend[that <: List] = ReversePrepend.result[`this`, that]

    /**
     * Returns reversed one.
     */
    final def reverse(implicit _reversePrepend: ReversePrepend[Nil, `this`]): reverse = _reversePrepend(Nil, _this)
    final type reverse = ReversePrepend.result[Nil, `this`]

    /**
     * Converts to <code>scala.List[Any]</code>.
     */
    def untyped: untyped // The implicit way would annoy toString.
    final type untyped = scala.List[Any]

    /**
     * Prepends <code>e</code>.
     */
    final def ::[A](e: A): Cons[A, `this`] = Cons(e, _this)

    final override def toString = untyped.toString

}


// Compiler will fail to search implicits.
// case object Nil; type Nil = Nil.type

sealed trait Nil extends List {
    private[mada] override type `this` = Nil

    override def head = throw new NoSuchElementException("head of empty list")
    override type head = meta.error
    override def tail = throw new NoSuchElementException("tail of empty list")
    override type tail = meta.error // Nil would `List.take` less-restrictive, but less-mathematical.
    override type isEmpty = meta.`true`
    override type accept[v <: Visitor] = v#visitNil

    override def untyped = scala.Nil
}

private[mada] object NilWrap {
    val value: Nil = new Nil{}
}


final case class Cons[h, t <: List](override val head: h, override val tail: t) extends List {
    private[mada] override type `this` = Cons[h, t]

    override type head = h
    override type tail = t
    override type isEmpty = meta.`false`
    override type accept[v <: Visitor] = v#visitCons[h, t]

    override def untyped = scala.::[Any](head, tail.untyped)
}


@compilerWorkaround
object List {

    // For some compiler bug, these are placed in companion module.

    /**
     * @return  <code>r#prepend[l]</code>.
     */
    type :::[l <: List, r <: List] = r#prepend[l]

    /**
     * @return  <code>r#reversePrepend[l]</code>.
     */
    type reverse_:::[l <: List, r <: List] = r#reversePrepend[l]

}