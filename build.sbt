val scala3Version = "3.2.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "new",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9" % Test
  )

Test / parallelExecution := false
Test / testOptions += Tests.Argument("-oF")

scalacOptions ++= Seq(          // use ++= to add to existing options
  "-encoding", "utf8",          // if an option takes an arg, supply it on the same line
  "-feature",                   // then put the next option on a new line for easy editing
  //"-explain",
  "-language:implicitConversions"
)
