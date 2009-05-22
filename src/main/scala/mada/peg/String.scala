

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


case class FromChar(_1: Char) extends Forwarder[Char] {
    override protected val delegate = single(_1)
}


case class Unstringize(_1: String) extends Forwarder[Char] {
    override protected val delegate = unstringizeBy(_1)(function.equal)
}

case class UnstringizeBy(_1: String, _2: (Char, Char) => Boolean) extends Forwarder[Char] {
    override protected val delegate = fromVectorBy(vector.unstringize(_1))(_2)
}
