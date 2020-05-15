package cn.jeff.vate.utils

import com.vaadin.flow.component.notification.Notification

fun showMessage(text: String, duration: Int = 2000,
				position: Notification.Position = Notification.Position.MIDDLE) {
	Notification.show(text, duration, position)
}
