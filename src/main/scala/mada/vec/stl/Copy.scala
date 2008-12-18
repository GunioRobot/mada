

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.stl


object Copy extends Copy; trait Copy extends Predefs {
    class MadaVecStlCopy[From](_1: Expr.Of[Vector[From]]) {
        def stl_copy[To >: From](_2: Vector.Pointer[To]) = CopyExpr(_1, _2).expr
        def stl_copyIf[To >: From](_2: Vector.Pointer[To], _3: From => Boolean) = CopyIfExpr(_1, _2, _3).expr
    }
    implicit def toMadaVecStlCopy[From](_1: Expr.Of[Vector[From]]): MadaVecStlCopy[From] = new MadaVecStlCopy[From](_1)
}


case class CopyExpr[From, To >: From](override val _1: Expr.Of[Vector[From]], _2: Vector.Pointer[To])
extends Expr.Method[Vector[From], Vector.Pointer[To]] {
    override protected def _default = {
        ForEachExpr[From](_1, { (e: From) => _2.write(e); _2 += 1 }).eval // enables fusion.
        _2
    }
}


case class CopyIfExpr[From, To >: From](_1: Expr.Of[Vector[From]], _2: Vector.Pointer[To], _3: From => Boolean)
extends Expr.Method[Vector[From], Vector.Pointer[To]] {
    override protected def _default = {
        val (v, i, j) = _1.eval.toTriple
        CopyIfFunc(v, i, j, _2, _3)
    }
}


object CopyIfFunc {
    def apply[From, To >: From](* : Vector[From], first: Long, __last: Long, __result: Vector.Pointer[To], __pred: From => Boolean): Vector.Pointer[To] = {
        var __first = first

        var __n = __last - __first
        while (__n > 0) {
            if (__pred(*(__first))) {
                *(__result) = *(__first)
                __result += 1
            }
            __first += 1
            __n -= 1
        }
        return __result
    }
}
