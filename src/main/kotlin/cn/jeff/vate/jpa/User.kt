package cn.jeff.vate.jpa

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class User(

		@Id
		var userName: String = "",

		var password: String = "",

		var lastExercise: String = ""

)
