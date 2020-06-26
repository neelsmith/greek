# `greek` library



## What it is

`greek` is a cross-platform library for working with strings representing ancient Greek.



## Current version: 5.5.1

Status:  **active development**. [Release notes](releases.md).


## Documentation


 - [User guide](https://neelsmith.github.io/greek/)
- Jupyter [notebooks illustrating usage](https://mybinder.org/v2/gh/neelsmith/greek-ipynb/master) [![Binder](https://mybinder.org/badge_logo.svg)](https://mybinder.org/v2/gh/neelsmith/greek-ipynb/master)



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
