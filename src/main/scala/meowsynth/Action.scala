package meowsynth

import compose.player._

sealed trait Action

object Action {
  final case class SetTagline(tagline: String) extends Action
  final case class PlayerStep(state: WebAudioPlayer.State, command: Command)
}
