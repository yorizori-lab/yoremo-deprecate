package com.yorizori.yoremo.http.thecatapi

import com.yorizori.yoremo.http.common.HttpClientProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TheCatApiClientConfig {

    @Bean("theCatApiClientProperties")
    @ConfigurationProperties("adapter.http.the-cat-api")
    fun theCatApiClientProperties() = HttpClientProperties()
}
