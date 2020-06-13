---
layout: page
title: "LiteraryGreekString: ASCII mapping"
---

## Alphabetic characters
The ASCII representation follows the same mapping for lower-case Greek letters used by the Perseus project, the SophoKeys keyboard extension for OS X, and others. Upper-case characters are represented by preceding the lower case letter with an asterisk, e.g., `a` == `α` and `*a` ==  `Α`.

 See a [full list of lower-case mappings](../lc).

## Breathings, accents, diacritics
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



## Punctuation

Greek punctuation characters are represented as follows:


| Greek character | ASCII representation |
|:----------------|:---------------------|
| Full stop       | .   (period)         |
| Half stop       | : (colon)            |
| Interrogation   | ; (semicolon)        |
| comma | , (comma)|
| elision | `'` (single quote) |

## White space

Space, tab, carriage-feed and new line characters and equated in both Unicode and ASCII representations.
