package cn.jeff.vate.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepo : JpaRepository<User, String>
