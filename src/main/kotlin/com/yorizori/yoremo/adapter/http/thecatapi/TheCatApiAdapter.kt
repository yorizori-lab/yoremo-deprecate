package com.yorizori.yoremo.adapter.http.thecatapi

import com.yorizori.yoremo.adapter.http.common.HttpClientProperties
import com.yorizori.yoremo.adapter.http.thecatapi.message.GetCatImages
import io.netty.handler.logging.LogLevel
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlow
import reactor.netty.http.client.HttpClient
import reactor.netty.transport.logging.AdvancedByteBufFormat
import java.time.Duration
import java.util.*

@Component
class TheCatApiAdapter(
    webClientBuilder: WebClient.Builder,
    @Qualifier("theCatApiClientProperties")
    theCatApiClientProperties: HttpClientProperties
) {
    private val webClient: WebClient = with(theCatApiClientProperties) {
        webClientBuilder.clientConnector(
            ReactorClientHttpConnector(
                HttpClient
                    .create()
                    .responseTimeout(Duration.ofMillis(this.responseTimeoutMs))
                    .wiretap(
                        HttpClient::class.qualifiedName!!,
                        LogLevel.DEBUG,
                        AdvancedByteBufFormat.TEXTUAL,
                    ),
            ),
        )
            .baseUrl(this.baseUrl!!)
            .build()
    }

    fun searchImages(
        requestParam: GetCatImages.RequestParam
    ): Flow<GetCatImages.Response> {
        return webClient
            .get()
            .uri("/v1/images/search") {
                it
                    .queryParam("limit", requestParam.limit)
                    .queryParam("has_breeds", requestParam.hasBreeds)
                    .queryParamIfPresent(
                        "category_ids",
                        Optional.ofNullable(requestParam.categoryIds?.joinToString(","))
                    )
                    .build()
            }
            .retrieve()
            .bodyToFlow<GetCatImages.Response>()
    }
}
