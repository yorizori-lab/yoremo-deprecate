package com.yorizori.yoremo.api.sample.usecase

import com.yorizori.yoremo.adapter.postgresql.sample.SampleAdapter
import com.yorizori.yoremo.api.sample.controller.message.SearchSample
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service

@Service
class SearchSampleUseCase(
    private val sampleAdapter: SampleAdapter
) {

    suspend fun search(
        requestParam: SearchSample.RequestParam
    ): SearchSample.Response {
        return sampleAdapter
            .findByMessage(requestParam.message)
            .toList()
            .let {
                SearchSample.Response(
                    totalCount = it.size,
                    items = it.map { sample ->
                        SearchSample.ResponseItem(
                            id = sample.id!!,
                            message = sample.message
                        )
                    }
                )
            }
    }
}
