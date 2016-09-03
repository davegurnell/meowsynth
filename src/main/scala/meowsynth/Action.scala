package meowsynth

import compose.player._
import diode.ActionType

sealed trait Action

object Action {
  final case class SetTagline(tagline: String) extends Action
  final case class Play(song: Song) extends Action
  case object Stop extends Action
  final case class PlayerStep(state: WebAudioPlayer.State, command: Command) extends Action

  implicit val actionType: ActionType[Action] =
    new ActionType[Action] {}
}
