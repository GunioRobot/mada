

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec.para


import mada.Vector
import mada.Vector._
import junit.framework.Assert._
import madatest.vec.detail.Example._


class SortTest {
    def testTrivial {
        val actual = fromArray(example1).seal.parallel.sortWith(_ < _)
        detail.TestVectorReadOnly(example1Sorted, actual)
    }

    def testLong: Unit = {
        val actual = longSample1.clone.parallel.sortWith(_ < _)
        assertEquals(longSample1.clone.sortWith(_ < _), actual)
    }

    def testImplicit: Unit = {
        val actual = fromArray(example1).seal.parallel.sort
        detail.TestVectorReadOnly(example1Sorted, actual)
    }

    def testOptimizeArray {
        val actual = fromArray(example1).parallel.sortWith(_ < _)
        detail.TestVectorReadOnly(example1Sorted, actual)
    }

    def testOptimizeArrayWindow {
        val actual = fromArray(example1).window(0, 0).window(0, example1.length).parallel.sortWith(_ < _)
        detail.TestVectorReadOnly(example1Sorted, actual)
    }

    def testOptimizeArrayList {
        val actual = fromJclList(fromArray(example1).toJclArrayList).parallel.sortWith(_ < _)
        detail.TestVectorReadOnly(example1Sorted, actual)
    }
}

class SortParallelPerfTest extends NoBenchmark {
    override def run = {
        longSample1.clone.parallel.seal.sortWith(_ < _)
    }
}

class SortNonParallelPerfTest extends NoBenchmark {
    override def run = {
        longSample1.clone.seal.seal.sortWith(_ < _)
    }
}

class SortParallelPartitionTest extends NoBenchmark {
    override def run = {
        // mada.vec.parallel.SortWith.partition(longSample1.clone, (_: Int) < (_: Int), mada.vec.parallel.DefaultGrainSize(longSample1))
    }
}
