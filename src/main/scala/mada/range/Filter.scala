
package mada.range


object Filter {
    def apply[A](base: Range[A], function: A => Boolean): Range[A] = new FilterRange(base, function)
}

class FilterRange[A](val base: Range[A], val function: A => Boolean)
        extends {
            val bq = base.end
            val q = bq.immutable
            val q_ = if (bq.traversal conformsTo BidirectionalTraversal) bq.clone else q
        } with PointerRange[A](new FilterPointer(base.begin, q, function), new FilterPointer(q_, q, function)) {
    override def filter(f: A => Boolean) = base.filter({(a: A) => function(a) && f(a)})
}

class FilterPointer[A](override val _base: Pointer[A], q: Pointer[A], val function: A => Boolean)
        extends PointerAdapter[A, A, FilterPointer[A]] {
    satisfy
    override def _traversal = base.traversal min BidirectionalTraversal
    override def _increment = { base++/; satisfy; }
    override def _clone = new FilterPointer(base.clone, q, function)
    override def _decrement = { while (!function(*(base--/))) { } }
    final private def satisfy = { while (base != q && !function(*(base))) { base++/ } }
}
