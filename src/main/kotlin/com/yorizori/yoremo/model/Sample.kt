package com.yorizori.yoremo.model

import java.time.LocalDateTime

data class Sample(
    val id: Long? = null,
    val message: String,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
)
