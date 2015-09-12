lazy val main = crossProject.
  crossType(CrossType.Full).
  settings(
    name          := "meowsynth",
    organization  := "io.underscore",
    scalaVersion  := "2.11.6",
    scalacOptions += "-feature"
  ).jvmSettings(
    initialCommands in console := """
      import meowsynth._
    """.trim.stripMargin
  ).jsSettings(
    persistLauncher         := true,
    persistLauncher in Test := false
  )

lazy val mainJVM = main.jvm

lazy val mainJS  = main.js

run     <<= run     in (mainJVM, Compile)

console <<= console in (mainJVM, Compile)