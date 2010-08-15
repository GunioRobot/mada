

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package peg


/**
 * Helps to build a recursive grammar.
 */
trait Rule extends AbstractPeg {
    protected  def rule: rule
    protected type rule <: Peg

    private lazy val p: p = rule.asPeg
    private type p        = rule#asPeg

    final override  def parse[xs <: List](xs: xs): parse[xs] = p.parse(xs)
    final override type parse[xs <: List]                    = p#parse[xs]

    final override  def width: width = p.width
    final override type width        = p#width
}
