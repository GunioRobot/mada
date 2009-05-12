

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import vector._


/**
 * Sequences that guarantees O(1) element access and O(1) length computation.
 * A vector is optionally writable but structurally-unmodifiable so that synchronization is unneeded.<p/>
 *
 * In vector, an index works like a "key" or "pointer"; it is not guaranteed to start from <code>0</code>.
 * You have to use the <code>nth</code> method if you need "0-to-size" indexing, or extract an index by using
 * the <code>start</code> method.<p/>
 *
 * Unless otherwise specified, these methods return projections to keep readability and writability.
 */
trait Vector[A] extends PartialFunction[Int, A] {


// kernel interface

    /**
     * @return  start index of this vector, which is NOT guaranteed to be <code>0</code>.
     */
    def start: Int

    /**
     * @return  end index of this vector, which is NOT guaranteed to be <code>size</code>.
     */
    def end: Int

    /**
     * @param   i   index of element to return.
     * @pre     this vector is readable.
     * @pre     <code>isDefinedAt(i)</code>
     * @return  the element at the specified position in this vector.
     * @throws  vector.NotReadableException if not overridden.
     */
    override def apply(i: Int): A = throw new vector.NotReadableException(this)

    /**
     * Replaces the element at the specified position in this vector with
     * the specified element.
     *
     * @param   i   index of element to replace.
     * @param   e   element to be stored at the specified position.
     * @pre     this vector is writable.
     * @pre     <code>isDefinedAt(i)</code>
     * @throws  vector.NotWritableException if not overridden.
     */
    def update(i: Int, e: A): Unit = throw new vector.NotWritableException(this)

    /**
     * @return  <code>(start <= i) && (i < end)</code>, possibly overridden in subclasses.
     */
    override def isDefinedAt(i: Int): Boolean = (start <= i) && (i < end)


// value semantics

    /**
     * Compares each element using predicate <code>p</code>.
     * Returns <code>false</code> if <code>size != that.size</code>.
     */
    def equalsIf[B](that: Vector[B])(p: (A, B) => Boolean): Boolean = EqualsIf(this, that, p)

    /**
     * Vector has value semantics <code>==</code>.
     *
     * @return  <code>equalsIf(that)(function.equal)</code>.
     */
    override def equals(that: Any): Boolean = Equals(this, that)

    /**
     * @return  <code>equalsIf(that)(function.equal)</code>.
     */
    final def equalsTo[B](that: Vector[B]): Boolean = equalsIf(that)(function.equal)

    /**
     * Generates hash code as value semantics.
     */
    override def hashCode: Int = HashCode_(this)


// regions

    /**
     * @return  <code>Region(this, _start, _end)</code>.
     * @see     apply as alias.
     */
    def region(_start: Int, _end: Int): Vector[A] = Region(this, _start, _end)

    @returnThis
    def regionBase: Vector[A] = this

    /**
     * @return  <code>(regionBase eq that.regionBase) && (start == that.start) && (end == that.end)</code>.
     */
    final def shallowEquals[B](that: Vector[B]): Boolean = (regionBase eq that.regionBase) && (start == that.start) && (end == that.end)

    /**
     * @pre     <code>!isEmpty</code>
     * @return  <code>this(start, end - 1)</code>.
     */
    def init: Vector[A] = { throwIfEmpty("init"); this(start, end - 1) }

    /**
     * @return  <code>this(start, start)</code>
     */
    def clear: Vector[A] = this(start, start)

    /**
     * @return  <code>this(start + n, start + m)</code>.
     */
    def window(n: Int, m: Int): Vector[A] = this(start + n, start + m)

    /**
     * @return  <code>this(start + i, end + j)</code>.
     */
    def offset(i: Int, j: Int): Vector[A] = this(start + i, end + j)

    /**
     * @return  <code>slice(n, end)</code>.
     */
    def slice(n: Int): Vector[A] = slice(n, end)

    /**
     * @return <code>drop(n).take(m - n)</code>.
     */
    def slice(n: Int, m: Int): Vector[A] = drop(n).take(m - n)

    /**
     * @return <code>this(Math.min(start + n, end), end)</code>.
     */
    def drop(n: Int): Vector[A] = this(Math.min(start + n, end), end)

