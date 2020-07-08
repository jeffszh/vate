package cn.jeff.vate.comp

import com.vaadin.flow.component.KeyDownEvent
import com.vaadin.flow.component.KeyPressEvent
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.html.Label
import com.vaadin.flow.component.orderedlayout.FlexComponent
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.timer

class TypingPane : VerticalLayout() {

	@Suppress("SpellCheckingInspection")
	private val exampleText = "fdfd jkjk ffddjjkk fdjk jkfd dfdf kjkj dfjk"
	private val exampleLabel = PngLabel(exampleText)
	private val inputLabel = PngLabel("")
	private val nextExerciseButton = Button("下一题")

	init {
		val timerLabel = Label("计时：")
		val typingTime = Date(0L)
		var typing = false
		val df = SimpleDateFormat("mm:ss.S")
		timer(period = 50) {
			if (typing) {
				typingTime.time += 50
				ui.get().access {
					timerLabel.text = "计时：${df.format(typingTime)}"
				}
			}
		}
		add(HorizontalLayout(
				Button("点击这里开始打字").apply {
					addFocusListener {
						text = "正在打字，计时中"
						typing = true
					}
					addBlurListener {
						text = "已暂停，点这里继续"
						typing = false
					}
					addListener(KeyPressEvent::class.java) { event ->
						forOneOfPrintableChars({ c ->
							event.key.matches("$c")
						}) { c ->
							val currentLen = inputLabel.length
							if (currentLen < exampleLabel.length) {
								val (refChar, _) = exampleLabel[currentLen]
								val st = if (refChar == c) {
									PngLabel.CharStatus.CORRECT
								} else {
									PngLabel.CharStatus.WRONG
								}
								inputLabel.addChar(c, st)

								// 如果已经到尾，且全部正确
								if (inputLabel.length == exampleLabel.length &&
										inputLabel.allRight()) {
									typing = false
									nextExerciseButton.isEnabled = true
									nextExerciseButton.focus()
									this.isEnabled = false
								}
							}
						}
					}
					addListener(KeyDownEvent::class.java) {
						//println("down --- key=${it.key}, code=${it.code}")
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
		add(nextExerciseButton)
		nextExerciseButton.isEnabled = false
	}

	private fun forOneOfPrintableChars(matcher: (Char) -> Boolean, consumer: (Char) -> Unit) {
		for (c in 'A'..'Z') {
			if (matcher(c)) {
				consumer(c)
				return
			}
		}
		for (c in 'a'..'z') {
			if (matcher(c)) {
				consumer(c)
				return
			}
		}
		for (c in '0'..'9') {
			if (matcher(c)) {
				consumer(c)
				return
			}
		}
		" !@#$%^&*(),.'\"?/<>".forEach { c ->
			if (matcher(c)) {
				consumer(c)
				return
			}
		}
	}

}
