import BuildHelper._
import Libraries._
import CommonProjects._

inThisBuild(List(
  organization := "compman.compsrv",
  homepage     := Some(url("https://github.com/devas123/competitionservice")),
  licenses     := List("Apache-2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0")),
  developers :=
    List(Developer("devas123", "Grigorii Grigorev", "grigorii.grigorev@gmail.com", url("https://github.com/devas123"))),
  scmInfo := Some(ScmInfo(
    url("https://github.com/devas123/competitionservice"),
    "scm:git:git@github.com:devas123/competitionservice.git"
  )),
  Test / parallelExecution      := false,
  Global / onChangedBuildSource := ReloadOnSourceChanges
))

addCommandAlias("fmt", "all scalafmtSbt scalafmt test:scalafmt")
addCommandAlias("check", "all scalafmtSbtCheck scalafmtCheck test:scalafmtCheck")

val zTestFramework = TestFramework("zio.test.sbt.ZTestFramework")

lazy val actorSystem = module("actor-system", "actor-system").settings(
  libraryDependencies ++= zioLoggingDependencies ++ zioDependencies ++ zioTestDependencies ++ catsDependencies,
  testFrameworks := Seq(zTestFramework)
)

lazy val zio_actors_fork = project.in(file(".")).settings(publish / skip := true)
  .aggregate(actorSystem)
