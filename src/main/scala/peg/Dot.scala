

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package peg


private[mada] case class Dot[A]() extends Forwarder[A] {
    override protected val delegate = advance[A](1)
}