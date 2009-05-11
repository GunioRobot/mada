

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.metatest


import mada.meta._
// import junit.framework.Assert._


class ForwardingTest {
    def testNone: Unit = ()

    type incre[n <: Nat] = n#increment
    type inc = quote1[incre, Nat, Nat]

    trait testTrivial {
        assert[forwarding1[inc]#apply1[_3N] == _4N]
    }
}