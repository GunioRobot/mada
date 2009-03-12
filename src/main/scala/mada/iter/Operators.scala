

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


/**
 * Contains operators missing from <code>Iterable</code>.
 */
trait Operators {
    sealed class MadaIterables[A](_1: Iterable[A]) {
        def equal[B](_2: Iterable[B]) = Iterables.equal(_1, _2)
        def equalBy[B](_2: Iterable[B])(_3: Functions.Predicate2[A, B]) = Iterables.equalBy(_1, _2)(_3)
        def filter_(_2: A => Boolean) = Iterables.filter(_1)(_2)
        def folderLeft[B](_2: B)(_3: (B, A) => B) = Iterables.folderLeft(_1, _2)(_3)
        def reducerLeft[B >: A](_2: (B, A) => B) = Iterables.reducerLeft[A, B](_1)(_2)
        def length = Iterables.length(_1)
        def seal = Iterables.seal(_1)
        def step(_2: Int) = Iterables.step(_1, _2)
        def takeWhile_(_2: A => Boolean) = Iterables.takeWhile(_1)(_2)
        def toHashSet = Iterables.toHashSet(_1)
        def toString_ = Iterables.stringFrom(_1)
        def withSideEffect(_2: A => Unit) = Iterables.withSideEffect(_1)(_2)
    }
    implicit def madaIterables[A](_1: Iterable[A]): MadaIterables[A] = new MadaIterables(_1)

    sealed class MadaIterablesIterable[A](_1: Iterable[Iterable[A]]) {
        def flatten = Iterables.flatten(_1)
    }
    implicit def madaIterablesIterable[A](_1: Iterable[Iterable[A]]): MadaIterablesIterable[A] = new MadaIterablesIterable(_1)

    sealed class MadaIterablesByName[A](_1: => Iterable[A]) {
        def `lazy` = Iterables.`lazy`(_1)
    }
    implicit def madaIterablesByName[A](_1: => Iterable[A]): MadaIterablesByName[A] = new MadaIterablesByName(_1)
}