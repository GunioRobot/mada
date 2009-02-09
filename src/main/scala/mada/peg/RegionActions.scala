

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


// `startAt >> SymbolMap("abc" --> endWith(f) >> "def")`

/**
 * Associates actions where Peg can't be placed.
 */
class RegionActions[A] {
    private val stack = new java.util.ArrayDeque[Int]

    /**
     * Marks starting point of actions.
     */
    val startAt: Peg[A] = new StartAtPeg

    private class StartAtPeg extends Peg[A] {
        override def parse(v: Vector[A], start: Int, end: Int) = {
            stack.push(start)
            start
        }
        override def width = 0
    }

    /**
     * Triggers the action.
     */
    def endWith(f: Peg.Action[A]): Peg[A] = new EndWithPeg(f)

    private class EndWithPeg(f: Peg.Action[A]) extends Peg[A] {
        override def parse(v: Vector[A], start: Int, end: Int) = {
            f(v(stack.pop, start))
            start
        }
        override def width = 0
    }
}
