lazy val scala211 = "2.11.12"
lazy val scala212 = "2.12.10"
lazy val supportedScalaVersions = List(scala212, scala211)

ThisBuild / scalaVersion := scala212
ThisBuild / turbo := true

lazy val root = (project in file("."))
  .aggregate(crossed.js, crossed.jvm)
  .settings(
        crossScalaVersions := Nil,
        publish / skip := true
    )

lazy val crossed = crossProject(JSPlatform, JVMPlatform).in(file(".")).
    settings(
      name := "greek",
      organization := "edu.holycross.shot",
      version := "5.5.1",
      licenses += ("GPL-3.0",url("https://opensource.org/licenses/gpl-3.0.html")),
      resolvers += Resolver.jcenterRepo,
      libraryDependencies ++= Seq(
        "org.scalatest" %%% "scalatest" % "3.1.2" % "test",
        "org.wvlet.airframe" %%% "airframe-log" % "20.5.2",

        "edu.holycross.shot.mid" %%% "orthography" % "2.0.0",
        "edu.holycross.shot.cite" %%% "xcite" % "4.3.0",
        "edu.holycross.shot" %%% "ohco2" % "10.20.0",
        "edu.holycross.shot" %%% "citevalidator" % "1.1.2",
        "edu.holycross.shot" %%% "scm" % "7.3.0",


      )
    ).
    jvmSettings(
      libraryDependencies ++= Seq(
          "org.scala-js" %% "scalajs-stubs" % "1.0.0" % "provided",

          // These are for unit tests using files.
          // They can (and should) be removed
          // when we get a better test harness
          //"edu.holycross.shot" %%% "midvalidator" % "13.0.0",
          //"edu.holycross.shot.mid" %%% "markupreader" % "1.0.0",
          //"org.homermultitext" %% "hmt-textmodel" % "7.0.0",
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
          "VERSION" -> "5.5.1"
        )
      )
