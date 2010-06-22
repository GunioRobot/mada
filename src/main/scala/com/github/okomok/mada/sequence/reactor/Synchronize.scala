

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package reactor


private[mada] case class Synchronize[-A](_1: Reactor[A]) extends Reactor[A] {
    override def onEnd = synchronized { _1.onEnd }
    override def react(e: A) = synchronized { _1.react(e) }
}
