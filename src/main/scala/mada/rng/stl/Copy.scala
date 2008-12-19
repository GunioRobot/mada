

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng.stl


object Copy extends Copy; trait Copy extends Predefs {
    class MadaRngStlCopy[From](_1: Expr.Of[Rng[From]]) {
        def stl_copy[To >: From](_2: Expr.Of[Pointer[To]]) = CopyExpr(_1, _2).expr
        def stl_copyIf[To >: From](_2: Expr.Of[Pointer[To]], _3: From => Boolean) = CopyIfExpr(_1, _2, _3).expr
    }
    implicit def toMadaRngStlCopy[From](_1: Expr.Of[Rng[From]]): MadaRngStlCopy[From] = new MadaRngStlCopy[From](_1)
}


case class CopyExpr[From, To >: From](override val _1: Expr.Of[Rng[From]], _2: Expr.Of[Pointer[To]])
        extends Expr.Method[Rng[From], Pointer[To]] {
    override protected def _default = {
        val p2 = _2.eval.copyIn(Traversal.Forward)
        ForeachExpr(_1, p2.output).eval // enables fusion.
        p2
    }
}

case class CopyIfExpr[From, To >: From](_1: Expr.Of[Rng[From]], _2: Expr.Of[Pointer[To]], _3: From => Boolean)
        extends Expr.Alias[Rng[From], Pointer[To]] {
    override protected def _alias = CopyExpr(FilterExpr(_1, _3), _2)
}