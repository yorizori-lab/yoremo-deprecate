package com.yorizori.yoremo.api.sample.controller

import com.yorizori.yoremo.api.sample.controller.message.GetEcho
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sample/v1")
class SampleController {

    @GetMapping("/echo")
    fun echo(
        request: GetEcho.Request
    ): GetEcho.Response {
        return GetEcho.Response(message = request.message)
    }
}
