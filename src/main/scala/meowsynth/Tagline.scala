package meowsynth

object Tagline {
  val taglines = Seq(
    "Meow meow meow!",
    "You make me want to meow.",
    "What's new, pussycat?",
    "Purrfect harmony.",
    "I'd like to teach the world to meow.",
    "Built with cats!",
    "Now with twice the meow."
  )

  def random = taglines((math.random * taglines.length).toInt)
}
