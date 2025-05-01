package com.yorizori.yoremo.adapter.postgresql.common

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import java.time.LocalDateTime

abstract class BaseEntity {
    @CreatedDate
    @Column("created_at")
    var createdAt: LocalDateTime? = LocalDateTime.now()
        protected set

    @LastModifiedDate
    @Column("updated_at")
    var updatedAt: LocalDateTime? = LocalDateTime.now()
        protected set
}
