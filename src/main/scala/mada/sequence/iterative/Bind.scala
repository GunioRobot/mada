

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterative


case class Bind[+A](_1: Iterator[A]) extends Iterative[A] {
    override def begin = _1
}

case class BindName[+A](_1: function.OfName[Iterator[A]]) extends Iterative[A] {
    override def begin = _1()
}