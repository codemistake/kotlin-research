package com.seyritey.psp.domain.ping.repository

import com.seyritey.psp.domain.ping.model.Ping
import io.micronaut.context.annotation.Executable
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.async.AsyncCrudRepository
import java.util.concurrent.CompletableFuture

@Repository
interface PingRepository : AsyncCrudRepository<Ping, Long> {
    @Executable
    fun save(entity: Ping): CompletableFuture<Ping>
}