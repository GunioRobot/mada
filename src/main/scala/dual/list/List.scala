

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


// See: HList.scala
//      at http://www.assembla.com/wiki/show/metascala


object List extends Common with ToSTuple


sealed abstract class List extends seq.AbstractSeq {
    type self <: List
    type asInstanceOfList = self

    type tail <: List
    type append[that <: Seq] <: List
    type map[f <: Function1] <: List
    type flatten <: List
    type filter[f <: Function1] <: List
    type sort[o <: Ordering] <: List
    type scanLeft[z <: Any, f <: Function2] <: List
    type scanRight[z <: Any, f <: Function2] <: List
    type init <: List
    type takeWhile[f <: Function1] <: List
    type dropWhile[f <: Function1] <: List
    type zip[that <: Seq] <: List

    final override  def toList: toList = self
    final override type toList         = self

    @aliasOf("addFirst")
    final def ::[e <: Any](e: e): addFirst[e] = addFirst(e)

    @equivalentTo("Cons(e, self)")
    final override  def addFirst[e <: Any](e: e): addFirst[e] = new _Cons().apply(e, self)
    final override type addFirst[e <: Any] = _Cons#apply[e, self]

    final override  def clear: clear = Nil
    final override type clear        = Nil

    final  def :::[that <: List](that: that): :::[that] = that.append(self)
    final type :::[that <: List]                        = that#append[self]

    final override  def last: last = nth(size.decrement)
    final override type last       = nth[size#decrement]

    final override  def take[n <: Nat](n: n): take[n] = new Take().apply(self, n)
    final override type take[n <: Nat] = Take#apply[self, n]

    final override  def drop[n <: Nat](n: n): drop[n] = new Drop().apply(self, n)
    final override type drop[n <: Nat] = Drop#apply[self, n]

    final  def reverse_:::[that <: List](that: that): reverse_:::[that] = that.reverseAppend(self)
    final type reverse_:::[that <: List]                                = that#reverseAppend[self]

    final override  def reverse: reverse = reverseAppend(Nil)
    final override type reverse          = reverseAppend[Nil]

    private[mada]  def reverseAppend[that <: List](that: that): reverseAppend[that]
    private[mada] type reverseAppend[that <: List] <: List

    final override  def unzip: unzip = new Unzip().apply(self)
    final override type unzip = Unzip#apply[self]

    /**
     * Returns the first element whose type is <code>k</code>.
     */
    @pre("List contains an element whose type is `k`.")
    final  def elementOf[e <: Any](implicit _elementOf: ElementOf[self, e]): elementOf[e] = _elementOf(self)
    final type elementOf[e <: Any] = ElementOf.result[self, e]

    final override type undual = scala.collection.immutable.List[scala.Any]
    final override def canEqual(that: scala.Any) = that.isInstanceOf[List]
}


sealed abstract class Nil extends List {
    type self = Nil

    override  def head: head = `throw`(new NoSuchElementException("dual.list.Nil.head"))
    override type head       = `throw`[_]

    override  def tail: tail = `throw`(new NoSuchElementException("dual.list.Nil.tail"))
    override type tail       = `throw`[_]

    override  def isEmpty: isEmpty = `true`
    override type isEmpty          = `true`

    override  def size: size = nat.peano._0
    override type size       = nat.peano._0

    override  def append[that <: Seq](that: that): append[that] = that.toList
    override type append[that <: Seq]                           = that#toList

    override  def map[f <: Function1](f: f): map[f] = self
    override type map[f <: Function1]               = self

    override  def flatten: flatten = self
    override type flatten          = self

    override  def filter[f <: Function1](f: f): filter[f] = self
    override type filter[f <: Function1]                  = self

    override  def partition[f <: Function1](f: f): partition[f] = Tuple2(self, self)
    override type partition[f <: Function1]                     = Tuple2[self, self]

    override  def sort[o <: Ordering](o: o): sort[o] = self
    override type sort[o <: Ordering]                = self

    override  def scanLeft[z <: Any, f <: Function2](z: z, f: f): scanLeft[z, f] = new _Cons().apply(z, self)
    override type scanLeft[z <: Any, f <: Function2] = _Cons#apply[z, self]

    override  def scanRight[z <: Any, f <: Function2](z: z, f: f): scanRight[z, f] = new _Cons().apply(z, self)
    override type scanRight[z <: Any, f <: Function2] = _Cons#apply[z, self]

    override  def nth[n <: Nat](n: n): nth[n] = `throw`(new NoSuchElementException("Nil.nth"))
    override type nth[n <: Nat]               = `throw`[_]

    override  def init: init = unsupported("list.Nil.init")
    override type init       = unsupported[_]

    override  def takeWhile[f <: Function1](f: f): takeWhile[f] = self
    override type takeWhile[f <: Function1]                     = self

