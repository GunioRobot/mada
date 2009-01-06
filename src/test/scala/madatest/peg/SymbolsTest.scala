

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


// See: http://www.javaworld.com/javaworld/jw-02-2001/jw-0216-ternary.html


import junit.framework.Assert._
import mada.Vector.compatibles._
import mada.Peg.compatibles._
import mada.Peg._


class SymbolsTest {
    def testTrivial: Unit = {
        val i = ("abc" ~ symbols("to", "too", "tot", "tab", "so")).parse("abcto")
        assertEquals(5L, i)
    }

    def testLongestMatch: Unit = {
        assertTrue(("abc" ~ symbols("to", "too", "tot", "tab", "so")) matches "abctoo")
    }

    def testTSTree: Unit = {
        val tree = new mada.peg.TSTree[Char, String](mada.vec.stl.Less[Char])

        assertFalse(tree.containsKey("to"))
        assertFalse(tree.containsKey(""))

        tree.put("to", "to")
        //println(tree.toString)
        tree.put("too", "too")
        //println(tree.toString)
        tree.put("tot", "tot")
        //println(tree.toString)
        tree.put("tab", "tab")
        //println(tree.toString)
        tree.put("so", "so")
        //println(tree.toString)

        assertEquals("so", tree.get("so").get)
        assertEquals("tab", tree.get("tab").get)
        assertEquals("to", tree.get("to").get)
        assertEquals("too", tree.get("too").get)
        assertEquals("tot", tree.get("tot").get)
        assertFalse(tree.containsKey(""))

        assertEquals(None, tree.parse("ztot", 0, 4))
        assertEquals(None, tree.parse("t", 0, 1))
        assertEquals(None, tree.parse("tzzzzz", 0, 6))
        assertEquals(None, tree.parse("", 0, 0))
        assertEquals(3L, tree.parse("tot", 0, 3).get._2)
        assertEquals(3L, tree.parse("totzzzzz", 0, 8).get._2)
        assertEquals(2L, tree.parse("toazzzzz", 0, 8).get._2)

        // empty-string key
        tree.put("", "EMPTY")
        assertTrue(tree.containsKey(""))
        assertEquals(0L, tree.parse("ztot", 0, 4).get._2) // 0-length match
        assertEquals(2L, tree.parse("zzzzztot", 2, 8).get._2) // 0-length match
    }

    def testBound: Unit = {
        val tree = new mada.peg.TSTree[Char, String](mada.vec.stl.Less[Char])

        tree.put("t", "t")
        assertFalse(tree.containsKey(""))
        assertTrue(tree.containsKey("t"))
        assertFalse(tree.containsKey(""))
    }

    def testEmptyStringKey: Unit = {
        val tree = new mada.peg.TSTree[Char, String](mada.vec.stl.Less[Char])

        tree.put("", "EMPTY")
        assertTrue(tree.containsKey(""))
        assertEquals("EMPTY", tree.get("").get)
    }
}
