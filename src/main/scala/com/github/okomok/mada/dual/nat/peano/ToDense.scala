

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package peano


private[mada] final case class ToDense[x <: Peano](x: x) {
     def apply: apply = `if`(x.isZero, always0(dense.Nil), Else()).apply.asInstanceOfNatDense
    type apply = `if`[x#isZero, always0[dense.Nil], Else]#apply#asInstanceOfNatDense

    final case class Else() extends Function0 {
         override  def self = this
         override type self = Else
         override  def apply: apply = dense.Cons(x.isOdd, Div2(x).apply.toDense)
         override type apply = dense.Cons[x.isOdd, Div2[x]#apply#toDense]
     }
}


private[mada] final case class Div2[x <: Peano](x: x)  {
      def apply: apply = `if`(x < _2, always0(Zero), Else()).apply.asInstanceOfNatPeano
     type apply = `if`[x# <[_2], always0[Zero], Else]#apply#asInstanceOfNatPeano

     final case class Else() extends Function0 {
         override  def self = this
         override type self = Else
         override  def apply: apply = Div2(x.decrement.decrement).apply.increment.asInstanceOf[apply]
         override type apply = Div2[x#decrement#decrement]#apply#increment
     }
}
