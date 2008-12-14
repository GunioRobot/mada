

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


trait IndexAccess[A] {
    def _set(i: Long, e: A): Unit = throw NotWritableIndexAccessError
    def _get(i: Long): A
    def _size: Long
    final def indexAccess = this
}


case class IndexAccessRngExpr[A](_1: IndexAccess[A]) extends Expr.ConstantOf[Rng[A]] {
    override protected def _of = new IndexAccessPointer(_1, 0) <=< new IndexAccessPointer(_1, _1._size)
}


class IndexAccessPointer[A](val indexAccess: IndexAccess[A], val startIndex: Long)
        extends PointerAdapter[Long, A, IndexAccessPointer[A]] {
    override protected val _base = new LongIntervalPointer(startIndex)

    override protected def _read = indexAccess._get(*(base))
    override protected def _write(e: A) = {
        try {
            indexAccess._set(*(base), e)
        } catch {
            case NotWritableIndexAccessError => throw new NotWritablePointerError(this)
        }
    }
    override protected def _copy = new IndexAccessPointer(indexAccess, *(base))

    override def _offsetRead(d: Long): A = indexAccess._get(*(base) + d)
    override def _offsetWrite(d: Long, e: A): Unit = indexAccess._set(*(base) + d, e)

    override def toString = new StringBuilder().append("IndexAccessPointer(").append(*(base)).append(") of ").append(indexAccess).toString
}

object NotWritableIndexAccessError extends Error