    /**
     * @return <code>this(start, Math.min(start + n, end))</code>.
     */
    def take(n: Int): Vector[A] = this(start, Math.min(start + n, end))

    /**
     * Returns the longest suffix of this vector whose first element
     * does not satisfy the predicate <code>p</code>.
     *
     * @param   p the test predicate.
     * @return  the longest suffix of the vector whose first element
     *          does not satisfy the predicate <code>p</code>.
     */
    def dropWhile(p: A => Boolean): Vector[A] = DropWhile(this, p)

    /**
     * Returns the longest prefix of this vector whose elements satisfy
     * the predicate <code>p</code>.
     *
     * @param   p  the test predicate.
     * @return  the longest prefix of this vector whose elements satisfy
     *          the predicate <code>p</code>.
     */
    def takeWhile(p: A => Boolean): Vector[A] = TakeWhile(this, p)


// division

    /**
     * Divides this vector into vector of Regions.
     * Each vector size is <code>n</code> except for the last one.
     *
     * @param   n   divisor
     * @return  <code>[this(start, n), this(start + n, 2*n), this(start + 2*n, 3*n),...]</code>.
     * @see     vector.undivide
     */
    def divide(n: Int): Vector[Vector[A]] = Divide(this, n)

    /**
     * @return  <code>(this(start, m), this(m, end))</code>, where <code>val m = Math.min(i, end)</code>.
     */
    def splitAt(i: Int): (Vector[A], Vector[A]) = SplitAt(this, i)

    /**
     * Returns the longest prefix of the vector whose elements all satisfy
     * the given predicate, and the rest of the vector.
     *
     * @param   p   the test predicate
     * @return  a pair consisting of the longest prefix of the vector whose
     *          elements all satisfy <code>p</code>, and the rest of the vector.
     */
    def span(p: A => Boolean): (Vector[A], Vector[A]) = Span(this, p)

    /**
     * @return  <code>span(function.not(p))</code>.
     */
    def break(p: A => Boolean): (Vector[A], Vector[A]) = span(function.not(p))


// first and last

    /**
     * @return  <code>this(start)</code>.
     */
    final def first: A = { throwIfEmpty("first"); this(start) }

    /**
     * @return  <code>this(end - 1)</code>.
     */
    final def last: A = { throwIfEmpty("last"); this(end - 1) }

    @aliasOf("randomAccessSeq.firstOption")
    final def firstOption: Option[A] = FirstOption(this)

    @aliasOf("randomAccessSeq.lastOption")
    final def lastOption: Option[A] = LastOption(this)


// as list

    @aliasOf("first")
    final def head: A = { throwIfEmpty("head"); first }

    /**
     * @return  <code>this(start + 1, end)</code>
     */
    def tail: Vector[A] = { throwIfEmpty("tail"); this(start + 1, end) }

    @aliasOf("isEmpty")
    final def isNil: Boolean = isEmpty


// filter

    /**
     * Returns all the elements of this vector that satisfy the
     * predicate <code>p</code>. The order of the elements is preserved.
     *
     * @param   p   the predicate used to filter the vector.
     * @return  the non-writable elements of this vector satisfying <code>p</code>.
     */
    def filter(p: A => Boolean): Vector[A] = Filter(this, p)

    /**
     * Returns all the elements of this vector that satisfy the
     * predicate <code>p</code>. The order of the elements is preserved.
     * Unlike <code>filter</code>, this requires no intermediate buffer,
     * but the state of this vector is unpredictable after calling this.
     *
     * @param   p   the predicate used to filter the vector.
     * @return  the elements of this vector satisfying <code>p</code>.
     */
    def mutatingFilter(p: A => Boolean): Vector[A] = MutatingFilter(this, p)

    /**
     * @return  <code>filter(function.not(p))</code>.
     */
    def remove(p: A => Boolean): Vector[A] = filter(function.not(p))

    /**
     * @return  <code>mutatingFilter(function.not(p))</code>.
     */
    def mutatingRemove(p: A => Boolean): Vector[A] = mutatingFilter(function.not(p))

