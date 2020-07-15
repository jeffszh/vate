package cn.jeff.vate.model

import java.lang.StringBuilder

class ShiftLetterExercise : BasicExercise(), KeyPosExercise {

	override val topic: String
		get() = "Shift键字母练习"
	override val lineCount: Int
		get() = levels.size
	override val levels = arrayOf(
			ex1, ex1, ex1, ex1,
			ex1, ex1, ex1, ex1,
			ex1, ex1, ex1, ex1,
			ex1, ex1, ex1, ex1
	)

	override fun generate(line: Int): String {
		val result = generateExample(line)
		val sb = StringBuilder()
		result.forEachIndexed { i, c ->
			sb.append(if (i == 0 || result[i - 1] == ' ') {
				c - 0x20
			} else {
				c
			})
		}
		return sb.toString()
	}

	companion object {
		@Suppress("SpellCheckingInspection")
		private const val ex1 = "qwertyuiopasdfghjklzxcvbnm"
	}

}
