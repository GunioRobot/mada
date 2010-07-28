

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package ordering


private[mada] final case class Match[o <: Ordering, x <: Any, y <: Any, flt <: Function0, fgt <: Function0, feq <: Function0](o: o, x: x, y: y, flt: flt, fgt: fgt, feq: feq) extends Function0 {
    type self = Match[o, x, y, flt, fgt, feq]

    private lazy val c: c = o.compare(x, y)
    private type c = o#compare[x, y]

    override  def apply: apply = `if`(c === LT, flt, `if`(c === GT, fgt, feq)).apply.asInstanceOf[apply]
    override type apply = `if`[c# ===[LT], flt, `if`[c# ===[GT], fgt, feq]]#apply
}