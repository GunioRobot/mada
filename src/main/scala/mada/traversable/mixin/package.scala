

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


package object mixin {

    val force = new Mixin {
        override def apply[B](tr: Traversable[B]) = tr.force
    }

}
