

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package arm


import scala.util.continuations


private[arm]
class Common {

    @returnThat
    def from[A](that: Arm[A]): Arm[A] = that

    @equivalentTo("a.foreach(f)")
    def using[A](a: Arm[A])(f: A => Unit): Unit = a.foreach(f)

    @equivalentTo("sequence.reactive.BlockContext.each(a)")
    def use[A](a: Arm[A]): A @continuations.cpsParam[Any, Any] = sequence.reactive.BlockContext.each(a)

    @equivalentTo("continuations.reset(ctx)")
    def scope[A](ctx: => A @continuations.cpsParam[A, Any]): Unit = continuations.reset(ctx)

}
