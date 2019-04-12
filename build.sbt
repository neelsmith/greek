name := "Greek string library"


crossScalaVersions in ThisBuild := Seq("2.11.8", "2.12.4")
scalaVersion := (crossScalaVersions in ThisBuild).value.last


lazy val root = project.in(file(".")).
    aggregate(crossedJVM, crossedJS).
    settings(
      publish := {},
      publishLocal := {}

    )

lazy val crossed = crossProject.in(file(".")).
    settings(
      name := "greek",
      organization := "edu.holycross.shot",
      version := "2.4.0",
      licenses += ("GPL-3.0",url("https://opensource.org/licenses/gpl-3.0.html")),
      resolvers += Resolver.jcenterRepo,
      libraryDependencies ++= Seq(
        "org.scala-js" %% "scalajs-stubs" % scalaJSVersion % "provided",
        "org.scalatest" %%% "scalatest" % "3.0.1" % "test",
        "edu.holycross.shot" %%% "midvalidator" % "6.0.2" // for MidOrthography

      )
    ).
    jvmSettings(
      tutTargetDirectory := file("docs"),
      tutSourceDirectory := file("tut")
    ).
    jsSettings(
      skip in packageJSDependencies := false,
      scalaJSUseMainModuleInitializer in Compile := true

    )

lazy val crossedJVM = crossed.jvm.enablePlugins(TutPlugin)
lazy val crossedJS = crossed.js
