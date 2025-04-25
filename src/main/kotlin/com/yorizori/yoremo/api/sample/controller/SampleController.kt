package com.yorizori.yoremo.api.sample.controller

import com.yorizori.yoremo.api.sample.controller.message.GetEcho
import com.yorizori.yoremo.api.sample.controller.message.GetSample
import com.yorizori.yoremo.api.sample.controller.message.SaveSample
import com.yorizori.yoremo.api.sample.usecase.GetSampleUseCase
import com.yorizori.yoremo.api.sample.usecase.SaveSampleUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sample/v1")
class SampleController(
    private val saveSampleUseCase: SaveSampleUseCase,
    private val getSampleUseCase: GetSampleUseCase
) {

    @GetMapping("/echo")
    fun echo(
        request: GetEcho.Request
    ): GetEcho.Response {
        return GetEcho.Response(message = request.message)
    }

    @PostMapping("/samples")
    suspend fun save(
        @RequestBody request: SaveSample.Request
    ): SaveSample.Response {
        return saveSampleUseCase.save(request)
    }

    @GetMapping("/samples/{id}")
    suspend fun get(
        request: GetSample.PathVariable,
    ): GetSample.Response {
        return getSampleUseCase.get(request)
    }
}
