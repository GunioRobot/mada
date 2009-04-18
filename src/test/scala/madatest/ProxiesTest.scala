

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest


import junit.framework.Assert._


class ProxiesVarTest {
    import mada.Proxies._

    def assign(v: Var[Int]) {
        v := 12
    }

    def testTrivial {
        var x = 10
        val v = new Var(x)
        assign(v)
        x = v.self
        assertTrue(x == 12)
    }

    def testTrivial2 {
        var x = 10
        val v = new Var(x)
        assign(v)
        x = v.self
        assertTrue(x == 12)
    }

    def testTrivial3 {
        val v = new Var(12)
        assertEquals(12, v.self)
        assertTrue(!v.isNull)

        v := 13
        assertEquals(13, v.self)
        assertTrue(!v.isNull)
        v.resign
        assertTrue(v.isNull)
        assertTrue(!v)

        v assign 14
        assertEquals(14, v.self)
        assertTrue(!v.isNull)
        v := Null
        assertTrue(v.isNull)
    }


    def testExtract: Unit = {
        val s = "abc"
        val r = new Var(s)
        val Mutable(p) = r
        assertSame(p, s)

        r match {
            case Null() => fail("doh")
            case Mutable(p) => assertSame(s, p)
            case _ => fail("doh")
        }
        ()
    }

    def testExtractEmpty: Unit = {
        val r = new Var[String]
        r match {
            case Mutable(p) => fail("doh")
            case Null() => ()
            case _ => fail("doh")
        }
    }

    def testSwap: Unit = {
        val v = new Var[Int](3)
        val w = new Var[Int](5)
        v proxySwap w
        assertEquals(5, v.self)
        assertEquals(3, w.self)
    }
}


class ProxiesLazyVarTest {
    import mada.Proxies._

    def testTrivial {
        val v = new LazyVar[Int]
        assertTrue(null == v.self)
        assertTrue(v.isNull)
        v := 11
        v := 12
        assertTrue(v.self == 11)
        assertTrue(null != v.self)
        assertTrue(!v.isNull)

        v.resign
        assertTrue(null == v.self)
        assertTrue(v.isNull)
        assertTrue(!v)
        v := 6
        v := 7
        assertTrue(v.self == 6)
    }
}
