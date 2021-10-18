package com.seyritey.psp.domain.ping.toClickhouseReplicator.entityListener

import com.fasterxml.jackson.annotation.JsonProperty

data class PingDto(
    val data: PingDataDto,
    @JsonProperty("created_at")
    val createdAt: Long?
) {
    data class PingDataDto(val message: String)
}
