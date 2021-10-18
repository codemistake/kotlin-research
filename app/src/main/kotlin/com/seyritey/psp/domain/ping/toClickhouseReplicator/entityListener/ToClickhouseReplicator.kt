package com.seyritey.psp.domain.ping.toClickhouseReplicator.entityListener

import com.seyritey.psp.domain.ping.model.Ping
import com.seyritey.psp.domain.ping.toClickhouseReplicator.kafkaProducer.PingToClickhouseReplicateProducer
import io.micronaut.data.annotation.event.PostPersist
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ToClickhouseReplicator(
    @Inject
    val replicateProducer: PingToClickhouseReplicateProducer
) {
    @PostPersist
    fun postPersist(ping: Ping) {
        replicateProducer.sendPing(
            UUID.randomUUID().toString(),
            PingEntityToQueueMessage().toMessage(ping)
        )
    }
}