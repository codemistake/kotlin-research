package com.seyritey.psp.application.ping

import com.seyritey.psp.domain.ping.PingService
import com.seyritey.psp.domain.ping.dto.PingData
import com.seyritey.psp.domain.ping.model.Ping
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.vertx.reactivex.pgclient.PgPool
import javax.inject.Inject

@Controller("/ping")
class PingController(
    @Inject
    val pingService: PingService,
    @Inject
    val client: PgPool
) {
    @Get("/")
    @Produces(MediaType.TEXT_PLAIN)
    fun index(): String {
        val id = pingService.save(
            Ping(
                PingData("Hello")
            )
        ).get().id

        val ping = pingService.findById(id).get()

        return "pong! " + ping?.data?.message + " date: " + ping?.createdAt.toString()
    }

    @Get("/json")
    @Produces(MediaType.APPLICATION_JSON)
    fun hello(): HttpResponse<*> {
        client.query("INSERT INTO ping(id) VALUES (DEFAULT)").rxExecute().blockingGet()

        return HttpResponse.ok(mapOf("ping" to "pong"))
    }
}