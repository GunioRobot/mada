

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual


class Always0[v <: Any](v: v) extends Function0 {
    override  def apply = v
    override type apply = v
}
