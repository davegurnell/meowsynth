package meowsynth.player.webaudio

import meowsynth.core._

import scala.concurrent._
import scala.scalajs.js
import scala.scalajs.js.typedarray.ArrayBuffer
import org.scalajs.dom
import org.scalajs.dom.{
  AudioBuffer,
  AudioContext,
  Event,
  XMLHttpRequest,
  setTimeout
}

object WebAudioPlayer {

  def play(song: Song)(implicit ec: ExecutionContext): Future[Unit] = {
    val ctx = new AudioContext()

    for {
      buffer <- loadSound(ctx, "samples/meow.wav")
      _      <- playSong(ctx, buffer, song)
    } yield ()
  }

  def loadSound(ctx: AudioContext, url: String): Future[AudioBuffer] = {
    val request = new XMLHttpRequest()
    val promise = Promise[AudioBuffer]

    request.open("GET", url, true)
    request.responseType = "arraybuffer"

    def response = request.response.asInstanceOf[ArrayBuffer]

    request.onload = (evt: Event) =>
      ctx.decodeAudioData(response, (buffer: AudioBuffer) => promise success buffer)

    request.send()

    promise.future
  }

  def playSong(ctx: AudioContext, buffer: AudioBuffer, song: Song)(implicit ec: ExecutionContext): Future[Unit] = song match {
    case Note(pitch, millis) =>
      Future {
        println("Playing note " + pitch)
        var source = ctx.createBufferSource()
        source.buffer = buffer
        source.connect(ctx.destination)
        source.playbackRate.setValueAtTime(pitch.playbackRate, 0)
        source.start(0)
      }.flatMap(_ => wait(millis))

    case Rest(millis) =>
      Future {
        println("Resting")
      }.flatMap(_ => wait(millis))

    case Sequence(a, b) =>
      for {
        _ <- playSong(ctx, buffer, a)
        _ <- playSong(ctx, buffer, b)
      } yield ()

    case Parallel(a, b) =>
      Future.sequence(Seq(
        playSong(ctx, buffer, a),
        playSong(ctx, buffer, b)
      )) map (_ => ())
  }

  def wait(millis: Int)(implicit ec: ExecutionContext): Future[Unit] = {
    println("Waiting " + millis)
    val promise = Promise[Unit]
    setTimeout((() => promise.success(())), millis)
    promise.future
  }
}