

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.list


/**
 * The bridge between phisical and logical hierarchy
 */
trait Sequence[+A] extends iterative.Sequence[A] { // physical

    @conversion
    def asList: List[A] // logical

    override def asIterative: Iterative[A] = AsIterative(asList) // logical super

    @optimize
    override def equals(that: Any): Boolean = that match {
        case that: Sequence[_] => asList.equalsIf(that.asList)(function.equal)
        case _ => super.equals(that)
    }

    override def hashCode = {
        var r = 1
        var it = asList
        while (!it.isEmpty) {
            r = 31 * r + it.head.hashCode
            it = it.tail
        }
        r
    }

}


object Sequence {

// logical hierarchy
    implicit def _asIterative[A](from: Sequence[A]): Iterative[A] = from.asIterative

}


trait SequenceForwarder[+A] extends Sequence[A] with iterative.SequenceForwarder[A] {
    override protected def delegate: Sequence[A]
    override def asList = delegate.asList
}