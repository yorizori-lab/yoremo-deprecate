package com.yorizori.yoremo.api.sample.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.web.reactive.server.WebTestClient

@WebFluxTest(SampleController::class)
class SampleControllerTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun echo() {
        // given
        val echoMessage = "Hello, Yoremo!"

        // when, then
        webTestClient
            .get()
            .uri {
                it.path("/sample/v1/echo")
                    .queryParam("message", echoMessage)
                    .build()
            }
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.message").isEqualTo(echoMessage)
    }
}
