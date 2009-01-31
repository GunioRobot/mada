

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada.Vector._
import mada.Vector
import mada.Vector.Compatibles._
import junit.framework.Assert._
import madatest.vec.detail.Example._


class FolderTest {
    def testTrivial: Unit = {
        val v = madaVector(  Array(1,2,3,4,5,6,7,8))
        val w = madaVector(Array(5,6,8,11,15,20,26,33,41))
        assertEquals(w, v.folder(5)(_ + _))
    }

    def testEmpty: Unit = {
        assertEquals(Vector.single(0), madaVector(empty1).folder(0)(_ + _))
    }
}