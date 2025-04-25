package com.yorizori.yoremo.adapter.http.thecatapi.message

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

/** [API 문서](https://developers.thecatapi.com) */
abstract class GetCatImages {

    data class RequestParam(
        // API 키 없이 10개 까지 요청 가능.
        val limit: Int,
        val hasBreeds: Boolean,
        val categoryIds: Collection<Int>?,
    )

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class Response(
        val id: String,
        val width: Int,
        val height: Int,
        val url: String,
        val breeds: List<Breed>?
    ) {
        data class Breed(
            val weight: Weight,
            val id: String,
            val name: String,
            val temperament: String,
            val origin: String,
            val countryCodes: String,
            val countryCode: String,
            val lifeSpan: String,
            val wikipediaUrl: String
        ) {
            data class Weight(
                val imperial: String,
                val metric: String
            )
        }
    }
}
