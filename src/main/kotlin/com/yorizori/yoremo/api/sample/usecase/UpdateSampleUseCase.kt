package com.yorizori.yoremo.api.sample.usecase

import com.yorizori.yoremo.adapter.postgresql.sample.SampleAdapter
import com.yorizori.yoremo.api.sample.controller.message.UpdateSample
import org.springframework.stereotype.Service

@Service
class UpdateSampleUseCase(
    private val sampleAdapter: SampleAdapter
) {
    suspend fun update(
        pathVariable: UpdateSample.PathVariable, request: UpdateSample.Request
    ): UpdateSample.Response {
        return sampleAdapter.update(pathVariable.id, request.message)
            .let {
                UpdateSample.Response(id = it.id!!, message = it.message)
            }
    }
}