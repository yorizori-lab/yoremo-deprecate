package com.yorizori.yoremo.api.sample.controller.message

abstract class UpdateSample {

    data class PathVariable(val id: Long)

    data class Request(val message: String)

    data class Response(
        val id: Long,
        val message: String
    )
}