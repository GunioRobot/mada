

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.stl


object Accumulate {
    def apply[A, B](v: Vector[A], __first: Long, __last: Long, init: B, __binary_op: (B, A) => B): B = {
        var __init = init
        ForEach(v, __first, __last, { (e: A) => __init = __binary_op(__init, e) })
        __init
    }
}
