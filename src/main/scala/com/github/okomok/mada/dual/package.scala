

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada


import annotation.elidable
import annotation.elidable.ASSERTION


package object dual {


// util

    /**
     * The dual throw
     */
     def `throw`[x <: scala.Throwable](x: x): `throw`[x] = throw x
    type `throw`[x <: scala.Throwable] = Nothing

    /**
     * The dual constant function
     */
     def always0[x <: Any](x: x): Always0[x] = Always0(x)
    type always0[x <: Any] = Always0[x]


// assertions

    /**
     * assertion
     */
    @elidable(ASSERTION)
     def assert[c <: Boolean](c: c): assert[c] = { if (!c.undual) throw new java.lang.AssertionError("dual.assert"); Unit }
    type assert[c <: Boolean] = Unit

    /**
     * negative assertion
     */
    @elidable(ASSERTION)
     def assertNot[b <: Boolean](b: b): assert[b] = { if (b.undual) throw new java.lang.AssertionError("dual.assertNot"); Unit }
    type assertNot[b <: Boolean] = Unit


// Boolean

    @aliasOf("boolean.Boolean")
    type Boolean = boolean.Boolean

    @aliasOf("boolean.`true`")
     val `true` = boolean.`true`
    type `true` = boolean.`true`

    @aliasOf("boolean.`false`")
     val `false` = boolean.`false`
    type `false` = boolean.`false`

    @aliasOf("boolean.`if`")
     def `if`[b <: Boolean, then <: Function0, _else <: Function0](b: b, then: then, _else: _else): `if`[b, then, _else] = boolean.`if`(b, then, _else)
    type `if`[b <: Boolean, then <: Function0, _else <: Function0] = boolean.`if`[b, then, _else]


// List

    @aliasOf("list.List")
    type List = list.List

    @equivalentTo("new Nil{}")
     val Nil = list.Nil
    type Nil = list.Nil

    @aliasOf("Cons")
    val :: = list.Cons

    @aliasOf("list.::")
    type ::[x <: Any, xs <: List] = list.::[x, xs]

    @aliasOf("list.:::")
    type :::[xs <: List, ys <: List] = list.:::[xs, ys]

    @aliasOf("list.reverse_:::")
    type reverse_:::[xs <: List, ys <: List] = list.reverse_:::[xs, ys]


// Option

    @aliasOf("option.Option")
    type Option = option.Option

    @aliasOf("option.None")
     val None = option.None
    type None = option.None

    @aliasOf("option.Some")
     val Some = option.Some
    type Some[x <: Any] = option.Some[x]


// Unit

    @equivalentTo("new Unit{}")
    val Unit: Unit = _Unit.value

}
