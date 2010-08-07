

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package function


private[dual]
final class Identity extends Function1 {
    type self = Identity
    override  def apply[v1 <: Any](v1: v1): apply[v1] = v1
    override type apply[v1 <: Any]                    = v1
}