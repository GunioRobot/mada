

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package meta


// See: Nats.scala
//      at http://www.assembla.com/wiki/show/metascala


import nat._


sealed trait Nat extends Operatable {
    type self <: Nat

    private[mada] type isZero <: Boolean
    type equals[that <: Nat] <: Boolean
    final override type Operand_== = Nat
    final override type operator_==[that <: Nat] = equals[that]

    type increment <: Nat
    type decrement <: Nat

    type add[that <: Nat] <: Nat
    final override type Operand_+ = Nat
    final override type operator_+[that <: Nat] = add[that]

    private[mada] type negateAdd[that <: Nat] <: Nat
    final type subtract[that <: Nat] = that#negateAdd[self]
    final override type Operand_- = Nat
    final override type operator_-[that <: Nat] = subtract[that]

    type multiply[that <: Nat] <: Nat

    type accept_Any[v <: Visitor[Any]] <: Any
    type accept_Nat[v <: Visitor[Nat]] <: Nat
    type accept_blendList[v <: Visitor[blend.List]] <: blend.List
}


// "case classes"

sealed trait Zero extends Nat {
    override type self = Zero
    override private[mada] type isZero = `true`
    override type equals[that <: Nat] = that#isZero
    override type increment = Succ[Zero]
    override type decrement = sentinel
    override type add[that <: Nat] = that
    override private[mada] type negateAdd[that <: Nat] = that
    override type multiply[that <: Nat] = Zero

    override type accept_Any[v <: Visitor[Any]] = v#visitZero
    override type accept_Nat[v <: Visitor[Nat]] = v#visitZero
    override type accept_blendList[v <: Visitor[blend.List]] = v#visitZero
}

sealed trait Succ[n <: Nat] extends Nat {
    override type self = Succ[n]
    override private[mada] type isZero = `false`
    override type equals[that <: Nat] = n#equals[that#decrement]
    override type increment = Succ[self]
    override type decrement = n
    override type add[that <: Nat] = Succ[n#add[that]]
    override private[mada] type negateAdd[that <: Nat] = n#negateAdd[that#decrement]
    override type multiply[that <: Nat] = n#multiply[that]#add[that]

    override type accept_Any[v <: Visitor[Any]] = v#visitSucc[n]
    override type accept_Nat[v <: Visitor[Nat]] = v#visitSucc[n]
    override type accept_blendList[v <: Visitor[blend.List]] = v#visitSucc[n]
}


// sentinel value

sealed trait sentinel extends Nat {
    override type self = sentinel
    override private[mada] type isZero = `false`
    override type equals[that <: Nat] = `false`
    override type increment = `null`
    override type decrement = self
    override type add[that <: Nat] = `null`
    override private[mada] type negateAdd[that <: Nat] = `null`
    override type multiply[that <: Nat] = `null`

    override type accept_Any[v <: Visitor[Any]] = `null`
    override type accept_Nat[v <: Visitor[Nat]] = `null`
    override type accept_blendList[v <: Visitor[blend.List]] = `null`
}