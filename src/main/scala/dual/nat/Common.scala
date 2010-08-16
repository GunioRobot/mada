

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat


private[dual]
class Common {
    @aliasOf("dense.Dense")
     val Dense = dense.Dense
    type Dense = dense.Dense

    @aliasOf("peano.Peano")
     val Peano = peano.Peano
    type Peano = peano.Peano

    /**
     * The natural ordering of Nat
     */
     val naturalOrdering: naturalOrdering = new NaturalOrdering
    type naturalOrdering                  =     NaturalOrdering
}
