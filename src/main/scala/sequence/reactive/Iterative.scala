

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
class FromIterative[+A](_1: Iterative[A]) extends Reactive[A] {
    override def foreach(f: A => Unit) = _1.foreach(f)
}


private
case class ToIterative[A](_1: Reactive[A]) extends iterative.Forwarder[A] {
    override protected val delegate = iterative.generator(impl)

    private def impl(y: iterative.Yield[A]): Unit = {
        for (x <- _1) {
            y(x)
        }
    }
}
