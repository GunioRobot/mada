

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


import ordering.{LT, GT, EQ}


private[dual]
object LexicographicalOrdering {
     def apply[eo <: Option](eo: eo): apply[eo] = Impl(eo)
    type apply[eo <: Option]                    = Impl[eo]

    case class Impl[eo <: Option](eo: eo) extends ordering.AbstractOrdering {
        type self = Impl[eo]

        override  def equiv[x <: Any, y <: Any](x: x, y: y): equiv[x, y] = Equal.apply(x.asInstanceOfList, y.asInstanceOfList, eo)
        override type equiv[x <: Any, y <: Any]                          = Equal.apply[x#asInstanceOfList, y#asInstanceOfList, eo]

        override  def compare[x <: Any, y <: Any](x: x, y: y): compare[x, y] = _compare(x.asInstanceOfList, y.asInstanceOfList)
        override type compare[x <: Any, y <: Any]                            = _compare[x#asInstanceOfList, y#asInstanceOfList]

        private  def _compare[xs <: List, ys <: List](xs: xs, ys: ys): _compare[xs, ys] =
            `if`(xs.isEmpty,
                `if`(ys.isEmpty, const0(EQ), const0(LT)),
                `if`(ys.isEmpty, const0(GT), Else(xs, ys, eo))
            ).apply.asInstanceOfOrderingResult.asInstanceOf[_compare[xs, ys]]

        private type _compare[xs <: List, ys <: List] =
            `if`[xs#isEmpty,
                `if`[ys#isEmpty, const0[EQ], const0[LT]],
                `if`[ys#isEmpty, const0[GT], Else[xs, ys, eo]]
            ]#apply#asInstanceOfOrderingResult
    }

    case class Else[xs <: List, ys <: List, eo <: Option](xs: xs, ys: ys, eo: eo) extends Function0 {
        type self = Else[xs, ys, eo]
        private lazy val _eo: _eo = eo.getOrNaturalOrdering(xs.head)
        private type _eo          = eo#getOrNaturalOrdering[xs#head]
        override  def apply: apply =
            _eo.`match`(xs.head, ys.head, const0(LT), const0(GT), CaseEQ(xs, ys, eo)).asInstanceOfOrderingResult.asInstanceOf[apply]
        override type apply =
            _eo#`match`[xs#head, ys#head, const0[LT], const0[GT], CaseEQ[xs, ys, eo]]#asInstanceOfOrderingResult
    }

    case class CaseEQ[xs <: List, ys <: List, eo <: Option](xs: xs, ys: ys, eo: eo) extends Function0 {
        type self = CaseEQ[xs, ys, eo]
        override  def apply: apply = Impl(eo).compare(xs.tail, ys.tail).asInstanceOf[apply]
        override type apply        = Impl[eo]#compare[xs#tail, ys#tail]
    }
}