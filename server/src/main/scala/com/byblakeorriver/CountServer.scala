package com.byblakeorriver

import java.util.concurrent.TimeUnit

import io.grpc.Server
import io.grpc.netty.NettyServerBuilder

object CountServer extends App {
  var server: Server = _

  def start(): Unit = {
      server = NettyServerBuilder
        .forPort(80)
        .addService(CounterService.newInstance())
        .maxConnectionAge(1L, TimeUnit.MINUTES)
        .maxConnectionAgeGrace(30L, TimeUnit.SECONDS)
        .build()
        .start()

    sys.addShutdownHook {
      server.shutdownNow()
    }
  }
  start()
  while (true) Thread.sleep(10000L)
}
