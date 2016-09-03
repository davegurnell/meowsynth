package meowsynth

import compose.core._
import compose.player._

case class Song(
  title: String,
  score: Score,
  tempo: Tempo
)

case class Model(
  tagline: String,
  menu: Seq[Song],
  playing: Option[Song],
  activeNotes: Set[Command.NoteOn]
)

object Model {
  val empty = Model(
    tagline = "Loading...",
    menu = Seq(
      Song("Smoke on the Water" , compose.examples.all.smokeOnTheWater,     Tempo(120)),
      Song("Twelve Bar Blues"   , compose.examples.all.twelveBarBlues,      Tempo(120)),
      Song("Bumblebee (??)"     , compose.examples.all.bumblebee,           Tempo(300)),
      Song("Jump"               , compose.examples.all.jump.transpose(-12), Tempo(120)),
      Song("Freebird!"          , compose.examples.all.freebird,            Tempo(180))
    ),
    playing = Option.empty,
    activeNotes = Set.empty
  )
}
