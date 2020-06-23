---
layout: page
title: Examples of usage of the LiteraryGreekString class
---

**Version 5.3.1**

## Examples of usage

The following examples in Scala illusrate the main features of the library.


Import the library:


```scala
import edu.holycross.shot.greek._
```

### Work with either representation


```scala
val wrath1 = LiteraryGreekString("mh=nin")
// wrath1: LiteraryGreekString = LiteraryGreekString("mh=nin")
    assert(wrath1.ucode == "μῆνιν")

    val wrath2 = LiteraryGreekString("μῆνιν")
// wrath2: LiteraryGreekString = LiteraryGreekString(
//   "\u03bc\u1fc6\u03bd\u03b9\u03bd"
// )
    assert(wrath2.ascii == "mh=nin")
```


### Equality testing


```scala
assert (wrath1 == wrath2)
```

### Alphabetic comparison

`<`  means "precedes alphabetically"; `>` means "follows alphabetically".





```scala
val horse1 = LiteraryGreekString("ἵππος ")
// horse1: LiteraryGreekString = LiteraryGreekString(
//   "\u1f35\u03c0\u03c0\u03bf\u03c2 "
// )
    val horse2 = LiteraryGreekString("i(/ppos ")
// horse2: LiteraryGreekString = LiteraryGreekString("i(/ppos ")
    val bird = LiteraryGreekString("ὄρνιθος")
// bird: LiteraryGreekString = LiteraryGreekString(
//   "\u1f44\u03c1\u03bd\u03b9\u03b8\u03bf\u03c2"
// )
    val animal = LiteraryGreekString("ζῷον")
// animal: LiteraryGreekString = LiteraryGreekString(
//   "\u03b6\u1ff7\u03bf\u03bd"
// )
    assert(animal < bird)
    assert(bird > horse2)
    assert(animal < horse2)
    assert(horse1 == horse2)
```





### Invalid input

In the current version, characters not explicitly defined in the  `LiteraryGreekString` are mapped to an "error  character," `#`.


#### Example

Code point 1008, `ϰ`, is a technical symbol represented by the Greek letter kappa:  it is not intended to represent the alphabetic character kappa in Greek text.  If we use code point 1008 to construct a Greek string, it will be mapped to an error in the ASCII representation.  The result of constructing a Greek string with code point 1008 is illustrated here:


```scala
var bad = LiteraryGreekString("ϰαϰῶς")
// bad: LiteraryGreekString = LiteraryGreekString(
//   "\u03f0\u03b1\u03f0\u1ff6\u03c2"
// )
    assert (bad.ascii == "#a#w=s")
```

vs. this example correctly using code point 954, `κ`:


```scala
var notSoBad = LiteraryGreekString("κακῶς")
// notSoBad: LiteraryGreekString = LiteraryGreekString(
//   "\u03ba\u03b1\u03ba\u1ff6\u03c2"
// )
    assert (notSoBad.ascii == "kakw=s")
```
