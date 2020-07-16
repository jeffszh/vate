package cn.jeff.vate.jpa

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class TypingRecord(

//		@Id
//		var id: String = "",

		@Id
		var date: Date = Date(),

		var user: String = "",

		var exercise: String? = null,
		var line: Int? = null,
		var timeLen: String = "",
		var speed: String? = null

)
