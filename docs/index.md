---
layout: page
title: Greek
---

**Version 5.3.1**

In the twenty-first century, it's still not possible to use internet standards to represent ancient Greek digitally.  The library documented here offers a way to work with semantically explicit representations of the numerous alphabetic writing systems used in the archaic and classical Greek world.

All Greek strings are represented in two formats:

1.  Unicode code points in form NFC
2.  a primarily or exclusively ASCII representation


## Goals

Main design goals:

-   Greek strings may be constructed from either a Unicode or ASCII representation: the second representation is automatically generated from the one submitted
-   only explicitly enumerated code points are accepted as valid
-   equality of two Greek strings does not depend on which format was used to construct the Greek string
-   comparison of two Greek strings follows the ordering of the Greek alphabet
-   the library is cross compiled to run either on the JVM or a javascript engine


## Implementations


The library defines an abstract trait, `GreekString`, that is implemented by:

-   `LiteraryGreekString`: [polytonic Greek in the orthography of modern print editions](./litgreek/).
-   `AtticGreekString` (in preliminaryt development): a digital representation of the Athenian writing system prior to the alphabetic reform of the archon Euclid (403 BCE).


The `LiteraryGreekString` also implements:

-  the `MidOrthography` trait, including methods for [classified tokenizations of Greek strings](./litgreek/tokenization/).

A companion `LGSValidator` class implements:

-  the `CiteValidator` trait, and can be plugged into pipelines for automated reporting on validation and verification such as the one supported by the [MID project validator](https://github.com/hcmid/projectvalidator).
