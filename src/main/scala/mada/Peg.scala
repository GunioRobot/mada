

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


object Peg {
    import peg._

    val FAILED: Long = -1

    def any[A]: Peg[A] = Any_[A]
    def begin[A]: Peg[A] = Begin[A]
    def end[A]: Peg[A] = End[A]
    def eps[A]: Peg[A] = Eps[A]
    def error[A]: Peg[A] = Error[A]
    def fail[A]: Peg[A] = Fail[A]
    def icase(str: String): Peg[Char] = Icase(str)
    def lowerCaseScan(p: Peg[Char]): Peg[Char] = LowerCaseScan(p)
    def longest[A](ps: Peg[A]*) = Longest(ps: _*)
    def shortest[A](ps: Peg[A]*) = Shortest(ps: _*)
    def range[A](i: A, j: A)(implicit c: A => Ordered[A]): Peg[A] = Range(i, j)(c)
    def set[A](es: A*): Peg[A] = Set(es: _*)
    def single[A](e: A): Peg[A] = Single(e)
    def switch[A](es: (Vector[A], Peg[A])*)(implicit c: A => Ordered[A]): Peg[A] = Switch(es: _*)(c)
    def symbols[A](vs: Vector[A]*)(implicit c: A => Ordered[A]): Peg[A] = Symbols(vs: _*)(c)

    val compatibles: Compatibles = Compatibles
    def stringPeg(str: String): Peg[Char] = StringPeg(str)
    def vectorPeg[A](v: Vector[A]): Peg[A] = VectorPeg(v)

    def __*[A]: Peg[A] = any[A].star
    def __*?[A](p: Peg[A]): Peg[A] = any[A].starBefore(p)
    def __*~[A](p: Peg[A]): Peg[A] = any[A].starUntil(p)

    def ?=[A](p: Peg[A]): Peg[A] = p.lookAhead
    def ?![A](p: Peg[A]): Peg[A] = p.lookAhead.not
    def ?<=[A](p: Peg[A]): Peg[A] = p.lookBehind
    def ?<![A](p: Peg[A]): Peg[A] = p.lookBehind.not
    def ?<<=[A](p: Peg[A]): Peg[A] = p.lookBack
    def ?<<![A](p: Peg[A]): Peg[A] = p.lookBack.not

    type Actions = peg.Actions
    type Printer = peg.Printer
    type PegProxy[A] = peg.PegProxy[A]
    type Rule[A] = peg.Rule[A]

    def rule1[A]: (Rule[A]) = Rule.make1[A]
    def rule2[A]: (Rule[A], Rule[A]) = Rule.make2[A]
    def rule3[A]: (Rule[A], Rule[A], Rule[A]) = Rule.make3[A]
    def rule4[A]: (Rule[A], Rule[A], Rule[A], Rule[A]) = Rule.make4[A]
    def rule5[A]: (Rule[A], Rule[A], Rule[A], Rule[A], Rule[A]) = Rule.make5[A]
}


trait Peg[A] {
    import peg._

    def parse(v: Vector[A], first: Long, last: Long): Long
    def length: Long = throw new UnsupportedOperationException("Peg.length")
    protected final val FAILED = Peg.FAILED

    final def action(f: Vector[A] => Any): Peg[A] = Action(this, f)
    final def and(that: Peg[A]): Peg[A] = And(this, that)
    final def andIf(pred: Vector[A] => Boolean): Peg[A] = AndIf(this, pred)
    final def identity: Peg[A] = Identity(this)
    final def lookAhead: Peg[A] = LookAhead(this)
    final def lookBehind: Peg[A] = LookBehind(this)
    final def lookBack: Peg[A] = LookBack(this)
    final def minus(that: Peg[A]) = Minus(this, that)
    final def named(name: String) = Named(this, name)
    final def not: Peg[A] = Not(this)
    final def plus: Peg[A] = Plus(this)
    final def plusBefore(that: Peg[A]): Peg[A] = PlusBefore(this, that)
    final def plusUntil(that: Peg[A]): Peg[A] = PlusUntil(this, that)
    final def opt: Peg[A] = Opt(this)
    final def optBefore(that: Peg[A]): Peg[A] = OptBefore(this, that)
    final def optUntil(that: Peg[A]): Peg[A] = OptUntil(this, that)
    final def or(that: Peg[A]): Peg[A] = Or(this, that)
    final def repeat(min: Long, max: Long): Peg[A] = Repeat(this, min, max)
    final def seqAnd(that: Peg[A]): Peg[A] = SeqAnd(this, that)
    final def seqOr(that: Peg[A]): Peg[A] = SeqOr(this, that)
    final def star: Peg[A] = Star(this)
    final def starBefore(that: Peg[A]): Peg[A] = StarBefore(this, that)
    final def starUntil(that: Peg[A]): Peg[A] = StarUntil(this, that)
    final def unmap[Z](f: Z => A): Peg[Z] = Unmap(this, f)
    final def prescan[Z](f: Vector[Z] => Vector[A]): Peg[Z] = Prescan(this, f)
    final def xor(that: Peg[A]): Peg[A] = Xor(this, that)

    final def parse(v: Vector[A]): Long = Parse(this, v)
    final def matches(v: Vector[A]): Boolean = Matches(this, v)

    final def unary_! : Peg[A] = not
    final def &(that: Peg[A]): Peg[A] = and(that)
    final def |(that: Peg[A]): Peg[A] = or(that)
    final def -(that: Peg[A]): Peg[A] = minus(that)
    final def ^(that: Peg[A]): Peg[A] = xor(that)
    final def &&(that: Peg[A]): Peg[A] = seqAnd(that)
    final def ||(that: Peg[A]): Peg[A] = seqOr(that)
    final def ~(that: Peg[A]): Peg[A] = seqAnd(that)
    final def * : Peg[A] = star
    final def *?(that: Peg[A]): Peg[A] = starBefore(that)
    final def *~(that: Peg[A]): Peg[A] = starUntil(that)
    final def + : Peg[A] = plus
    final def +?(that: Peg[A]): Peg[A] = plusBefore(that)
    final def +~(that: Peg[A]): Peg[A] = plusUntil(that)
    final def ? : Peg[A] = opt
    final def ??(that: Peg[A]): Peg[A] = optBefore(that)
    final def ?~(that: Peg[A]): Peg[A] = optUntil(that)
    final def ^^(f: Vector[A] => Any): Peg[A] = action(f)

    final def ~?=(that: Peg[A]): Peg[A] = seqAnd(that.lookAhead)
    final def ~?!(that: Peg[A]): Peg[A] = seqAnd(that.lookAhead.not)
    final def ~?<=(that: Peg[A]): Peg[A] = seqAnd(that.lookBehind)
    final def ~?<!(that: Peg[A]): Peg[A] = seqAnd(that.lookBehind.not)
    final def ~?<<=(that: Peg[A]): Peg[A] = seqAnd(that.lookBack)
    final def ~?<<!(that: Peg[A]): Peg[A] = seqAnd(that.lookBack.not)
}
