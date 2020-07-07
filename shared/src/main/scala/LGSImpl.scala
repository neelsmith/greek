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
case class LGSImpl(str: String)  extends LGSTrait with  Ordered[GreekString] with LogSupport  {

  /** All valid characters in the ASCII representation of this system
  * in their alphabetic order in Greek.
  */
  def alphabetString = "*abgdezhqiklmncoprstufxyw'.|()/\\=+,:;.— \n\r"
  // use concrete encoding in LGSTrait
  def combos: String = fixedCombos(str)

  /** Alphabetically ordered Vector of vowel characters in `ascii` view.*/
  def vowels = Vector('a','e','h','i','o','u','w')
  /** Alphabetically ordered Vector of consonant characters in `ascii` view.*/
  def consonants = Vector('b','g','d','z','q','k','l','m','n','c','p',
    'r','s','t','f','x','y') //,'Σ')
  /** Breathing characters. */
  def breathings = Vector(')', '(')
  /** Accent characters. */
  def accents = Vector('=', '/', '\\')

  def punctuationString: String = {
    //"(),;:.?"
    """,;:".—"""
  }

 def stripAccent: LGSImpl = LGSImpl(stripAccentString(ascii))
 def stripBreathing: LGSImpl = LGSImpl(stripBreathingString(ascii))
 def stripBreathingAccent: LGSImpl = LGSImpl(stripBreathingAccentString(ascii))
 def toLower: LGSImpl = LGSImpl(toLowerString(ascii))

    /** Characters in addition to breathings and accents that combine with
    * other characters in `ucode` view.
    */
    val comboChars = Vector('|','+')

    val whiteSpace = Vector(' ','\t', '\n', '\r' )

    val numericTick: Character = 'ʹ'
    val typography = Vector('\'',  '*', numericTick)

    val validList = vowels.mkString("") + consonants.mkString("") + breathings.mkString("") + accents.mkString("") + comboChars.mkString("") + punctuationString.mkString("") + whiteSpace.mkString("") + typography.mkString("")



    def flipGrave: LGSImpl = {
      LGSImpl(flipGraveString(ascii))
    }




  //require(str.nonEmpty, "Cannot create LiteraryGreekString from empty String")

  /** The ASCII representation of this string.
  */
  //val ascii = literaryAsciiOf(combos.replaceAll("ς","σ"))


  //val ucode =  literaryUcodeOf(fixedCombos.replace("s ","Σ ").replaceAll("s$","Σ").replaceAll("σ ", "ς ").replaceAll("σ$", "ς"))

  /** The representation of this string with glyphs in the "Greek and Coptic"
  * and "Extended Greek" blocks of Unicode.

  val ucode =  {
    val lowered = literaryUcodeOf(combos).replaceAll("σ ", "ς ").replaceAll("σ$", "ς")
    lowered
  }*/

  /**

  def fixedCombos: String = {
    if (str.head == '“') {
      '“' +  CodePointTranscoder.swapPrecedingBreathings(str.tail)
    } else {
      CodePointTranscoder.swapPrecedingBreathings(str)
    }
  }*/


  /** Compare this string to a second [[GreekString]] alphabetically
  * using the [[GreekString]] trait's implementation of [[asciiCompare]].
  *
  * @param that Second [[GreekString]] to compare.
  */
  override def compare(that:GreekString): Int = {
    asciiCompare(this.flipGrave.ascii, that.flipGrave.ascii)
  }


  /** Required function to convert lowercase to uppercase form.
  */
  def toUpper: LGSTrait /*= {
    ucString(ascii,"")
  }*/

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





  /** Recursively converts characters in src to upper case form.
  *
  * @param src Ascii representation of a [[GreekString]] to convert to upper case.
  * @param accumulator String of previously converted characters.

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
  */


  /** Capitalize first letter of the string if not already
  * in uppercase form.

  def capitalize: LiteraryGreekString = {
    if (ascii.head == '*') {
      LiteraryGreekString(ascii)
    } else {
      LiteraryGreekString("*" + ascii)
    }
  }
  */

  /** Capitalize all white-space delimited words in the string.

  def camelCase: LiteraryGreekString = {
    val splits = ascii.split(" ").toVector.map(LiteraryGreekString(_))
    val camelAscii = splits.map(_.capitalize.ascii)
    LiteraryGreekString(camelAscii.mkString(" "))
  }
  */


  /** Format a transliterated version of the string value for
  * human readers. */
  def xlit: String /* = {
    val replacements = stripBreathingAccent.ascii.replaceAll("h", "ê").replaceAll("q", "th").
    replaceAll("c", "x").replaceAll("f", "ph").replaceAll("x", "ch").
    replaceAll("y", "ps").replaceAll("w", "ô")

    val uc = "\\*(.)".r
    uc.replaceAllIn(replacements,m => m.group(1).toUpperCase)
  }*/


}
