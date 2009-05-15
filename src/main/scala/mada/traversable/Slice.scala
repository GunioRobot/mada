

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class Slice[A](_1: Traversable[A], _2: Int, _3: Int) extends Traversable[A] {
    if (_2 > _3) {
        throw new IllegalArgumentException("slice" + (_2, _3))
    }
    override def start = _1.drop(_2).take(_3 - _2).start
}