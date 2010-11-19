

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Breakable[A](_1: Reactive[A]) extends Reactive[Tuple2[A, Function0[Unit]]] {
    override def close() = _1.close()
    override def forloop(f: Tuple2[A, Function0[Unit]] => Unit, k: => Unit) {
        val _k = eval.Lazy{k}
        _1 _for { x =>
            f(x, () => {close();_k()})
        } _then {
            _k()
        }
    }
}
