---
layout: page
title: Greek string library
---

In 2017, it's still not possible to use internet standards to represent ancient Greek digitally.  The library documented here offers a way to work with semantically explicit representations of the numerous alphabetic writing systems used in the archaic and classical Greek world.

All Greek strings are represented in two formats:

1. Unicode code points in form NFC
2. a primarily or exclusively ASCII representation


## Goals




- Greek strings may be constructed from either representation: the second representation is automatically generated
- only explicitly enumerated code points are accepted as valid
- equality of two Greek strings does not depend on which format was used to construct the Greek string
- comparison of two Greek strings follows the ordering of the Greek alphabet
- the library is cross compiled to run either on the JVM or a javascript engine



## Implementations

The library defines an abstract trait, `GreekString`.  In the current version, the Greek string trait is implemented by the following classes. (Follow links for a user's guide to each class.)

- `LiteraryGreekString`: a digital representation of polytonic Greek in the orthography of modern print editions.  [User's guide](litgreek).

In development:

- `AtticGreekString`: a digital representation of the Athenian writing system prior to the alphabetic reform of the archon Euclid (403 BCE).

## Where to get it

- source:  [github](https://github.com/neelsmith/greek)
- binaries: [jcenter](https://bintray.com/neelsmith/maven/greek) (including API documentation)
