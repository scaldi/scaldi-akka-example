name := "scaldi-akka-example"
 
version := "1.0-SNAPSHOT"
 
scalaVersion := "2.10.3"
 
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.2.3",
  "org.scaldi" %% "scaldi-akka" % "0.3.1",
  "org.scala-lang" % "scala-reflect" % scalaVersion.value
)

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"