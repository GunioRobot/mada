

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.str


/**
 * Contains implicit conversions around <code>String</code>.
 */
trait Compatibles {
    import Strings._

    implicit def madaStringsToIterator(s: String): Iterator[Char] = toIterator(s)
}