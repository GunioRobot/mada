

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class Filter[A](_1: Reactive[A], _2: A => Boolean) extends Reactive[A] {
    override def subscribe(k: Reactor[A]) = {
        val j = new Reactor[A] {
            override def onEnd = k.onEnd
            override def react(e: A) = if (_2(e)) k.react(e)
        }
        _1.subscribe(j)
    }
}


case class Remove[A](_1: Reactive[A], _2: A => Boolean) extends Forwarder[A] {
    override protected val delegate = _1.filter(function.not(_2))
}