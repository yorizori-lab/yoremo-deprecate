package com.yorizori.yoremo.adapter.postgresql.sample

import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface SampleRepository : CoroutineCrudRepository<SampleEntity, Long>
