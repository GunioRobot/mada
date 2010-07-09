

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


package object boolean extends boolean.OperatorCommon {

    @equivalentTo("new `true`{}")
    val `true` = _Boolean.`true`

    @equivalentTo("new `false`{}")
    val `false` = _Boolean.`false`

    /**
     * The if-expression to return Any.
     */
     def `if`[b <: Boolean, then <: Function0, _else <: Function0](b: b, then: then, _else: _else): `if`[b, then, _else] = b.`if`(then, _else)
    type `if`[b <: Boolean, then <: Function0, _else <: Function0] = b#`if`[then, _else]

}
