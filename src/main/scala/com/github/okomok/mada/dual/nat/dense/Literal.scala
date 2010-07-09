

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package dense


/**
 * Contains natural number literals.
 */
object Literal extends LiteralCommon


private[mada] trait LiteralCommon {
    private[mada]  val _0B = _Boolean.`false`
    private[mada]  val _1B = _Boolean.`true`
    private[mada] type _0B = `false`
    private[mada] type _1B = `true`

	val  _0 = _Dense.Nil
	val  _1 = Cons(_1B, _Dense.Nil)
	val  _2 = Cons(_0B, Cons(_1B, _Dense.Nil))
	val  _3 = Cons(_1B, Cons(_1B, _Dense.Nil))
	val  _4 = Cons(_0B, Cons(_0B, Cons(_1B, _Dense.Nil)))
	val  _5 = Cons(_1B, Cons(_0B, Cons(_1B, _Dense.Nil)))
	val  _6 = Cons(_0B, Cons(_1B, Cons(_1B, _Dense.Nil)))
	val  _7 = Cons(_1B, Cons(_1B, Cons(_1B, _Dense.Nil)))
	val  _8 = Cons(_0B, Cons(_0B, Cons(_0B, Cons(_1B, _Dense.Nil))))
	val  _9 = Cons(_1B, Cons(_0B, Cons(_0B, Cons(_1B, _Dense.Nil))))
	val _10 = Cons(_0B, Cons(_1B, Cons(_0B, Cons(_1B, _Dense.Nil))))
	val _11 = Cons(_1B, Cons(_1B, Cons(_0B, Cons(_1B, _Dense.Nil))))
	val _12 = Cons(_0B, Cons(_0B, Cons(_1B, Cons(_1B, _Dense.Nil))))
	val _13 = Cons(_1B, Cons(_0B, Cons(_1B, Cons(_1B, _Dense.Nil))))
	val _14 = Cons(_0B, Cons(_1B, Cons(_1B, Cons(_1B, _Dense.Nil))))
	val _15 = Cons(_1B, Cons(_1B, Cons(_1B, Cons(_1B, _Dense.Nil))))

	type  _0 = Nil
	type  _1 = Cons[_1B, Nil]
	type  _2 = Cons[_0B, Cons[_1B, Nil]]
	type  _3 = Cons[_1B, Cons[_1B, Nil]]
	type  _4 = Cons[_0B, Cons[_0B, Cons[_1B, Nil]]]
	type  _5 = Cons[_1B, Cons[_0B, Cons[_1B, Nil]]]
	type  _6 = Cons[_0B, Cons[_1B, Cons[_1B, Nil]]]
	type  _7 = Cons[_1B, Cons[_1B, Cons[_1B, Nil]]]
	type  _8 = Cons[_0B, Cons[_0B, Cons[_0B, Cons[_1B, Nil]]]]
	type  _9 = Cons[_1B, Cons[_0B, Cons[_0B, Cons[_1B, Nil]]]]
	type _10 = Cons[_0B, Cons[_1B, Cons[_0B, Cons[_1B, Nil]]]]
	type _11 = Cons[_1B, Cons[_1B, Cons[_0B, Cons[_1B, Nil]]]]
	type _12 = Cons[_0B, Cons[_0B, Cons[_1B, Cons[_1B, Nil]]]]
	type _13 = Cons[_1B, Cons[_0B, Cons[_1B, Cons[_1B, Nil]]]]
	type _14 = Cons[_0B, Cons[_1B, Cons[_1B, Cons[_1B, Nil]]]]
	type _15 = Cons[_1B, Cons[_1B, Cons[_1B, Cons[_1B, Nil]]]]
}