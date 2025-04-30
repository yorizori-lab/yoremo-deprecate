package com.yorizori.yoremo.api.sample.usecase

import com.yorizori.yoremo.adapter.mysql.sample.SampleAdapter
import com.yorizori.yoremo.api.sample.controller.message.UpdateSample
import com.yorizori.yoremo.model.Sample
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UpdateSampleUseCase(
    private val sampleAdapter: SampleAdapter
) {
    suspend fun update(pathVariable: UpdateSample.PathVariable, request: UpdateSample.Request): UpdateSample.Response {
        return sampleAdapter.update(pathVariable.id, Sample(message = request.message))
            ?.let {
                UpdateSample.Response(id = it.id!!, message = it.message)
            } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Sample not found")
    }
}