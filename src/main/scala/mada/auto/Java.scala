

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.auto


case class FromJCloseable[A <: java.io.Closeable](_1: A) extends Auto[A] {
    override def get = _1
    override def end = _1.close
}

case class FromJLock[A <: java.util.concurrent.locks.Lock](_1: A) extends Auto[A] {
    override def get = _1
    override def begin = _1.lock
    override def end = _1.unlock
}