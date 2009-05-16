

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class Repeat[A](_1: A) extends Traversable[A] {
    override def start = new Traverser[A] {
        override def isEnd = false
        override def deref = _1
        override def increment = ()
    }
}