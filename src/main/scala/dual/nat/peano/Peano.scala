

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package peano


// See: Nats.scala
//      at http://www.assembla.com/wiki/show/metascala


object Peano extends Common


sealed trait Peano extends AbstractNat {
    type self <: Peano

    final override  def asNatPeano = self
    final override type asNatPeano = self

    override type increment <: Peano
    override type decrement <: Peano
    override type plus[that <: Nat] <: Peano
    override type minus[that <: Nat] <: Peano
    override type times[that <: Nat] <: Peano
    override type exp[that <: Nat] <: Peano
    override type bitAnd[that <: Nat] <: Peano
    override type bitOr[that <: Nat] <: Peano

     def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f]
    type foldRight[z <: Any, f <: Function2] <: Any

     def isEven: isEven
    type isEven <: Boolean

     def isOdd: isOdd
    type isOdd <: Boolean
}


private[dual]
sealed abstract class AbstractPeano extends Peano {
    final override  def increment: increment = Succ(self)
    final override type increment            = Succ[self]

    final override  def plus[that <: Nat](that: that): plus[that] = Plus.apply(self, that.asNatPeano)
    final override type plus[that <: Nat]                         = Plus.apply[self, that#asNatPeano]

    final override  def minus[that <: Nat](that: that): minus[that] = Minus.apply(self, that.asNatPeano)
    final override type minus[that <: Nat]                          = Minus.apply[self, that#asNatPeano]

    final override  def times[that <: Nat](that: that): times[that] = Times.apply(self, that.asNatPeano)
    final override type times[that <: Nat]                          = Times.apply[self, that#asNatPeano]

    final override  def divMod[that <: Nat](that: that): divMod[that] = DivMod.apply(self, that.asNatPeano).asInstanceOf[divMod[that]]
    final override type divMod[that <: Nat]                           = DivMod.apply[self, that#asNatPeano]

    final override  def exp[that <: Nat](that: that): exp[that] = asNatDense.exp(that).asNatPeano
    final override type exp[that <: Nat]                        = asNatDense#exp[that]#asNatPeano

    final override  def lt[that <: Nat](that: that): lt[that] = that.asNatPeano.lteq(self).not
    final override type lt[that <: Nat]                       = that#asNatPeano#lteq[self]#not

    final override  def bitAnd[that <: Nat](that: that): bitAnd[that] = asNatDense.bitAnd(that).asNatPeano
    final override type bitAnd[that <: Nat]                           = asNatDense#bitAnd[that]#asNatPeano

    final override  def bitOr[that <: Nat](that: that): bitOr[that] = asNatDense.bitOr(that).asNatPeano
    final override type bitOr[that <: Nat]                          = asNatDense#bitOr[that]#asNatPeano

    final override  def isOdd: isOdd = isEven.not
    final override type isOdd        = isEven#not

    final override  def asNatDense: asNatDense = ToDense.apply(self)
    final override type asNatDense             = ToDense.apply[self]
}


sealed trait Zero extends AbstractPeano {
    type self = Zero

    override  def isZero: isZero = `true`
    override type isZero         = `true`

    override  def decrement: decrement = unsupported("nat.peano.Zero.decrement")
    override type decrement            = unsupported[_]

    override  def equal[that <: Nat](that: that): equal[that] = that.isZero
    override type equal[that <: Nat]                          = that#isZero

    override  def lteq[that <: Nat](that: that): lteq[that] = `true`
    override type lteq[that <: Nat]                         = `true`

    override  def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f] = z
    override type foldRight[z <: Any, f <: Function2]                              = z

    override  def isEven: isEven = `true`
    override type isEven         = `true`

    override def undual: undual = 0
}


final case class Succ[n <: Peano](override val decrement: n) extends AbstractPeano {
    type self = Succ[n]

    override  def isZero: isZero = `false`
    override type isZero         = `false`

    override type decrement = n

    override  def equal[that <: Nat](that: that): equal[that] = SuccEq.apply(self, that.asNatPeano)
    override type equal[that <: Nat]                          = SuccEq.apply[self, that#asNatPeano]

    override  def lteq[that <: Nat](that: that): lteq[that] = SuccLtEq.apply(self, that.asNatPeano)
    override type lteq[that <: Nat]                         = SuccLtEq.apply[self, that#asNatPeano]

    override  def foldRight[z <: Any, f <: Function2](z: z, f: f): foldRight[z, f] = f.apply(self, decrement.foldRight(z, f))
    override type foldRight[z <: Any, f <: Function2]                              = f#apply[self, decrement#foldRight[z, f]]

    override lazy val isEven: isEven = decrement.isEven.not
    override type isEven             = decrement#isEven#not

    override def undual: undual = 1 + decrement.undual
}


private[dual]
object _Peano {
    val Zero = new Zero{}
}
