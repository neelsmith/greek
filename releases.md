# greek: release notes


**1.2.0**: Adds alphabetic comparison and sorting.

**1.1.0**:  Adds semantic comparison with `==`.

**1.0.0**:  initial release.  Includes a `LiteraryGreekString`class implementing the `GreekString` trait.  Supports lower-case only. In JVM or ScalaJS environment, ASCII representations are mapped to equivalent representations in Unicode form NFC.  In JVM environment, any Unicode representation is normalized to NFC and mapped to an ASCII representation.  In ScalaJS, only Unicode submissions in form NFC are guaranteed to map correctly to ASCII representations due to lack of support in the underlying Javascript string implementations.
