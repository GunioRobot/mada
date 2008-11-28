
package mada.rng


trait PointerFacade[E, P] extends Pointer[E] with PointerPre_* {
    protected def _equals(that: P): Boolean = { throw new ErrorNotSinglePass(this) }
    protected def _difference(that: P): Long = { throw new ErrorNotRandomAccess(this) }
    protected def _compatible(that: P) { }

    override final def equals(that: Any) = {
        val p = that.asInstanceOf[P]
        _compatible(p)
        _equals(p)
    }

    override final def _difference_(that: Pointer[E]): Long = {
        val p = that.asInstanceOf[P]
        _compatible(p)
        _difference(p)
    }
}
