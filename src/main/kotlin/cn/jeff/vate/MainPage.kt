package cn.jeff.vate

import cn.jeff.vate.utils.showMessage
import com.vaadin.flow.component.UI
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.BeforeEnterEvent
import com.vaadin.flow.router.BeforeEnterObserver
import com.vaadin.flow.router.Route
import org.springframework.boot.jdbc.DatabaseDriver

@Route("")
class MainPage : VerticalLayout(), BeforeEnterObserver {

	private var userName = ""

	init {
		userName = UI.getCurrent().session.getAttribute("userName")?.toString() ?: ""
		add("$userName 歡迎光臨！")
		add(Button("看看？") {
			showMessage("別急，這個網站還沒做好。")
		})
	}

	override fun beforeEnter(event: BeforeEnterEvent?) {
		if (userName.isEmpty()) {
			event!!.rerouteTo(LoginPage::class.java)
		}
	}

}
