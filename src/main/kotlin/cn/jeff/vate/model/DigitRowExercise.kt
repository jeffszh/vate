package cn.jeff.vate.model

class DigitRowExercise : BasicExercise(), KeyPosExercise {

	override val topic: String
		get() = "数字行键位练习"
	override val lineCount: Int
		get() = levels.size
	override val levels = arrayOf(
			ex1, ex1, ex1, ex1,
			ex2, ex2, ex2, ex2,
			ex3, ex3, ex3, ex3,
			ex3, ex3, ex3, ex3
	)

	override fun generate(line: Int): String = generateExample(line)

	companion object {
		@Suppress("SpellCheckingInspection")
		private const val ex1 = "1234567890-=asdfghjkl;'\\"
		@Suppress("SpellCheckingInspection")
		private const val ex2 = "1234567890-=1234567890-=asdfghjkl;'\\zxcvbnm,./"
		@Suppress("SpellCheckingInspection")
		private const val ex3 = "1234567890-=1234567890-=1234567890-=asdfghjkl;'\\zxcvbnm,./qwertyuiop[]"
	}

}
