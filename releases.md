# greek: release notes

**1.3.2**:  add em dash to `LiteraryGreekString` puncutation characters.

**1.3.1**: fixes ambiguous mapping of some unicode code points that are duplicated in two blocks of the Unicode standard.

**1.3.0**: adds a variety of new functions for working with accents and formatting for case, both in `GreekString` trait and in the `LiteraryGreekString` class.

**1.2.2**: In `LiteraryGreekString`, add support for breathing-accent combinations on upper-case vowels.

**1.2.1**: Bug fixes in handling accented vowel combinations with equivalents in both "modern Greek" and "ancient Greek" blocks of Unicode, and in determining equivalences of strings with terminal or medial sigma.

**1.2.0**: Adds alphabetic comparison.

**1.1.0**:  Adds semantic comparison with `==`.

**1.0.0**:  initial release.  Includes a `LiteraryGreekString`class implementing the `GreekString` trait.  Supports lower-case only. In JVM or ScalaJS environment, ASCII representations are mapped to equivalent representations in Unicode form NFC.  In JVM environment, any Unicode representation is normalized to NFC and mapped to an ASCII representation.  In ScalaJS, only Unicode submissions in form NFC are guaranteed to map correctly to ASCII representations due to lack of support in the underlying Javascript string implementations.
