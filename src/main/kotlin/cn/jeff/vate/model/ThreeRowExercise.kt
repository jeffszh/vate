package cn.jeff.vate.model

class ThreeRowExercise : BasicExercise(), KeyPosExercise {

	override val topic: String
		get() = "三行键位练习"
	override val lineCount: Int
		get() = levels.size
	override val levels = arrayOf(
			ex1, ex1, ex1, ex1,
			ex1, ex1, ex1, ex1,
			ex1, ex1, ex1, ex1,
			ex1, ex1, ex1, ex1
	)

	override fun generate(line: Int): String = generateExample(line)

	companion object {
		@Suppress("SpellCheckingInspection")
		private const val ex1 = "qwertyuiop[]asdfghjkl;'\\zxcvbnm,./"
	}

}
