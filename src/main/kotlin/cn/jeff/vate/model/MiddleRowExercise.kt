package cn.jeff.vate.model

import java.lang.StringBuilder
import kotlin.math.min
import kotlin.random.Random

class MiddleRowExercise : Exercise {

	override fun toString() = topic

	override val topic: String
		get() = "中行键位练习"
	override val lineCount: Int
		get() = levels.size

	override fun generate(line: Int): String = generateExample(line)

	companion object {
		@Suppress("SpellCheckingInspection")
		private const val ex1 = "dfjk"
		@Suppress("SpellCheckingInspection")
		private const val ex2 = "asl;"
		@Suppress("SpellCheckingInspection")
		private const val ex3 = "asdfjkl;"
		@Suppress("SpellCheckingInspection")
		private const val ex4 = "afghj;"
		@Suppress("SpellCheckingInspection")
		private const val ex5 = "asdfghjkl;"
		@Suppress("SpellCheckingInspection")
		private const val ex6 = "asdfghjkl;'\\"
		private val levels = arrayOf(
				ex1, ex1, ex2, ex2,
				ex3, ex3, ex3, ex3,
				ex4, ex4,
				ex5, ex5, ex5, ex5,
				ex6, ex6, ex6, ex6, ex6, ex6
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
