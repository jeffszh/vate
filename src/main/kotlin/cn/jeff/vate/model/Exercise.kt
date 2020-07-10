package cn.jeff.vate.model

/**
 * # 习题
 * 一个习题包含若干行。
 */
interface Exercise {

	/**
	 * 习题的标题
	 */
	val topic: String

	/**
	 * 习题的行数
	 */
	val lineCount: Int

	/**
	 * 生成习题的内容
	 *
	 * @param line 行号
	 * @return 习题内容（让练习者照着打字的内容）
	 */
	fun generate(line: Int): String

}
