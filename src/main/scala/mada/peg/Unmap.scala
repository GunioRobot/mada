

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


case class Unmap[A, Z](_1: Peg[A], _2: Z => A) extends Forwarder[Z] {
    override protected val delegate = _1.readMap{ (v: Vector[Z]) => v.map(_2) }
}
