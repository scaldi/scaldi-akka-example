name := "scaldi-akka-example"
 
version := "1.0-SNAPSHOT"
 
scalaVersion := "2.11.0"
 
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.2",
  "org.scaldi" %% "scaldi-akka" % "0.3.2",
  "org.scala-lang" % "scala-reflect" % scalaVersion.value
)

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"