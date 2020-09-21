import cats.effect.{ContextShift, IO}

import scala.concurrent.ExecutionContext

object IOAsync {

  implicit val cs: ContextShift[IO] = IO.contextShift(ExecutionContext.global)

  class Task(val name: String)

  val io: IO[Task] = IO.async( k => {
    val task = new Task("task 1")
    println(s"${Thread.currentThread().getName} = executing ${task.name}")
    k(Left(new IllegalArgumentException("new Exception")))
  })


  def main(args: Array[String]): Unit = {
    val value = IO.shift.flatMap(_ => io)
    value.unsafeRunAsync {
      case Right(task) => println(s"${Thread.currentThread().getName} = ${task.name} finished")
      case Left(ex) => println(s"${Thread.currentThread().getName} = ${ex.getMessage}")
    }

    val result = io.unsafeRunSync()
    println(s"${Thread.currentThread().getName} = ${result.name} finished")

  }
}
