package com.yorizori.yoremo.api.sample.usecase

import com.yorizori.yoremo.adapter.mysql.sample.SampleAdapter
import com.yorizori.yoremo.api.sample.controller.message.GetSample
import org.springframework.stereotype.Service

@Service
class GetSampleUseCase(
    private val sampleAdapter: SampleAdapter
) {

    suspend fun get(request: GetSample.PathVariable): GetSample.Response {
        val sample = sampleAdapter.findById(request.id)
            ?: throw IllegalArgumentException("sample not found. id: ${request.id}")

        return GetSample.Response(
            id = sample.id!!,
            message = sample.message,
            createdAt = sample.createdAt,
            updatedAt = sample.updatedAt
        )
    }
}
