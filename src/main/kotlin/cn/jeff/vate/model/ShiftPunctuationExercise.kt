package cn.jeff.vate.model

class ShiftPunctuationExercise : BasicExercise(), KeyPosExercise {

	override val topic: String
		get() = "Shift标点符号练习"
	override val lineCount: Int
		get() = levels.size
	override val levels = arrayOf(
			ex1, ex1, ex1, ex1, ex1, ex1, ex1, ex1,
			ex2, ex2, ex2, ex2, ex2, ex2, ex2, ex2, ex2, ex2
	)

	override fun generate(line: Int): String = generateExample(line)

	companion object {
		@Suppress("SpellCheckingInspection")
		private const val ex1 = "asdfghjklasdfghjklasdfghjkl;:'\"\\|,.<>/?!@#\$%^&*()-=_+"
		@Suppress("SpellCheckingInspection")
		private const val ex2 = "asdfghjklqwertyuiopzxcvbnm;:'\"\\|,.<>/?!@#\$%^&*()-=_+"
	}

}
