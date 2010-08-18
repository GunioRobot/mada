

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


import annotation.elidable
import annotation.elidable.ALL


package object free {

    /**
     * assertion
     */
    @elidable(ALL)
    def assert(a: `true`): scala.Unit = ()

    /**
     * type assertion
     */
    // @elidable(ALL) crashes compiler.
    def assert[a >: `true` <: `true`]: scala.Unit = () // `case class` doesn't work well.

    /**
     * negative assertion
     */
    @elidable(ALL)
    def assertNot(a: `false`): scala.Unit = ()

    /**
     * negative type assertion
     */
    def assertNot[a >: `false` <: `false`]: scala.Unit = ()

    /**
     * type assertion of identity equality
     */
    def assertSame[a >: b <: b, b]: scala.Unit = ()

    /**
     * type assertion if <code>a</code> is lower than <code>b</code>.
     */
    def assertConforms[a <: b, b]: scala.Unit = ()

}