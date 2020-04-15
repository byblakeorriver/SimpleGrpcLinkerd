package com.byblakeorriver

import monix.execution.ExecutionModel.AlwaysAsyncExecution
import monix.execution.Scheduler
import monix.execution.schedulers.SchedulerService

object CountScheduler {
  implicit lazy val taskScheduler: SchedulerService =
    Scheduler.computation(
      name = "task-pool",
      parallelism = 16,
      executionModel = AlwaysAsyncExecution,
      reporter = (ex: Throwable) => ()
    )

}
