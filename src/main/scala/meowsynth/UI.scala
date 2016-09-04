package meowsynth

import compose.core._
import diode.react.ModelProxy
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom._
import scalajs.js

object UI extends KeyboardPositions {
  val root = ReactComponentB[ModelProxy[Model]]("meowsynth")
    .render_P { proxy =>
      <.div(
        ^.className := "background",
        <.div(
          ^.className := "container",
          title,
          tagline(proxy),
          songMenu(proxy),
          keyboard(proxy),
          credits
        )
      )
    }
    .build

  val title =
    <.h1(
      ^.className := "title",
      "Meowsynth"
    )

  val tagline = ReactComponentB[ModelProxy[Model]]("tagline")
    .render_P { proxy =>
      <.p(
        ^.className := "tagline",
        proxy.value.tagline
      )
    }
    .build

  val songMenu = ReactComponentB[ModelProxy[Model]]("song-menu")
    .render_P { proxy =>
      def playButton(song: Song) =
        <.button(
          ^.className := (if(proxy.value.playing.contains(song)) "play playing" else "play"),
          ^.onClick ==> { evt: ReactEvent => proxy.dispatch(Action.Play(song)) },
          "PLAY: " + song.title
        )

      val stopButton =
        <.button(
          ^.className := "stop",
          ^.onClick ==> { evt: ReactEvent => proxy.dispatch(Action.Stop) },
          "STAAAHP"
        )

      <.div(
        <.ul(
          ^.className := "song-menu",
          proxy.value.menu.map(song => <.li(playButton(song))),
          <.li(stopButton)
        )
      )
    }
    .build

  val keyboard = ReactComponentB[ModelProxy[Model]]("keyboard")
    .render_P { proxy =>
      val className: String =
        if(proxy.value.playing.nonEmpty) "keyboard keyboard-active" else "keyboard keyboard-hidden"

      val notes: Seq[Pitch] =
        proxy.value.activeNotes.toSeq.map(_.pitch).sortBy(_.value)

      val right: Option[Pitch] =
        notes.lastOption

      val left: Option[Pitch] =
        notes.headOption.filterNot(l => Some(l) == right)

      <.div(
        ^.className   := className,
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

  val credits =
    <.div(
      ^.className := "credits",
      <.p(
        "Copyright 2015-16 ", <.a(^.href := "http://davegurnell.com", "Dave Gurnell"), ". "
      ),
      <.p(
        "Built with ", <.a(^.href := "http://scala-js.org", "ScalaJS"),
        " and ", <.a(^.href := "https://github.com/underscoreio/compose", "Compose"), ". ",
        "Grab the code from ", <.a(^.href := "https://github.com/davegurnell/meowsynth", "Github"), "."
      ),
      <.p(
        "Background image from ",
        <.a(
          ^.href := "http://www.catsonsynthesizersinspace.com/post/99721923347",
          "Cats on Synthesizers in Space"
        ), "."
      )
    )

  val component = MeowCircuit
    .connect(model => model).apply(proxy => root(proxy))

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
    0,   // octave 0
    0,   // octave 1
    0,   // octave 2
    176, // octave 3
    351, // octave 4
    525, // octave 5
    697  // octave 6
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