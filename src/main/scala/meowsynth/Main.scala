package meowsynth

import japgolly.scalajs.react._
import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

object Main extends JSApp {
  @JSExport def main(): Unit = {
    MeowCircuit.dispatch(Action.SetTagline(Tagline.random))
    UI.mount()
  }
}