    /**
     * @return  <code>(filter(p), remove(p))</code>.
     */
    def partition(p: A => Boolean): (Vector[A], Vector[A]) = (filter(p), remove(p))


// map

    /**
     * Returns the vector resulting from applying the given function <code>f</code>
     * to each element of this vector.
     *
     * @pre <code>f</code> has no side effects.
     */
    def map[B](f: A => B): Vector[B] = Map(this, f)

    /**
     * @return  <code>vector.flatten(map(f))</code>.
     */
    def flatMap[B](f: A => Vector[B]): Vector[B] = vector.flatten(map(f).toIterable)

    /**
     * Casts element to type <code>B</code>.
     */
    def asVectorOf[B]: Vector[B] = AsVectorOf[A, B](this)


// loop

    /**
     * Similar to <code>foreach</code>, but loop is breakable by <code>f</code> returning <code>false</code>.
     */
    def loop[F <: (A => Boolean)](i: Int, j: Int, f: F): F = Loop(this, i, j, f)

    /**
     * Alias of <code>elements.foreach</code>.
     */
    final def foreach(f: A => Unit): Unit = Foreach(this, f)

    /**
     * Apply a function <code>f</code> to all elements of this vector.
     * <code>f</code> may be called in any order, and
     * there is no guarantee <code>f</code> calls are single-threaded.
     *
     * @param   f   a function that is applied to every element.
     */
    def each(f: A => Unit): Unit = foreach(f)


// search

    @aliasOf("elements.find")
    final def find(p: A => Boolean): Option[A] = Find(this, p)

    /**
     * Find and return the element of this vector satisfying a
     * predicate, if any. No guarantee the element is the first one.
     *
     * @param   p   the predicate
     * @return  the element in the iterable object satisfying
     *          <code>p</code>, or <code>None</code> if none exists.
     */
    def seek(p: A => Boolean): Option[A] = find(p)

    @aliasOf("randomAccessSeq.count")
    def count(p: A => Boolean): Int = Count(this, p)

    /**
     * @return  <code>exists(function.equalTo(e))</code>.
     */
    final def contains(e: Any): Boolean = exists(function.equalTo(e))

    /**
     * @return  <code>seek(function.not(p)).isEmpty</code>.
     */
    final def forall(p: A => Boolean): Boolean = seek(function.not(p)).isEmpty

    /**
     * @return  <code>!seek(p).isEmpty</code>.
     */
    final def exists(p: A => Boolean): Boolean = !seek(p).isEmpty


// folding

    @aliasOf("elements.foldLeft")
    final def foldLeft[B](z: B)(op: (B, A) => B): B = FoldLeft(this, z, op)

    @aliasOf("elements.foldRight")
    final def foldRight[B](z: B)(op: (A, B) => B): B = reverse.foldLeft(z)(function.flip(op))

    /**
     * @return  <code>tail.foldLeft[B](head)(op)</code>.
     */
    final def reduceLeft[B >: A](op: (B, A) => B): B = tail.foldLeft[B](head)(op)

    /**
     * @return  <code>reverse.reduceLeft(function.flip(op))</code>.
     */
    final def reduceRight[B >: A](op: (A, B) => B): B = reverse.reduceLeft(function.flip(op))

    /**
     * Returns the prefix sum of this vector. (a.k.a. scanl)
     *
     * @return  <code>[z, op(z, this(start)), op(op(z, this(start)), this(start + 1)),...]</code>.
     */
    def folderLeft[B](z: B)(op: (B, A) => B): Vector[B] = FolderLeft(this, z, op)

    /**
     * @return  <code>reverse.folderLeft(z)(function.flip(op))</code>.
     */
    def folderRight[B](z: B)(op: (A, B) => B): Vector[B] = reverse.folderLeft(z)(function.flip(op))

    /**
     * @return  <code>tail.folderLeft(head)</code>.
     */
    def reducerLeft[B >: A](op: (B, A) => B): Vector[B] = tail.folderLeft[B](head)(op)

    /**
     * @return  <code>reverse.reducerLeft(function.flip(op))</code>.
     */
    def reducerRight[B >: A](op: (A, B) => B): Vector[B] = reverse.reducerLeft(function.flip(op))


// sort

