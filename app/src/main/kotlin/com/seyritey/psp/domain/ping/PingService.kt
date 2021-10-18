package com.seyritey.psp.domain.ping

import com.seyritey.psp.domain.ping.model.Ping
import com.seyritey.psp.domain.ping.repository.PingRepository
import java.util.concurrent.CompletableFuture
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PingService(
    @Inject
    val pingRepository: PingRepository
) {
    fun save(ping: Ping): CompletableFuture<Ping> {
        return pingRepository.save(ping)
    }

    fun findById(id: Long): CompletableFuture<Ping?> {
        return pingRepository.findById(id)
    }
}