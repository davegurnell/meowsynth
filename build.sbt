enablePlugins(ScalaJSPlugin)
workbenchSettings

name                    := "meowsynth"
organization            := "io.underscore"
version                 := "0.0.1"

scalaVersion            := "2.11.6"
scalacOptions           += "-feature"
//scalacOptions           += "-deprecation"

persistLauncher         := true
persistLauncher in Test := false
libraryDependencies     += "org.scala-js" %%% "scalajs-dom" % "0.8.1"
bootSnippet             := "meowsynth.Main.main()"
updateBrowsers         <<= updateBrowsers.triggeredBy(fastOptJS in Compile)