    override  def dropWhile[f <: Function1](f: f): dropWhile[f] = self
    override type dropWhile[f <: Function1]                     = self

    override  def span[f <: Function1](f: f): span[f] = Tuple2(self, self)
    override type span[f <: Function1]                = Tuple2[self, self]

    override  def splitAt[n <: Nat](n: n): splitAt[n] = Tuple2(self, self)
    override type splitAt[n <: Nat]                   = Tuple2[self, self]

    override private[mada]  def reverseAppend[that <: List](that: that) = that
    override private[mada] type reverseAppend[that <: List]             = that

    override  def zip[that <: Seq](that: that): zip[that] = self
    override type zip[that <: Seq]                        = self

    override  def undual: undual = scala.collection.immutable.Nil
}


final case class Cons[x <: Any, xs <: List](override val head: x, override val tail: xs) extends List {
    type self = Cons[x, xs]

    override type head = x
    override type tail = xs

    override  def isEmpty: isEmpty = `false`
    override type isEmpty          = `false`

    override  val size: size = tail.size.increment
    override type size       = tail#size#increment

    override  def append[that <: Seq](that: that): append[that] = Cons(self.head, self.tail.append(that))
    private type _append[self <: List, that <: Seq]             = Cons[self#head, self#tail#append[that]]
    override type append[that <: Seq] = _append[self, that]

    override  def map[f <: Function1](f: f): map[f] = Cons(f.apply(self.head), self.tail.map(f))
    private type _map[self <: List, f <: Function1] = Cons[f#apply[self#head], self#tail#map[f]]
    override type map[f <: Function1] = _map[self, f]

    override  def flatten: flatten = tail.flatten. :::(head.asInstanceOfList)
    override type flatten          = tail#flatten# :::[head#asInstanceOfList]

    override  def filter[f <: Function1](f: f): filter[f] = new ConsFilter().apply(head, tail, f)
    override type filter[f <: Function1] = ConsFilter#apply[head, tail, f]

    override  def partition[f <: Function1](f: f): partition[f] = new ConsPartition().apply(head, tail, f)
    override type partition[f <: Function1] = ConsPartition#apply[head, tail, f]

    override  def sort[o <: Ordering](o: o): sort[o] = new ConsSort().apply(self, o)
    override type sort[o <: Ordering] = ConsSort#apply[self, o]

    override  def scanLeft[z <: Any, f <: Function2](z: z, f: f): scanLeft[z, f] = new ConsScanLeft().apply(head, tail, z, f)
    override type scanLeft[z <: Any, f <: Function2] = ConsScanLeft#apply[head, tail, z, f]

    override  def scanRight[z <: Any, f <: Function2](z: z, f: f): scanRight[z, f] = new ConsScanRight().apply(head, tail, z, f)
    override type scanRight[z <: Any, f <: Function2] = ConsScanRight#apply[head, tail, z, f]

    override  def nth[n <: Nat](n: n): nth[n] = new ConsNth().apply(head, tail, n)
    override type nth[n <: Nat] = ConsNth#apply[head, tail, n]

    override  def init: init = new ConsInit().apply(head, tail)
    override type init = ConsInit#apply[head, tail]

    override  def takeWhile[f <: Function1](f: f): takeWhile[f] = new ConsTakeWhile().apply(head, tail, f)
    override type takeWhile[f <: Function1] = ConsTakeWhile#apply[head, tail, f]

    override  def dropWhile[f <: Function1](f: f): dropWhile[f] = new ConsDropWhile().apply(self, f)
    override type dropWhile[f <: Function1] = ConsDropWhile#apply[self, f]

    override  def span[f <: Function1](f: f): span[f] = new ConsSpan().apply(self, f)
    override type span[f <: Function1] = ConsSpan#apply[self, f]

    override  def splitAt[n <: Nat](n: n): splitAt[n] = new ConsSplitAt().apply(self, n)
    override type splitAt[n <: Nat] = ConsSplitAt#apply[self, n]

    override private[mada]  def reverseAppend[that <: List](that: that) = self.tail.reverseAppend(Cons(self.head, that))
    private type _reverseAppend[self <: List, that <: List]             = self#tail#reverseAppend[Cons[self.head, that]]
    override private[mada] type reverseAppend[that <: List] = _reverseAppend[self, that]

    override  def zip[that <: Seq](that: that): zip[that] = new ConsZip().apply(self, that)
    override type zip[that <: Seq] = ConsZip#apply[self, that]

    override  def undual: undual = head.undual :: tail.undual
}


@typeInstantiationErrorWorkaround
private[mada] final class _Cons {
     def apply[x <: Any, xs <: List](x: x, xs: xs): apply[x, xs] = Cons(x, xs)
    type apply[x <: Any, xs <: List] = Cons[x, xs]
}


private[mada] object _List {
    val Nil = new Nil{}
}
