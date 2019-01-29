# `greek` library

## What it is

`greek` is a cross-platform library for working with strings representing ancient Greek.

## Current version: 2.0.0

Status:  **active development**. [Release notes](releases.md). Get automatic nofication of new binary releases from jcenter:

<a href='https://bintray.com/neelsmith/maven/greek?source=watch' alt='Get automatic notifications about new "greek" versions'><img src='https://www.bintray.com/docs/images/bintray_badge_color.png'></a>

## Documentation


<https://neelsmith.github.io/greek/>





## License

[GPL 3.0](http://www.opensource.org/licenses/gpl-3.0.html)


## Using, building and test

`greek` is compiled for both the JVM and ScalaJS using scala versions 2.10, 2.11 and 2.12.  Binaries for all platforms are available from jcenter.

If you are using sbt, include `Resolver.jcenterRepo` in your list of resolvers

    resolvers += Resolver.jcenterRepo

and add this to your library dependencies:

    "edu.holycross.shot" %% "greek" % VERSION

For maven, ivy or gradle equivalents, refer to <https://bintray.com/neelsmith/maven/greek>.

To build from source and test, use normal sbt commands (`compile`, `test` ...).
