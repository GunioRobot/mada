

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest


import mada.sequence
import junit.framework.Assert._


class SortedTest {

// merge

    def testMerge: Unit = {
        new NotStartable[Int]() merge new NotStartable[Int]()
        val A1 = sequence.of(1,6,7,10,14,17)
        val A2 = sequence.of(2,5,8,11,13,18)
        val A3 = sequence.of(3,4,9,12,15,16)
        val AA = sequence.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18)
        val B1 = A1 merge A2 merge A3
        assertEquals(AA, B1)
        assertEquals(AA, B1) // run again.
    }

    def testMergeEmpty: Unit = {
        val A1 = sequence.of(1,6,7,10,14,17)
        val A2 = sequence.of(2,5,8,11,13,18)
        val A3 = sequence.of(3,4,9,12,15,16)
        val AA = sequence.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18)
        val B1 = A1 merge A2 merge sequence.empty merge A3 merge sequence.empty
        assertEquals(AA, B1)
        assertEquals(AA, B1) // run again.
        val B2 = sequence.emptyOf[Int] merge A1 merge A2 merge A3 merge sequence.empty
//        println(sequence.toString(B2))
        assertEquals(B2, AA)
        assertEquals(B2, AA) // run again.
    }

    def testMergeEmpty0: Unit = {
        val B1 = sequence.emptyOf[Int] merge sequence.empty merge sequence.empty
        assertTrue( B1.isEmpty )
        assertTrue( B1.isEmpty ) // run again.
    }


// union

    def ltNoCase(ch1: Char, ch2: Char): Boolean = {
        import java.lang.Character.toLowerCase
        toLowerCase(ch1) < toLowerCase(ch2)
    }

    def testUnion: Unit = {
        new NotStartable[Int]() union new NotStartable[Int]()
        val A1 = sequence.of(1,3,5,7,9,11)
        val A2 = sequence.of(1,1,2,3,5,8,13)
        val AA = sequence.of(1,1,2,3,5,7,8,9,11,13)
        val B1 = A1 union A2
        assertEquals(AA, B1)
        assertEquals(AA, B1) // run again.
    }

    def testUnionEmpty: Unit = {
        val A1 = sequence.of(1,3,5,7,9,11)
        val A2 = sequence.of(1,1,2,3,5,8,13)
        val AA = sequence.of(1,1,2,3,5,7,8,9,11,13)
        val B1 = sequence.emptyOf[Int] union A1 union A2 union sequence.empty
        assertEquals(AA, B1)
        assertEquals(AA, B1) // run again.
    }

    def testUnionEmpty0: Unit = {
        val B1 = sequence.emptyOf[Int] union sequence.empty union sequence.empty
        assertTrue( B1.isEmpty )
        assertTrue( B1.isEmpty ) // run again.
    }

    def testUnionStable: Unit = {
        val A1 = "abBBfH"
        val A2 = "ABbCDFFhh"
        val AA = sequence.from("abBBCDfFHh")
        val B1 = sequence.from(A1).unionBy(A2)(ltNoCase)
        assertEquals(AA, B1)
        assertEquals(AA, B1) // run again.
    }


// intersection

    def testIntersection: Unit = {
        new NotStartable[Int]() intersection new NotStartable[Int]()
        val A1 = sequence.of(1,3,5,7,9,11)
        val A2 = sequence.of(1,1,2,3,5,8,13)
        val AA = sequence.of(1,3,5)
        val B1 = A1 intersection A2
        assertEquals(AA, B1)
        assertEquals(AA, B1) // run again.
    }

    def testIntersectionEmpty: Unit = {
        val A1 = sequence.of(1,3,5,7,9,11)
        val A2 = sequence.of(1,1,2,3,5,8,13)
        val AA = sequence.of(1,3,5)
        val B1 = sequence.emptyOf[Int] intersection A1 intersection A2 intersection sequence.empty
        assertTrue( B1.isEmpty )
        assertTrue( B1.isEmpty ) // run again.
    }

    def testIntersectionEmpty0: Unit = {
        val B1 = sequence.emptyOf[Int] intersection sequence.empty intersection sequence.empty
        assertTrue( B1.isEmpty )
        assertTrue( B1.isEmpty ) // run again.
    }

    def testIntersectionStable: Unit = {
        val A1 = "abbBBfhH"
        val A2 = "ABBCDFFH"
        val AA = sequence.from("abbfh")
        val B1 = sequence.from(A1).intersectionBy(A2)(ltNoCase)
        assertEquals(AA, B1)
        assertEquals(AA, B1) // run again.
    }


// difference

    def testDifference: Unit = {
        new NotStartable[Int]() difference new NotStartable[Int]()
        val A1 = sequence.of(1,3,5,7,9,11)
        val A2 = sequence.of(1,1,2,3,5,8,13)
        val AA = sequence.of(7,9,11)
        val B1 = A1 difference A2
        assertEquals(AA, B1)
        assertEquals(AA, B1) // run again.
    }

    def testDifferenceEmpty: Unit = {
        val A1 = sequence.of(1,3,5,7,9,11)
        val A2 = sequence.of(1,1,2,3,5,8,13)
        val AA = sequence.of(7,9,11)
        val B1 = A1 difference sequence.empty difference A2 difference sequence.empty
        assertEquals(AA, B1)
        assertEquals(AA, B1) // run again.
    }

    def testDifferenceEmpty0: Unit = {
        val B1 = sequence.emptyOf[Int] difference sequence.empty difference sequence.empty
        assertTrue( B1.isEmpty )
        assertTrue( B1.isEmpty ) // run again.
    }

    def testDifferenceStable: Unit = {
        val A1 = "abbBBfghH"
        val A2 = "ABBCDFFH"
        val AA = sequence.from("BBgH")
        val B1 = sequence.from(A1).differenceBy(A2)(ltNoCase)
        assertEquals(AA, B1)
        assertEquals(AA, B1) // run again.
    }


// symmetricDifference

    def testSymmetricDifference: Unit = {
        new NotStartable[Int]() symmetricDifference new NotStartable[Int]()
        val A1 = sequence.of(1,3,5,7,9,11)
        val A2 = sequence.of(1,1,2,3,5,8,13)
        val AA = sequence.of(1,2,7,8,9,11,13)
        val B1 = A1 symmetricDifference A2
        assertEquals(AA, B1)
        assertEquals(AA, B1) // run again.
        val B2 = A1 symmetricDifference A1
        assertTrue( B2.isEmpty )
        assertTrue( B2.isEmpty ) // run again.
    }

    def testSymmetricDifferenceEmpty: Unit = {
        val A1 = sequence.of(1,3,5,7,9,11)
        val A2 = sequence.of(1,1,2,3,5,8,13)
        val AA = sequence.of(1,2,7,8,9,11,13)
        val B1 = A1 symmetricDifference sequence.empty symmetricDifference A2 symmetricDifference sequence.empty
        assertEquals(AA, B1)
        assertEquals(AA, B1) // run again.
    }

    def testSymmetricDifferenceEmpty0: Unit = {
        val B1 = sequence.emptyOf[Int] symmetricDifference sequence.empty symmetricDifference sequence.empty
        assertTrue( B1.isEmpty )
        assertTrue( B1.isEmpty ) // run again.
    }

    def testSymmetricDifferenceStable: Unit = {
        val A1 = "abbBBfghH"
        val A2 = "ABBCDFFH"
        val AA = sequence.from("BBCDFgH")
        val B1 = sequence.from(A1).symmetricDifferenceBy(A2)(ltNoCase)
        assertEquals(AA, B1)
        assertEquals(AA, B1) // run again.
    }
}