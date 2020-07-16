package cn.jeff.vate.comp

import cn.jeff.vate.jpa.TypingRecord
import cn.jeff.vate.jpa.TypingRepo
import cn.jeff.vate.jpa.UserRepo
import com.vaadin.flow.component.KeyDownEvent
import com.vaadin.flow.component.KeyPressEvent
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.html.Label
import com.vaadin.flow.component.orderedlayout.FlexComponent
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import org.springframework.data.repository.findByIdOrNull
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.timer
import kotlin.math.roundToInt

class TypingPane(
		private val userName: String,
		private val typingRepo: TypingRepo,
		private val userRepo: UserRepo,
		private val selectExercisePane: SelectExercisePane
) : VerticalLayout() {

	private val exampleLabel = PngLabel(" ")
	private val inputLabel = PngLabel("")
	private val startTypingButton = Button("点击这里开始打字")
	private val nextExerciseButton = Button("下一题") {
		nextExercise()
	}
	private val typingTime = Date(0L)

	init {
		val timerLabel = Label("计时：")
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
				startTypingButton.apply {
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
									saveRecord()
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

		selectExercisePane.changeListener = {
			exerciseChanged()
		}

		val user = userRepo.findByIdOrNull(userName)
		val lastExerciseTopic = user?.lastExercise
		val lastExercise = selectExercisePane.getExerciseByName(lastExerciseTopic)
		selectExercisePane.selectedExercise = lastExercise
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
		" !@#$%^&*()-=_+[]{};'\\:\"|,./<>?".forEach { c ->
			if (matcher(c)) {
				consumer(c)
				return
			}
		}
	}

	private fun nextExercise() {
		with(selectExercisePane) {
			selectedExerciseLine = if (selectedExerciseLine < selectedExercise.lineCount) {
				selectedExerciseLine + 1
			} else {
				1
			}
			exampleLabel.init(selectedExercise.generate(selectedExerciseLine))
			inputLabel.init("")
		}
		startTypingButton.isEnabled = true
		startTypingButton.focus()
		nextExerciseButton.isEnabled = false
		typingTime.time = 0L
	}

	private fun exerciseChanged() {
		println("改选：${selectExercisePane.selectedExercise}, ${selectExercisePane.selectedExerciseLine}")
		val user = userRepo.findByIdOrNull(userName)
		user?.let {
			it.lastExercise = selectExercisePane.selectedExercise.topic
			userRepo.save(it)
		}
		val example = selectExercisePane.selectedExercise.generate(selectExercisePane.selectedExerciseLine)
		exampleLabel.init(example)
		inputLabel.init("")
	}

	private fun saveRecord() {
		val now = Date()
		val timeInMinutes = typingTime.time.toDouble() / 1000.0 / 60.0
		val speed = exampleLabel.length.toDouble() / timeInMinutes
		val roundedSpeed = (speed * 10.0).roundToInt().toDouble() / 10.0
		val typingRecord = TypingRecord(
//				SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now),
				now,
				userName,
				selectExercisePane.selectedExercise.topic,
				selectExercisePane.selectedExerciseLine,
				SimpleDateFormat("mm:ss.S").format(typingTime),
				"$roundedSpeed kps"
		)
		typingRepo.save(typingRecord)
	}

}
