package com.yorizori.yoremo.adapter.mysql.sample

import com.yorizori.yoremo.model.Sample
import org.springframework.stereotype.Component

@Component
class SampleAdapter(
    private val sampleRepository: SampleRepository
) {

    suspend fun save(sample: Sample): Sample {
        return sampleRepository.save(SampleEntity.from(sample)).toModel()
    }

    suspend fun findById(id: Long): Sample? {
        return sampleRepository.findById(id)?.toModel()
    }

    suspend fun update(id: Long, sample: Sample): Sample? {
        val existingSample = sampleRepository.findById(id) ?: return null
        val updatedEntity = existingSample.copy(message = sample.message)
        return sampleRepository.save(updatedEntity).toModel()
    }
}
