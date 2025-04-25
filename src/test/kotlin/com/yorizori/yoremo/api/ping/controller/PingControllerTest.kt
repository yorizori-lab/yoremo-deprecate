package com.yorizori.yoremo.api.ping.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.web.reactive.server.WebTestClient

@WebFluxTest(PingController::class)
class PingControllerTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun ping() {
        webTestClient
            .get()
            .uri("/ping")
            .exchange()
            .expectStatus().isOk
            .expectBody()
    }
}
