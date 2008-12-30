

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object Begin {
    def apply[A]: Parser[A] = new BeginParser[A]
}

class BeginParser[A] extends Parser[A] {
    override def parse(s: Scanner[A], first: Long, last: Long): Long = {
        if (first == 0) {
            first
        } else {
            FAILED
        }
    }
}


object End {
    def apply[A]: Parser[A] = new EndParser[A]
}

class EndParser[A] extends Parser[A] {
    override def parse(s: Scanner[A], first: Long, last: Long): Long = {
        if (first == last) {
            first
        } else {
            FAILED
        }
    }
}