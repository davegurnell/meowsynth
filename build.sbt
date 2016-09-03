name         in ThisBuild := "meowsynth"
organization in ThisBuild := "com.davegurnell"
version      in ThisBuild := "0.1.0"
scalaVersion in ThisBuild := "2.11.8"

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
    resolvers               += Resolver.sonatypeRepo("snapshots"),
    libraryDependencies    ++= Seq(
      "io.underscore"                     %%% "compose-core"     % "0.4.0",
      "io.underscore"                     %%% "compose-player"   % "0.4.0",
      "io.underscore"                     %%% "compose-examples" % "0.4.0",
      "com.github.japgolly.scalajs-react" %%% "core"             % "0.11.1",
      "com.github.japgolly.scalajs-react" %%% "extra"            % "0.11.1",
      "com.lihaoyi"                       %%% "scalatags"        % "0.6.0",
      "me.chrons"                         %%% "diode"            % "1.0.0",
      "me.chrons"                         %%% "diode-devtools"   % "1.0.0",
      "me.chrons"                         %%% "diode-react"      % "1.0.0",
      "me.chrons"                         %%% "boopickle"        % "1.1.2"
    ),
    jsDependencies ++= Seq(
      ("org.webjars.bower" % "react" % "0.14.7" / "react-with-addons.js").commonJSName("React").minified("react-with-addons.min.js"),
      ("org.webjars.bower" % "react" % "0.14.7" / "react-dom.js").commonJSName("ReactDOM").minified("react-dom.min.js").dependsOn("react-with-addons.js")
    )
  )

