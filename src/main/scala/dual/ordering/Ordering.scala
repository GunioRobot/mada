

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package ordering


object Ordering extends Common


trait Ordering extends Equiv {
    type self <: Ordering
    type asInstanceOfOrdering = self

     def compare[x <: Any, y <: Any](x: x, y: y): compare[x, y]
    type compare[x <: Any, y <: Any] <: Result

     def lteq[x <: Any, y <: Any](x: x, y: y): lteq[x, y]
    type lteq[x <: Any, y <: Any] <: Boolean

     def gteq[x <: Any, y <: Any](x: x, y: y): gteq[x, y]
    type gteq[x <: Any, y <: Any] <: Boolean

     def lt[x <: Any, y <: Any](x: x, y: y): lt[x, y]
    type lt[x <: Any, y <: Any] <: Boolean

     def gt[x <: Any, y <: Any](x: x, y: y): gt[x, y]
    type gt[x <: Any, y <: Any] <: Boolean

     def equiv[x <: Any, y <: Any](x: x, y: y): equiv[x, y]
    type equiv[x <: Any, y <: Any] <: Boolean

     def `match`[x <: Any, y <: Any, flt <: Function0, fgt <: Function0, feq <: Function0](x: x, y: y, flt: flt, fgt: fgt, feq: feq): `match`[x, y, flt, fgt, feq]
    type `match`[x <: Any, y <: Any, flt <: Function0, fgt <: Function0, feq <: Function0] <: Any
}
