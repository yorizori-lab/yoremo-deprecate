package com.yorizori.yoremo.adapter.postgresql.sample

import com.yorizori.yoremo.model.Sample
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.reactive.asFlow
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.r2dbc.core.select
import org.springframework.data.relational.core.query.Criteria.where
import org.springframework.data.relational.core.query.Query.query
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

@Component
class SampleAdapter(
    private val sampleRepository: SampleRepository,
    private val r2dbcEntityTemplate: R2dbcEntityTemplate,
) {

    suspend fun save(sample: Sample): Sample {
        return sampleRepository.save(SampleEntity.from(sample)).toModel()
    }

    suspend fun findById(id: Long): Sample? {
        return sampleRepository.findById(id)?.toModel()
    }

    suspend fun update(id: Long, message: String): Sample {
        val existingSample = sampleRepository.findById(id)
            ?: throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Sample not found id : $id"
            )
        val updatedEntity = existingSample.copy(message = message)
        return sampleRepository.save(updatedEntity).toModel()
    }

    suspend fun findByMessage(message: String): Flow<Sample> {
        return r2dbcEntityTemplate
            .select<SampleEntity>()
            .matching(
                query(
                    where(Sample::message.name).`is`(message)
                )
            )
            .all()
            .asFlow()
            .map { it.toModel() }
    }
}