    /**
     * @return  <code>sortBy(c)</code>.
     */
    final def sort(implicit c: Compare[A]): Vector[A] = sortBy(c)

    /**
     * Sort this vector according to the comparison function <code>lt</code>.
     * Note this vector is mutated.
     *
     * @param   lt  strict weak ordering
     * @return  this vector sorted according to <code>lt</code>.
     */
    def sortBy(lt: compare.Func[A]): Vector[A] = Sort(this, lt)

    /**
     * @return  <code>stableSortBy(c)</code>.
     */
    final def stableSort(implicit c: Compare[A]): Vector[A] = stableSortBy(c)

    /**
     * Stable sort this vector according to the comparison function <code>lt</code>.
     * Note this vector is mutated.
     *
     * @param   lt  strict weak ordering
     * @return  this vector sorted according to <code>lt</code>.
     */
    def stableSortBy(lt: compare.Func[A]): Vector[A] = StableSort(this, lt)


// concatenation

    /**
     * @return  <code>vector.fromRandomAccessSeq(randomAccessSeq.projection ++ that)</code>
     */
    def append(that: Vector[A]): Vector[A] = Append(this, that)


// permutation

    /**
     * Reorders using "0-to-size" index mapping <code>f</code>.
     */
    def permutation(f: Int => Int): Vector[A] = Permutation(this, f)

    /**
     * Returns a non-writable circular vector from this vector.
     *
     * @return  <code>[this(start),...,this(end - 1),this(start),...,this(end - 1),...n times...]</code>
     */
    def cycle(n: Int): Vector[A] = Cycle(this, n)

    /**
     * Turns this vector into "0-to-size" indexing vector.
     */
    def nth: Vector[A] = _nth
    private lazy val _nth: Vector[A] = Nth(this)

    /**
     * Reverses order of elements.
     */
    def reverse: Vector[A] = Reverse(this)

    /**
     * Returns steps with specified stride <code>n</code>.
     *
     * @return  <code>[this(start), this(start + n), this(start + 2*n), ...]</code>.
     */
    def step(n: Int): Vector[A] = Step(this, n)

    /**
     * @return  <code>this(i, end) ++ this(start, i)</code>.
     */
    def rotate(i: Int): Vector[A] = this(start + i, end) ++ this(start, start + i)


// zip

    /**
     * Returns a vector formed from this vector and the specified vector
     * <code>that</code> by associating each element of the former with
     * the element at the same position in the latter.
     *  If one of the two vectors is longer than the other, its remaining elements are ignored.
     *
     * @return  <code>[(this(start), that(that.start)), (this(start + 1), that(that.start + 1)), (this(start + 2), that(that.start + 2)), ...]</code>.
     */
    def zip[B](that: Vector[B]): Vector[(A, B)] = Zip(this, that)


// attributes

    /**
     * Converts to a strict collection.
     *
     * @return  non-writable vector.
     */
    def force: Vector[A] = Force(this)

    /**
     * Returns a vector whose elements are lazy.
     */
    def memoize: Vector[A] = Memoize(this)

    /**
     * Creates a vector whose <code>isDefinedAt(i)</code> returns true
     * iif <code>start <= i && i < end</code>.
     */
    def bounds: Vector[A] = Bounds(this)

    /**
     * @return  a read only alias of this vector.
     */
    def readOnly: Vector[A] = ReadOnly(this)

    /**
     * @return  an alias of this vector.
     */
    def identity: Vector[A] = Identity(this)

    /**
     * @return  an alias of this vector, but any override is turned off.
     */
    final def seal: Vector[A] = Seal(this)

    /**
     * Returns synchronized one.
     */
    final def synchronize: Vector[A] = Synchronize(this)


// mixin

    /**
     * Runtime mixin; <code>mx</code> is applied to every vector-to-vector method.
     */
    def mixin(mx: Mixin): Vector[A] = NewMixin(this, mx)

    /**
     * Reverts <code>mixin</code>.
     */
    def unmixin: Vector[A] = this


// copy

