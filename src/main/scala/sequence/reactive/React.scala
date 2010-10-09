

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class React[A](_1: Reactive[A], _2: A => Unit) extends Reactive[A] {
    override def close = _1.close
    override def foreach(f: A => Unit) = {
        for (x <- _1) {
            _2(x)
            f(x)
        }
    }
}