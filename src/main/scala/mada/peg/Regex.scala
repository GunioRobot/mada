

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Regex {
    def apply(x: String): Peg[Char] = Peg.fromRegexPattern(java.util.regex.Pattern.compile(x))
}
