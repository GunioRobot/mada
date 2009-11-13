

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


import java.util.concurrent.atomic


private class IfFirst[-T](_then: T => Unit, _else: T => Unit) extends Function1[T, Unit] {
    private val first = new atomic.AtomicBoolean(true)

    override def apply(x: T): Unit = {
        if (first.get && first.compareAndSet(true, false)) {
            return _then(x)
        }
        _else(x)
    }
}


private class SkipFirst[-T](f: T => Unit) extends Function1[T, Unit] {
    private val delegate = new IfFirst[T](_ => (), f)
    override def apply(x: T) = delegate(x)
}

private class SkipTimes[-T](f: T => Unit, n: Int) extends Function1[T, Unit] {
    private val count = new atomic.AtomicInteger(n)

    override def apply(x: T): Unit = {
        var old = 0
        do {
            old = count.get
            if (old == 0) {
                return f(x)
            }
        } while (!count.compareAndSet(old, old - 1))
    }
}


private class SkipWhile[-T](f: T => Unit, p: T => Boolean) extends Function1[T, Unit] {
    @volatile private var begins = false

    override def apply(x: T): Unit = {
        if (begins) {
            return f(x)
        }
        if (!p(x)) {
            begins = true
            return f(x)
        }
    }
}
