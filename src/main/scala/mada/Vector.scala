

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility types and methods operating on type <code>Vector</code>.
 */
object Vector {
    import vec._

    /**
     * @return  <code>Math.MIN_INT</code>, which is the reserved index by <code>mada.Vector</code>.
     */
    final val NULL_INDEX = Math.MIN_INT

    /**
     * Thrown if vector is not readable.
     */
    class NotReadableException[A](val vector: Vector[A]) extends RuntimeException

    /**
     * Thrown if vector is not writable.
     */
    class NotWritableException[A](val vector: Vector[A]) extends RuntimeException

    /**
     * Concatenate all argument sequences into a single vector.
     *
     * @param   vs the given argument sequences
     * @return  the projection vector created from the concatenated arguments
     */
    def concat[A](vs: Vector[A]*): Vector[A] = Concat(vs: _*)

    /**
     * @return  an empty vector.
     */
    def empty[A]: Vector[A] = Empty.apply[A]

    /**
     * Create a vector that is the concantenation of all vectors
     * returned by a given vector of vectors.
     *
     * @param   vv The vector which returns on each call to next
     *             a new vector whose elements are to be concatenated to the result.
     * @return  the newly created writable vector (not a projection into <code>vv</code>).
     */
    def flatten[A](vv: Vector[Vector[A]]): Vector[A] = Flatten(vv)

    /**
     * @return  <code>v.filter(_.isLeft).map(_.left.get)</code>.
     */
    def lefts[A, B](v: Vector[Either[A, B]]): Vector[A] = Lefts(v)

    /**
     * @return  <code>v.filter(_.isRight).map(_.right.get)</code>.
     */
    def rights[A, B](v: Vector[Either[A, B]]): Vector[B] = Rights(v)

    /**
     * Converts to lower case letters.
     */
    def lowerCase(v: Vector[Char]): Vector[Char] = LowerCase(v)

    /**
     * Converts to upper case letters.
     */
    def upperCase(v: Vector[Char]): Vector[Char] = UpperCase(v)

    /**
     * Creates a vector containing of successive integers.
     *
     * @param   i the value of the first element of the vector
     * @param   j the value of the last element of the vector plus 1
     * @return  the sorted vector of all integers in range [i, j).
     */
    def range(i: Int, j: Int): Vector[Int] = Range(i, j)

    /**
     * @param   e the element
     * @return  the writable vector with one single element.
     */
    def single[A](e: A): Vector[A] = Single(e)

    /**
     * Converts characters to <code>String</code>.
     */
    def stringize(v: Vector[Char]): String = Stringize(v)

    /**
     * Reverts <code>Vector[A]#divide</code>.
     *
     * @pre     each vector is the same size except for the last one.
     */
    def undivide[A](vv: Vector[Vector[A]]): Vector[A] = Undivide(vv)

    /**
     * @return  flatten(vv.map({ v => sep.append(v) }))
     */
    def untokenize[A](vv: Vector[Vector[A]], sep: Vector[A]): Vector[A] = Untokenize(vv, sep)

    /**
     * Reverts <code>Vector[A]#zip</code>.
     */
    def unzip[A, B](v: Vector[(A, B)]): (Vector[A], Vector[B]) = Unzip(v)

    /**
     * Creates a <code>lazy</code> vector.
     */
    def `lazy`[A](v: => Vector[A]) = Lazy(v)

    /**
     * Creates a <code>synchronized</code> vector.
     */
    def `synchronized`[A](v: Vector[A]) = Synchronized(v)

    /**
     * Alias of <code>vec.Compatibles</code>
     */
    val Compatibles = vec.Compatibles

    /**
     * Converts an <code>Array</code> to vector.
     */
    def arrayVector[A](from: Array[A]): Vector[A] = ArrayVector(from)

    /**
     * Converts a <code>Cell</code> to vector.
     */
    def cellVector[A](from: Cell[A]): Vector[A] = CellVector(from)

    /**
     * Converts a <code>java.lang.CharSequence</code> to vector.
     */
    def jclCharSequenceVector(from: java.lang.CharSequence): Vector[Char] = jcl.CharSequenceVector(from)

    /**
     * Converts a <code>java.util.List</code> to vector.
     */
    def jclListVector[A](from: java.util.List[A]): Vector[A] = jcl.ListVector(from)

    /**
     * Converts an <code>Option</code> to vector.
     */
    def optionVector[A](from: Option[A]): Vector[A] = OptionVector(from)

