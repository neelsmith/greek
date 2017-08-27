package edu.holycross.shot.greek

import scala.scalajs.js
import js.annotation.JSExport

/** Representation of a Greek string written in conventional literary orthography.
*
* @param str A string in either the ascii or ucode representation of the [[LiteraryGreekString]]
* system.
*/
@JSExport  case class LiteraryGreekString(str: String) extends GreekString with  Ordered[GreekString] {


  /** The ASCII representation of this string.
  */
  val ascii = literaryAsciiOf(fixedCombos.replaceAll("ς","σ"))

  /** The representation of this string with glyphs in the "Greek and Coptic"
  * and "Extended Greek" blocks of Unicode.
  */
  val ucode =    literaryUcodeOf(fixedCombos.replace("s ","Σ ").replaceAll("σ ", "ς "))



  val fixedCombos = CodePointTranscoder.swapPrecedingBreathings(str)


  /** Compare this string to a second [[GreekString]] alphabetically
  * using the [[GreekString]] trait's implementatoin of [[asciiCompare]].
  *
  * @param that Second [[GreekString]] to compare.
  */
  override def compare(that:GreekString): Int = {
    asciiCompare(this.ascii, that.ascii)
  }


  /** Required function to convert lowercase to uppercase form.
  */
  def toUpper: LiteraryGreekString = {
    ucString(ascii,"")
  }

  /** Recursively converts characters in src to upper case form.
  *
  * @param src Ascii representation of a [[GreekString]] to convert to upper case.
  * @param accumulator String of previously converted characters.
  */
  private def ucString(src: String, accumulator: String) : LiteraryGreekString = {
    if (src.isEmpty) {
      LiteraryGreekString(accumulator)

    } else {
      if (src.head == '*') {
        val transferred = accumulator + src(0) + src(1)
        ucString(src.drop(2), transferred)
      } else {
        val transferred = accumulator + "*" + src(0)
        ucString(src.tail, transferred)
      }
    }
  }

  /** Required function to convert uppercase to lowercase form.
  */
  def toLower: LiteraryGreekString = {
    lcString(ascii,"")
  }


  /** Recursively converts characters in src to upper case form.
  *
  * @param src Ascii representation of a [[GreekString]] to convert to upper case.
  * @param accumulator String of previously converted characters.
  */
  private def lcString(src: String, accumulator: String) : LiteraryGreekString = {
    if (src.isEmpty) {
      LiteraryGreekString(accumulator)

    } else {
      if (src.head == '*') {
        lcString(src.tail, accumulator)
      } else {
        lcString(src.tail, accumulator + src.head)
      }
    }
  }



  /** Capitalize first letter of the string if not already
  * in uppercase form.
  */
  def capitalize: LiteraryGreekString = {
    if (ascii.head == '*') {
      LiteraryGreekString(ascii)
    } else {
      LiteraryGreekString("*" + ascii)
    }
  }

  /** Capitalize all white-space delimited words in the string.
  */
  def camelCase: LiteraryGreekString = {
    val splits = ascii.split(" ").toVector.map(LiteraryGreekString(_))
    val camelAscii = splits.map(_.capitalize.ascii)
    LiteraryGreekString(camelAscii.mkString(" "))
  }

  /**
  */
  def stripAccent: LiteraryGreekString = {
    stripAccs(ascii,"")
  }
  private def stripAccs(src: String, accumulator: String): LiteraryGreekString = {
    if (src.isEmpty) {
      LiteraryGreekString(accumulator)

    } else {
      if (isAccent(src.head)) {
        stripAccs(src.tail, accumulator)
      } else {
        stripAccs(src.tail, accumulator + src.head)
      }
    }
  }


  /** All valid characters in the ASCII representation of this system
  * in their alphabetic order.
  */
  val alphabetString ="""abgdezhqiklmncoprsΣtufxyw|()/\=+,:;.""" + " \n\r"

}

object LiteraryGreekString {
  val vowels = Vector('a','e','h','i','o','u','w')
  val consonants = Vector('b','g','d','z','q','k','l','m','n','c','p',
    'r','s','t','f','x','y','Σ')
  val breathings = Vector(')', '(')
  val accents = Vector('=', '/', '\\')
  val comboChars = Vector('|','+')

  def isVowel (c: Character): Boolean = {vowels.contains(c)}
  def isConsonant (c: Character): Boolean = {consonants.contains(c)}
  def isAlpha(c: Character): Boolean = (isVowel(c) || isConsonant(c))
  def isAccent(c: Character): Boolean = {accents.contains(c)}
  def isBreathing(c: Character): Boolean = {breathings.contains(c)}
  def isCombining(c: Character): Boolean = {
    (comboChars.contains(c) || isAccent(c) || isBreathing(c))

  }


  def classOfChar(c: Character): String = {
    if (vowels.contains(c)) {
      "vowel"
    } else if (consonants.contains(c)) {
      "consonant"
    } else if (breathings.contains(c)) {
      "breathing"
    } else if (accents.contains(c)) {
      "accent"
    } else if (comboChars.contains(c)) {
      "combining"
    } else {
      "invalid"
    }
  }


  // extract run of ascii chars to put in
  // a single combined point
  def peekAhead(s: String, accumulator: String): String = {
    if (s.size < 2) {
      accumulator + s
    } else {
      if (s(0) == '*') {
        if (s.size == 2) {
          accumulator + s
        } else if (isCombining(s(2))) {
          peekAhead(s.drop(2), accumulator + s.take(2))
        } else {
          accumulator + s.take(2)
        }

      } else if (isCombining(s(1))) {
        peekAhead(s.drop(1), accumulator + s.head)
      } else {
        accumulator + s.head.toString
      }
    }
  }
  def asciiToUcode(ascii: String, ucode: String): String = {
    if (ascii.size == 0 ) {
      ucode

    } else if (ascii.size == 1) {
      ucode + CodePointTranscoder.ucodeCodePoint(ascii)

    } else {
      val chunk = peekAhead(ascii,"")
      val newUcode = ucode + CodePointTranscoder.ucodeCodePoint(chunk)
      val newAscii = ascii.drop(chunk.size)
      asciiToUcode(newAscii, newUcode)
    }
  }


  //ucode must be already normalized to NFC
  def nfcToAscii(ucode: String, ascii: String): String = {
    if (ucode.size == 0 ) {
      ascii

    } else if (ucode.size == 1) {
      ascii +  CodePointTranscoder.asciiCodePoint(ucode)

    } else {
      val newUcode = ucode.drop(1)
      val newAscii = ascii + CodePointTranscoder.asciiCodePoint(ucode.head.toString)
      nfcToAscii(newUcode,newAscii )
    }
  }

}
