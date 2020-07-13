package cn.jeff.vate.model

import java.lang.StringBuilder
import kotlin.math.min
import kotlin.random.Random

class ThreeRowExercise : Exercise {

	override fun toString() = topic

	override val topic: String
		get() = "三行键位练习"
	override val lineCount: Int
		get() = levels.size

	override fun generate(line: Int): String = generateExample(line)

	companion object {
		@Suppress("SpellCheckingInspection")
		private const val ex1 = "qwertyuiop[]asdfghjkl;'\\zxcvbnm,./"
		private val levels = arrayOf(
				ex1, ex1, ex1, ex1,
				ex1, ex1, ex1, ex1,
				ex1, ex1, ex1, ex1,
				ex1, ex1, ex1, ex1
		)

		private fun generateExample(level: Int): String {
			val lv = min(level - 1, levels.size - 1)
			val exString = levels[lv]
			val sb = StringBuilder()
			repeat(8) {
				repeat(4) {
					sb.append(randomCharInString(exString))
				}
				sb.append(' ')
			}
			repeat(4) {
				sb.append(randomCharInString(exString))
			}
			return sb.toString()
		}

		private fun randomCharInString(str: String): Char {
			val i = Random.nextInt(str.length)
			return str[i]
		}
	}

}
