---
layout: page
title: Greek
---

## The `greek` library

**Version 9.0.0**

In the twenty-first century, it's still not possible to use internet standards to represent ancient Greek digitally.  The library documented here offers a way to work with semantically explicit representations of the numerous alphabetic writing systems used in the archaic and classical Greek world.

Classes extending the `GreekString` trait represent a String in two formats:

1.  Unicode code points in form NFC
2.  a primarily or exclusively ASCII representation


Other abstractions supported in this library include the `LGSTrait` for Greek strings in some variation on the conventional  orthography of printed literary Greek, the `MidOrthography` trait for classified tokenization of strings, and the `CiteValidator` trait for evaluating compliance of strings wtih the definition of an `MidOrthography`.  By mixing these traits, we can minimize the effort needed to create a derivative classes tailored to corpora in specific orthographies.


## Goals

Main design goals:

-   Greek strings may be constructed from either a Unicode or ASCII representation: the second representation is automatically generated from the one submitted
-   only explicitly enumerated code points are accepted as valid
-   equality of two Greek strings does not depend on which format was used to construct the Greek string
-   comparison of two Greek strings follows the ordering of the Greek alphabet
-   the library is cross compiled to run either on the JVM or a javascript engine
