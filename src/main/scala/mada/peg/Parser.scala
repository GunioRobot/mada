

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Parser {
    val FAILED: Long = -1

    def any[A]: Parser[A] = parser.Any_[A]
    def begin[A]: Parser[A] = parser.Begin[A]
    def end[A]: Parser[A] = parser.End[A]
    def eps[A]: Parser[A] = parser.Eps[A]
    def error[A]: Parser[A] = parser.Error[A]
    def fail[A]: Parser[A] = parser.Fail[A]
    def fromIterator[A](it: Iterator[A]): Parser[A] = parser.FromIterator(it)
    def fromString(str: String): Parser[Char] = parser.FromString(str)
    def range[A](i: A, j: A)(implicit c: A => Ordered[A]): Parser[A] = parser.Range(i, j)(c)
    def set[A](es: A*): Parser[A] = parser.Set(es: _*)
    def single[A](e: A): Parser[A] = parser.Single(e)

    def ^[A](p: Parser[A]): Parser[A] = p.not
    def ?=[A](p: Parser[A], n: Long): Parser[A] = p.after(n)
    def ?<=[A](p: Parser[A]): Parser[A] = p.before
}


trait Parser[A] {
    import parser._

    final val FAILED = Parser.FAILED
    def parse(s: Scanner[A], begin: Long, end: Long): Long

    def after(n: Long): Parser[A] = After(this, n)
    def before: Parser[A] = Before(this)
    def not: Parser[A] = Not(this)
    def plus: Parser[A] = Plus(this)
    def opt: Parser[A] = Opt(this)
    def or(that: Parser[A]): Parser[A] = Or(this, that)
    def repeat(min: Long, max: Long): Parser[A] = Repeat(this, min, max)
    def star: Parser[A] = Star(this)
    def starBefore(that: Parser[A]): Parser[A] = StarBefore(this, that)
    def starUntil(that: Parser[A]): Parser[A] = StarUntil(this, that)
    def then(that: Parser[A]): Parser[A] = Then(this, that)
    def unmap[Z](f: Z => A): Parser[Z] = Unmap(this, f)

    def ~(that: Parser[A]): Parser[A] = then(that)
    def |(that: Parser[A]): Parser[A] = or(that)
    def * = star
    def + = plus
    def ? = opt
}