    /**
     * Copies all the elements into another.
     *
     * @pre     <code>size == that.size</code>.
     * @pre     <code>that</code> is writable.
     * @return  this vector.
     */
    def copyTo[B >: A](that: Vector[B]): Vector[A] = CopyTo(this, that)

    /**
     * Returns a shallow copy of this vector. (The elements themselves are not copied.)
     *
     * @return  a writable clone of this vector.
     */
    override def clone: Vector[A] = vector.fromArray(toArray)

    /**
     * @return  <code>writer(start)</code>
     */
    final def writer: Writer[A] = Writer(this)

    /**
     * @return  <code>new Writer(this, i)</code>
     */
    final def writer(i: Int): Writer[A] = Writer(this, i)


// parallel support

    /**
     * @return  <code>parallel(defaultGrainSize)</code>
     */
    final def parallel: Vector[A] = parallel(defaultGrainSize)

    /**
     * Requests a vector to perform parallel methods.
     */
    def parallel(grainSize: Int): Vector[A] = Parallel(this, grainSize)

    /**
     * Specifies the grain size, which is used to divide this vector in parallel methods.
     */
    def grainSize: Int = size

    /**
     * Specifies the default grain size.
     */
    def defaultGrainSize: Int = DefaultGrainSize(this)

    /**
     * Waits for parallel element calculations over.
     */
    final def join: Unit = foreach{ e => () }

    /**
     * @return  <code>divide(grainSize).parallel(1)</code>.
     */
    final def parallelRegions(grainSize: Int): Vector[Vector[A]] = divide(grainSize).parallel(1)


// associative folding

    /**
     * @pre     <code>op</code> is associative.
     * @return  <code>foldLeft(z)(op)</code>
     */
    def fold(z: A)(op: (A, A) => A): A = foldLeft(z)(op)

    /**
     * @pre     <code>op</code> is associative.
     * @return  <code>folderLeft(z)(op)</code>
     */
    def folder(z: A)(op: (A, A) => A): Vector[A] = folderLeft(z)(op)

    /**
     * @pre     <code>op</code> is associative.
     * @return  <code>reduceLeft(op)</code>
     */
    def reduce(op: (A, A) => A): A = reduceLeft(op)

    /**
     * @pre     <code>op</code> is associative.
     * @return  <code>reducerLeft(op)</code>
     */
    def reducer(op: (A, A) => A): Vector[A] = reducerLeft(op)


// conversions

    override def toString: String = ToString(this)

    /**
     * @return  <code>vector.toArray(this)</code>.
     */
    final def toArray: Array[A] = vector.toArray(this)

    /**
     * @return  <code>vector.toIterable(this)</code>.
     */
    final def toIterable: Iterable[A] = vector.toIterable(this)

    /**
     * @return  <code>vector.toJclArrayList(this)</code>.
     */
    final def toJclArrayList: java.util.ArrayList[A] = vector.toJclArrayList(this)

    /**
     * @return  <code>vector.toIterator</code>.
     */
    final def toLinearAccessSeq: Seq[A] = vector.toLinearAccessSeq(this)

    /**
     * @return  <code>vector.toList(this)</code>.
     */
    final def toList: List[A] = vector.toList(this)

    /**
     * @return  <code>vector.toRandomAccessSeq(this)</code>.
     */
    final def toRandomAccessSeq: scala.collection.mutable.Vector[A] = vector.toRandomAccessSeq(this)

    /**
     * @return  <code>vector.toStream(this)</code>.
     */
    final def toStream: Stream[A] = vector.toStream(this)


// trivials

    @returnThis
    final def asVector: Vector[A] = this

    /**
     * @return  <code>start == end</code>.
     */
    final def isEmpty: Boolean = start == end

    /**
     * @return  <code>end - start</code>.
     */
    final def size: Int = end - start

    /**
     * @return  <code>that</code>.
     */
    final def always[B](that: Vector[B]): Vector[B] = that

    /**
     * @return  <code>f(this); this</code>.
     */
    final def sideEffect(f: Vector[A] => Unit): Vector[A] = { f(this); this }

    /**
     * @return  <code>vector.range(start, end)</code>.
     */
    final def indices: Vector[Int] = vector.range(start, end)

