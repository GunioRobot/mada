

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.list


case class Drop[+A](_1: List[A], _2: Int) extends Forwarder[A] {
    override protected val delegate: List[A] = {
        if (_2 <= 0) {
            _1
        } else if (_1.isNil) {
            nil
        } else {
            _1.tail.drop(_2 - 1)
        }
    }
}
