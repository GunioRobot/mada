

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


import java.util.concurrent.atomic.AtomicReference


object Find {
    def apply[A, B](v: Vector[A], p: A => Boolean, grainSize: Int): Option[A] = {
        if (v.isEmpty) {
            None
        } else {
            val ar = new AtomicReference[A]
            val bp = new Breakable1(p, true)
            v.divide(grainSize).
                parallel(1).foreach({ w => breakingFind(w, bp, ar) })
            deref(ar)
        }
    }

    private def breakingFind[A](v: Vector[A], p: Breakable1[A], ar: AtomicReference[A]): Unit = {
        val x = v.find(p)
        if (!x.isEmpty) {
            ar.compareAndSet(null.asInstanceOf[A], x.get)
            p.break
        }
    }

    private def deref[A](ar: AtomicReference[A]): Option[A] = {
        val a = ar.get
        if (a == null) {
            None
        } else {
            Some(a.asInstanceOf[A])
        }
    }
}