

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


/*
 *
 * Copyright (c) 1994
 * Hewlett-Packard Company
 *
 * Copyright (c) 1996,1997
 * Silicon Graphics Computer Systems, Inc.
 *
 * Copyright (c) 1997
 * Moscow Center for SPARC Technology
 *
 * Copyright (c) 1999
 * Boris Fomitchev
 *
 * This material is provided "as is", with absolutely no warranty expressed
 * or implied. Any use is at your own risk.
 *
 * Permission to use or copy this software for any purpose is hereby granted
 * without fee, provided the above notices are retained on all copies.
 * Permission to modify the code and to distribute modified code is granted,
 * provided the above notices are retained, and a notice that the code was
 * modified is included with the above copyright notice.
 *
 */


package mada.vec2


object Equals {
    def apply[A1, A2](v1: Vector[A1], v2: Vector[A2]): Boolean = EqualsIf(v1, v2, (_: A1) == (_: A2))
}


object EqualsIf {
    def apply[A1, A2](v1: Vector[A1], v2: Vector[A2], __binary_pred: (A1, A2) => Boolean): Boolean = {
        var (__first1, __last1) = v1.toPair
        var (__first2, __last2) = v2.toPair

        if (__last1 - __first1 != __last2 - __first2) {
            return false
        }

        while (__first1 != __last1) {
            if (!__binary_pred(v1(__first1), v2(__first2))) {
                return false
            }
            __first1 +=1; __first2 += 1
        }
        true
    }
}
