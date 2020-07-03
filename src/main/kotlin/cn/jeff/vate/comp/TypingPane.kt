package cn.jeff.vate.comp

import com.vaadin.flow.component.KeyDownEvent
import com.vaadin.flow.component.KeyPressEvent
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.html.Label
import com.vaadin.flow.component.orderedlayout.FlexComponent
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout

class TypingPane : VerticalLayout() {

	@Suppress("SpellCheckingInspection")
	private val exampleText = "fdfd jkjk ffddjjkk fdjk jkfd dfdf kjkj dfjk"
	private val exampleLabel = PngLabel(exampleText)
	private val inputLabel = PngLabel("")

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
					addListener(KeyPressEvent::class.java) {
						when {
							it.key.matches("a") -> {
								inputLabel.addChar('a', PngLabel.CharStatus.CORRECT)
							}
							it.key.matches("b") -> {
								inputLabel.addChar('b', PngLabel.CharStatus.WRONG)
							}
							else -> {
								println("key=${it.key}, code=${it.code}")
							}
						}
					}
					addListener(KeyDownEvent::class.java) {
						println("down --- key=${it.key}, code=${it.code}")
						if (it.key.matches("Backspace")) {
							inputLabel.delChar()
						}
					}
				},
				timerLabel
		).also {
			it.defaultVerticalComponentAlignment = FlexComponent.Alignment.BASELINE
		})

		add(exampleLabel)
		add(inputLabel)
	}

}
