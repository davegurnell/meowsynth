package meowsynth

import compose.player._
import diode._
import diode.react.ReactConnector
import scala.concurrent._
import scalajs.concurrent.JSExecutionContext.Implicits.queue

object MeowCircuit extends Circuit[Model] with ReactConnector[Model] {
  def initialModel = Model.empty

  def actionHandler = composeHandlers(
    new MeowHandler(zoomRW(identity)((model, update) => update))
  )

  val player = new WebAudioPlayer(
    sampleUrl = "/target/scala-2.11/classes/samples/meow.wav",
    callback  = Some((state, command) => dispatch(Action.PlayerStep(state, command)))
  )
}

class MeowHandler[M](modelRW: ModelRW[M, Model]) extends ActionHandler(modelRW) {
  def handle = {
    case Action.SetTagline(tagline) =>
      updated(value.copy(tagline = tagline))

    case Action.Play(song) =>
      MeowCircuit.player.play(song.score, song.tempo)
      updated(value.copy(playing = Some(song)))

    case Action.Stop =>
      updated(value.copy(playing = None))

    case Action.PlayerStep(state, command) =>
      println(command + " / " + state.playing)
      updated(value.copy(activeNotes = state.playing))
  }
}
