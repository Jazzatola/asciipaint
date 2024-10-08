import sbt.Keys.*

lazy val root = (project in file(".")).
  settings(
    name := "asciipaint",
    version := "1.0",
    scalaVersion := "3.5.1"
  )

  libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % "test"
