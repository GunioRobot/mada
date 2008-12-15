

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


import Equals._
import From._
import ReadOnly._
import ShallowEquals._
import jcl.ToArrayList._


object Rng extends Namespace
        with ToArray
        with AsRngBy
        with AsRngOf
        with Begin with End
        with ToCell
        with Compatibles
        with CopyTo with CopyBackwardTo
        with Indirect with Outdirect
        with Distance
        with Drop
        with DropWhile
        with Equals
        with EqualsTo
        with Exists
        with Filter
        with Find
        with FindPointerOf
        with First with Last
        with FoldLeft
        with Forall
        with Force
        with Foreach
        with From
        with IsEmpty
        with Loop
        with Map
        with Offset
        with Permutation
        with ReadOnly
        with Reverse
        with ShallowEquals
        with Size
        with Slice
        with Take
        with TakeWhile
        with ToRng


trait Rng[A] extends Expr.Start[Rng[A]]
        with Traversal.Modeller
        with detail.ForComprehension[A] {
    protected def _begin: Pointer[A]
    protected def _end: Pointer[A]

    final def begin = _begin
    final def end = _end
    override protected def _traversal = begin.traversal

    override def equals(that: Any) = equals(that.asInstanceOf[Rng[A]])
    override def toString = detail.ToString(this)

    final def equals(that: Rng[A]) = /.requals(that)./
    final def shallowEquals(that: Rng[A]) = /.shallowEquals(that)./
    final def shallowToString = detail.ShallowToString(this)
    final def readOnly = /.readOnly./

    final def rng = this

    final def toPair: (Pointer[A], Pointer[A]) = (begin, end)
    final def toTriple: (Pointer[A], Long, Long) = { val (p, q) = toPair; (p, 0, q - p) }
}
