# `greek` library: release notes

**5.3.1**: correct publication of binaries.

**5.3.0**: `LiteraryGreekString` adds `xlit` method.

**5.2.0**: Meaningful output from `verify` method of `CiteValidator` trait.

**5.1.0**: `LiteraryGreekString` implements the `CiteValidator` trait.

**5.0.1**: fixes a bug in handling barytone accent.

**5.0.0**: API-breaking change to use new MID `orthography` library.

**4.1.0**: adds support for SJS 1.0.

**4.0.2**: correctly parse allowed values for OUDEN.

**4.0.1**: fixes a bug in parsing empty fractional values.

**4.0.0**:  Make signatures of `toInt` and `toDouble` consistent.

**3.0.0**:  API-breaking revisions of classes for working with numeric values in Milesian notation.


**2.6.2**: shut up insanely verbose logging.


**2.6.1**: fixes a bug in transcoding of numeric expressions mixing ASCII with codepoints BMP.

**2.6.0**: adds support for minutes in `MilesianNumeric` class.


**2.5.2**: fixes a bug in handling of numeric values for six, ninety and ninehundred in ASCII representation.


**2.5.1**: implements usable functionality in the `MilesianNumeric` class and companion object for integer values up to MAX_INT = 999.

**2.4.0**:  adds functions for stripping breathing from a `LiteraryGreekString`.  This is useful in applications like syllabification.

**2.3.3**: adds omitted code point transcoding for upper-case omega.

**2.3.2**: fixes a bug determing Unicode or ASCII string in quoted words.

**2.3.1**: fixes a bug when quoted word has initial breathing.

**2.3.0**: add support for curly quotes and em-dash

**2.2.5**: recognize elision.

**2.2.4**: work with terminal sigma.

**2.2.3**: recognizes valid capitalized ascii strings.

**2.2.2**: fixes a bug in sorting Greek strings.

**2.2.1**: change all graves to acutes before comparing for sort

**2.2.0**: add concrete `valid` function to `GreekString` trait

**2.1.1**:  Updates to `MidOrthography` library.

**2.1.0**:  Updates to `MidOrthography` trait add new concreate functions to `LiteraryGreekString`.


**2.0.0**:   `LiteraryGreekString` object implements `MidOrthography` trait.  API-breaking change from `val` to function for `GreekString`'s `alphabetString`.  Dependency on `midvalidator` library means that `greek` now compiles only for Scala >= 2.11.


**1.4.0**:  restores cross publishing for multiple Scala versions in both JVM and ScalaJS environments.  Adds automated testing of end-user documentation with scala `tut` library.


**1.3.8**:  silently corrects the widespread error in many legacy texts of
misformatting upper-case Greek words starting with vowels in such a way that
accents and breathings are placing on a preceding space, not on the vowel.

**1.3.7**: uniform cross publication.

**1.3.6**:  support Scala 2.10.6 through 2.12.3 and ScalaJS 1.0.

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
