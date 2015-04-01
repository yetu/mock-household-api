name := """mock-household-api"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  cache, json,
  "com.yetu" %% "siren-scala" % "0.4.0"
)
