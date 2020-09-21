name := "cats-effects"

version := "0.1"

scalaVersion := "2.13.3"

libraryDependencies += "org.typelevel" %% "cats-effect" % "2.1.4" withSources() withJavadoc()

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-unchecked",
  "-language:postfixOps",
  "-language:higherKinds",
  "-Ypartial-unification")
