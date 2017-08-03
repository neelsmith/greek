# greek: release notes

**1.3.5**: update ScalaJs version to support Scala up through most current version.

**1.3.4**:  cross-compile for Scala 2.12 as well as 2.11.

**1.3.3**:  adds double quote mark to `LiteraryGreekString` characters.

**1.3.2**:  adds em dash to `LiteraryGreekString` puncutation characters.

**1.3.1**: fixes ambiguous mapping of some unicode code points that are duplicated in two blocks of the Unicode standard.

**1.3.0**: adds a variety of new functions for working with accents and formatting for case, both in `GreekString` trait and in the `LiteraryGreekString` class.

**1.2.2**: in `LiteraryGreekString`, adds support for breathing-accent combinations on upper-case vowels.

**1.2.1**: bug fixes in handling accented vowel combinations with equivalents in both "modern Greek" and "ancient Greek" blocks of Unicode, and in determining equivalences of strings with terminal or medial sigma.

**1.2.0**: adds alphabetic comparison.

**1.1.0**:  adds semantic comparison with `==`.

**1.0.0**:  initial release.  Includes a `LiteraryGreekString`class implementing the `GreekString` trait.  Supports lower-case only. In JVM or ScalaJS environment, ASCII representations are mapped to equivalent representations in Unicode form NFC.  In JVM environment, any Unicode representation is normalized to NFC and mapped to an ASCII representation.  In ScalaJS, only Unicode submissions in form NFC are guaranteed to map correctly to ASCII representations due to lack of support in the underlying Javascript string implementations.
