
package madatest.rng


import detail.Example._
import mada.rng.From._
import mada.rng.ToRng._
import mada.rng.ToList._
import junit.framework.Assert._


class ListTest {
    def testFrom {
        detail.TestForwardReadOnly(example1, from(example1.elements.toList).eval)
    }

    def testTo {
        assertEquals(example1.elements.toList.toString, from(example1).rng_toList.eval.toString)
    }

    def testFusion {
        val lst = example1.elements.toList
        assertSame(lst, from(lst).rng_toList.eval)
    }

    def testEmpty {
        detail.TestEmpty(from(empty1.elements.toList).eval)
    }
}