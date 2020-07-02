package cn.jeff.vate.comp

import com.vaadin.flow.component.*

@Tag("B")
class MyComp(text: String) : Component(), Focusable<MyComp> {

	init {
		element.text = text
		addListener(KeyDownEvent::class.java) {
			println("keyDownEvent = $it")
		}
		addListener(KeyPressEvent::class.java) {
			println("keyPressEvent = $it")
		}
		addFocusListener {
			element.text = "有焦点"
			it.source.element.text = "有焦点了"
		}
		addBlurListener {
			element.text = "失去焦点"
		}
		isEnabled = true
	}

}
