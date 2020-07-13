package cn.jeff.vate.model

import java.lang.StringBuilder
import kotlin.math.min
import kotlin.random.Random

interface KeyPosExercise {

	val levels: Array<String>

	fun generateExample(level: Int): String {
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
