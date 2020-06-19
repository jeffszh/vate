package cn.jeff.vate

import cn.jeff.vate.jpa.VateRepo
import cn.jeff.vate.utils.showMessage
import com.vaadin.flow.component.UI
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.html.Label
import com.vaadin.flow.component.orderedlayout.FlexComponent
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.BeforeEnterEvent
import com.vaadin.flow.router.BeforeEnterObserver
import com.vaadin.flow.router.Route

@Route("")
class MainPage(vateRepo: VateRepo) : VerticalLayout(), BeforeEnterObserver {

	private var userName = ""

	init {
		userName = UI.getCurrent().session.getAttribute("userName")?.toString() ?: ""
		add(HorizontalLayout(
				Label("$userName 歡迎光臨！"),
				Button("登出") {
					userName = ""
					UI.getCurrent().session.setAttribute("userName", userName)
					UI.getCurrent().navigate("")
				}
		).apply {
			defaultVerticalComponentAlignment = FlexComponent.Alignment.BASELINE
		})
		add(Button("看看？") {
			showMessage("別急，這個網站還沒做好。")
		})
		add(Button("数据") {
			val data = vateRepo.findAll()
			showMessage("data.size=${data.size}")
			if (data.isNotEmpty()) {
				showMessage("${data[0]}")
			}
		})
	}

	override fun beforeEnter(event: BeforeEnterEvent?) {
		if (userName.isEmpty()) {
			event!!.rerouteTo(LoginPage::class.java)
		}
	}

}
