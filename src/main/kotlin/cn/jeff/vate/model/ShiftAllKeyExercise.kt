package cn.jeff.vate.model

class ShiftAllKeyExercise : BasicExercise(), KeyPosExercise {

	override val topic: String
		get() = "Shift键强化练习"
	override val lineCount: Int
		get() = levels.size
	override val levels = arrayOf(
			ex1, ex1, ex1, ex1, ex1, ex1, ex1, ex1,
			ex2, ex2, ex2, ex2, ex2, ex2, ex2, ex2, ex2, ex2
	)

	override fun generate(line: Int): String = generateExample(line)

	companion object {
		@Suppress("SpellCheckingInspection")
		private const val ex1 = "ASDFGHJKLasdfghjklasdfghjkl;:'\"\\|,.<>/?!@#\$%^&*()-=_+"
		@Suppress("SpellCheckingInspection")
		private const val ex2 = "ASDFGHJKLQWERTYUIOPZXCVBNMasdfghjklqwertyuiopzxcvbnm;:'\"\\|,.<>/?!@#\$%^&*()-=_+"
	}

}
