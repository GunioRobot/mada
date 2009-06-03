

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.vectortest.detail


import mada.sequence._
import junit.framework.Assert._


object TestEmpty {
    def apply[A](v: Vector[A]) {
        assertEquals(0, v.size)
    }
}