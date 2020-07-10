package cn.jeff.vate.model

class UpperRowExercise : Exercise {

	override fun toString() = topic

	override val topic: String
		get() = "上行键位练习"
	override val lineCount: Int
		get() = 10

	override fun generate(line: Int): String {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

}
