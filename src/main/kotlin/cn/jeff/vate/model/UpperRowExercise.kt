package cn.jeff.vate.model

class UpperRowExercise : BasicExercise(), KeyPosExercise {

	override val topic: String
		get() = "上行键位练习"
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
		private const val ex1 = "dfjkerui"
		@Suppress("SpellCheckingInspection")
		private const val ex2 = "asl;qwop"
		@Suppress("SpellCheckingInspection")
		private const val ex3 = "asdfjklqweruiop"
		@Suppress("SpellCheckingInspection")
		private const val ex4 = "afghj;qrtyup"
		@Suppress("SpellCheckingInspection")
		private const val ex5 = "asdfghjkl;qwertyuiop"
		@Suppress("SpellCheckingInspection")
		private const val ex6 = "asdfghjkl;'\\qwertyuiop[]"
	}

}
