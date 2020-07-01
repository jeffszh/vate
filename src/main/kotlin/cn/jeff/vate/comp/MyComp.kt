package cn.jeff.vate.comp

import com.vaadin.flow.component.Component
import com.vaadin.flow.component.Tag

@Tag("B")
class MyComp(text: String) : Component() {

	init {
		element.text = text
	}

}
