package com.yorizori.yoremo.api.sample.controller.message

import java.time.LocalDateTime

abstract class GetSample {

    data class PathVariable(
        val id: Long
    )

    data class Response(
        val id: Long,
        val message: String,
        val createdAt: LocalDateTime?,
        val updatedAt: LocalDateTime?
    )
}
