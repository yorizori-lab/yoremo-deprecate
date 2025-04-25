package com.yorizori.yoremo.adapter.mysql.sample

import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface SampleRepository : CoroutineCrudRepository<SampleEntity, Long>
