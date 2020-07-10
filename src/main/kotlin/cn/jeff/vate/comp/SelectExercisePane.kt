package cn.jeff.vate.comp

import cn.jeff.vate.model.Exercise
import cn.jeff.vate.model.MiddleRowExercise
import cn.jeff.vate.model.UpperRowExercise
import cn.jeff.vate.utils.ReentrantChanger
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.select.Select

class SelectExercisePane : HorizontalLayout() {

	companion object {
		val allExercises = arrayOf(
				MiddleRowExercise(),
				UpperRowExercise()
		)
	}

	private val exerciseSelector: Select<Exercise>
	var selectedExercise: Exercise
		get() = exerciseSelector.value
		set(value) {
			reentrantChanger.applyChange {
				exerciseSelector.value = value
				resetLineSelector(value.lineCount)
			}
		}

	private val lineSelector: Select<Int>
	var selectedExerciseLine: Int
		get() = lineSelector.value
		set(value) {
			lineSelector.value = value
		}

	var changeListener: (() -> Unit)? = null
	private val reentrantChanger = ReentrantChanger {
		changeListener?.invoke()
	}

	init {
		exerciseSelector = Select(*allExercises)
		lineSelector = Select()
		exerciseSelector.label = "请选择练习题"
		exerciseSelector.addValueChangeListener {
			reentrantChanger.applyChange {
				resetLineSelector(exerciseSelector.value.lineCount)
			}
		}
		add(exerciseSelector)
		lineSelector.label = "当前行"
		lineSelector.addValueChangeListener {
			reentrantChanger.applyChange { }
		}
		add(lineSelector)
		selectedExercise = allExercises[0]
	}

	private fun resetLineSelector(count: Int) {
		lineSelector.setItems(*(1..count).toList().toTypedArray())
		lineSelector.value = 1
	}

}
