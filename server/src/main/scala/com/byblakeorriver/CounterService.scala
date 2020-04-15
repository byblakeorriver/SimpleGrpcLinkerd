package com.byblakeorriver

import com.byblakorriver.countforme.countforme.{CountForMeGrpc, CountRequest, CountResponse}
import io.grpc.stub.StreamObserver
import io.grpc.{BindableService, ServerServiceDefinition}
import com.byblakeorriver.CountScheduler.taskScheduler

object CounterService {
  def newInstance(): BindableService = new CounterImpl

  private class CounterImpl extends CountForMeGrpc.CountForMe with BindableService {
    override def count(request: CountRequest, responseObserver: StreamObserver[CountResponse]): Unit = {
      var n: Long = 0
      while (true) {
        Thread.sleep(1000L)
        responseObserver.onNext(CountResponse(n))
        n += 1
      }
    }

    override def bindService(): ServerServiceDefinition =
      CountForMeGrpc.bindService(this, taskScheduler)
  }
}
