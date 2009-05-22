

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


case class SeqOpt[A](_1: Peg[A], _2: Peg[A]) extends Forwarder[A] {
    override protected val delegate = _2 >> _1.?
}
