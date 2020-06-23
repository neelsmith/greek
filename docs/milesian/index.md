---
layout: page
title:  "'Milesian' numerals"
---


**Version 5.3.1**


Support for numeric notation in the "Milesian" system is not yet fully integrated within the `LiteraryGreekString` class, but there is a similar, separate `GreekNumeric` trait.  In addition to conversion between ASCII and Unicode notation, its classes can compute numeric values.



```scala
import edu.holycross.shot.greek._
```

Implementations include the `MilesianInteger`.

```scala
val milesianInt = MilesianInteger("ra'")
// milesianInt: MilesianInteger = MilesianInteger("ra'")
println(milesianInt.ucode + " == " + milesianInt.toInt)
// ραʹ == 101
```
