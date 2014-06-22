name := "scaldi-akka-example"
 
version := "1.0-SNAPSHOT"
 
scalaVersion := "2.11.1"
 
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.3",
  "org.scaldi" %% "scaldi-akka" % "0.4",
  "org.scala-lang" % "scala-reflect" % scalaVersion.value
)

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"