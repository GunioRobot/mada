
package madatest.rng.detail


import mada.rng.From._
import mada.rng.Rng
import mada.rng._
import junit.framework.Assert._


object TestForwardReadWrite {
    def apply[A <% Ordered[A]](expected: Array[A], actual: Rng[A]): Unit = {
        assertEquals(ForwardTraversal, actual.traversal)
        impl(expected, actual)
    }

    def impl[A <% Ordered[A]](expected: Array[A], actual: Rng[A]): Unit = {
        TestForwardReadOnly.impl(expected, actual)

        SelectionSort(actual)
        val ex = CopyArray(expected); SelectionSort(from(ex).eval)
        assertEquals(from(ex).eval, actual)
    }
}


object TestForwardReadOnly {
    def apply[A](expected: Array[A], actual: Rng[A]): Unit = {
        assertEquals(ForwardTraversal, actual.traversal)
        impl(expected, actual)
    }

    def impl[A](expected: Array[A], actual: Rng[A]) {
        AssertModels(actual, ForwardTraversal)
        assertTrue("testing rng is too small", expected.length >= 2)

        TestSinglePassReadOnly.impl(expected, actual)

        TestForwardReadablePointer((actual.begin, expected(0)), (actual.begin.pre_++, expected(1)))

        val ex = from(expected).eval
        val w: Long = expected.length / 3;
        assertEquals(
            actual.begin <=< Search(actual, ex.begin.advance(w) <=< ex.begin.advance(2 * w)),
            ex.begin <=< Search(ex, ex.begin.advance(w) <=< ex.begin.advance(2 * w))
        )
    }
}