    /**
     * Converts a <code>Product</code> to vector.
     */
    def productVector(from: Product): Vector[Any] = ProductVector(from)

    /**
     * Converts a <code>RandomAccessSeq</code> to vector.
     */
    def randomAccessSeqVector[A](from: RandomAccessSeq[A]): Vector[A] = RandomAccessSeqVector(from)

    /**
     * Converts a <code>String</code> to vector.
     */
    def stringVector(from: String): Vector[Char] = StringVector(from)

    /**
     * Converts a vector to <code>java.lang.CharSequence</code>.
     */
    def jclCharSequence(from: Vector[Char]): java.lang.CharSequence = jcl.VectorCharSequence(from)

    /**
     * Converts an <code>Iterator</code> to vector.
     */
    def fromIterator[A](from: Iterator[A]): Vector[A] = FromIterator(from)

    /**
     * Converts a <code>java.util.Iterator</code> to vector.
     */
    def fromJclIterator[A](from: java.util.Iterator[A]): Vector[A] = jcl.FromIterator(from)

    /**
     * Converts values to vector.
     */
    def fromValues[A](from: A*): Vector[A] = FromValues(from: _*)

    /**
     * Converts a <code>Func</code> to <code>Func3</code>.
     */
    def triplify[A, B](f: Func[A, B]): Func3[A, B] = Triplify(f)

    /**
     * Converts a <code>Func3</code> to <code>Func</code>.
     */
    def untriplify[A, B](f: Func3[A, B]): Func[A, B] = Untriplify(f)

    /**
     * Alias of <code>(Vector[A], Int, Int)</code>
     */
    type Triple[A] = (Vector[A], Int, Int)

    /**
     * Alias of <code>Vector[A] => B</code>
     */
    type Func[A, B] = Vector[A] => B

    /**
     * Alias of <code>(Vector[A], Int, Int) => B</code>
     */
    type Func3[A, B] = (Vector[A], Int, Int) => B

    /**
     * Alias of <code>vec.Adapter</code>
     */
    val Adapter = vec.Adapter

    /**
     * Alias of <code>vec.Adapter</code>
     */
    type Adapter[Z, A] = vec.Adapter[Z, A]

    /**
     * Alias of <code>vec.NotWritable</code>
     */
    type NotWritable[A] = vec.NotWritable[A]

    /**
     * Alias of <code>vec.Region</code>
     */
    val Region = vec.Region

    /**
     * Alias of <code>vec.Region</code>
     */
    type Region[A] = vec.Region[A]

    /**
     * Alias of <code>vec.IntFileVector</code>
     */
    type IntFileVector = vec.IntFileVector

    /**
     * Alias of <code>vec.LongFileVector</code>
     */
    type LongFileVector = vec.LongFileVector

    /**
     * Alias of <code>vec.Writer</code>
     */
    type Writer[A] = vec.Writer[A]
}


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
trait Vector[A] extends PartialFunction[Int, A] with HashCode.OfRef {
    import vec._


// kernel interface

    /**
     * @return  start index of this vector, which is NOT guaranteed to be <code>0</code>.
     */
    def start: Int

    /**
     * @return  end index of this vector, which is NOT guaranteed to be <code>this.size</code>.
     */
    def end: Int

    /**
     * @param   i index of element to return.
     * @pre     this vector is readable.
     * @pre     <code>this.isDefinedAt(i)</code>.
     * @return  the element at the specified position in this vector.
     * @throws  <code>Vector.NotReadableException</code> if not overridden.
     */
    override def apply(i: Int): A = throw new Vector.NotReadableException(this)

    /**
     * Replaces the element at the specified position in this vector with
     * the specified element.
     *
     * @param   i index of element to replace.
     * @param   e element to be stored at the specified position.
     * @pre     this vector is writable.
     * @pre     <code>this.isDefinedAt(i)</code>.
     * @throws  <code>Vector.NotWritableException</code> if not overridden.
     */
    def update(i: Int, e: A): Unit = throw new Vector.NotWritableException(this)

    /**
     * @return  <code>(this.start <= x) && (x < this.end)</code>, possibly overridden in subclasses.
     */
    override def isDefinedAt(i: Int): Boolean = IsDefinedAt(this, i)


// value semantics

    /**
     * Compares each element using predicate <code>p</code>.
     * Returns <code>false</code> if <code>this.size != that.size</code>.
     */
    def equalsWith[B](that: Vector[B])(p: (A, B) => Boolean): Boolean = EqualsWith(this, that, p)

