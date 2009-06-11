

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.list


/**
 * The matcher for list containing one element.
 */
object Single {

    def apply[A](x: A): List[A] = Cons(x, Nil)

    def unapply[A](xs: List[A]): Option[A] = xs match {
        case Cons(x, xs) if xs().isNil => Some(x)
        case _ => None
    }

}
