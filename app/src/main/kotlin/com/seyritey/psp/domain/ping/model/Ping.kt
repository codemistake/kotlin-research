package com.seyritey.psp.domain.ping.model

import com.seyritey.psp.domain.ping.dto.PingData
import com.seyritey.psp.infrastructure.entity.BaseEntity
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
open class Ping (
    @Type(type = "jsonb")
    @Column(columnDefinition = "json")
    open val data: PingData
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    open val id: Long = 0

    @CreationTimestamp
    @Column(name = "created_at", insertable = false, updatable = false)
    open val createdAt: Date? = null
}