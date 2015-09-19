package meowsynth.player.debug

import meowsynth.core._
import scala.concurrent._

object DebugPlayer {
  def play(song: Song)(implicit ec: ExecutionContext): Future[Unit] = song match {
    case Note(pitch, millis) =>
      Future {
        println("Playing note " + pitch)
        Thread.sleep(millis)
      }

    case Rest(millis) =>
      Future {
        println("Resting")
        Thread.sleep(millis)
      }

    case Sequence(a, b) =>
      for {
        _ <- play(a)
        _ <- play(b)
      } yield ()

    case Parallel(a, b) =>
      Future.sequence(Seq(
        play(a),
        play(b)
      )) map (_ => ())
  }
}
