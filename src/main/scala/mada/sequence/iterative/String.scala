

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package iterative


case class Unstringize(_1: String) extends Forwarder[Char] {
    override protected val delegate = fromSIterable(_1)
}
