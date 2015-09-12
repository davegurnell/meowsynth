package meowsynth

import meowsynth.player.debug._
import meowsynth.song._
import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App {
  Await.result(
    DebugPlayer.play(Song.demo),
    1000.seconds)
}
