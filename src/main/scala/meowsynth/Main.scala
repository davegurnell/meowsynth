package meowsynth

import japgolly.scalajs.react._
import compose.core._
import compose.core.Pitch._
import compose.core.Score.{Rest => r}
import compose.player._
import meowsynth.Action._
import scala.concurrent._
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

object Main extends JSApp {
  @JSExport def main(): Unit = {
    val player  = new WebAudioPlayer(
      sampleUrl = "/target/scala-2.11/classes/samples/bell.wav",
      callback  = Some((state, command) => {
        MeowCircuit.dispatch(PlayerStep(state, command))
      })
    )

    def chordProgression = {
      ( C3.q  | E3.q  | G3.q  ) +
      ( C3.q  | F3.q  | A3.q  ) +
      ( D3.q  | F3.q  | As3.q ) +
      ( Ds3.q | G3.q  | As3.q ) +
      ( Ds3.q | Gs3.q | C4.q  ) +
      ( F3.q  | Gs3.q | Cs4.q ) +
      ( Fs3.q | As3.q | Cs4.q ) +
      ( Fs3.q | B3.q  | Ds4.q ) +
      ( Gs3.q | B3.q  | E4.q  ) +
      ( A3.q  | Cs4.q | E4.q  ) +
      ( A3.q  | D4.q  | Fs4.q ) +
      ( B3.q  | D4.q  | G4.q  ) +
      ( C4.w  | E4.w  | G4.w  )
    }

    def scales = {
      Score.Par(
        (0 to 100).map(n => Score.Note(Pitch.C0 transpose n, Duration.Eighth)).foldLeft[Score](Score.Empty)(Score.Seq.apply _),
        (0 to 100).map(n => Score.Note(Pitch.G0 transpose n, Duration.Eighth)).foldLeft[Score](Score.Empty)(Score.Seq.apply _)
      )
    }

    def smokeOnTheWater = {
      (
        (E4.q + G4.q + A4.q.dotted + E4.q + G4.q + As4.e + A4.h) |
        (B3.q + D4.q + E4.q.dotted + B3.q + D4.q + F4.e  + E4.h) |
        (E3.q + G3.q + A3.q.dotted + E3.q + G3.q + As3.e + A3.h)
      ) + (
        (E4.q + G4.q + A4.q.dotted + G4.q + E4.h.doubleDotted) |
        (B3.q + D4.q + E4.q.dotted + D4.q + B3.h.doubleDotted) |
        (E3.q + G3.q + A3.q.dotted + G3.q + E3.h.doubleDotted)
      )
    } transpose -12

    def twelveBar = {
      val bar =
        ( E3.q | B3.q  ) +
        ( E3.s | B3.s  ) +
        ( E3.q | Cs4.q ) +
        ( E3.s | B3.s  )

      (bar transpose 0 repeat 4) +
      (bar transpose 5 repeat 2) +
      (bar transpose 0 repeat 2) +
      (bar transpose 7 repeat 1) +
      (bar transpose 5 repeat 1) +
      (bar transpose 0 repeat 2)
    }

    player.play(smokeOnTheWater, Tempo(200))

    MeowCircuit.dispatch(Motd("Meow meow meow!"))
    UI.mount()
  }
}
