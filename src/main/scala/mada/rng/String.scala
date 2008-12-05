
package mada.rng


import Foreach._


//  String <-> Expr[Rng[Char]]

object StringCompatible; trait StringCompatible {
    implicit def madaRng_String2ExprRng(from: String): Expr[Rng[Char]] = FromStringExpr(Expr(from)).expr
}


// toRng

object StringToRng extends StringToRng; trait StringToRng extends Predefs {
    class MadaRngStringToRng(_1: Expr[String]) {
        def toRng = FromStringExpr(_1).expr
    }
    implicit def toMadaRngStringToRng(_1: Expr[String]): MadaRngStringToRng = new MadaRngStringToRng(_1)
}

case class FromStringExpr(_1: Expr[String]) extends Expr[Rng[Char]] {
    override def _eval[U](c: Context[Rng[Char], U]): U = c match {
        case DefaultContext => _1 match {
            case StringizeExpr(x1) => x1.eval
            case _ => delegate.eval
        }
        case _ => delegate.eval(c)
    }

    private def delegate = IndexAccessRngExpr(new StringIndexAccess(_1.eval))
}

class StringIndexAccess(val base: String) extends IndexAccess[Char] {
    override def _get(i: Long) = base.charAt(i.toInt)
    override def _size = base.length
}


// stringize

object Stringize extends Stringize; trait Stringize extends Predefs {
    class MadaRngStringize(_1: Expr[Rng[Char]]) {
        def stringize = StringizeExpr(_1).expr
    }
    implicit def toMadaRngStringize(_1: Expr[Rng[Char]]): MadaRngStringize = new MadaRngStringize(_1)
}

case class StringizeExpr(_1: Expr[Rng[Char]]) extends Expr[String] {
    override def _eval = _1 match {
        case FromStringExpr(x1) => x1.eval
        case _ => {
            val sb = new StringBuilder
            _1.foreach(sb.append(_)).eval
            sb.toString
        }
    }
}
