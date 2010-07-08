

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual


final case class Always0[v <: Any](private val v: v) extends Function0 {
    override  def self = this
    override type self = Always0[v]

    override  def apply = v
    override type apply = v
}