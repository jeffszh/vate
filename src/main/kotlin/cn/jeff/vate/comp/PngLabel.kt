package cn.jeff.vate.comp

import com.vaadin.flow.component.html.Image
import com.vaadin.flow.component.orderedlayout.HorizontalLayout

class PngLabel(text: String, initColor: Int = 0) : HorizontalLayout() {

	val chars = text.toMutableList()
	val charColors = MutableList(chars.size) { initColor }

	init {
		isSpacing = false
		rebuild()
	}

	fun rebuild() {
		removeAll()
		chars.forEachIndexed { i, c ->
			add(Image("font-png/blue/${c.toByte()}.png", "$c"))
		}
	}

}
