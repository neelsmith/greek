package edu.holycross.shot.greek

import scala.scalajs.js
import scala.scalajs.js.annotation._


/** Representation of a Greek string written in the classical Attic alphabet.
*
* @param str A string in either the ascii or ucode representation of the [[AtticGreekString]]
* system.
*/
@JSExportAll  case class AtticGreekString(str: String) extends GreekString {
  /** The ASCII representation of this string.*/
  val ascii = atticAsciiOf(str)
  /** The Unicode representation of this string.*/
  val ucode = atticUcodeOf(str)


  /** Required function to convert lowercase to uppercase form.
  */
  def toUpper: AtticGreekString = {
    if (ascii.head == '*') {
      AtticGreekString(ascii)
    } else {
      AtticGreekString(ascii.tail)
    }
  }

  /** Required function to convert uppercase to lowercase form.
  */
  def toLower: AtticGreekString = {
    if (ascii.head == '*') {
      AtticGreekString(ascii.tail)
    } else {
      AtticGreekString(ascii)
    }
  }

  /** Required function to create a new [[GreekString]] with accents removed.
  */
  def stripAccent: AtticGreekString = {
    stripAccs(ascii,"")
  }

  /** Create a [[AtticGreekString]] with no accent characters
  * from an `ascii` String by recursively looking at the first character
  * of the ascii string and adding it to a new string only if it is
  * not an accent.
  *
  * @param src Remaining ascii String to strip accents from.
  * @param accumulator String of non-accent characters accumulated so far.
  *
  */
  def stripAccs(src: String, accumulator: String): AtticGreekString = {
    if (src.isEmpty) {
      AtticGreekString(accumulator)

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
  val alphabetString = "abgdezqiklmnoprstufxh \n\r"
}

object AtticGreekString {

  def asciiToUcode(s: String): String = {
    s
  }
}
