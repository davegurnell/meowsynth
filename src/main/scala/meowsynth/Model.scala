package meowsynth

import compose.player._

case class Model(
  motd      : String,
  scoreText : String,
  playing   : Set[Command.NoteOn]
)

object Model {
  def create = Model(
    motd      = "Hello world!",
    scoreText = "a b c",
    playing   = Set.empty
  )
}
