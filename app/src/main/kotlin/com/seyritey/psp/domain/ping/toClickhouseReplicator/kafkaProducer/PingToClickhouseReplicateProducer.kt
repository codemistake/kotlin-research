package com.seyritey.psp.domain.ping.toClickhouseReplicator.kafkaProducer

import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.Topic

@KafkaClient(
    id = "ping-client",
    acks = KafkaClient.Acknowledge.ALL
)
interface PingToClickhouseReplicateProducer {
    @Topic("ping-to-clickhouse")
    fun sendPing(@KafkaKey key: String, message: String)
}