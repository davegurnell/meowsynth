name         in ThisBuild := "meowsynth"
organization in ThisBuild := "com.davegurnell"
version      in ThisBuild := "0.1.0"
scalaVersion in ThisBuild := "2.11.7"

lazy val meowsynth = project.in(file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(workbenchSettings : _*)
  .settings(
    scalacOptions           += "-feature",
    scalacOptions           += "-deprecation",
    persistLauncher         := true,
    persistLauncher in Test := false,
    bootSnippet             := "meowsynth.Main().main()",
    updateBrowsers         <<= updateBrowsers.triggeredBy(fastOptJS in Compile),
    resolvers               += Resolver.bintrayRepo("davegurnell", "maven"),
    libraryDependencies    ++= Seq(
      "com.davegurnell"                   %%% "compose-core"   % "0.2.0-SNAPSHOT" changing(),
      "com.github.japgolly.scalajs-react" %%% "core"           % "0.10.4",
      "com.github.japgolly.scalajs-react" %%% "extra"          % "0.10.4",
      "me.chrons"                         %%% "diode"          % "0.5.0",
      "me.chrons"                         %%% "diode-devtools" % "0.5.0",
      "me.chrons"                         %%% "diode-react"    % "0.5.0",
      "me.chrons"                         %%% "boopickle"      % "1.1.2"
    ),
    jsDependencies ++= Seq(
      ("org.webjars.bower" % "react" % "0.14.7" / "react-with-addons.js")
        .commonJSName("React")
        .minified("react-with-addons.min.js"),
      ("org.webjars.bower" % "react" % "0.14.7" / "react-dom.js")
        .commonJSName("ReactDOM")
        .minified("react-dom.min.js")
        .dependsOn("react-with-addons.js")
    )
  )

