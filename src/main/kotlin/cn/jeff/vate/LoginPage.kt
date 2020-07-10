package cn.jeff.vate

import cn.jeff.vate.jpa.UserRepo
import cn.jeff.vate.utils.showMessage
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.PasswordField
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.Route

@Route("login")
class LoginPage(userRepo: UserRepo) : VerticalLayout() {

	private val userMap: Map<String, String> = userRepo.findAll().map {
		it.userName to it.password
	}.toMap()

	init {
		val tfUserName = TextField("用戶名", "請輸入用戶名")
		val tfPassword = PasswordField("密碼", "請輸入密碼")
		add(tfUserName, tfPassword)
		add(Button("登錄") {
			val userName = tfUserName.value.trim()
			val password = tfPassword.value
			when {
				userName.isEmpty() -> showMessage("用戶名不能為空！")
				userMap[userName] == password -> {
					ui.get().session.setAttribute("userName", userName)
					ui.get().navigate("")
				}
				else -> showMessage("用戶名、密碼不正確！")
			}
		})
	}

}
