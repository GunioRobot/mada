

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package vector


case class Single[A](/*@volatile ..compiler error!*/ var _1: A) extends Vector[A] {
    override def start = 0
    override def end = 1
    override def apply(i: Int) = _1
    override def update(i: Int, e: A) = _1 = e
}
