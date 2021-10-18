package com.seyritey.psp.domain.ping.toClickhouseReplicator.entityListener

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.seyritey.psp.domain.ping.model.Ping

class PingEntityToQueueMessage(
    private val mapper: ObjectMapper = jacksonObjectMapper()
) {
    fun toMessage(ping: Ping): String {
        val pingDto = PingDto(
            data = PingDto.PingDataDto(
                message = ping.data.message
            ),
            createdAt = ping.createdAt?.time
        )

        val messageNode: ObjectNode = mapper.createObjectNode()
        messageNode.put("table", "ping")
        messageNode.set<JsonNode>("entity", mapper.convertValue(pingDto,JsonNode::class.java))

        return messageNode.toString()
    }
}