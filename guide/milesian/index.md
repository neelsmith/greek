---
layout: page
title:  "'Milesian' numerals"
---


**Version @VERSION**


Support for numeric notation in the "Milesian" system is not yet fully integrated within the `LiteraryGreekString` class, but there is a similar, separate `GreekNumeric` trait.  In addition to conversion between ASCII and Unicode notation, its classes can compute numeric values.



```scala mdoc
import edu.holycross.shot.greek._
```

Implementations include the `MilesianInteger`.

```scala mdoc
val milesianInt = MilesianInteger("ra'")
println(milesianInt.ucode + " == " + milesianInt.toInt)
```
