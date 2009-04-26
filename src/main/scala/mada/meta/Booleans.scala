

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Contains meta Boolean functionalities.
 */
trait Booleans { this: Meta.type =>

    sealed trait Boolean extends Object {
        type and[that <: Boolean] <: Boolean
        type or[that <: Boolean] <: Boolean
        type not <: Boolean

        private[mada] type _if[R, then <: R, _else <: R] <: R
    }

    sealed trait `true` extends Boolean {
        override type and[that <: Boolean] = that
        override type or[that <: Boolean] = `true`
        override type not = `false`

        private[mada] override type _if[R, then <: R, _else <: R] = then
    }

    sealed trait `false` extends Boolean {
        override type and[that <: Boolean] = `false`
        override type or[that <: Boolean] = that
        override type not = `true`

        private[mada] override type _if[R, then <: R, _else <: R] = _else
    }

    // TODO: Move to Operators.
    type &&[a <: Boolean, b <: Boolean] = a#and[b]
    type ||[a <: Boolean, b <: Boolean] = a#or[b]
    type ![a <: Boolean] = a#not

    type `if`[R, cond <: Boolean, then <: R, _else <: R] = cond#_if[R, then, _else]

}
