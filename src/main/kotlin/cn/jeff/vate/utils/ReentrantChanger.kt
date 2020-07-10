package cn.jeff.vate.utils

class ReentrantChanger(private val changeTarget: () -> Unit) {

	private var runCount = 0

	fun applyChange(changingFunc: () -> Unit) {
		runCount++
		changingFunc()
		if (--runCount == 0) {
			changeTarget()
		}
	}

}