    /**
     * Returns a set entry as pair, which is useful for <code>peg.switch</code>.
     *
     * @return  <code>(this, p)</code>
     */
    final def -->(p: Peg[A]): (Vector[A], Peg[A]) = (this, p)


// aliases

    @aliasOf("size")
    final def length: Int = size

    @aliasOf("toIterable.elements")
    final def elements: Iterator[A] = toIterable.elements

    @aliasOf("region")
    final def apply(_start: Int, _end: Int): Vector[A] = region(_start, _end)

    @aliasOf("append")
    final def ++(that: Vector[A]): Vector[A] = append(that)

    @aliasOf("foldLeft")
    final def /:[B](z: B)(op: (B, A) => B): B = foldLeft(z)(op)

    @aliasOf("foldRight")
    final def :\[B](z: B)(op: (A, B) => B): B = foldRight(z)(op)


// implementation helpers

    private def throwIfEmpty(method: String) = ThrowIf.empty(this, method)
}


object Vector extends Compatibles {


// pattern matching

    @aliasOf("fromValues")
    def apply[A](from: A*): Vector[A] = fromValues(from: _*)

    def unapplySeq[A](from: Vector[A]): Option[Seq[A]] = Some(toRandomAccessSeq(from))


// operators

    sealed class MadaVectorIterableVector[A](_1: Iterable[Vector[A]]) {
        def flatten = vector.flatten(_1)
        def unsplit(_2: Vector[A]) = vector.unsplit(_1)(_2)
    }
    implicit def madaVectorIterableVector[A](_1: Iterable[Vector[A]]): MadaVectorIterableVector[A] = new MadaVectorIterableVector(_1)

    sealed class MadaVectorEither[A, B](_1: Vector[Either[A, B]]) {
        def lefts = vector.lefts(_1)
        def rights = vector.rights(_1)
    }
    implicit def madaVectorEither[A, B](_1: Vector[Either[A, B]]): MadaVectorEither[A, B] = new MadaVectorEither(_1)

    sealed class MadaVectorVector[A](_1: Vector[Vector[A]]) {
        def undivide = vector.undivide(_1)
    }
    implicit def madaVectorVector[A](_1: Vector[Vector[A]]): MadaVectorVector[A] = new MadaVectorVector(_1)

    sealed class MadaVectorPair[A, B](_1: Vector[(A, B)]) {
        def unzip = vector.unzip(_1)
    }
    implicit def madaVectorPair[A, B](_1: Vector[(A, B)]): MadaVectorPair[A, B] = new MadaVectorPair(_1)

    sealed class MadaVectorByName[A](_1: => Vector[A]) {
        def `lazy` = vector.`lazy`(_1)
    }
    implicit def madaVectorByName[A](_1: => Vector[A]): MadaVectorByName[A] = new MadaVectorByName(_1)

    sealed class MadaVectorChar(_1: Vector[Char]) {
        def lowerCase = vector.lowerCase(_1)
        def upperCase = vector.upperCase(_1)
        def stringize = vector.stringize(_1)
    }
    implicit def madaVectorChar(_1: Vector[Char]): MadaVectorChar = new MadaVectorChar(_1)


// eligibles

    // Hmm, Ordering should have taken [-A]?
    implicit def forOrdering[A](implicit c: Ordering[A]): Ordering[Vector[A]] = {
        compare.toOrdering(forCompare(compare.fromOrdering(c)))
    }

    /*implicit*/ def forOrdering_[A](implicit c: compare.GetOrdered[A]): Ordering[Vector[A]] = {
        compare.toOrdering(forCompare(compare.fromGetOrdered(c)))
    }

    // For unambiguous overload resolution, `implicit` is facing the alternative
    // of `madaVectorToOrdered` or ...
    /*implicit*/ def forCompare[A](implicit c: Compare[A]): Compare[Vector[A]] = new Compare[Vector[A]] {
        override def apply(v: Vector[A], w: Vector[A]) = {
            stl.LexicographicalCompare(v, v.start, v.end, w, w.start, w.end, c)
        }
        override def threeWay(v: Vector[A], w: Vector[A]) = {
            stl.LexicographicalCompare3way(v, v.start, v.end, w, w.start, w.end, c)
        }
    }

}
