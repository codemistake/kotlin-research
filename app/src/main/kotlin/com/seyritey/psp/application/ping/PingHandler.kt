package com.seyritey.psp.application.ping

import com.seyritey.psp.domain.ping.PingService
import com.seyritey.psp.domain.ping.dto.PingData
import com.seyritey.psp.domain.ping.model.Ping
import com.seyritey.thrift.psp.ping.EntryDto
import com.seyritey.thrift.psp.ping.PingService.Iface as PingServiceIf
import io.vertx.reactivex.pgclient.PgPool
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class PingHandler(
    @Inject
    val pingService: PingService,
    @Inject
    val client: PgPool
) : PingServiceIf
{
    private val logger: Logger = LoggerFactory.getLogger(PingHandler::class.java)

    override fun ping(p0: String?, p1: EntryDto?): String {
        logger.info("Пришел запрос ping")
        val id = pingService.save(
            Ping(
                PingData("Hello")
            )
        ).get().id

        val ping = pingService.findById(id).get()

        return "pong! " + ping?.data?.message + " date: " + ping?.createdAt.toString()
    }

    override fun pingJson(): String {
        client.query("INSERT INTO ping(id) VALUES (DEFAULT)").rxExecute().blockingGet()

        return mapOf("ping" to "pong").toString()
    }
}