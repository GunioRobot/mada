

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


case class Repeat[+A](_1: A) extends Sequence[A] {
    override def begin = new Iterator[A] {
        override def isEnd = false
        override def deref = _1
        override def increment = ()
    }
}