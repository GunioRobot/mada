
package mada


// Expr

object Expr {
    def apply[A](e: => A) = new ConstantExpr(e).expr
}

trait Expr[A] {
    def _eval: A = { throw new ErrorUnknownContext(this, DefaultContext) }
    def _eval[B](c: Context[A, B]): B = c(this)

    final def eval: A = eval(DefaultContext)
    final def eval[B](c: Context[A, B]): B = _eval(c)

    case object DefaultContext extends Context[A, A] {
        override def apply(x: Expr[A]) = x._eval
    }

    final def expr = this
    final def toLazy[A] = LazyExpr(this).expr
}


// Context

trait Context[A, B] extends (Expr[A] => B)


// predefined expressions

class ConstantExpr[A](e: => A) extends Expr[A] {
    override def _eval = e
}

case class LazyExpr[A](_1: Expr[A]) extends Expr[A] {
    private val c = new LazyContext(_1.DefaultContext) // DefaultContext only
    override def _eval = _1.eval(c)
}

case class ForwardExpr[To](_1: Expr[To]) extends Expr[To] {
    override def _eval[B](c: Context[To, B]) = _1.eval(c)
}

object ExprConversions extends ExprConversions

trait ExprConversions {
    implicit def toMadaExpr[A](e: => A) = Expr(e)
}


// predefined contexts

case class LazyContext[A, B](c: Context[A, B]) extends Context[A, B] {
    private val e = new LazyRef[B]
    override def apply(x: Expr[A]) = e := c(x)
}


// exceptions

case class ErrorUnknownExpr[A, B](expr: Expr[A], context: Context[A, B]) extends UnsupportedOperationException
case class ErrorUnknownContext[A, B](expr: Expr[A], context: Context[A, B]) extends UnsupportedOperationException
