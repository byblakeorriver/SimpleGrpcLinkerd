package com.byblakeorriver

import com.byblakorriver.countforme.countforme.{CountForMeGrpc, CountRequest, CountResponse}
import io.grpc.{ManagedChannel, Status}
import io.grpc.Status.Code
import io.grpc.netty.NettyChannelBuilder
import io.grpc.stub.StreamObserver

object CountClient extends App {
  val serverAddress: String = "count-server"
  val serverPort: Int = 80

  val channelBuilder: NettyChannelBuilder =
    NettyChannelBuilder
      .forAddress(serverAddress, serverPort)
      .usePlaintext()

  val channel: ManagedChannel = channelBuilder.build()

  val countRequest = CountRequest.defaultInstance

  val observer = new StreamObserver[CountResponse] {
    override def onNext(value: CountResponse): Unit =
      println(s"*~~~~~~~ The Count is ${value.number} ~~~~~~**")

    override def onError(t: Throwable): Unit = {
      val status = Status.fromThrowable(t)
      println(s"STATUS: ${status.getCode}")
      println(s"CAUSE: ${status.getCause}")
      println(s"DESC: ${status.getDescription}")
      status.getCode match {
        case Code.UNAVAILABLE =>
          println(s"DAT STUFF IS UNAVAILABLE")
          connect()
        case _ =>
      }
    }

    override def onCompleted(): Unit = ()
  }

  def connect(): Unit =
    Thread.sleep(10000L)
    println(s"CONNECTING")
    CountForMeGrpc.stub(channel).count(countRequest, observer)

  connect()
  while (true) {
    Thread.sleep(10000L)
  }
}
