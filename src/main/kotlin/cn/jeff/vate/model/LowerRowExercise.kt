package cn.jeff.vate.model

class LowerRowExercise : BasicExercise(), KeyPosExercise {

	override val topic: String
		get() = "下行键位练习"
	override val lineCount: Int
		get() = levels.size
	override val levels = arrayOf(
			ex1, ex1, ex1, ex2, ex2, ex2,
			ex3, ex3, ex3, ex3, ex3, ex3,
			ex4, ex4, ex4,
			ex5, ex5, ex5, ex5, ex5, ex5,
			ex6, ex6, ex6, ex6, ex6, ex6, ex6, ex6, ex6
	)

	override fun generate(line: Int): String = generateExample(line)

	companion object {
		@Suppress("SpellCheckingInspection")
		private const val ex1 = "dfjkcvm,"
		@Suppress("SpellCheckingInspection")
		private const val ex2 = "asl;zx./"
		@Suppress("SpellCheckingInspection")
		private const val ex3 = "asdfjkl;zxcvm,./"
		@Suppress("SpellCheckingInspection")
		private const val ex4 = "afghj;zvbnm/"
		@Suppress("SpellCheckingInspection")
		private const val ex5 = "asdfghjkl;zxcvbnm,./"
		@Suppress("SpellCheckingInspection")
		private const val ex6 = "asdfghjkl;'\\zxvbnm,./"
	}

}
