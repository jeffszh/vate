package cn.jeff.vate.model

class MiddleRowExercise : Exercise {

	override fun toString() = topic

	override val topic: String
		get() = "中行键位练习"
	override val lineCount: Int
		get() = 10

	override fun generate(line: Int): String {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

}
