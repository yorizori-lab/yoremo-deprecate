package com.yorizori.yoremo.adapter.mysql.sample

import com.yorizori.yoremo.model.Sample
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

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

    suspend fun update(id: Long, message: String): Sample? {
        val existingSample = sampleRepository.findById(id)
            ?: throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Sample not found id : $id"
            )
        val updatedEntity = existingSample.copy(message = message)
        return sampleRepository.save(updatedEntity).toModel()
    }
}
