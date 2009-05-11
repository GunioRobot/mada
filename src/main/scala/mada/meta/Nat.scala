

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


// See: Nats.scala
//      at http://www.assembla.com/wiki/show/metascala


import nat._


trait Nat extends Operatable {
    type `this` <: Nat
    final type equals[that <: Nat] = identity#equals[that#identity]
    final override type Operand_== = Nat
    final override type operator_==[that <: Nat] = equals[that]

    type increment <: Nat
    type decrement <: Nat

    type add[that <: Nat] <: Nat
    final override type Operand_+ = Nat
    final override type operator_+[that <: Nat] = add[that]

    final type subtract[that <: Nat] = that#accept[subtractVisitor[`this`]]
    final override type Operand_- = Nat
    final override type operator_-[that <: Nat] = subtract[that]

    type multiply[that <: Nat] <: Nat

    type identity <: Identity
    type accept[v <: Visitor] <: v#Result // More generic algorithms (fold etc) won't work.
}


// "case classes"

sealed trait Zero extends Nat {
    override type `this` = Zero
    override type increment = Succ[Zero]
    override type decrement = error
    override type add[that <: Nat] = that
    override type multiply[that <: Nat] = Zero
    override type identity = nat._0I
    override type accept[v <: Visitor] = v#visitZero
}


sealed trait Succ[n <: Nat] extends Nat {
    override type `this` = Succ[n]
    override type increment = Succ[`this`]
    override type decrement = n
    override type add[that <: Nat] = Succ[n#add[that]]
    override type multiply[that <: Nat] = n#multiply[that]#add[that]
    override type identity = n#identity#increment
    override type accept[v <: Visitor] = v#visitSucc[n]
}