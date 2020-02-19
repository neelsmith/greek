package edu.holycross.shot
import java.text.Normalizer

import wvlet.log._
import wvlet.log.LogFormatter.SourceCodeLogFormatter

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
* sequences to glyphs taken primarily from the so-called "Greek and Coptic"
* and "Extended Greek" blocks of Unicode.  Using the concrete implementation of
* the [[GreekString]] trait's `asciiCompare` function, any class extending
* the [[GreekString]] trait can trivially extend Scala's `Ordered` trait as well.
*
*  ==JVM vs. Javascript implementation==
* The JVM implementation normalizes all Unicode to Form NFC, using standard
* Java classes.  No comparable library exists for Javascript, and it is probable
* that String comparisons and conversions from `ascii` to `ucode` representations
* of classes implementing the [[GreekString]] trait will only work correctly
* for Unicode that is already normalized to Form NFC.
*/
package object greek extends LogSupport {
  Logger.setDefaultLogLevel(LogLevel.DEBUG)
  val numericTick = '\u0374'



  def isAscii(s: String): Boolean = {
    val asciiAlphas = s.toLowerCase.map(ch => (ch.toInt >= 'a' && ch.toInt <= 'z' ) )
    asciiAlphas.contains(true)
  }

  def milesianUcodeOf(s: String) : String = {
    if (s.head.toInt > 127) {
      Normalizer.normalize(s, Normalizer.Form.NFC)

    } else {
      debug("Create LiteraryGreekString of " + s)
      LiteraryGreekString.asciiToUcode(s,"") //+ numericTick
    }
  }



  def milesianAsciiOf (s: String): String = {
    debug("milesianAsciiOf " + s)
    // catch special cases of stigma, qoppa and sampi
    //val flagged = s.replaceAll(MilesianNumeric.sigmaString,"STIGMA").replaceAll(MilesianNumeric.qoppaString,"QOPPA").replaceAll

    val normalized = Normalizer.normalize(s, Normalizer.Form.NFC)
    if (s.head.toInt > 127) {
      LiteraryGreekString.nfcToAscii(normalized,"")

    } else {
      // handle special cases
      normalized
    }
  }


  /** Create [[LiteraryGreekString]]'s `ascii` view of a String.
  * If the first character is <= 127, assume that the String
  * is already in `ascii` view; otherwise, normalize to Unicode
  * form NFC before converting.
  *
  * @param s String to create `ascii` view for.
  */
  def literaryAsciiOf (s: String): String = {
    val checkFirst = if (s.head == '“') {
      s(1)
    } else {
      s.head
    }

    if (checkFirst.toInt > 127) {
      val normalized = Normalizer.normalize(s, Normalizer.Form.NFC)
      LiteraryGreekString.nfcToAscii(normalized,"")

    } else {
      // handle terminal sigma
      Normalizer.normalize(s, Normalizer.Form.NFC)
    }
  }



  /** Create [[LiteraryGreekString]]'s `ucode` view of a String.
  * If the first character is <= 127, assume that the String
  * is in `ascii` view and convert it; otherwise, normalize to Unicode
  * form NFC.
  *
  * @param s String to create `ucode` view for.
  */
  def literaryUcodeOf(s: String) : String = {
      val checkFirst = if (s.head == '“') {
        s(1)
      } else {
        s.head
      }

    if (checkFirst.toInt > 127) {
      Normalizer.normalize(s, Normalizer.Form.NFC)


      // waht was all this about?
      /*
      if (s.head.toInt == 787){
        val asciified = CodePointTranscoder.asciiCodePoint(s.tail.head.toString) + ")" + literaryAsciiOf(s.tail.tail)
        val asciiLeader = CodePointTranscoder.asciiCodePoint(s.tail.head.toString)
        val leader = CodePointTranscoder.ucodeCodePoint(asciiLeader + ")")
        Normalizer.normalize(leader + s.tail.tail, Normalizer.Form.NFC)

      } else if (s.tail.head == 787 ) {
        //smoothUpperCase(s.tail.tail)
        Normalizer.normalize(s, Normalizer.Form.NFC)
      } else {*/


    } else {
      LiteraryGreekString.asciiToUcode(s,"")
    }
  }





  def atticAsciiOf(s: String) : String = {
    "ATTIC ASCII OF  " + s
  }


  def atticUcodeOf(s: String) : String = {
    if (s.head.toInt > 127) {
      Normalizer.normalize(s, Normalizer.Form.NFC)
    } else {
      AtticGreekString.asciiToUcode(s)
    }
    "ATTIC Ucode OF  " + s
  }

}
