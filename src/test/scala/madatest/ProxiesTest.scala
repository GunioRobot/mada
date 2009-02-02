

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest


import junit.framework.Assert._


class ProxiesRefTest {
    import mada.Proxies._

    def assign(v: Ref[Int]) {
        v := 12
    }

    def testTrivial {
        var x = 10
        val v = new Ref(x)
        assign(v)
        x = v.self
        assertTrue(x == 12)
    }

    def testTrivial2 {
        var x = 10
        val v = new Ref(x)
        assign(v)
        x = v.self
        assertTrue(x == 12)
    }

    def testExtract: Unit = {
        val s = "abc"
        val r = new Ref(s)
        val Mutable(p) = r
        assertSame(p, s)

        r match {
            case Mutable.Null() => fail("doh")
            case Mutable(p) => assertSame(s, p)
            case _ => fail("doh")
        }
        ()
    }

    def testExtractNull: Unit = {
        val r = new Ref[String]
        r match {
            case Mutable(p) => fail("doh")
            case Mutable.Null() => ()
            case _ => fail("doh")
        }
    }

}
