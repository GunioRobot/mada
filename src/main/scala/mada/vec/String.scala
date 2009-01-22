

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object StringVector {
    def apply(from: String): Vector[Char] = new StringVector(from)
}

class StringVector(val from: String) extends Vector[Char] with NotWritable[Char] {
    override def size = from.length
    override def apply(i: Int) = from.charAt(i)

    override def force = this
}


object Stringize {
    def apply(from: Vector[Char]): String = from match {
        case from: StringVector => from.from // conversion fusion
        case _ => {
            val sb = new StringBuilder(from.size)
            for (e <- from) {
                sb.append(e)
            }
            sb.toString
        }
    }
}
