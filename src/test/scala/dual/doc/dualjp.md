


# dual_jp

`mada.dual` �ɂ��Đ��������y�[�W�ł��B




## Metaprogramming in Scala �̃n�W�}��

* [MetaScala]
* [Michid's Weblog]
* [Scala�Ō^���x���v���O���~���O] @kmizu
* [Apocalisp]




## �p��̒�`

�����͉��l�p��ł���A�����ł͒ʂ��Ȃ��ꍇ������܂��B


### metatype

`dual.Any`��subtype��_metatype_�ƒ�`���܂��B

    package dual
    sealed abstract class Boolean extends dual.Any { ...

���ꂪmeta�Ȑ��E�̌^�ɂȂ�܂��B`dual.Any`���킴�킴�K�v�ȗ��R�͌�q�B


### metamethod

_metamethod_ �Ƃ́Ametatype�������Ƃ���Atype���邢��type-constructor�ł��B

abstract metamethod�̃J�^�`�͂����Ȃ�܂�:

    type not <: dual.Boolean // �����Ȃ�
    type equal[that <: dual.Boolean] <: dual.Boolean // �������

metamethod��override����ɂ͂������܂�:

    override type not = ...
    override type equal[that <: dual.Boolean] = ...

`override`�͖����Ă����\�ł��Bmetamethod�͍�`override`�ł��Ȃ����Ƃɒ��ӁB

metamethod�̌Ăяo���ɂ́A`#`���g���܂�:

    type foo[b <: dual.Boolean, c <: dual.Boolean] = b#equal[c]


�����̂Ȃ�metamethod�͂��΂���_metavalue_�ƌĂ΂�܂��B
�����ɂ́Ameta��`val`�Ƃ����̂͂���܂��񂪁A����������邽�߁A`lazy val`�Ɠ����Ӗ��ɂȂ�܂��B


### metadependent

metamethod�̌Ăяo������������͂�metamethod�̈����Ɉˑ����Ă���ꍇ�A�����_metadependent_�ł���A�ƌ����܂��B

    class foo[b <: dual.Boolean] {
        type bar = b#not
    }

���A`bar`��metadependent�ł��B

    class wow[b <: dual.Boolean] {
        type buz = dual.`true`#not
    }

`buz`��metadependent�ł͂���܂���B


### dualmethod

polymorphic-method�Ƃ��Ă�metamethod�Ƃ��Ă��A __�����悤�Ȋ�����__���p�ł��閼�O���A_dualmethod_�ƌ����܂��B

     def foo[b <: dual.Boolean, c <: dual.Boolean](b: b, c: c): foo[b, c] = b.equal(c)
    type foo[b <: dual.Boolean, c <: dual.Boolean] = b#equal[c]

���Adualmethod�ł���`foo`���A������dualmethod�ł���`equal`���Ăяo���Ă��܂��B

�����̂Ȃ�dualmethod�͂��΂���_dualvalue_�ƌĂ΂�܂��B




## meta-generics�͓����Ȃ�

meta�Ȑ��E��`if`����邱�Ƃ��l���܂��B�܂��t�c�[�̐��E�ōl���܂��B

    def myIf[T](b: scala.Boolean, _then: => T, _else: => T): T

�����polymorphic-method(������generics)�ł��B
�����meta�Ȑ��E�ɈڐA���܂��Bby-name�����͂ЂƂ܂��������āA

    type metaIf[T, b <: dual.Boolean, _then <: T, _else <: T] <: T // ???

����́Ameta�Ȑ��E��generics�ł���ƍl�����܂��B�������A����͂��܂������܂���B
�����̌o���ɂ��ƁA��ʂ�meta��generics�͂ǂ�Ȍ`���ł���A���܂������Ȃ��悤�ł��B

�d���Ȃ��̂ŁAGenerics��by-name�������Ȃ����E(�܂�A�̂�Java)��`if`���l���܂��B
�t�c�[�̐��E:

    trait MyFuntion0 {
        def apply: scala.Any
    }

    def myIf(b: scala.Boolean, _then: MyFunction0, _else: MyFunction0): MyFunction0

by-name�����̑���ɁA`MyFunction0`�ő�p���܂��B�߂�l�^��`MyFunction0`�Ȃ̂́A`if`���l�X�g���鎞�ɕ֗�������ł��B
���āA�����meta�Ȑ��E�ɈڐA����ƁA

    package dual

    trait Function0 extends dual.Any {
        type apply <: dual.Any
    }

    type `if`[b <: dual.Boolean, _then: dual.Function0, _else: dual.Function0] <: dual.Function0




## dual.Any


### meta-asInstanceOf

Meta-generics�������̂ŁA������_�E���L���X�g���K�v�ł��B
�܂�Ameta��`asInstanceOf`�����O�Ŏ������Ȃ���΂Ȃ�܂���B���ꂪ`dual.Any`�̎d���ɂȂ�܂�:

    package dual
    
    trait Any {
        type asBoolean <: dual.Boolean
        type asFunction0 <: dual.Function0
        // ...
    }

    sealed abstract class `true` extends dual.Boolean {
        type asBoolean = `true`
        // ...
    }

����Ń_�E���L���X�g�������ł��܂����B
meta��`isInstanceOf`��(�\�ł͂�����̂�)�܂���������Ă��܂���B


### meta-this

meta��`this`�Q�Ƃ����O�ŏ��������Ȃ��Ƃ����܂���B`self`�Ƃ������O�ɂȂ��Ă��܂�:

    package dual

    trait Any {
        type self <: Any
        // ...
    }

    sealed abstract class `true` extends dual.Boolean {
        type self = `true`
        // ...
    }




## Primitives

Scala��meta�Ȑ��E��primitive��p�ӂ��Ă���ĂȂ��̂ŁA�S�Ď��O�Ŏ������܂��B
`dual`�̒񋟂���primitive��metatype��:

* `Boolean`
* `Nat` (���R���^)
* `Unit`
* `FunctionN`
* `TupleN`

`Nat`�̎����Ƃ��āA`dual.nat.peano`��`dual.nat.dense`�̓���p�ӂ���Ă��܂��B
�O�҂͏����Ȑ����A��҂͑傫�Ȑ��������ӂł��B




## Duality

`mada.dual`�Œ񋟂����@�\�͂قƂ�ǑS���Adualmethod�ɂȂ��Ă��܂��B
�l�̌v�Z�Ƌ��ɁA�^���ω����Ă䂫�܂�:

    def myAssert(a: dual.`true`) = ()

    def testDuality {
        import dual.nat.peano.Literal._
        val i: _3 = _3
        val j: _5#minus[_2] = _5.minus(_2)
        myAssert(i.equal(j)) // compile-time check
    }




## �t�c�[�̐��E�Ƃ̑Θb


### undual

`dual.Any.undual`�ɂ����dual�Ȑ��E����t�c�[�̐��E�֔����o�����Ƃ��ł��܂�:

    import dual.nat.peano.Literal._

    def testUndual {
        val i: scala.Int = _3.times(_2).undual
        assertEquals(6, i)
    }


### Boxing

�t�c�[�̌^�́Adual�Ȑ��E�Ŏg�����߂�`dual.Box`���g���ĕϊ����Ȃ��Ƃ����܂���:

    def testBox {
        val j = dual.Tuple2(dual.Box(2), dual.Box(3))
        assertEquals(3, j._2.undual)
    }

�c�O�Ȃ���auto-boxing�͂���܂���B




## Heterogeneous List

`mada.dual`�̃f�[�^�\���͑S��Heterogeneous�ł��B�^�͂��̂܂ܕۑ�����܂��B
��Ƃ���`dual.List`���g���Ă݂܂�:

    import dual.nat.peano.Literal._

    object add2 extends dual.Function1 {
        override type self = add2.type
        override  def apply[x <: dual.Any](x: x): apply[x] = x.asNat plus _2
        override type apply[x <: dual.Any] = x#asNat#plus[_2]
    }

    def testTrivial {
        val xs = _3 :: _4 :: _5 :: _6 :: dual.Nil
        val u = xs.map(add2) // returns a view
        assertEquals(5 :: 6 :: 7 :: 8 :: Nil, u.undual)
        locally {
            import dual.::
            val v: _5 :: _6 :: _7 :: _8 :: dual.Nil = u.force
        }
    }

`add2`�̂悤��`dual.FunctionN`�̎�����on-the-fly�ō�邱�Ƃ͏o���Ȃ��̂ŁA����������`���Ȃ��Ƃ����܂���B
�܂��A`dual.List`��dualmethod��(�����Ă�)view��Ԃ����Ƃɒ��ӂ��Ă��������B`force`��__canonical__�ȃJ�^�`�ɕϊ��ł��܂��B

[MetaScala]�ƈ���āA`mada.dual`��`implicit`���g���Ă��܂���B
��ʂɁA`implicit`��__composable__�ł͂���܂���B������method����傫��method��g�ݗ��Ă�̂�����ł��B




## �v�Z���f��

Metaprogramming in Scala��:

* Pure(����p�Ȃ�)�ȃI�u�W�F�N�g�w���ł��B(����Ȍ��ꍡ�܂ł���܂����H)
* metamehod�֓n�������́A�n���܂��ɕ]������܂��Bby-name�����͂���܂��񂪁A�����`dual.Function0`���g���܂��B
* meta��`eq`�͂���܂���B�^���������ǂ������f���邱�Ƃ͏o���܂���B
* metadependent�łȂ�metamethod�̌Ăяo���̖߂�l�ɂ́A�Öق�`asXXX`���t������܂��B
* ������������܂��Bmetamethod�̌Ăяo���́A���ꂪ�������ł��肳������΁A��x�����]������܂���B

�t�B�{�i�b�`���̗�:

    type fibonacci[n <: dual.Nat] = dual.`if`[n#lt[_2], dual.const0[n], FibElse[n]]#apply#asNat

    trait FibElse[n <: dual.Nat] extends dual.Function0 {
        type self = FibElse[n]
        override type apply = fibonacci[n#decrement]#plus[fibonacci[n#decrement#decrement]]
    }

����`fibonacci`��`O(n)`�œ����܂��B������:

    type fibonacci[n <: dual.Nat] = dual.`if`[n#lt[_2], dual.const0[n], FibElse[n]]#apply#asNat

    trait FibElse[n <: dual.Nat] extends dual.Function0 {
        type self = FibElse[n]
        override type apply = fibonacci[n#minus[_1]]#plus[fibonacci[n#decrement#decrement]]
    }

�����`O(n^2)`�ɂȂ�܂��B�ċA�Ăяo�����̎��̃J�^�`���قȂ��Ă���A����������Ȃ�����ł��B

���Ȃ݂ɁA�ǂ������킯��

    class wow {
        type f = fibonacci[_10]
        type g = fibonacci[_10]
    }

����`fibonacci[_10]`�́A���Ƃ��]������܂��Bmetadependent�łȂ�����A�Ɛ�������܂��B




## dual�̖��_


### ���[�U��`�^

meta��`asInstanceOf`��`dual.Any.asXXX`�Ŏ������܂������A����ɂ����
`mada.dual`�̗��p�҂�metatype����邱�Ƃ��s�\�ɂȂ�܂����B
�����`dual.TupleN`��`dual.map`����g����K�v������܂��B


### �^���S��

`dual.Any.asXXX`�̕s���ȌĂяo���̓R���p�C���G���[�ɂȂ�܂���(`Nothing`�I�Ȃ��̂�Ԃ�)�B
���̂��߁A�^���S����__����__��܂��Ă��܂��B


### assertion

�R���p�C����assertion�͎����ł��Ȃ��Ǝv���܂��B
�������Ametadependent�łȂ������ł�`dual.free.assert`���g�p�ł��܂��B
�����͏�L��`myAssert`�Ɠ����ł��B




## �����̒��ӓ_


### class��class�������

    class Outer[m <: dual.Nat](m: m) {
        type apply[n <: dual.Nat] = ...

        case class Inner[n <: dual.Nat](n: n) extends dual.Function0 {
            type self = Inner[n]
            override type apply = ...
            // ...
        }
    }

���̂悤�ȃJ�^�`�������N���X�́A�R���p�C�����ƂĂ��x���Ȃ�ꍇ������܂��B
�ʓ|�ɂȂ�܂����Aobject��class�Ȃ���Ȃ��ł��B


### case class�͒x���Ȃ�

case��t���Ă��R���p�C�����Ԃɉe�����Ȃ��悤�ł��B��������dualmethod���ȒP�ɒ�`�ł��܂��B


### metatype�Ɏ����������Ȃ�

    trait MyMetatype extends dual.Any {
        type foo <: dual.Nat
        type bar = foo#plus[foo] // !!?
    }

���G�ȏ󋵂Ɍ���܂����A`bar`�̌Ăяo���ɂ���āA�R���p�C�����N���b�V�����邱�Ƃ�����܂��B
�����́A������p�̒��ԃN���X����邱�Ƃł��B(Java�Ɠ����悤��)




## Links

* [Browse Source]
* [Browse Test Source]
* [The Scala Programming Language]


Shunsuke Sogame <<okomok@gmail.com>>




[MIT License]: http://www.opensource.org/licenses/mit-license.php "MIT License"
[Browse Source]: http://github.com/okomok/mada/tree/master/src/main/scala/dual/ "Browse Source"
[Browse Test Source]: http://github.com/okomok/mada/tree/master/src/test/scala/dual/ "Browse Test Source"
[The Scala Programming Language]: http://www.scala-lang.org/ "The Scala Programming Language"
[PEG]: http://en.wikipedia.org/wiki/Parsing_expression_grammar "PEG"
[MetaScala]: http://www.assembla.com/wiki/show/metascala "MetaScala"
[Michid's Weblog]: http://michid.wordpress.com/ "Michid's Weblog"
[Apocalisp]: http://apocalisp.wordpress.com/ "Apocalisp"
[Boost.Fusion]: http://www.boost.org/doc/libs/release/libs/fusion/ "Boost.Fusion"
[scala.react]: http://lamp.epfl.ch/~imaier/ "scala.react"
[Reactive Extensions]: http://msdn.microsoft.com/en-us/devlabs/ee794896.aspx "Reactive Extensions"
[scala.Responder]: http://scala.sygneca.com/libs/responder "scala.Responder"
[scala.collection.Traversable]: http://www.scala-lang.org/archives/downloads/distrib/files/nightly/docs/library/scala/collection/Traversable.html "scala.collection.Traversable"
[scala-arm]: http://github.com/jsuereth/scala-arm "scala-arm"
[ARM in Java]: http://www.infoq.com/news/2010/08/arm-blocks "Automatic Resource Management in Java"
[Scala�Ō^���x���v���O���~���O]: http://d.hatena.ne.jp/kmizushima/20090418/1240072077 "Scala�Ō^���x���v���O���~���O"
