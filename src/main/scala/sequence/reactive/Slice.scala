

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private[reactive]
case class Slice[+A](_1: Reactive[A], _2: Int, _3: Int) extends Forwarder[A] {
    Precondition.range(_2, _3, "slice")
    override protected val delegate = _1.take(_3).drop(_2)
}
