

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Contains meta Boolean functionalities.
 */
trait Booleans { this: Meta.type =>

    trait Boolean extends Object {
        override type This = Boolean

        type and[that <: Boolean] <: Boolean
        type or[that <: Boolean] <: Boolean
        type not <: Boolean
        type equals[that <: Boolean] <: Boolean
        override type operator_==[that <: This] = equals[that]

        private[mada] type isTrue <: Boolean
        private[mada] type isFalse <: Boolean

        private[mada] type _if[then <: Object, _else <: then#This] <: then#This // doesn't work in dependent context.
        private[mada] type natIf[then <: Nat, _else <: Nat] <: Nat
    }

    sealed trait `true` extends Boolean {
        override type `this` = `true`

        override type and[that <: Boolean] = that
        override type or[that <: Boolean] = `true`
        override type not = `false`
        override type equals[that <: Boolean] = that#isTrue

        private[mada] override type isTrue = `true`
        private[mada] override type isFalse = `false`

        private[mada] override type _if[then <: Object, _else <: then#This] = then#`this`
        private[mada] override type natIf[then <: Nat, _else <: Nat] = then
    }

    sealed trait `false` extends Boolean {
        override type `this` = `false`

        override type and[that <: Boolean] = `false`
        override type or[that <: Boolean] = that
        override type not = `true`
        override type equals[that <: Boolean] = that#isFalse

        private[mada] override type isTrue = `false`
        private[mada] override type isFalse = `true`

        private[mada] override type _if[then <: Object, _else <: then#This] = _else
        private[mada] override type natIf[then <: Nat, _else <: Nat] = _else
    }

    type `if`[cond <: Boolean, then <: Object, _else <: then#This] = cond#_if[then, _else]
    type natIf[cond <: Boolean, then <: Nat, _else <: Nat] = cond#natIf[then, _else]



    // TODO: Move to Operators.
    type &&[a <: Boolean, b <: Boolean] = a#and[b]
    type ||[a <: Boolean, b <: Boolean] = a#or[b]
    type ![a <: Boolean] = a#not

}
