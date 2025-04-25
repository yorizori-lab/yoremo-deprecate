package com.yorizori.yoremo.api.sample.controller.message

abstract class GetEcho {

    data class Request(val message: String)

    data class Response(val message: String)
}
