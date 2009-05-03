

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


/**
 * Contains meta natural numbers,
 * which have constant-time methods only.
 */
trait Nats { this: Meta.type =>

    trait Nat extends Object {
        private[mada] type `this` <: Nat

        type increment <: Nat
        type decrement <: Nat
        type equals[that <: Nat] <: Boolean

        override type Operand_== = Nat
        override type operator_==[that <: Nat] = equals[that]

        override type operator_++ = increment
        override type operator_-- = decrement

        private[mada] type is0 <: Boolean
        private[mada] type is1 <: Boolean
        private[mada] type is2 <: Boolean
        private[mada] type is3 <: Boolean
        private[mada] type is4 <: Boolean
        private[mada] type is5 <: Boolean
        private[mada] type is6 <: Boolean
        private[mada] type is7 <: Boolean
        private[mada] type is8 <: Boolean
        private[mada] type is9 <: Boolean
        private[mada] type is10 <: Boolean

        // Parameterized FoldLeftFunction won't work, hence z can't be used as basis.
        type FoldLeftFunction = Function2 { type Argument22 >: Nat; type Result2 <: Argument21 }
        type foldLeft[z <: op#Result2, op <: FoldLeftFunction] <: op#Result2
    }

    sealed trait _0N extends Nat {
        override type increment = _1N
        override type decrement = throwError
        override type equals[that <: Nat] = that#is0

        private[mada] override type is0 = `true`
        private[mada] override type is1 = `false`
        private[mada] override type is2 = `false`
        private[mada] override type is3 = `false`
        private[mada] override type is4 = `false`
        private[mada] override type is5 = `false`
        private[mada] override type is6 = `false`
        private[mada] override type is7 = `false`
        private[mada] override type is8 = `false`
        private[mada] override type is9 = `false`
        private[mada] override type is10 = `false`

        override type foldLeft[z <: op#Result2, op <: FoldLeftFunction] = z
    }

    sealed trait PositiveNat extends Nat {
        override type foldLeft[z <: op#Result2, op <: FoldLeftFunction] = decrement#foldLeft[op#apply2[z, decrement], op]
    }

    sealed trait _1N extends Nat with PositiveNat {
        override type increment = _2N
        override type decrement = _0N
        override type equals[that <: Nat] = that#is1

        private[mada] override type is0 = `false`
        private[mada] override type is1 = `true`
        private[mada] override type is2 = `false`
        private[mada] override type is3 = `false`
        private[mada] override type is4 = `false`
        private[mada] override type is5 = `false`
        private[mada] override type is6 = `false`
        private[mada] override type is7 = `false`
        private[mada] override type is8 = `false`
        private[mada] override type is9 = `false`
        private[mada] override type is10 = `false`
    }

    sealed trait _2N extends Nat with PositiveNat {
        override type increment = _3N
        override type decrement = _1N
        override type equals[that <: Nat] = that#is2

        private[mada] override type is0 = `false`
        private[mada] override type is1 = `false`
        private[mada] override type is2 = `true`
        private[mada] override type is3 = `false`
        private[mada] override type is4 = `false`
        private[mada] override type is5 = `false`
        private[mada] override type is6 = `false`
        private[mada] override type is7 = `false`
        private[mada] override type is8 = `false`
        private[mada] override type is9 = `false`
        private[mada] override type is10 = `false`
    }

    sealed trait _3N extends Nat with PositiveNat {
        override type increment = _4N
        override type decrement = _2N
        override type equals[that <: Nat] = that#is3

        private[mada] override type is0 = `false`
        private[mada] override type is1 = `false`
        private[mada] override type is2 = `false`
        private[mada] override type is3 = `true`
        private[mada] override type is4 = `false`
        private[mada] override type is5 = `false`
        private[mada] override type is6 = `false`
        private[mada] override type is7 = `false`
        private[mada] override type is8 = `false`
        private[mada] override type is9 = `false`
        private[mada] override type is10 = `false`
    }

    sealed trait _4N extends Nat with PositiveNat {
        override type increment = _5N
        override type decrement = _3N
        override type equals[that <: Nat] = that#is4

        private[mada] override type is0 = `false`
        private[mada] override type is1 = `false`
        private[mada] override type is2 = `false`
        private[mada] override type is3 = `false`
        private[mada] override type is4 = `true`
        private[mada] override type is5 = `false`
        private[mada] override type is6 = `false`
        private[mada] override type is7 = `false`
        private[mada] override type is8 = `false`
        private[mada] override type is9 = `false`
        private[mada] override type is10 = `false`
    }

    sealed trait _5N extends Nat with PositiveNat {
        override type increment = _6N
        override type decrement = _4N
        override type equals[that <: Nat] = that#is5

