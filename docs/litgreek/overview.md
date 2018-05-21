---
layout: page
title: Overview of the LiteraryGreekString class
---


The `LiteraryGreekString` class defines a digital representation of polytonic Greek as it appears in the orthography of modern print editions.  It associates 232 Unicode code points with unique ASCII strings composed from a set of lower-case ASCII characters and non-alphabetic characters.

The mapping of Unicode code points to ASCII equivalents is fully documented [here](ascii).

## Differences between the JVM and ScalaJS binaries

When compiled for the JVM, the `LiteraryGreekString` class will accept equivalent Unicode representations for the 232 code points defined in the library.  Due to limitations in the underlying javascript String class, this is not yet supported when the library is compiled for ScalaJS. (This feature may be added in the future if EcmaScript 6  browser implementations provide support for Unicode normalization.)
