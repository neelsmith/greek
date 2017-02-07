# `greek` library

## What it is

`greek` is a cross-platform library for working with strings representing ancient Greek.

## Current version: 1.2

[Release notes](releases.md)


## License

[GPL 3.0](http://www.opensource.org/licenses/gpl-3.0.html)


## Using, building and test

`greek` is compiled for both the JVM and ScalaJS using scala versions 2.10, 2.11 and 2.12.


If you are using sbt, include `Resolver.jcenterRepo` in your list of resolvers

    resolvers += Resolver.jcenterRepo
    
 and add this to your library dependencies:

    "edu.holycross.shot" %% "greek" % "1.0.0"

For maven, ivy or gradle equivalents, refer to <https://bintray.com/neelsmith/maven/greek>.

To build from source and test, use normal sbt commands (`compile`, `test` ...).
