

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package map; package bstree


private[mada] final class NodeRemove {
     def apply[m <: BSTree, k <: Any](m: m, k: k): apply[m, k] =
        m.ord.`match`(k, m.key, CaseLT(m, k), CaseGT(m, k), CaseEQ(m, k)).asInstanceOfMapBSTree.asInstanceOf[apply[m, k]]
    type apply[m <: BSTree, k <: Any] =
        m#ord#`match`[k, m#key, CaseLT[m, k], CaseGT[m, k], CaseEQ[m, k]]#asInstanceOfMapBSTree

    case class CaseLT[m <: BSTree, k <: Any](m: m, k: k) extends Function0 {
        type self = CaseLT[m, k]
        override  def apply: apply = new Balance().apply(m.key, m.value, m.left.remove(k), m.right).asInstanceOf[apply]
        override type apply        =     Balance#  apply[m#key, m#value, m#left#remove[k], m#right]
    }

    case class CaseGT[m <: BSTree, k <: Any](m: m, k: k) extends Function0 {
        type self = CaseGT[m, k]
        override  def apply: apply = new Balance().apply(m.key, m.value, m.left, m.right.remove(k)).asInstanceOf[apply]
        override type apply        =     Balance#  apply[m#key, m#value, m#left, m#right#remove[k]]
    }

    case class CaseEQ[m <: BSTree, k <: Any](m: m, k: k) extends Function0 {
        type self = CaseEQ[m, k]
        override  def apply: apply = new Glue().apply(m.left, m.right)
        override type apply        =     Glue#  apply[m#left, m#right]
    }
}