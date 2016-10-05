name := """akka-persistence"""

version := "1.0"

scalaVersion := "2.11.6"

val akkaVersion = "2.4.9-RC2"

libraryDependencies ++= Seq(
  "com.typesafe.akka"         %% "akka-actor"       % akkaVersion,
  "com.typesafe.akka"         %% "akka-persistence" % akkaVersion,
  "org.iq80.leveldb"          %  "leveldb"          % "0.7",
  "org.fusesource.leveldbjni" %  "leveldbjni-all"   % "1.8",
  "org.scalatest"             %% "scalatest"        % "2.2.4" % "test")

fork := true

javaOptions += "-Dconfig.file=conf/application.conf"