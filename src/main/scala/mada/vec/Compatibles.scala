

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


/**
 * Contains implicit conversions around <code>Vector</code>.
 */
trait Compatibles {
    import Vector._

// from
    implicit def madaVectorFromArray[A](from: Array[A]): Vector[A] = fromArray(from)
    implicit def madaVectorFromCell[A](from: Cell[A]): Vector[A] = fromCell(from)
    implicit def madaVectorFromJclCharSequence(from: java.lang.CharSequence): Vector[Char] = fromJclCharSequence(from)
    implicit def madaVectorFromJclList[A](from: java.util.List[A]): Vector[A] = fromJclList(from)
    implicit def madaVectorFromOption[A](from: Option[A]): Vector[A] = fromOption(from)
    implicit def madaVectorFromProduct(from: Product): Vector[Any] = fromProduct(from)
    implicit def madaVectorFromRandomAccessSeq[A](from: RandomAccessSeq[A]): Vector[A] = fromRandomAccessSeq(from)
    implicit def madaVectorFromString(from: String): Vector[Char] = fromString(from)
// to
    implicit def madaVectorToJclCharSequence(from: Vector[Char]): java.lang.CharSequence = toJclCharSequence(from)
    implicit def madaVectorToIterator[A](from: Vector[A]): Iterator[A] = toIterator(from)
    implicit def madaVectorToRandomAccessSeq[A](from: Vector[A]): RandomAccessSeq.Mutable[A] = toRandomAccessSeq(from)
    implicit def madaVectorToJclListIterator[A](from: Vector[A]): java.util.ListIterator[A] = toJclListIterator(from)
    implicit def madaVectorToLinearAccessSeq[A](from: Vector[A]): Seq[A] = toLinearAccessSeq(from)
    implicit def madaVectorToProduct[A](from: Vector[A]): Product = toProduct(from)
    implicit def madaVectorToStream[A](from: Vector[A]): Stream[A] = toStream(from)
}
