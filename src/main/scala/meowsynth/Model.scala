package meowsynth

import compose.player._

case class Model(
  tagline   : String,
  scoreText : String,
  playing   : Set[Command.NoteOn]
)

object Model {
  def create = Model(
    tagline   = "Loading...",
    scoreText = "a b c",
    playing   = Set.empty
  )
}
