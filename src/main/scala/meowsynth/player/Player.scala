package meowsynth.player

import meowsynth.core._
import scala.concurrent._

trait Player {
  def play(song: Song)(implicit ec: ExecutionContext): Future[Unit]
}
