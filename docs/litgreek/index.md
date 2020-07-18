---
layout: page
title: The LiteraryGreekString
has_children: true
---

The `LiteraryGreekString` class defines a digital representation of polytonic Greek as it appears in the orthography of modern print editions.  It associates 232 Unicode code points with unique ASCII strings composed from a set of lower-case ASCII characters and non-alphabetic characters.

-  [Examples of usage](./examples/)

It implements the `MidOrthography` trait, so you can use it to create a classified tokenization of Greek texts.

- [Tokenization](./tokenization/)



## ASCII <-> Unicode mappings

### Alphabetic characters

The ASCII representation follows the same mapping for lower-case Greek letters used by the Perseus project, the SophoKeys keyboard extension for OS X, and others. Upper-case characters are represented by preceding the lower case letter with an asterisk, e.g., `a` == `α` and `*a` ==  `Α`.

- See a [full list of lower-case mappings](./lc/).

### Breathings, accents, diacritics

The following ASCII characters are used for breathing, accent and diacritical characters:

| Greek character  | ASCII representation |
|:-----------------|:---------------------|
| smooth breathing | )                    |
| rough breathing  | (                    |
| acute accent     | /                    |
| grave accent     | \\                   |
| circumflex       | =                    |
| diaeresis        | +                    |

The ordering of ASCII characters is

1. vowel
2. breathing (if any)
3. accent (if any)
4. diaeresis (if any)

Unlike the ASCII mapping used by the Perseus project and others, the ordering of characters is not affected by case. E.g., `a)/` == `ἄ` and `*a)/` == `Ἄ`.

### Punctuation

Greek punctuation characters are represented as follows:


| Greek character | ASCII representation |
|:----------------|:---------------------|
| Full stop       | .   (period)         |
| Half stop       | : (colon)            |
| Interrogation   | ; (semicolon)        |
| comma | , (comma)|
| elision | `'` (single quote) |

### White space

Space, tab, carriage-feed and new line characters and equated in both Unicode and ASCII representations.


## Differences between the JVM and ScalaJS binaries

When compiled for the JVM, the `LiteraryGreekString` class will accept equivalent Unicode representations for the 232 code points defined in the library.  Due to limitations in the underlying javascript String class, this is not yet supported when the library is compiled for ScalaJS. (This feature may be added in the future if EcmaScript 6  browser implementations provide support for Unicode normalization.)
