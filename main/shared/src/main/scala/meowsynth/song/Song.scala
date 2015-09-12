package meowsynth.song

sealed trait Song
final case class Note(pitch: Pitch, millis: Int) extends Song
final case class Rest(millis: Int) extends Song
final case class Sequence(a: Song, b: Song) extends Song
final case class Parallel(a: Song, b: Song) extends Song

final case class Pitch(number: Int)
final case class Length(number: Int)


object Song {
  val demo: Song =
    Sequence(Note(Pitch(0), 1000),
      Sequence(Note(Pitch(1), 1000),
        Note(Pitch(2), 1000)))
}