    /**
     * Type-safe alias of <code>Any.equals</code>
     */
    final def equalsTo[B](that: Vector[B]): Boolean = EqualsTo(this, that)

    /**
     * @return <code>this.equalsWith(that)(Functions.equal)</code>.
     */
    override def equals(that: Any): Boolean = Equals(this, that)

    override def hashCode: Int = HashCode_(this)
    override def hashCodeOfRef: Int = super.hashCode


// regions

    /**
     * @return  <code>Region(this, _start, _end)</code>.
     * @see     apply as alias.
     */
    def region(_start: Int, _end: Int): Vector[A] = Region(this, _start, _end)

    /**
     * @pre     <code>!this.isEmpty</code>
     * @return  the vector without its last element.
     */
    final def init: Vector[A] = Init(this)

    /**
     * @return  <code>this(this.start + n, this.start + m)</code>
     */
    final def window(n: Int, m: Int): Vector[A] = Window(this, n, m)

    /**
     * @return <code>Vector.randomAccessSeqVector(this.randomAccessSeq.projection.drop(n))</code>
     */
    final def drop(n: Int): Vector[A] = Drop(this, n)

    /**
     * @return <code>Vector.randomAccessSeqVector(this.randomAccessSeq.projection.take(n))</code>
     */
    final def take(n: Int): Vector[A] = Take(this, n)

    /**
     * Returns the longest suffix of this vector whose first element
     * does not satisfy the predicate <code>p</code>.
     *
     * @param   p the test predicate.
     * @return  the longest suffix of the vector whose first element
     *          does not satisfy the predicate <code>p</code>.
     */
    final def dropWhile(p: A => Boolean): Vector[A] = DropWhile(this, p)

    /**
     * Returns the longest prefix of this vector whose elements satisfy
     * the predicate <code>p</code>.
     *
     * @param   p the test predicate.
     * @return  the longest prefix of this vector whose elements satisfy
     *          the predicate <code>p</code>.
     */
    final def takeWhile(p: A => Boolean): Vector[A] = TakeWhile(this, p)

    /**
     * @return  <code>this(this.start + i, this.end + j)</code>.
     */
    final def offset(i: Int, j: Int): Vector[A] = Offset(this, i, j)

    /**
     * @return  <code>this.slice(n, this.end)</code>.
     */
    final def slice(n: Int): Vector[A] = Slice(this, n)

    /**
     * @return <code>Vector.randomAccessSeqVector(this.randomAccessSeq.projection.slice(n, m))</code>
     */
    final def slice(n: Int, m: Int): Vector[A] = Slice(this, n, m)


// division

    /**
     * Divides this vector into vector of Regions.
     * Each vector size is <code>n</code> except for the last one.
     *
     * @param   n divisor
     * @return  <code>[this(this.start, n), this(this.start + n, 2*n), this(this.start + 2*n, 3*n),...]</code>.
     * @see     Vector.undivide
     */
    final def divide(n: Int): Vector[Vector[A]] = Divide(this, n)

    /**
     * @return  <code>(this(this.start, m), this(m, this.end))</code>, where <code>val m = Math.min(i, this.end)</code>.
     */
    final def splitAt(i: Int): (Vector[A], Vector[A]) = SplitAt(this, i)

    /**
     * Returns the longest prefix of the vector whose elements all satisfy
     * the given predicate, and the rest of the vector.
     *
     * @param   p the test predicate
     * @return  a pair consisting of the longest prefix of the vector whose
     *          elements all satisfy <code>p</code>, and the rest of the vector.
     */
    final def span(p: A => Boolean): (Vector[A], Vector[A]) = Span(this, p)

    /**
     * @return  <code>this.span(Functions.not(p))</code>.
     */
    final def break(p: A => Boolean): (Vector[A], Vector[A]) = Break(this, p)


// first and last

    /**
     * Alias of <code>this.randomAccessSeq.first</code>
     */
    final def first: A = First(this)

    /**
     * Alias of <code>this.randomAccessSeq.last</code>
     */
    final def last: A = Last(this)

    /**
     * Alias of <code>this.randomAccessSeq.firstOption</code>
     */
    final def firstOption: Option[A] = FirstOption(this)

    /**
     * Alias of <code>this.randomAccessSeq.lastOption</code>
     */
    final def lastOption: Option[A] = LastOption(this)


// as list

