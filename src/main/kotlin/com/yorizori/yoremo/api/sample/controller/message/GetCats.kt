package com.yorizori.yoremo.api.sample.controller.message

abstract class GetCats {

    data class RequestParam(
        // API 키 없이 10개 까지 요청 가능.
        val limit: Int,
        val hasBreeds: Boolean,
        val categoryIds: Collection<Int>?,
    )

    data class Response(
        val size: Int,
        val items: List<ResponseItem>,
    )

    data class ResponseItem(
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
