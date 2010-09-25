

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
object Closer extends (Reactive[_] => Unit) {
    override def apply(r: Reactive[_]) = r.close
}
