package com.yorizori.yoremo.api.sample.usecase

import com.yorizori.yoremo.api.sample.controller.message.GetCats
import com.yorizori.yoremo.adapter.http.thecatapi.TheCatApiAdapter
import com.yorizori.yoremo.adapter.http.thecatapi.message.GetCatImages
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service

@Service
class GetCatsUseCase(
    private val theCatApiAdapter: TheCatApiAdapter
) {
    suspend fun get(requestParam: GetCats.RequestParam): GetCats.Response {
        return theCatApiAdapter.searchImages(
            GetCatImages.RequestParam(
                limit = requestParam.limit,
                hasBreeds = requestParam.hasBreeds,
                categoryIds = requestParam.categoryIds,
            )
        )
            .toList()
            .let {
                GetCats.Response(
                    size = it.size,
                    items = it.map {
                        GetCats.ResponseItem(
                            id = it.id,
                            url = it.url,
                            width = it.width,
                            height = it.height,
                            breeds = it.breeds?.map { breed ->
                                GetCats.ResponseItem.Breed(
                                    id = breed.id,
                                    name = breed.name,
                                    origin = breed.origin,
                                    temperament = breed.temperament,
                                    weight = GetCats.ResponseItem.Breed.Weight(
                                        imperial = breed.weight.imperial,
                                        metric = breed.weight.metric
                                    ),
                                    countryCodes = breed.countryCodes,
                                    countryCode = breed.countryCode,
                                    lifeSpan = breed.lifeSpan,
                                    wikipediaUrl = breed.wikipediaUrl,
                                )
                            }
                        )
                    }
                )
            }
    }
}
