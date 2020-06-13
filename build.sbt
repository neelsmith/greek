lazy val scala212 = "2.12.10"
lazy val supportedScalaVersions = List(scala212)
ThisBuild / scalaVersion := scala212
ThisBuild / turbo := true


lazy val root = project.in(file(".")).
    aggregate(crossed.js, crossed.jvm).
    settings(
        crossScalaVersions := Nil,
        publish / skip := true
    )

lazy val crossed = crossProject(JSPlatform, JVMPlatform).in(file(".")).
    settings(
      name := "greek",
      organization := "edu.holycross.shot",
      version := "4.1.0",
      licenses += ("GPL-3.0",url("https://opensource.org/licenses/gpl-3.0.html")),
      resolvers += Resolver.jcenterRepo,
      libraryDependencies ++= Seq(
        "org.scalatest" %%% "scalatest" % "3.1.2" % "test",
        "org.wvlet.airframe" %%% "airframe-log" % "20.5.2",

        "edu.holycross.shot" %%% "midvalidator" % "12.3.0", // for MidOrthography


      )
    ).
    jvmSettings(
      libraryDependencies ++= Seq(
          "org.scala-js" %% "scalajs-stubs" % "1.0.0" % "provided",
        )
    ).
    jsSettings(
      // JS-specific settings:
        scalaJSUseMainModuleInitializer := true,

    )

    lazy val docs = project       // new documentation project
      .in(file("docs-build")) // important: it must not be docs/
      .dependsOn(crossed.jvm)
      .enablePlugins(MdocPlugin)
      .settings(
        mdocIn := file("guide"),
        mdocOut := file("docs"),
        mdocExtraArguments := Seq("--no-link-hygiene"),
        mdocVariables := Map(
          "VERSION" -> "4.1.0"
        )
      )
