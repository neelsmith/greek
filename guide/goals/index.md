---
layout: page
title: Goals
---


Main design goals:

-   Greek strings may be constructed from either a Unicode or ASCII representation: the second representation is automatically generated from the one submitted
-   only explicitly enumerated code points are accepted as valid
-   equality of two Greek strings does not depend on which format was used to construct the Greek string
-   comparison of two Greek strings follows the ordering of the Greek alphabet
-   the library is cross compiled to run either on the JVM or a javascript engine
