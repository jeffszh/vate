package cn.jeff.vate.comp

import com.vaadin.flow.component.html.Image
import com.vaadin.flow.component.orderedlayout.HorizontalLayout

class PngLabel(text: String, initColor: Int = 0) : HorizontalLayout() {

	companion object {
		private val colorDirNames = arrayOf("black", "blue", "red")
	}

	private val chars = text.toMutableList()
	private val charColors = MutableList(chars.size) { initColor }

	init {
		isSpacing = false
		rebuild()
	}

	private fun rebuild() {
		removeAll()
		chars.forEachIndexed { i, c ->
			add(Image("font-png/${colorDirNames[charColors[i]]}/${c.toByte()}.png", "$c"))
		}
	}

	fun addChar(c: Char, color: Int) {
		chars.add(c)
		charColors.add(color)
		add(Image("font-png/${colorDirNames[color]}/${c.toByte()}.png", "$c"))
	}

}
