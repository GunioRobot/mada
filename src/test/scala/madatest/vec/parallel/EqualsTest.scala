

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec.parallel


import mada.Vectors._
import mada.Vectors.Compatibles._

import junit.framework.Assert._
import madatest.vec.detail.Example._
import madatest.vec.detail._


class EqualsTest {
    def testTrivial: Unit = {
        assertTrue(example1.parallel(1000) == mada.Vectors.from(example1))
        assertTrue(example1.parallel(6) equalsTo example1)
        assertTrue(example1.parallel == mada.Vectors.from(example1))
        assertFalse(example1.parallel == mada.Vectors.from(example2))
    }
}
