package com.seyritey.psp.application.ping

import com.seyritey.psp.domain.ping.dto.PingData
import com.seyritey.psp.domain.ping.model.Ping
import com.seyritey.psp.domain.ping.repository.PingRepository
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import java.util.concurrent.CompletableFuture
import javax.inject.Inject

@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class PingControllerTest{
    @Inject
    @field:Client("/")
    lateinit var client: RxHttpClient
    @Inject
    lateinit var pingRepository: PingRepository

    @Test
    fun testSuccessRawPingPong() {
        val testPing = Ping(PingData("test"))
        `when`(
            pingRepository.findById(anyLong())
        ).thenReturn(
            CompletableFuture.completedFuture(testPing)
        )
        `when`(
            pingRepository.save(any())
        ).thenReturn(
            CompletableFuture.completedFuture(testPing)
        )

        //when:
        val request: HttpRequest<Any> = HttpRequest.GET("/ping")
        val rsp = client.toBlocking().exchange(request, String::class.java)

        //then:
        assertEquals(HttpStatus.OK, rsp.status)
        assertThat(rsp.body(), containsString("pong!"))
        verify(pingRepository).save(any())
        verify(pingRepository).findById(testPing.id)
    }

    @Test
    fun testSuccessJsonPingPong() {
        //when:
        val request: HttpRequest<Any> = HttpRequest.GET("/ping/json")
        val rsp = client.toBlocking().exchange(request, String::class.java)

        //then:
        assertEquals(HttpStatus.OK, rsp.status)
        assertEquals(MediaType::APPLICATION_JSON.get(), rsp.contentType.get().toString())
        assertEquals("{\"ping\":\"pong\"}", rsp.body())
    }

    @MockBean(PingRepository::class)
    fun pingRepository(): PingRepository {
        return mock(PingRepository::class.java)
    }
}