    /**
     * Alias of <code>this.first</code>
     */
    final def head: A = Head(this)

    /**
     * @pre     <code>!this.isEmpty</code>
     * @return  the vector without its first element.
     */
    final def tail: Vector[A] = Tail(this)

    /**
     * Alias of <code>this.isEmpty</code>
     */
    final def isNil: Boolean = IsNil(this)


// filter

    /**
     * Returns all the elements of this vector that satisfy the
     * predicate <code>p</code>. The order of the elements is preserved.
     *
     * @param   p the predicate used to filter the vector.
     * @return  the non-writable elements of this vector satisfying <code>p</code>.
     */
    def filter(p: A => Boolean): Vector[A] = Filter(this, p)

    /**
     * Returns all the elements of this vector that satisfy the
     * predicate <code>p</code>. The order of the elements is preserved.
     * Unlike <code>this.filter</code>, this requires no intermediate buffer,
     * but the state of this vector is unpredictable after calling this.
     *
     * @param   p the predicate used to filter the vector.
     * @return  the elements of this vector satisfying <code>p</code>.
     */
    def mutatingFilter(p: A => Boolean): Vector[A] = MutatingFilter(this, p)

    /**
     * @return  <code>this.filter(Functions.not(p))</code>.
     */
    final def remove(p: A => Boolean): Vector[A] = Remove(this, p)

    /**
     * @return  <code>this.mutatingFilter(Functions.not(p))</code>.
     */
    final def mutatingRemove(p: A => Boolean): Vector[A] = MutatingRemove(this, p)

    /**
     * @return  <code>(this.filter(p), this.remove(p))</code>.
     */
    final def partition(p: A => Boolean): (Vector[A], Vector[A]) = Partition(this, p)


// map

    /**
     * @return  <code>Vector.randomAccessSeqVector(this.randomAccessSeq.projection.map(f))</code>
     */
    def map[B](f: A => B): Vector[B] = Map(this, f)

    /**
     * @return  <code>Vector.flatten(this.map(f))</code>.
     */
    def flatMap[B](f: A => Vector[B]): Vector[B] = FlatMap(this, f)

    /**
     * Casts element to type <code>B</code>.
     */
    final def asVectorOf[B]: Vector[B] = AsVectorOf[A, B](this)


// foreach

    /**
     * Equivalent to <code>this.foreach</code>, but loop is breakable by <code>f</code> returning <code>false</code>.
     */
    def loop[F <: (A => Boolean)](i: Int, j: Int, f: F): F = Loop(this, i, j, f)
    /**
     * Alias of <code>this.elements.foreach</code>.
     */
    final def foreach(f: A => Unit): Unit = Foreach(this, f)

    /**
     * Apply a function <code>f</code> to all elements of this vector.
     * <code>f</code> may be called in any order, and
     * there is no guarantee <code>f</code> calls are single-threaded.
     *
     * @param   f a function that is applied to every element.
     */
    def pareach(f: A => Unit): Unit = Pareach(this, f)


// search

    /**
     * Alias of <code>this.elements.find</code>.
     */
    final def find(p: A => Boolean): Option[A] = Find(this, p)

    /**
     * Find and return the element of this vector satisfying a
     * predicate, if any. No guarantee the element is the first one.
     *
     * @param   p the predicate
     * @return  the element in the iterable object satisfying
     *          <code>p</code>, or <code>None</code> if none exists.
     */
    def seek(p: A => Boolean): Option[A] = Seek(this, p)

    /**
     * Alias of <code>this.randomAccessSeq.count</code>
     */
    def count(p: A => Boolean): Int = Count(this, p)

    /**
     * Alias of <code>this.elements.contains</code>.
     */
    final def contains(e: Any): Boolean = Contains(this, e)

    /**
     * Alias of <code>this.elements.forall</code>.
     */
    final def forall(p: A => Boolean): Boolean = Forall(this, p)

    /**
     * Alias of <code>this.elements.exists</code>.
     */
    final def exists(p: A => Boolean): Boolean = Exists(this, p)


// folding

    /**
     * @pre     <code>op</code> is associative.
     * @return  <code>this.foldLeft(z)(op)</code>
     */
    def fold(z: A)(op: (A, A) => A): A = Fold(this, z, op)

    /**
     * @pre     <code>op</code> is associative.
     * @return  <code>this.folderLeft(op)</code>
     */
    def folder(z: A)(op: (A, A) => A): Vector[A] = Folder(this, z, op)

