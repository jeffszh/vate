package cn.jeff.vate.model

abstract class ArticleExercise : BasicExercise() {

	abstract val article: String

	private val lineList: List<String>
		get() = article.split('\n')

	override val lineCount: Int
		get() = lineList.count()

	override fun generate(line: Int): String = lineList[line - 1]

}
