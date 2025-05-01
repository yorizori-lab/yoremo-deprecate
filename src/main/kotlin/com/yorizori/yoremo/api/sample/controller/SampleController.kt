package com.yorizori.yoremo.api.sample.controller

import com.yorizori.yoremo.api.sample.controller.message.*
import com.yorizori.yoremo.api.sample.usecase.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sample/v1")
class SampleController(
    private val saveSampleUseCase: SaveSampleUseCase,
    private val getSampleUseCase: GetSampleUseCase,
    private val updateSampleUseCase: UpdateSampleUseCase,
    private val getCatsUseCase: GetCatsUseCase,
    private val searchSampleUseCase: SearchSampleUseCase
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

    @PutMapping("/samples/{id}")
    suspend fun update(
        id: UpdateSample.PathVariable,
        @RequestBody request: UpdateSample.Request
    ): UpdateSample.Response {
        return updateSampleUseCase.update(id, request)
    }

    @GetMapping("/samples/search")
    suspend fun getByMessage(
        request: SearchSample.RequestParam
    ): SearchSample.Response {
        return searchSampleUseCase.search(request)
    }

    @GetMapping("/cats")
    suspend fun getCats(
        requestParam: GetCats.RequestParam
    ): GetCats.Response {
        return getCatsUseCase.get(requestParam)
    }
}
