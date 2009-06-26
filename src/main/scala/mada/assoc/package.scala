

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods operating on associative container.
 */
package object assoc {


    @packageObjectBrokenOverload
    object lazyGet {

        /**
         * Gets the value: puts only if <code>key</code> is not contained.
         */
        @notThreadSafe
        def apply[K, V](m: scala.collection.mutable.Map[K, V])(key: K)(value: => V): V = m.get(key) match {
            case None => {
                val v = value
                m += ((key, v))
                v
            }
            case Some(v) => v
        }

        /**
         * Gets the value: puts only if <code>key</code> is not contained.
         */
        def apply[K, V](m: java.util.concurrent.ConcurrentMap[K, () => V])(key: K)(value: => V): V = {
            // See: Java Concurrency in Practice - Listing 5.19
            option.fromRef(m.get(key)).getOrElse{
                val v = util.byLazy(value)
                option.fromRef(m.putIfAbsent(key, v)).getOrElse{
                    v
                }
            }.apply()
        }

    }

}
