package cn.jeff.vate

import cn.jeff.vate.comp.MyComp
import cn.jeff.vate.comp.SelectExercisePane
import cn.jeff.vate.comp.TypingPane
import cn.jeff.vate.jpa.TypingRepo
import cn.jeff.vate.jpa.UserRepo
import cn.jeff.vate.jpa.VateRepo
import cn.jeff.vate.utils.showMessage
import com.vaadin.flow.component.UI
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.html.Label
import com.vaadin.flow.component.orderedlayout.FlexComponent
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.page.Push
import com.vaadin.flow.router.BeforeEnterEvent
import com.vaadin.flow.router.BeforeEnterObserver
import com.vaadin.flow.router.Route

@Route("")
@Push
class MainPage(
		vateRepo: VateRepo,
		typingRepo: TypingRepo,
		userRepo: UserRepo
) : VerticalLayout(), BeforeEnterObserver {

	private var userName = ""

	init {
		userName = UI.getCurrent().session.getAttribute("userName")?.toString() ?: ""
		add(HorizontalLayout(
				Label("$userName 歡迎光臨！"),
				Button("登出") {
					userName = ""
					UI.getCurrent().session.setAttribute("userName", userName)
					UI.getCurrent().navigate("")
				},
				Button("看看？") {
					showMessage("别看了，开始打字练习吧！")
				}.apply { isVisible = false },
				Button("数据") {
					val data = vateRepo.findAll()
					showMessage("data.size=${data.size}")
					if (data.isNotEmpty()) {
						showMessage("${data[0]}")
					}
				}.apply { isVisible = false }
		).apply {
			defaultVerticalComponentAlignment = FlexComponent.Alignment.BASELINE
		})
		add(MyComp("Good good study, day day up!"))

		val selectExercisePane = SelectExercisePane()
		add(selectExercisePane)

		add(TypingPane(userName, typingRepo, userRepo, selectExercisePane))
	}

	override fun beforeEnter(event: BeforeEnterEvent?) {
		if (userName.isEmpty()) {
			event!!.rerouteTo(LoginPage::class.java)
		}
	}

}
