package cn.jeff.vate.comp

import com.vaadin.flow.component.Component
import com.vaadin.flow.component.html.Image
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import java.util.*

class PngLabel(text: String, initColor: CharStatus = CharStatus.NORMAL) : HorizontalLayout() {

	private val charList = LinkedList<Char>()
	private val charStatusList = LinkedList<CharStatus>()
	private val childList = LinkedList<Component>()

	init {
		init(text, initColor)
	}

	fun init(text: String, initColor: CharStatus = CharStatus.NORMAL) {
		isSpacing = false
		charList.clear()
		charStatusList.clear()
		text.forEach {
			charList.add(it)
			charStatusList.add(initColor)
		}
		rebuild()
	}

	private fun rebuild() {
		removeAll()
		childList.clear()
		charList.forEachIndexed { i, c ->
			val img = Image("font-png/${charStatusList[i].color}/${c.toByte()}.png", "$c")
			childList.add(img)
			add(img)
		}
	}

	/**
	 * 在尾部添加字符
	 *
	 * @param c 要添加的字符
	 * @param charStatus 要添加字符的状态
	 */
	fun addChar(c: Char, charStatus: CharStatus) {
		charList.add(c)
		charStatusList.add(charStatus)
		val img = Image("font-png/${charStatus.color}/${c.toByte()}.png", "$c")
		childList.add(img)
		add(img)
	}

	fun delChar() {
		if (charList.isNotEmpty()) {
			charList.removeLast()
			charStatusList.removeLast()
			remove(childList.removeLast())
		}
	}

	val length: Int
		get() = charList.size

	operator fun get(i: Int) =
			charList[i] to charStatusList[i]

	fun allRight() = charStatusList.all {
		it == CharStatus.CORRECT
	}

	enum class CharStatus {
		NORMAL {
			override val color = "black"
		},
		CORRECT {
			override val color = "blue"
		},
		WRONG {
			override val color = "red"
		};

		abstract val color: String
	}

}
