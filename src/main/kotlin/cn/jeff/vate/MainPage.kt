package cn.jeff.vate

import cn.jeff.vate.utils.showMessage
import com.vaadin.flow.component.UI
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.BeforeEnterEvent
import com.vaadin.flow.router.BeforeEnterObserver
import com.vaadin.flow.router.Route

@Route("")
class MainPage : VerticalLayout(), BeforeEnterObserver {

	var userName = ""

	init {
		userName = UI.getCurrent().session.getAttribute("userName")?.toString() ?: ""
		add("$userName 歡迎光臨！")
		add(Button("來個問候？") {
			showMessage("問候你爸爸！")
		})
	}

	override fun beforeEnter(event: BeforeEnterEvent?) {
		if (userName.isEmpty()) {
			event!!.rerouteTo(LoginPage::class.java)
		}
	}

}
