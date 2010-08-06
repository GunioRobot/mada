

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


import nat.peano


// Product

trait Product extends Any {
    type self <: Product
    type asInstanceOfProduct = self

     def arity: arity
    type arity <: Nat

     def productElement[n <: Nat](n: n): productElement[n]
    type productElement[n <: Nat] <: Any

    override def canEqual(that: scala.Any) = that.isInstanceOf[Product]
}


// Product1

trait Product1 extends Product {
    type self <: Product1
    type asInstanceOfProduct1 = self

     def _1: _1
    type _1 <: Any

    override def canEqual(that: scala.Any) = that.isInstanceOf[Product1]
}

private[dual]
trait AbstractProduct1 extends Product1 {
    final override  def arity: arity = peano._1
    final override type arity        = peano._1

    final override  def productElement[n <: Nat](n: n): productElement[n] =
        `if`(n  === peano._0,
            const0(_1),
            throw0(new IndexOutOfBoundsException(n.toString))
        ).apply

    final override type productElement[n <: Nat] =
        `if`[n# ===[peano._0],
            const0[_1],
            throw0[_]
        ]#apply
}


// Product2

object Product2 {
     def eqv[e1 <: Equiv, e2 <: Equiv](e1: e1, e2: e2): eqv[e1, e2] = new Eqv(e1, e2)
    type eqv[e1 <: Equiv, e2 <: Equiv] = Eqv[e1, e2]

    private[dual]
    final class Eqv[e1 <: Equiv, e2 <: Equiv](e1: e1, e2: e2) extends Equiv {
        type self = Eqv[e1, e2]
        override  def equiv[x <: Any, y <: Any](x: x, y: y): equiv[x, y] =
            (e1.equiv(x.asInstanceOfProduct2._1, y.asInstanceOfProduct2._1)  && e1.equiv(x.asInstanceOfProduct2._2, y.asInstanceOfProduct2._2)).asInstanceOf[equiv[x, y]]
        override type equiv[x <: Any, y <: Any] =
             e1#equiv[x#asInstanceOfProduct2#_1, y#asInstanceOfProduct2#_1]# &&[e1#equiv[x#asInstanceOfProduct2#_2, y#asInstanceOfProduct2#_2]]
    }
}

trait Product2 extends Product {
    type self <: Product2
    type asInstanceOfProduct2 = self

     def _1: _1
    type _1 <: Any

     def _2: _2
    type _2 <: Any

    override def canEqual(that: scala.Any) = that.isInstanceOf[Product2]
}

private[dual]
trait AbstractProduct2 extends Product2 {
    final override  def arity: arity = peano._2
    final override type arity        = peano._2

    final override  def productElement[n <: Nat](n: n): productElement[n] =
        `if`(n  === peano._0,
            const0(_1),
            `if`(n  === peano._1,
                const0(_2),
                throw0(new IndexOutOfBoundsException(n.toString))
            )
        ).apply.asInstanceOf[productElement[n]]

    final override type productElement[n <: Nat] =
        `if`[n# ===[peano._0],
            const0[_1],
            `if`[n# ===[peano._1],
                const0[_2],
                throw0[_]
            ]
        ]#apply
}
