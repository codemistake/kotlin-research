package com.seyritey.psp.infrastructure

import com.seyritey.psp.application.ping.PingHandler
import com.seyritey.thrift.psp.ping.PingService
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.discovery.event.ServiceReadyEvent
import io.micronaut.scheduling.annotation.Async
import org.apache.thrift.TMultiplexedProcessor
import org.apache.thrift.protocol.TBinaryProtocol
import org.apache.thrift.protocol.TProtocolFactory
import org.apache.thrift.server.TServer
import org.apache.thrift.server.TSimpleServer
import org.apache.thrift.transport.TServerSocket
import org.apache.thrift.transport.TServerTransport
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
open class ThriftServer(
    var pingHandler: PingHandler
) : ApplicationEventListener<ServiceReadyEvent?> {
    private val logger: Logger = LoggerFactory.getLogger(ThriftServer::class.java)
    private val port = 9080

    private fun registerHandlers(multiplex: TMultiplexedProcessor) {
        multiplex.registerProcessor("PingService", PingService.Processor(pingHandler))
    }

    @Async
    override fun onApplicationEvent(event: ServiceReadyEvent?) {
        try {
            val serverTransport: TServerTransport = TServerSocket(port)
            val protocolFactory: TProtocolFactory = TBinaryProtocol.Factory()
            val multiplex = TMultiplexedProcessor()

            registerHandlers(multiplex)
            TSimpleServer(TServer.Args(serverTransport).protocolFactory(protocolFactory).processor(multiplex)).serve()

            logger.info("Thrift server was started. Port: $port")
        } catch (e: Exception) {
            logger.error(e.message)
            throw e
        }
    }
}