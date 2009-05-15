

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


@compatibles
trait Compatibles { self: Traversable.type =>
    implicit def madaTraversableFromIterable[A](from: Iterable[A]): Traversable[A] = fromIterable(from)
    implicit def madaTraversableToIterable[A](from: Traversable[A]): Iterable[A] = from.toIterable
}