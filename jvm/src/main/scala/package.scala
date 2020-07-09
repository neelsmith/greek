package edu.holycross.shot
// package-level implementatoins for JVM of crucial
// asciiOf and ucodeOf methods.


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
  //Logger.setDefaultLogLevel(LogLevel.DEBUG)

  def isAscii(s: String): Boolean = {
    val asciiAlphas = s.toLowerCase.map(ch => (ch.toInt >= 'a' && ch.toInt <= 'z' ) )
    asciiAlphas.contains(true)
  }

  def milesianUcodeOf(s: String, alphabetCPs:  Vector[Int]) : String = {
    if (s.head.toInt > 127) {
      Normalizer.normalize(s, Normalizer.Form.NFC)

    } else {
      debug("Create LiteraryGreekOrthography of " + s)
      ucodeForString(s,alphabetCPs, Vector.empty[Char]) //+ numericTick
    }
  }

  def milesianAsciiOf (s: String, alphabetCPs: Vector[Int]): String = {
    if (s.isEmpty) {""} else {



      // catch special cases of stigma, qoppa and sampi
      //val flagged = s.replaceAll(MilesianNumeric.sigmaString,"STIGMA").replaceAll(MilesianNumeric.qoppaString,"QOPPA").replaceAll

      val normalized = Normalizer.normalize(s, Normalizer.Form.NFC)
      debug("milesianAsciiOf " + s + " normalized to " + normalized)
      if (s.head.toInt > 127) {
        asciiForString(normalized)

      } else {

        // handle special cases
        normalized
      }
    }
  }


  /** Create [[LiteraryGreekOrthography]]'s `ascii` view of a String.
  * If the first character is <= 127, assume that the String
  * is already in `ascii` view; otherwise, normalize to Unicode
  * form NFC before converting.
  *
  * @param s String to create `ascii` view for.
  */
  def asciiOf (s: String, alphabetCPs: Vector[Int]): String = {
    //Logger.setDefaultLogLevel(LogLevel.DEBUG)

    val checkFirst = if (s.head == '“') {
      s(1)
    } else {
      s.head
    }

    if (checkFirst.toInt > 127) {
      val normalized = Normalizer.normalize(s.replaceAll(s"${CodePointTranscoder.terminalSigma}", s"${CodePointTranscoder.sigma}" ), Normalizer.Form.NFC)
      debug(s"asciiOf ${s} normalized to " + normalized)
      //LiteraryGreekOrthography.nfcToAscii(normalized,"")
      asciiForString(normalized)
    } else {
      // VALIDATE THIS, TOO!
      // handle terminal sigma
      Normalizer.normalize(s, Normalizer.Form.NFC)
    }
  }

  /** Create [[LiteraryGreekOrthography]]'s `ucode` view of a String.
  * If the first character is <= 127, assume that the String
  * is in `ascii` view and convert it; otherwise, normalize to Unicode
  * form NFC.
  *
  * @param s String to create `ucode` view for.
  * @param alphabetCPs All code points allowed in this alphabet
  */
  def ucodeOf(s: String, alphabetCPs: Vector[Int], combining: Vector[Char] ) : String = {
      val checkFirst = if (s.head == '“') {
        s(1)
      } else {
        s.head
      }

    if (checkFirst.toInt > 127) {
      Normalizer.normalize(s, Normalizer.Form.NFC).replaceAll("σ ", "ς ").replaceAll("σ$", "ς")

    } else {
      ucodeForString(s,alphabetCPs, combining).replaceAll("σ ", "ς ").replaceAll("σ$", "ς")
    }
  }
  // ANNOTATE FOR TAILREC
  //
  /** Recursively build a unicode form from an ascii String.
  *
  * @param ascii Ascii string to convert to Unicode.
  * @param validCpList List of all code points allowed in this orthography.
  * @param ucode Recursively accumulated Unicode string.
  * @param idx Index by code point into the ascii string to convert.
  */
  def ucodeForString(ascii: String,  validCpList: Vector[Int], combining: Vector[Char], ucode: String = "", idx: Int = 0): String = {
    debug(s"At ${idx}, ucode is " + ucode)
    if (idx >= ascii.length) {
      ucode

    } else {
      val cp = ascii.codePointAt(idx)
      val cpAsString = CodePointTranscoder.cpsToString(Vector(cp))
      val newIndex = idx + java.lang.Character.charCount(cp)
      val charIndex = ascii.offsetByCodePoints(0, idx)
      val trimmedAscii = ascii.slice(charIndex, ascii.size)

      val initialCluster = CodePointTranscoder.peekAhead(trimmedAscii, combining = combining)
      val skipChars = initialCluster.size - 1
      debug("Trim ascii to " + trimmedAscii)

      /// convert ascii with CPTranscoder
      // but pass along ucode beyond ascii
      val newUcode = if (cp < 127) {
        CodePointTranscoder.ucodeCodePoint(initialCluster)
      } else {
        cpAsString
      }
      debug(s"${cpAsString} = ${cp} yield ${newUcode}")
      if (validCpList.contains(cp)) {
        ucodeForString(ascii, validCpList, combining, ucode + newUcode, newIndex + skipChars)
      } else {
        ucodeForString(ascii, validCpList, combining, ucode + s"#${newUcode}#", newIndex + skipChars)
      }
    }
  }


  def asciiForString(ucode: String,  ascii: String = "", idx: Int = 0): String = {

    if (idx >= ucode.size) { //ucode.codePoints().count()){
      ascii
    } else {
      val cp = ucode.codePointAt(idx)
      val newCpString = CodePointTranscoder.cpsToString(Vector(cp))
      val newIndex = idx + java.lang.Character.charCount(cp)
      val newAscii = CodePointTranscoder.asciiCodePoint(newCpString)

      debug("asciiForString " + ucode + " at idx " + idx)
      debug("newAscii " + newAscii + " from cp " + cp)
      debug("New idx will be " + newIndex )
      asciiForString(ucode, ascii + newAscii, newIndex)

    }
  }




  def atticAsciiOf(s: String) : String = {
    "ATTIC ASCII OF  " + s
  }


  def atticUcodeOf(s: String) : String = {
    /*
    if (s.head.toInt > 127) {
      Normalizer.normalize(s, Normalizer.Form.NFC)
    } else {
      AtticGreekString.asciiForString(s)
    }
    */
    "ATTIC Ucode OF  " + s
  }

}
