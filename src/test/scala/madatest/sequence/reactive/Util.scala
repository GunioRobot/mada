

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package reactivetest


import mada.sequence._
import java.util.concurrent.CyclicBarrier
import java.util.ArrayList


class IntSenders(_data: Vector[Int]*) {
    private val data: Vector[Vector[Int]] = vector.from(_data.toArray[Vector[Int]])
    private val barrier = new CyclicBarrier(data.size + 1)
    private val senders: Vector[IntSender] = {
        val buf = new ArrayList[IntSender]
        for (datum <- data) {
            val s = new IntSender(datum, barrier)
            buf.add(s)
        }
        vector.from(buf)
    }

    def apply(n: Int): IntSender = senders.nth(n)

    def start: Unit = barrier.await

    def shutdown(f: => Unit) = {
        barrier.await
        f
    }
}


class IntSender(datum: Vector[Int], barrier: CyclicBarrier) extends Reactive[Int] {
    override def subscribe(k: Reactor[Int]) = {
        new Thread {
            override def run = {
                barrier.await

                for (i <- datum) {
                    k.react(i)
                }
                k.onEnd

                barrier.await
            }
        }.start
    }
}


class IntReceiver(expected: Vector[Int]) extends Reactor[Int] {
    private val buf = new ArrayList[Int]
    private var endCount = 0

    override def onEnd = synchronized { endCount += 1 }
    override def react(e: Int) = synchronized { buf.add(e); }

    def assertMe = {
        import junit.framework.Assert._
        assertEquals(1, endCount)
        assertEquals(expected, vector.from(buf).sort)
    }
}
