import sbt.Keys._

ThisBuild / scalaVersion     := "2.12.10"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.traveldio"
ThisBuild / organizationName := "traveldio"

lazy val root = (project in file("."))
  .settings(
    name := "redis-stream-handler",

    libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-sql" % "2.4.1" % "provided",
      "org.apache.spark" %% "spark-streaming" % "2.4.1" % "provided",
      "com.redislabs" % "spark-redis" % "2.4.1",
      "org.scalatest" %% "scalatest" % "3.2.2" % Test
    ),
    assemblyJarName in assembly := "redishandler-assembly.jar",
    assemblyMergeStrategy in assembly := {
      case PathList("META-INF", xs @ _*) => MergeStrategy.discard
      case "application.conf"  => MergeStrategy.concat
      case x => MergeStrategy.first
    }
  )