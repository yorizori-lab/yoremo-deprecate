package com.yorizori.yoremo.api.sample.controller.message

abstract class SearchSample {

    data class RequestParam(val message: String)

    data class Response(
        val totalCount: Int,
        val items: List<ResponseItem>
    )

    data class ResponseItem(
        val id: Long,
        val message: String
    )
}
