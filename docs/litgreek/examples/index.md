---
layout: page
title: Usage
parent: The LiteraryGreekString
---

**Version 9.0.0**


Import the library:


```scala
import edu.holycross.shot.greek._
```

Work with ASCII or Unicode representation:

```scala
    val wrath1 = LiteraryGreekString("mh=nin")
    assert(wrath1.ucode == "μῆνιν")

    val wrath2 = LiteraryGreekString("μῆνιν")
    assert(wrath2.ascii == "mh=nin")
```


Equality testing works on the computed ASCII form:


```scala
    assert (wrath1 == wrath2)
```

Alphabetic comparison follows the logic of the Greek alphabet.  The functions
`<`  and `>` mean "precedes alphabetically" and "follows alphabetically" respectively.


```scala
    val horse1 = LiteraryGreekString("ἵππος ")
    val horse2 = LiteraryGreekString("i(/ppos ")
    assert(horse1 == horse2)

    val bird = LiteraryGreekString("ὄρνιθος")
    val animal = LiteraryGreekString("ζῷον")
    assert(animal < bird)
    assert(bird > horse2)
    assert(animal < horse2)

```

In converting from Unicode to Ascii forms, characters not explicitly defined in the  `LiteraryGreekString` are bracketed bedtween hash tags `#`.


**Example**: code point 1008, `ϰ`, is a technical symbol represented by the Greek letter kappa:  it is not intended to represent the alphabetic character kappa in Greek text.  If we use code point 1008 to construct a Greek string, it will be mapped to an error in the ASCII representation.

```scala
    var bad = LiteraryGreekString("ϰαϰῶς")
    assert (bad.ascii == "#ϰ#a#ϰ#w=s")
```


vs. this example correctly using code point 954, `κ`:


```scala
var notSoBad = LiteraryGreekString("κακῶς")
// notSoBad: LiteraryGreekString = LiteraryGreekString(
//   "\u03ba\u03b1\u03ba\u1ff6\u03c2"
// )
    assert (notSoBad.ascii == "kakw=s")
```