        private[mada] override type is0 = `false`
        private[mada] override type is1 = `false`
        private[mada] override type is2 = `false`
        private[mada] override type is3 = `false`
        private[mada] override type is4 = `false`
        private[mada] override type is5 = `true`
        private[mada] override type is6 = `false`
        private[mada] override type is7 = `false`
        private[mada] override type is8 = `false`
        private[mada] override type is9 = `false`
        private[mada] override type is10 = `false`
    }

    sealed trait _6N extends Nat with PositiveNat {
        override type increment = _7N
        override type decrement = _5N
        override type equals[that <: Nat] = that#is6

        private[mada] override type is0 = `false`
        private[mada] override type is1 = `false`
        private[mada] override type is2 = `false`
        private[mada] override type is3 = `false`
        private[mada] override type is4 = `false`
        private[mada] override type is5 = `false`
        private[mada] override type is6 = `true`
        private[mada] override type is7 = `false`
        private[mada] override type is8 = `false`
        private[mada] override type is9 = `false`
        private[mada] override type is10 = `false`
    }

    sealed trait _7N extends Nat with PositiveNat {
        override type increment = _8N
        override type decrement = _6N
        override type equals[that <: Nat] = that#is7

        private[mada] override type is0 = `false`
        private[mada] override type is1 = `false`
        private[mada] override type is2 = `false`
        private[mada] override type is3 = `false`
        private[mada] override type is4 = `false`
        private[mada] override type is5 = `false`
        private[mada] override type is6 = `false`
        private[mada] override type is7 = `true`
        private[mada] override type is8 = `false`
        private[mada] override type is9 = `false`
        private[mada] override type is10 = `false`
     }

    sealed trait _8N extends Nat with PositiveNat {
        override type increment = _9N
        override type decrement = _7N
        override type equals[that <: Nat] = that#is8

        private[mada] override type is0 = `false`
        private[mada] override type is1 = `false`
        private[mada] override type is2 = `false`
        private[mada] override type is3 = `false`
        private[mada] override type is4 = `false`
        private[mada] override type is5 = `false`
        private[mada] override type is6 = `false`
        private[mada] override type is7 = `false`
        private[mada] override type is8 = `true`
        private[mada] override type is9 = `false`
        private[mada] override type is10 = `false`
    }

    sealed trait _9N extends Nat with PositiveNat {
        override type increment = _10N
        override type decrement = _8N
        override type equals[that <: Nat] = that#is9

        private[mada] override type is0 = `false`
        private[mada] override type is1 = `false`
        private[mada] override type is2 = `false`
        private[mada] override type is3 = `false`
        private[mada] override type is4 = `false`
        private[mada] override type is5 = `false`
        private[mada] override type is6 = `false`
        private[mada] override type is7 = `false`
        private[mada] override type is8 = `false`
        private[mada] override type is9 = `true`
        private[mada] override type is10 = `false`
    }

    sealed trait _10N extends Nat with PositiveNat {
        override type increment = throwError
        override type decrement = _9N
        override type equals[that <: Nat] = that#is10

        private[mada] override type is0 = `false`
        private[mada] override type is1 = `false`
        private[mada] override type is2 = `false`
        private[mada] override type is3 = `false`
        private[mada] override type is4 = `false`
        private[mada] override type is5 = `false`
        private[mada] override type is6 = `false`
        private[mada] override type is7 = `false`
        private[mada] override type is8 = `false`
        private[mada] override type is9 = `false`
        private[mada] override type is10 = `true`
    }

    implicit val unmeta_0N_Int = Unmeta[_0N, Int](0)
    implicit val unmeta_1N_Int = Unmeta[_1N, Int](1)
    implicit val unmeta_2N_Int = Unmeta[_2N, Int](2)
    implicit val unmeta_3N_Int = Unmeta[_3N, Int](3)
    implicit val unmeta_4N_Int = Unmeta[_4N, Int](4)
    implicit val unmeta_5N_Int = Unmeta[_5N, Int](5)
    implicit val unmeta_6N_Int = Unmeta[_6N, Int](6)
    implicit val unmeta_7N_Int = Unmeta[_7N, Int](7)
    implicit val unmeta_8N_Int = Unmeta[_8N, Int](8)
    implicit val unmeta_9N_Int = Unmeta[_9N, Int](9)
    implicit val unmeta_10N_Int = Unmeta[_10N, Int](10)
    // implicit def positiveNatToInt[n <: Nat](implicit c: Unmeta[n, Int]): Unmeta[n#increment, Int] = Unmeta[n#increment, Int](1 + c.value) // doesn't work.

}
