package cn.jeff.vate

import org.springframework.boot.jdbc.DatabaseDriver
import java.sql.DriverManager

fun main() {
	println("試試HSQL數據庫。")
	val driverClassName = DatabaseDriver.HSQLDB.driverClassName
	println(driverClassName)
	Class.forName(org.hsqldb.jdbcDriver::class.java.name)
	val conn = DriverManager.getConnection("jdbc:hsqldb:vate_db", "SA", "")
	println(conn.clientInfo)
	conn.close()
}
