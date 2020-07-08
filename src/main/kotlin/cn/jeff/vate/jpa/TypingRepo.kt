package cn.jeff.vate.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface TypingRepo : JpaRepository<TypingRecord, String>
