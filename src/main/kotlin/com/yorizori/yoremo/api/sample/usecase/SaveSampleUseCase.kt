package com.yorizori.yoremo.api.sample.usecase

import com.yorizori.yoremo.adapter.postgresql.sample.SampleAdapter
import com.yorizori.yoremo.api.sample.controller.message.SaveSample
import com.yorizori.yoremo.model.Sample
import org.springframework.stereotype.Service

@Service
class SaveSampleUseCase(
    private val sampleAdapter: SampleAdapter
) {
    suspend fun save(request: SaveSample.Request): SaveSample.Response {
        return sampleAdapter.save(
            Sample(message = request.message)
        ).let {
            SaveSample.Response(id = it.id!!, message = it.message)
        }
    }
}