    /**
     * @pre     <code>op</code> is associative.
     * @return  <code>this.reduceLeft(op)</code>
     */
    def reduce(op: (A, A) => A): A = Reduce(this, op)

    /**
     * @pre     <code>op</code> is associative.
     * @return  <code>this.reduceerLeft(op)</code>
     */
    def reducer(op: (A, A) => A): Vector[A] = Reducer(this, op)

    /**
     * Alias of <code>this.elements.foldLeft</code>.
     */
    final def foldLeft[B](z: B)(op: (B, A) => B): B = FoldLeft(this, z, op)

    /**
     * Alias of <code>this.elements.foldRight</code>.
     */
    final def foldRight[B](z: B)(op: (A, B) => B): B = FoldRight(this, z, op)

    /**
     * Alias of <code>this.elements.reduceLeft</code>.
     */
    final def reduceLeft[B >: A](op: (B, A) => B): B = ReduceLeft(this, op)

    /**
     * Alias of <code>this.elements.reduceRight</code>.
     */
    final def reduceRight[B >: A](op: (A, B) => B): B = ReduceRight(this, op)

    /**
     * Returns the prefix sum of this vector.
     *
     * @return  <code>[z, op(z, this(this.start)), op(op(z, this(this.start)), this(this.start + 1)),...]</code>
     */
    final def folderLeft[B](z: B)(op: (B, A) => B): Vector[B] = FolderLeft(this, z, op)

    /**
     * @return  <code>this.reverse.folderLeft(z)(Functions.flip(op))</code>
     */
    final def folderRight[B](z: B)(op: (A, B) => B): Vector[B] = FolderRight(this, z, op)

    /**
     * @pre     <code>!isEmpty</code>
     * @return  <code>this.tail.folderLeft(this.head)</code>
     */
    final def reducerLeft[B >: A](op: (B, A) => B): Vector[B] = ReducerLeft(this, op)

    /**
     * @pre     <code>!isEmpty</code>
     * @return  <code>this.reverse.reducerLeft(Functions.flip(op))</code>
     */
    final def reducerRight[B >: A](op: (A, B) => B): Vector[B] = ReducerRight(this, op)


// sort

    /**
     * Sort this vector according to the comparison function <code>lt</code>.
     * Note this vector is mutated.
     *
     * @param   lt the "less-than" comparison function
     * @return  this vector sorted according to the comparison function <code>lt</code>.
     */
    def sortWith(lt: (A, A) => Boolean): Vector[A] = SortWith(this, lt)

    /**
     * @return  <code>this.sortWith(Functions.less(c))</code>.
     */
    final def sort(implicit c: A => Ordered[A]): Vector[A] = Sort(this, c)


// concatenation

    /**
     * @return  <code>Vector.randomAccessSeqVector(this.randomAccessSeq.projection ++ that)</code>
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
     * @return  <code>[this(this.start),...,this(this.end - 1),this(this.start),...,this(this.end - 1),...n times...]</code>
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
     * @return  <code>this(i, this.end) ++ this(this.start, i)</code>.
     */
    final def rotate(i: Int): Vector[A] = Rotate(this, i)

    /**
     * Returns steps with specified stride <code>n</code>.
     *
     * @return  <code>[this(this.start), this(this.start + n), this(this.start + 2*n), ...]</code>.
     */
    def step(n: Int): Vector[A] = Step(this, n)

    /**
     * Returns a vector formed from this vector and the specified vector
     * <code>that</code> by associating each element of the former with
     * the element at the same position in the latter.
     *  If one of the two vectors is longer than the other, its remaining elements are ignored.
     *
     * @return  <code>[(this(this.start), that(that.start)), (this(this.start + 1), that(that.start + 1)), (this(this.start + 2), that(that.start + 2)), ...]</code>.
     */
    def zip[B](that: Vector[B]): Vector[(A, B)] = Zip(this, that)


// modifying attributes

    /**
     * Converts to a strict collection.
     *
     * @return  non-writable vector.
     */
    def force: Vector[A] = Force(this)

    /**
     * Returns a vector whose elements are lazy.
     */
    def lazyValues : Vector[A] = LazyValues(this)

    /**
     * Creates a vector whose <code>isDefinedAt(i)</code> returns true
     * iif <code>this.start <= i && i < this.end</code>.
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
    final def cut: Vector[A] = Cut(this)


// copy

    /**
     * Copies all the elements into another.
     *
     * @pre     <code>this.size == that.size</code>.
     * @pre     <code>that</code> is writable.
     * @return  this vector.
     */
    def copyTo[B >: A](that: Vector[B]): Vector[A] = CopyTo(this, that)

