val scala3Version = "3.1.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "new",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
  )
