package meowsynth.core

sealed trait Song
final case class Note(pitch: Pitch, millis: Int) extends Song
final case class Rest(millis: Int) extends Song
final case class Sequence(a: Song, b: Song) extends Song
final case class Parallel(a: Song, b: Song) extends Song

final case class Pitch(number: Int) {
  def playbackRate =
    math.pow(2, (number - 60) / 12.0)
}

final case class Length(number: Int)


object Song {
  val demo: Song =
    Sequence(
      Note(Pitch(60), 1000),
      Sequence(
        Parallel(
          Note(Pitch(64), 1000),
          Note(Pitch(67), 1000)
        ),
        Parallel(
          Note(Pitch(67), 1000),
          Note(Pitch(72), 1000)
        )
      )
    )
}
