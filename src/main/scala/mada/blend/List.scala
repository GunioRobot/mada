

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.blend


// See: HList.scala
//      at http://www.assembla.com/wiki/show/metascala


import Meta.{ Int, Nat }


@provider
trait Lists { this: Blend.type =>


    object List {

        trait Visitor {
            type Result
            type visitNil <: Result
            type visitCons[h, t <: List] <: Result
        }

        def typed[l <: List](from: scala.List[Any])(implicit _typed: Typed[l]) = _typed(from)

        @specializer
        trait Typed[l <: List] extends (scala.List[Any] => l)

        object Typed {
            implicit val ofNil = new Typed[Nil] {
                override def apply(_l: scala.List[Any]) = Nil
            }

            implicit def ofCons[h, t <: List](implicit _typed: Typed[t]) = new Typed[Cons[h, t]] {
                override def apply(_l: scala.List[Any]) = Cons(_l.head.asInstanceOf[h], _typed(_l.tail))
            }
        }

    }


// List

    sealed trait List { // this: `this` =>
        private val _this = this.asInstanceOf[`this`]
        private[mada] type `this` <: List

        /**
         * Returns the first element.
         */
        def head: head
        type head

        /**
         * Returns the trailing list.
         */
        def tail: tail
        type tail <: List

        /**
         * Is this list nil?
         */
        final def isEmpty(implicit _unmeta: Meta.Unmeta[isEmpty, scala.Boolean]): scala.Boolean = _unmeta()
        type isEmpty <: Meta.Boolean

        type accept[v <: List.Visitor] <: v#Result

        /**
         * Returns the <code>i</code>-th element.
         */
        final def at[i <: Int](implicit _at: At[`this`, i#toNat]): at[i] = _at(_this)
        final type at[i <: Int] = metaAt[`this`, i#toNat]

        /**
         * Drops EXACTLY <code>i</code> elements.
         */
        final def drop[i <: Int](implicit _drop: Drop[`this`, i#toNat]): drop[i] = _drop(_this)
        final type drop[i <: Int] = metaDrop[`this`, i#toNat]

        /**
         * Takes EXACTLY <code>i</code> elements.
         */
        final def take[i <: Int](implicit _take: Take[`this`, i#toNat]): take[i] = _take(_this)
        final type take[i <: Int] = metaTake[`this`, i#toNat]

        /**
         * Returns the length.
         */
        final def length(implicit _unmeta: Meta.Unmeta[length, scala.Int]): scala.Int = _unmeta()
        type length <: Int

        /**
         * Prepends <code>that</code>.
         */
        final def :::[that <: List](_that: that)(implicit _prepend: Prepend[`this`, that]): prepend[that] = _prepend(_this, _that)
        final type prepend[that <: List] = metaPrepend[`this`, that]

        /**
         * Converts to <code>scala.List[Any]</code>.
         */
        def untyped: scala.List[Any]

        /**
         * Prepends <code>e</code>.
         */
        final def ::[A](e: A): Cons[A, `this`] = Cons(e, _this)

        final override def toString = untyped.toString
    }


// Nil

    sealed trait Nil extends List {
        private[mada] override type `this` = Nil

        override def head = throw new NoSuchElementException("head of empty list")
        override type head = Meta.error
        override def tail = throw new NoSuchElementException("tail of empty list")
        override type tail = Meta.error // Nil would `List.take` less-restrictive, but less-mathematical.
        override type isEmpty = Meta.`true`
        override type accept[v <: List.Visitor] = v#visitNil
        override type length = Meta._0I

        override def untyped = scala.Nil
    }

    val Nil: Nil = new Nil{}

    // Compiler will fail to search implicits.
    // case object Nil; type Nil = Nil.type


// Cons

    final case class Cons[h, t <: List](override val head: h, override val tail: t) extends List {
        private[mada] override type `this` = Cons[h, t]

        override type head = h
        override type tail = t
        override type isEmpty = Meta.`false`
        override type accept[v <: List.Visitor] = v#visitCons[h, t]
        override type length = tail#length#increment

        override def untyped = scala.::[Any](head, tail.untyped)
    }

    /**
     * Alias of <code>Cons</code>
     */
    type ::[h, t <: List] = Cons[h, t]


// prepend

    type metaPrepend[l <: List, n <: List] = n#accept[prependVisitor[l]]

    sealed trait prependVisitor[l <: List] extends List.Visitor {
        override type Result = List
        override type visitNil = l
        override type visitCons[h, t <: List] = Cons[h, t#accept[prependVisitor[l]]]
    }

    @specializer
    trait Prepend[l <: List, n <: List] extends ((l, n) => metaPrepend[l, n])

    object Prepend {
        implicit def ofNil[l <: List] = new Prepend[l, Nil] {
            override def apply(_l: l, _n: Nil) = _l
        }

        implicit def ofCons[l <: List, h, t <: List](implicit _prepend: Prepend[l, t]) = new Prepend[l, Cons[h, t]] {
            override def apply(_l: l, _n: Cons[h, t]) = Cons(_n.head, _prepend(_l, _n.tail))
        }
    }


// at

    type metaAt[l <: List, n <: Nat] = n#accept[atVisitor[l]]

    sealed trait atVisitor[l <: List] extends Nat.Visitor {
        override type Result = Any
        override type visitZero = l#head
        override type visitSucc[n <: Nat] = n#accept[atVisitor[l#tail]]
    }

    @specializer
    trait At[l <: List, n <: Nat] extends (l => metaAt[l, n])

    object At {
        implicit def ofZero[h, t <: List] = new At[Cons[h, t], Nat.zero] {
            override def apply(_l: Cons[h, t]) = _l.head
        }

        implicit def ofSucc[h, t <: List, n <: Nat](implicit _at: At[t, n]) = new At[Cons[h, t], Nat.succ[n]] {
            override def apply(_l: Cons[h, t]) = _at(_l.tail)
        }
    }


// drop

    type metaDrop[l <: List, n <: Nat] = n#accept[dropVisitor[l]]

    sealed trait dropVisitor[l <: List] extends Nat.Visitor {
        override type Result = List
        override type visitZero = l
        override type visitSucc[n <: Nat] = n#accept[dropVisitor[l#tail]]
    }

    @specializer
    trait Drop[l <: List, n <: Nat] extends (l => metaDrop[l, n])

    object Drop {
        implicit def ofZero[l <: List] = new Drop[l, Nat.zero] {
            override def apply(_l: l) = _l
        }

        implicit def ofSucc[h, t <: List, n <: Nat](implicit _drop: Drop[t, n]) = new Drop[Cons[h, t], Nat.succ[n]] {
            override def apply(_l: Cons[h, t]) = _drop(_l.tail)
        }
    }


// take

    type metaTake[l <: List, n <: Nat] = n#accept[takeVisitor[l]]

    sealed trait takeVisitor[l <: List] extends Nat.Visitor {
        override type Result = List
        override type visitZero = Nil
        override type visitSucc[n <: Nat] = Cons[l#head, n#accept[takeVisitor[l#tail]]]
    }

    @specializer
    trait Take[l <: List, n <: Nat] extends (l => metaTake[l, n])

    object Take {
        implicit def ofZero[l <: List] = new Take[l, Nat.zero] {
            override def apply(_l: l) = Nil
        }

        implicit def ofSucc[h, t <: List, n <: Nat](implicit _take: Take[t, n]) = new Take[Cons[h, t], Nat.succ[n]] {
            override def apply(_l: Cons[h, t]) = Cons(_l.head, _take(_l.tail))
        }
    }

}
