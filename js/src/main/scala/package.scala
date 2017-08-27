package edu.holycross.shot


import scala.scalajs.js
// Useless? import js.JSStringOps._


/** Package for representing text in Ancient Greek, since the fundamental
* assumption of Unicode that code points belong to a single language is wrong,
* as is the definition in the ISO 639 standards of a single "Greek" dialect
* for all Greek "to 1453".
*
*  ==Overview==
* Classes implementing the [[GreekString]] trait define an encoding system
* for writing some form of ancient Greek with an ordered set of
* characters drawn from the ASCII character set whenever possible.
* The [[CodePointTranscoder]] object provides a mapping of ASCII character
* sequence to glyphs taken primarily from the so-called "Greek and Coptic"
* and "Extended Greek" blocks of Unicode.  Using the concrete implementation of
* the [[GreekString]] trait's `asciiCompare` function, any class extending
* the [[GreekString]] trait can trivially extend Scala's `Ordered` trait as well.
*
*  ==JVM vs. Javascript implementation==
* The JVM implementation normalizes all Unicode to Form NFC, using standard
* Java classes.  No comparable exists for Javascript, and it is probable
* that String comparisons and conversions from `ascii` to `ucode` representations
* of classes implementing the [[GreekString]] trait will only work correctly
* for Unicode that is already normalized to Form NFC.
*/
package object greek {


  def literaryAsciiOf (s: String): String = {
    if (s.head.toInt > 127) {
      // Probably ONLY WORKS FOR Unicode in form NFC
      LiteraryGreekString.nfcToAscii(s,"")

    } else {
      s
    }
  }

  def literaryUcodeOf(s: String) : String = {
    if (s.head.toInt > 127) {
      s
    } else {
      LiteraryGreekString.asciiToUcode(s,"")
    }
  }





  //////////////////////////////////////////
  def atticAsciiOf(s: String) : String = {
    "ATTIC ASCII OF  " + s
  }
  def atticUcodeOf(s: String) : String = {
    "ATTIC Ucode OF  " + s
  }
}
