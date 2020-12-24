scalaVersion := "2.13.4"
organization := "org.github.trickster"

lazy val root = (project in file("."))
  .settings(
    name := "hello",
    version := "0.1",// https://mvnrepository.com/artifact/org.scalatest/scalatest
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.3" % Test,
    libraryDependencies += "org.typelevel" %% "cats-parse" % "0.2.0"
  )
