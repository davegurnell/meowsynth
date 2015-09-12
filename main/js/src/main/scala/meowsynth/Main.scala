package meowsynth

import meowsynth.song._
import meowsynth.player.webaudio._
import scala.concurrent._
import scala.concurrent.duration._

import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

object Main extends JSApp {
  @JSExport def main(): Unit = {
    WebAudioPlayer.play(Song.demo)
  }
}
