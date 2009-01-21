

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object StringVector {
    def apply(u: String): Vector[Char] = new StringVector(u)
}

class StringVector(val string: String) extends Vector[Char] with NotWritable[Char] {
    override def size = string.length
    override def apply(i: Int) = string.charAt(i)

    override def force = this
}


object Stringize {
    def apply(v: Vector[Char]): String = v match {
        case v: StringVector => v.string // stringize-stringVector fusion
        case _ => {
            val sb = new StringBuilder(v.size)
            for (e <- v) {
                sb.append(e)
            }
            sb.toString
        }
    }
}
