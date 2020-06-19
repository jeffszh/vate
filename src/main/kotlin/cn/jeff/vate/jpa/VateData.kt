package cn.jeff.vate.jpa

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class VateData(

		@Id
		var id: String = "",

		var msg: String = ""

)
