import cats.effect.IO

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._
import scala.language.postfixOps

object IOExample {

  implicit val ec: ExecutionContext = ExecutionContext.global

  val ioa: IO[Unit] = IO { println("hey!") }

  val programIO: IO[Unit] =
    for {
      _ <- ioa
      _ <- ioa
    } yield ()


  val future: Future[Unit] = Future {
    println("hey!")
  }

  val programFuture: Future[Unit] =
    for {
      _ <- future
      _ <- future
    } yield ()

  def main(args: Array[String]): Unit = {

    println("Futures:")
    Await.result(programFuture, 0 nanos)

    println("IO:")
    programIO.unsafeRunSync()
  }

}
