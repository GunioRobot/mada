

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object Plus {
    def apply[A](p: Parser[A]): Parser[A] = p seqAnd p.star
}

object PlusBefore {
    def apply[A](p: Parser[A], q: Parser[A]): Parser[A] = p seqAnd p.starBefore(q)
}

object PlusUntil {
    def apply[A](p: Parser[A], q: Parser[A]): Parser[A] = p seqAnd p.starUntil(q)
}