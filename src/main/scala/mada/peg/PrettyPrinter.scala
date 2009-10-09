

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package peg


/**
 * Prints matched input information.
 */
trait PrettyPrinter {

    /**
     * Closes output resource.
     */
    def close: Unit

    /**
     * Outputs matched inputs.
     */
    def print[A](p: Peg[A]): Peg[A]

    @equivalentTo("print(p.named(name))")
    final def print[A](name: String)(p: Peg[A]): Peg[A] = print(p.named(name))

    @aliasOf("print")
    final def apply[A](p: Peg[A]): Peg[A] = print(p)

    @aliasOf("print")
    final def apply[A](name: String)(p: Peg[A]): Peg[A] = print(name)(p)

}
