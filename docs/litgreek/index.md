---
layout: page
title: The LiteraryGreekString class
---

The `LiteraryGreekString` class defines a digital representation of polytonic Greek as it appears in the orthography of modern print editions.  It associates 232 Unicode code points with unique ASCII strings composed from a set of lower-case ASCII characters and non-alphabetic characters.

The mapping of Unicode code points to ASCII equivalents is fully documented [here](ascii).


## Differences between the JVM and ScalaJS binaries

When compiled for the JVM, the `LiteraryGreekString` class will accept equivalent Unicode representations for the 232 code points defined in the library.  Due to limitations in the underlying javascript String class, this is not yet supported when the library is compiled for ScalaJS. (This feature may be added in the future if EcmaScript 6  browser implementations provide support for Unicode normalization.)

## Examples of usage

The following examples in Scala illusrate the main features of the library.


Import the library:


```scala
import edu.holycross.shot.greek._
```

### Work with either representation


```scala
    val wrath1 = LiteraryGreekString("mh=nin")
    assert(wrath1.ucode == "μῆνιν")

    val wrath2 = LiteraryGreekString("μῆνιν")
    assert(wrath2.ascii == "mh=nin")
```


### Equality testing


```scala
    val wrath1 = LiteraryGreekString("mh=nin")
    val wrath2 = LiteraryGreekString("μῆνιν")
    assert (wrath1 == wrath2)
```

### Alphabetic comparison

`<`  means "precedes alphabetically"; `>` means "follows alphabetically".





```scala
    val horse1 = LiteraryGreekString("ἵππος ")
    val horse2 = LiteraryGreekString("i(/ppos ")
    val bird = LiteraryGreekString("ὄρνιθος")
    val animal = LiteraryGreekString("ζῷον")
    assert(animal < bird)
    assert(bird > horse2)
    assert(animal < horse2)
```
    assert(horse1 == horse2)




### Invalid input

In the current version, characters not explicitly defined in the  `LiteraryGreekString` are mapped to an "error  character," `#`.


#### Example

Code point 1008, `ϰ`, is a technical symbol represented by the Greek letter kappa:  it is not intended to represent the alphabetic character kappa in Greek text.  If we use code point 1008 to construct a Greek string, it will be mapped to an error in the ASCII representation.  The result of constructing a Greek string with code point 1008 is illustrated here:



    var bad = LiteraryGreekString("ϰαϰῶς")
    assert (bad.ascii == "#a#w=s")


vs. this example correctly using code point 954, `κ`:

    var notSoBad = LiteraryGreekString("κακῶς")
    assert (notSoBad.ascii = "kakw=s")
