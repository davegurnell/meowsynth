package meowsynth

import compose.core._
import diode.react.ModelProxy
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom._

object UI extends KeyboardPositions {
  val root = ReactComponentB[ModelProxy[Model]]("meowsynth")
    .render_P { proxy =>
      <.div(
        ^.className := "container",
        <.h1(
          ^.className := "title",
          "Meowsynth"
        ),
        <.p(
          ^.className := "tagline",
          proxy.value.tagline
        ),
        // editor(proxy),
        visualizer(proxy)
        // transport(proxy)
      )
    }
    .build

  val editor = ReactComponentB[ModelProxy[Model]]("editor")
    .render_P { proxy =>
      <.textarea(
        ^.className := "editor",
        ^.value := proxy.value.scoreText
      )
    }
    .build

  val visualizer = ReactComponentB[ModelProxy[Model]]("visualizer")
    .render_P { proxy =>
      val notes = proxy.value.playing.toSeq.map(_.pitch).sortBy(_.value)
      val right = notes.lastOption
      val left  = notes.headOption.filterNot(l => Some(l) == right)
      <.div(
        ^.className := "visualizer",
        <.div(
          ^.className := "paw right-paw",
          ^.top       := pawTop(right),
          ^.left      := pawLeft(right)
          // s"${pawTop(right)},${pawLeft(right)}"
        ),
        <.div(
          ^.className := "paw left-paw",
          ^.top       := pawTop(left),
          ^.left      := pawLeft(left)
          // s"${pawTop(left)},${pawLeft(left)}"
        )
      )
    }
    .build

  val transport = ReactComponentB[ModelProxy[Model]]("transport")
    .render_P { proxy =>
      <.div(
        <.button("STAWP"),
        <.button("PLAAY")
      )
    }
    .build

  val component = MeowCircuit
    .connect(model => model)(proxy => root(proxy))

  def mount() = ReactDOM.render(component, document.getElementById("ui"))
}

trait KeyboardPositions {
  private val notePositions = Seq(
    (0   , 240), // C
    (10  , 180), // C#
    (28  , 240), // D
    (44  , 180), // D#
    (55  , 240), // E
    (76  , 240), // F
    (88  , 180), // F#
    (103 , 240), // G
    (116 , 180), // G#
    (128 , 240), // A
    (143 , 180), // A#
    (156 , 240)  // B
  )

  private val octavePositions = Seq(
    0,
    0,
    0,
    176,
    351,
    525,
    697
  )

  private val baseLeft = 65

  private val restingLeft = 512
  private val restingTop = 300

  def pawLeft(pitch: Option[Pitch]): Int = pitch match {
    case Some(Pitch(value)) =>
      val octave = (value + 12 * 4 + 9) / 12
      val note   = (value + 12 * 4 + 9) % 12
      baseLeft +
        notePositions(note)._1 +
        octavePositions(math.max(0, math.min(octavePositions.length - 1, octave)))

    case None =>
      restingLeft
  }

  def pawTop(pitch: Option[Pitch]): Int = pitch match {
    case Some(Pitch(value)) =>
      val note   = (value + 12 * 4 + 9) % 12
      notePositions(note)._2

    case None =>
      restingTop
  }
}