

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


// `from( symbolMap("abc" --> until(f) >> "def") )`

class RangeActions[A] {
    val stack = new java.util.ArrayDeque[Long]

    def apply(p: Peg[A]): Peg[A] = from(p)
    def from(p: Peg[A]): Peg[A] = new FromPeg(p)

    class FromPeg(override val self: Peg[A]) extends PegProxy[A] {
        override def parse(v: Vector[A], first: Long, last: Long) = {
            stack.push(first)
            self.parse(v, first, last)
        }
    }

    def until(f: Vector.Func3[A, Any]): UntilPeg = new UntilPeg(f)

    class UntilPeg(f: Vector.Func3[A, Any]) extends Peg[A] {
        override def parse(v: Vector[A], first: Long, last: Long) = {
            f(v, stack.pop, first)
            first
        }
        override def length = 0
    }
}
