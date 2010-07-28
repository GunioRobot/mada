

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package function


final class Const0[v <: Any](v: => v) extends Function0 {
    type self = Const0[v]
    override lazy val apply: apply = v
    override type apply = v
}

object Const0 {
    def apply[v <: Any](v: => v): Const0[v] = new Const0(v)
}