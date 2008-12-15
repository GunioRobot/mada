

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


import Advance._


object Pointer extends Namespace
        with PointerOrdered
        with PointerPreOps


trait Pointer[A] extends Expr.Start[Pointer[A]]
        with Traversal.Modeller {
// element-access
    protected def _read: A = throw new NotReadablePointerError(this)
    protected def _write(e: A): Unit = throw new NotWritablePointerError(this)
    final def read: A = _read
    final def write(e: A): Pointer[A] = { _write(e); this }

// single-pass
    protected def _increment: Unit = throw new NotOverriddenPointerMethodError(this)
    protected def _equals_(that: Pointer[A]): Boolean
    override final def equals(that: Any): Boolean = _equals_(that.asInstanceOf[Pointer[A]])
    final def pre_++ : Pointer[A] = { assertModels(SinglePass); _increment; this }

// forward
    protected def _copy: Pointer[A] = throw new NotOverriddenPointerMethodError(this)
    final def copy: Pointer[A] = { assertModels(Forward); _copy }
    final def ++ : Pointer[A] = { val tmp = copy; pre_++; tmp }

// bidirectional
    protected def _decrement: Unit = throw new NotOverriddenPointerMethodError(this)
    final def pre_-- : Pointer[A] = { assertModels(Bidirectional); _decrement; this }
    final def -- : Pointer[A] = { val tmp = copy; pre_--; tmp }

// random-access
    protected def _offset(d: Long): Unit = throw new NotOverriddenPointerMethodError(this)
    protected def _difference_(that: Pointer[A]): Long = throw new NotOverriddenPointerMethodError(this)
    final def - (that: Pointer[A]): Long = { assertModels(RandomAccess); _difference_(that) }
    final def +=(d: Long): Pointer[A] = { assertModels(RandomAccess); _offset(d); this }
    final def -=(d: Long): Pointer[A] = this += (-d)
    final def + (d: Long): Pointer[A] = copy += d
    final def - (d: Long): Pointer[A] = copy -= d
    final def < (that: Pointer[A]): Boolean = this - that < 0
    final def > (that: Pointer[A]): Boolean = this - that > 0
    final def <= (that: Pointer[A]): Boolean = this - that <= 0
    final def >= (that: Pointer[A]): Boolean = this - that >= 0
  // optional
    protected def _offsetRead(d: Long): A = (this + d).read
    protected def _offsetWrite(d: Long, e: A): Unit = (this + d).write(e)
    final def apply(d: Long): A = _offsetRead(d)
    final def update(d: Long, e: A): Unit = _offsetWrite(d, e)

// debug
    protected def _invariant = { }

// utilities
    final def advance(d: Long) = /.advance(d)./
    final def output: A => Pointer[A] = detail.PointerOutput(this, _)
    final def swap(that: Pointer[A]) = detail.PointerSwap(this, that)
    final def swap(i: Long, j: Long) = detail.PointerSwap(this, i, j)
    final def <=<(that: Pointer[A]) = new detail.PointerRng(this, that).rng
    final def copyIn(t: Traversal): Pointer[A] = if (traversal <:< t) copy else this
    final def pointer = this
}


// prefix operations

object PointerPreOps extends PointerPreOps; trait PointerPreOps extends Namespace
        with PointerPre_*
        with PointerPre_++
        with PointerPre_--

object PointerPre_* extends PointerPre_*; trait PointerPre_* {
    object * {
        def apply[A](p: Pointer[A]): A = p.read
        def update[A](p: Pointer[A], e: A): Unit = p.write(e)

        def apply[A](p: Pointer[A], d: Long): A = p(d)
        def update[A](p: Pointer[A], d: Long, e: A): Unit = p(d) = e
    }
}

object PointerPre_++ extends PointerPre_++; trait PointerPre_++ {
    final def ++[A](p: Pointer[A]): Pointer[A] = p.pre_++
}

object PointerPre_-- extends PointerPre_--; trait PointerPre_-- {
    final def --[A](p: Pointer[A]): Pointer[A] = p.pre_--
}


// errors

class NotReadablePointerError[A](val pointer: Pointer[A]) extends Error
class NotWritablePointerError[A](val pointer: Pointer[A]) extends Error
class NotOverriddenPointerMethodError[A](val pointer: Pointer[A]) extends Error
