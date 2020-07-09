package edu.holycross.shot.greek

import edu.holycross.shot.mid.orthography._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._
import edu.holycross.shot.citevalidator._
import edu.holycross.shot.scm._
import edu.holycross.shot.dse._

import wvlet.log._
import wvlet.log.LogFormatter.SourceCodeLogFormatter


import scala.scalajs.js
import scala.scalajs.js.annotation._
import scala.annotation.tailrec

/** Representation of a Greek string written in conventional literary orthography.
*
* @param str A string in either the ascii or ucode representation of the [[LiteraryGreekString]]
* system.
*/
trait LGSTrait  extends GreekString with  Ordered[GreekString] with LogSupport  {


  //////////////////////////////////////////////
  // REQUIRED METHODS TO IMPLEMENT FOR LGSTrait:
  def alphabetString: String //= LiteraryGreekString.alphabetString
  def punctuationString: String

  def vowels: Vector[Char]
  def consonants: Vector[Char]
  def accents: Vector[Char]
  def breathings: Vector[Char]
  def comboChars: Vector[Char]
  def combos: String


  /** The ASCII representation of this string.
  */
  def ascii : String


  //val ucode =  literaryUcodeOf(fixedCombos.replace("s ","Σ ").replaceAll("s$","Σ").replaceAll("σ ", "ς ").replaceAll("σ$", "ς"))
  /** The representation of this string with glyphs in the "Greek and Coptic"
  * and "Extended Greek" blocks of Unicode.
  */
  def ucode : String //=  literaryUcodeOf(combos).replaceAll("σ ", "ς ").replaceAll("σ$", "ς")




  /**  Check leading characters including quotation marks
  * and differing conventions for indicating upper-case in ascii,
  * and create a String following greek library conventions.
  *
  * @param str String to check.
  */
  def fixedCombos(str: String): String = {
    if (str.head == '“') {
      '“' +  CodePointTranscoder.swapPrecedingBreathings(str.tail)
    } else {
      CodePointTranscoder.swapPrecedingBreathings(str)
    }
  }

  /** Format a transliterated version of the string value for
  * human readers. */
  def xlit: String  = {
    val replacements = stripBreathingAccentString.replaceAll("h", "ê").replaceAll("q", "th").
    replaceAll("c", "x").replaceAll("f", "ph").replaceAll("x", "ch").
    replaceAll("y", "ps").replaceAll("w", "ô")

    val uc = "\\*(.)".r
    uc.replaceAllIn(replacements,m => m.group(1).toUpperCase)
  }

  /** Capitalize first letter of the string if not already
  * in uppercase form.
  */
  def capitalizeString: String = {
    if (ascii.head == '*') {
      ascii
    } else {
      "*" + ascii
    }
  }



  ////////// Methods for working with classifications of characters
  //
  /** True if given character is a vowel.
  *
  * @param c Character to check.
  */
  def isVowel (c: Character): Boolean = {vowels.contains(c)}

  /** True if given character is a consonant.
  *
  * @param c Character to check.
  */
  def isConsonant (c: Character): Boolean = {consonants.contains(c)}

  /** True if given character is alphabetic.
  *
  * @param c Character to check.
  */
  def isAlpha(c: Character): Boolean = (isVowel(c) || isConsonant(c))

  /** True if given character is an accent.
  *
  * @param c Character to check.
  */
  def isAccent(c: Character): Boolean = {accents.contains(c)}

  /** True if given character is a breathing.
  *
  * @param c Character to check.
  */
  def isBreathing(c: Character): Boolean = {breathings.contains(c)}

  /** True if given character combines with other characters in `ucode` view.
  *
  * @param c Character to check.
  */
  def isCombining(c: Character): Boolean = {
    (comboChars.contains(c) || isAccent(c) || isBreathing(c))
  }
  /** String label for class of a character.
  *
  * @param c Character to classify.
  */
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


  /////////////////////////////////////////////////////////////////////////
  /// SUPPORT FOR REQUIRED METHODS OF GreekString trait
  //
  def stripAccentString: String = {
    stripAccs(ascii)
  }
  /** Strip all accent characters from an `ascii` String
  * by recursively looking at the first character
  * of the ascii string and adding it to a new string only if it is
  * not an accent.
  *
  * @param src Remaining ascii String to strip accents from.
  * @param accumulator String of non-accent characters accumulated so far.
  */
  @tailrec private def stripAccs(src: String, accumulator: String = ""): String = {
    if (src.isEmpty) {
      accumulator

    } else {
      if (isAccent(src.head)) {
        stripAccs(src.tail, accumulator)
      } else {
        stripAccs(src.tail, accumulator + src.head)
      }
    }
  }

  def stripBreathingString: String = {
    stripBreathingsString(ascii,"")
  }
  @tailrec private def stripBreathingsString(src: String, accumulator: String = ""): String = {
    if (src.isEmpty) {
      accumulator

    } else {

      if (isBreathing(src.head)) {
        stripBreathingsString(src.tail, accumulator)
      } else {
        stripBreathingsString(src.tail, accumulator + src.head)
      }
    }
  }

  def stripBreathingAccentString: String = {
    stripAccs(stripBreathingString,"")
  }

  def flipGraveString: String  =  {
    ascii.replaceAll("\\\\", "/")
  }


  def upperCase: String =  {
    ucString(ascii,"")
  }
  /** Recursively converts characters in src to upper case form.
  *
  * @param src Ascii representation of a [[GreekString]] to convert to upper case.
  * @param accumulator String of previously converted characters.
  */
  private def ucString(src: String, accumulator: String) : String = {
    if (src.isEmpty) {
      accumulator

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


  def lowerCase = lcString(ascii)
  /** Recursively converts characters in src to upper case form.
  *
  * @param src Ascii representation of a [[GreekString]] to convert to upper case.
  * @param accumulator String of previously converted characters.
  */
  @tailrec private def lcString(src: String, accumulator: String = "") : String = {
    if (src.isEmpty) {
      accumulator

    } else {
      if (src.head == '*') {
        lcString(src.tail, accumulator)
      } else {
        lcString(src.tail, accumulator + src.head)
      }
    }
  }


  /////////////// REQUIRED BY Ordered[GreekString]
  //
  /** Compare this string to a second [[GreekString]] alphabetically
  * using the [[GreekString]] trait's implementation of [[asciiCompare]].
  *
  * @param that Second [[GreekString]] to compare.
  */
  override def compare(that:GreekString): Int = {
    asciiCompare(this.flipGrave.ascii, that.flipGrave.ascii)
  }
}
