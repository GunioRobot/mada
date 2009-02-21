

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


/**
 * Contains implicit conversions around <code>Peg</code>.
 */
trait Compatibles {
    /**
     * Converts a character to <code>Peg</code>.
     */
    implicit def madaPegFromChar(from: Char): Peg[Char] = Single(from)

    /**
     * Converts a <code>Iterable</code> to <code>Peg</code>.
     */
    implicit def madaPegFromIterable[A](from: Iterable[A]): Peg[A] = FromIterable(from)

    /**
     * Converts a <code>Regex</code> to <code>Peg</code>.
     */
    implicit def madaPegFromRegex(from: scala.util.matching.Regex): Peg[Char] = FromRegexPattern(from.pattern)

    /**
     * Converts a <code>regex.Pattern</code> to <code>Peg</code>.
     */
    implicit def madaPegFromRegexPattern(from: java.util.regex.Pattern): Peg[Char] = FromRegexPattern(from)

    /**
     * Converts a <code>String</code> to <code>Peg</code>.
     */
    implicit def madaPegFromString(from: String): Peg[Char] = FromString(from)

    /**
     * Converts a <code>Vector</code> to <code>Peg</code>.
     */
    implicit def madaPegFromVector[A](from: Vector[A]): Peg[A] = FromVector(from)
}
