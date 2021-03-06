val mainScala = "2.13.1"
val allScala  = Seq("2.11.12", "2.12.10", mainScala)

inThisBuild(
  List(
    organization := "nl.vroste",
    version := "0.0.1",
    homepage := Some(url("https://github.com/svroonland/zio-amqp")),
    licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
    scalaVersion := mainScala,
    crossScalaVersions := allScala,
    parallelExecution in Test := false,
    fork in Test := true,
    fork in run := true,
    publishMavenStyle := true,
    publishArtifact in Test := false,
    assemblyJarName in assembly := "zio-amqp-" + version.value + ".jar",
    test in assembly := {},
    target in assembly := file(baseDirectory.value + "/../bin/"),
    assemblyMergeStrategy in assembly := {
      case PathList("META-INF", xs @ _*)       => MergeStrategy.discard
      case n if n.startsWith("reference.conf") => MergeStrategy.concat
      case _                                   => MergeStrategy.first
    },
    bintrayOrganization := Some("vroste"),
    bintrayReleaseOnPublish in ThisBuild := false,
    bintrayPackageLabels := Seq("zio", "amqp")
  )
)

name := "zio-amqp"
scalafmtOnCompile := true

libraryDependencies ++= Seq(
  "dev.zio"        %% "zio-streams"                 % "1.0.0-RC18-1",
  "dev.zio"        %% "zio-test"                    % "1.0.0-RC18-1" % "test",
  "dev.zio"        %% "zio-test-sbt"                % "1.0.0-RC18-1" % "test",
  "dev.zio"        %% "zio-interop-java"            % "1.1.0.0-RC6",
  "dev.zio"        %% "zio-interop-reactivestreams" % "1.0.3.5-RC5",
  "com.rabbitmq"   % "amqp-client"                  % "5.7.3",
  "ch.qos.logback" % "logback-classic"              % "1.2.3"
)

testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")

addCommandAlias("fmt", "all scalafmtSbt scalafmt test:scalafmt")
addCommandAlias("check", "all scalafmtSbtCheck scalafmtCheck test:scalafmtCheck")