    /**
     * Returns a shallow copy of this vector. (The elements themselves are not copied.)
     *
     * @return  a writable clone of this vector.
     */
    override def clone: Vector[A] = Clone(this)

    /**
     * @return  <code>this.writer(this.start)</code>
     */
    final def writer: Writer[A] = Writer(this)

    /**
     * @return  <code>new Writer(this, i)</code>
     */
    final def writer(i: Int): Writer[A] = Writer(this, i)


// parallel support

    /**
     * Is this vector methods possibly performing in parallel?
     */
    def isParallel: Boolean = false

    /**
     * @return  <code>this.parallel(this.defaultGrainSize)</code>
     */
    final def parallel: Vector[A] = Parallel(this)

    /**
     * Requests a vector to perform parallel methods.
     */
    final def parallel(grainSize: Int): Vector[A] = Parallel(this, grainSize)

    /**
     * Waits for parallel element calculations over.
     */
    final def join: Unit = Join(this)

    /**
     * Reverts <code>this.parallel</code>.
     */
    def unparallel: Vector[A] = this

    /**
     * Specifies the grain size, which is used to divide this vector in parallel methods.
     */
    def grainSize: Int = size

    /**
     * Specifies the default grain size.
     */
    def defaultGrainSize: Int = DefaultGrainSize(this)


// conversions

    /**
     * @return  an <code>Iterator</code> of this vector.
     */
    final def iterator: Iterator[A] = VectorIterator(this)

    /**
     * @return  a <code>java.util.Iterator</code> of this vector.
     */
    final def jclListIterator: java.util.ListIterator[A] = jcl.VectorListIterator(this)


    final def linearAccessSeq: Seq[A] = LinearAccessSeq(this)

    /**
     * @return  a <code>RandomAccessSeq.Mutable</code> projection of this vector.
     */
    final def randomAccessSeq: RandomAccessSeq.Mutable[A] = VectorRandomAccessSeq(this)

    /**
     * @return  a <code>Stream</code> of this vector.
     */
    final def stream: Stream[A] = VectorStream(this)

    /**
     * @return  a new <code>Array</code> which enumerates all elements of this vector.
     */
    final def toArray: Array[A] = ToArray(this)

    /**
     * @return  a new <code>java.util.ArrayList</code> which enumerates all elements of this vector.
     */
    final def toJclArrayList: java.util.ArrayList[A] = jcl.ToArrayList(this)

    /**
     * @return  a <code>List</code> which enumerates all elements of this vector.
     */
    final def toList: List[A] = ToList(this)

    /**
     * @return  a string representation of this vector.
     */
    override def toString: String = ToString(this)


// trivials

    /**
     * @return  <code>this</code>.
     */
    final def vector: Vector[A] = this

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
     * @return  <code>this(this.start, this.start)</code>
     */
    final def clear: Vector[A] = this(start, start)

    /**
     * @return  <code>f(this); this</code>.
     */
    final def sideEffect(f: Vector[A] => Any): Vector[A] = { f(this); this }

    /**
     * @return  <code>Vector.range(this.start, this.end)</code>.
     */
    final def indices: Vector[Int] = Vector.range(start, end)

    /**
     * Returns a set entry as pair, which is useful for <code>Peg.switch</code>.
     *
     * @return  <code>(this, p)</code>
     */
    final def -->(p: Peg[A]): (Vector[A], Peg[A]) = (this, p)


// aliases

    /**
     * Alias of <code>this.size</code>
     */
    final def length: Int = size

    /**
     * Alias of <code>this.iterator</code>
     */
    final def elements: Iterator[A] = iterator

    /**
     * Alias of <code>this.region</code>
     */
    final def apply(_start: Int, _end: Int): Vector[A] = region(_start, _end)

    /**
     * Alias of <code>this.append</code>
     */
    final def ++(that: Vector[A]): Vector[A] = append(that)

    /**
     * Alias of <code>this.foldLeft</code>
     */
    final def /:[B](z: B)(op: (B, A) => B): B = foldLeft(z)(op)

    /**
     * Alias of <code>this.foldRight</code>
     */
    final def :\[B](z: B)(op: (A, B) => B): B = foldRight(z)(op)
}
