package com.yorizori.yoremo.adapter.postgresql.sample


import com.yorizori.yoremo.adapter.postgresql.common.BaseEntity
import com.yorizori.yoremo.model.Sample
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table


@Table("sample")
data class SampleEntity(
    @Id
    val id: Long? = null,
    val message: String,
) : BaseEntity() {

    companion object {
        fun from(sample: Sample): SampleEntity {
            return SampleEntity(id = sample.id, message = sample.message)
        }
    }

    fun toModel(): Sample {
        return Sample(
            id = id,
            message = message,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }
}
