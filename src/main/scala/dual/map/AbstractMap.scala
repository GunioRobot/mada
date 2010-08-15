

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package map


trait AbstractMap extends Map {
    final override  def putList[xs <: List](xs: xs): putList[xs] = PutList.apply(self, xs)
    final override type putList[xs <: List]                      = PutList.apply[self, xs]

    final override  def contains[k <: Any](k: k): contains[k] = get(k).isEmpty.not
    final override type contains[k <: Any]                    = get[k]#isEmpty#not

    final override  def equal[that <: Map](that: that): equal[that] = Equal.apply(self, that, None)
    final override type equal[that <: Map]                          = Equal.apply[self, that, None]

    final override  def equalWith[that <: Map, ve <: Equiv](that: that, ve: ve): equalWith[that, ve] = Equal.apply(self, that, Some(ve))
    final override type equalWith[that <: Map, ve <: Equiv]                                          = Equal.apply[self, that, Some[ve]]

    final override  def union[that <: Map](that: that): union[that] = that.putList(asList)
    final override type union[that <: Map]                          = that#putList[asList]
}
