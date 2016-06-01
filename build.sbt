name := "challenger"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.scalanlp" % "breeze_2.11" % "0.12",
  "org.scalanlp" % "breeze-natives_2.11" % "0.12",
  "org.scalatest" % "scalatest_2.11" % "2.2.6" % Test
)

// fork := true

// javaOptions in run += "-Xmx8G"

mainClass in (Compile, run) := Some("com.challenger.Driver")
