name := "scaldi-akka-example"
version := "1.0-SNAPSHOT"
 
scalaVersion := "2.11.5"
 
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.1",
  "com.typesafe.akka" %% "akka-remote" % "2.4.1",
  "org.scaldi" %% "scaldi-akka" % "0.5.7",
  "org.scala-lang" % "scala-reflect" % scalaVersion.value
)