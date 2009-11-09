

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package iterative


case class Strict[+A](_1: Iterative[A]) extends Forwarder[A] {
    override protected val delegate = _1.mix(Mixin.force)
}