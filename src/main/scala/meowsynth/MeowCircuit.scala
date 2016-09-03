package meowsynth

import compose.core.Score
import compose.player.WebAudioPlayer
import diode._
import diode.react.ReactConnector
import scala.concurrent._
import scalajs.concurrent.JSExecutionContext.Implicits.queue

object MeowCircuit extends Circuit[Model] with ReactConnector[Model] {
  def initialModel = Model.empty

  def actionHandler = composeHandlers(
    new MeowHandler(zoomRW(identity)((model, update) => update))
  )

  // TODO: This is hideous. Work out how to remove it.
  var playing: Option[Score] = None

  val player = new WebAudioPlayer(
    sampleUrl = "samples/meow.wav",
    callback  = (state, command) => {
      if(playing.contains(state.score)) {
        dispatch(Action.PlayerStep(state, command))
        state
      } else {
        state.stop
      }
    }
  )
}

class MeowHandler[M](modelRW: ModelRW[M, Model]) extends ActionHandler(modelRW) {
  def handle = {
    case Action.SetTagline(tagline) =>
      updated(value.copy(tagline = tagline))

    case Action.Play(song) =>
      MeowCircuit.playing = Some(song.score)
      MeowCircuit.player.play(song.score, song.tempo)
      updated(value.copy(playing = Some(song)))

    case Action.Stop =>
      MeowCircuit.playing = None
      updated(value.copy(playing = None))

    case Action.PlayerStep(state, command) =>
      updated(value.copy(activeNotes = state.playing))
  }
}
