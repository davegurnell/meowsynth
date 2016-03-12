package meowsynth

import diode._
import diode.react.ReactConnector

object MeowCircuit extends Circuit[Model] with ReactConnector[Model] {
  def initialModel = Model.create

  def actionHandler = combineHandlers(
    new MeowHandler(zoomRW(identity)((model, update) => update))
  )
}

class MeowHandler[M](modelRW: ModelRW[M, Model]) extends ActionHandler(modelRW) {
  def handle = {
    case Action.SetTagline(tagline) =>
      updated(value.copy(tagline = tagline))

    case Action.PlayerStep(state, command) =>
      println(command + " / " + state.playing)
      updated(value.copy(playing = state.playing))
  }
}
