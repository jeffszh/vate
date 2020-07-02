package cn.jeff.vate.comp

import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.html.Label
import com.vaadin.flow.component.orderedlayout.FlexComponent
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout

class TypingPane : VerticalLayout() {

	init {
		val timerLabel = Label("计时：")
		add(HorizontalLayout(
				Button("点击这里开始打字").apply {
					addFocusListener {
						text = "正在打字，计时中"
					}
					addBlurListener {
						text = "已暂停，点这里继续"
					}
				},
				timerLabel
		).also {
			it.defaultVerticalComponentAlignment = FlexComponent.Alignment.BASELINE
		})
		add(PngLabel("Abc"))
	}

}
