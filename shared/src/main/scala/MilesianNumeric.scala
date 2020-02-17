package edu.holycross.shot.greek

import scala.scalajs.js
import scala.scalajs.js.annotation._


import edu.holycross.shot.mid.validator._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._

import wvlet.log._
import wvlet.log.LogFormatter.SourceCodeLogFormatter

/** Representation of a Greek string written in conventional literary orthography.
*
* @param str A string in either the ascii or ucode representation of the [[GreekNumeric]]
* system.
*/
@JSExportAll  case class MilesianNumeric(str: String) extends GreekNumeric with  Ordered[GreekNumeric] {

  /** Permit keyboard entry of single quote as numeric tick mark,
  * but replace with proper Unicode code point.*/
  def unicodeTickString = {
    str.replaceFirst("'", MilesianNumeric.numericTick)
  }

  /** Split string into integer versus fractional parts.
  * The unicode numeric tick separates integer and fracional parts.
  * If it is absent, a fractional string must include the "seconds"
  * marker (double quote); otherwise, the string is an integer.
  */
  def stringParts: (String, String) = {
    val segments = unicodeTickString.split(MilesianNumeric.numericTick).toVector

    segments.size match {
      case 1 => {
        if (segments(0).contains("\"")) {
          ("", segments(0).replaceFirst("\"", ""))
        } else {
          (segments(0), "")
        }
      }
      case 2 => (segments(0), segments(1).replaceFirst("\"", ""))
    }
  }

  /** Integer component, if any, of submitted String.
  * If no integer component, this value is an empty String.*/
  def intString = stringParts._1.trim

  /** Fractional component, if any, of submitted String.
  * If no fractional component, this value is an empty String.*/
  def fractString = stringParts._2.trim

  /** Ascii representation of integer component of this numeric.*/
  def asciiInt = {
    if (intString.isEmpty) { "" } else {
      milesianAsciiOf(intString) + MilesianNumeric.numericTick
    }
  }

  /** Ascii representation of fractional component of this numeric.*/
  def asciiFract = {
    if (fractString.isEmpty) { "" } else {
      milesianAsciiOf(fractString) +"\""
    }
  }

  /** Ascii representation of this numeric.*/
  def ascii: String = {
    asciiInt + asciiFract
  }




  /** Unicode representation of integer component of this numeric.*/
  def  ucodeInt = {
    if (intString.isEmpty) { "" } else {
      milesianUcodeOf(intString) + MilesianNumeric.numericTick
    }
  }

  /** Unicode representation of fractional component of this numeric.*/
  def ucodeFract = {
    if (fractString.isEmpty) { "" } else {
      milesianUcodeOf(fractString) +"\""
    }
  }

  /** Unicode representation of this numeric.*/
  def ucode: String = {
      ucodeInt + ucodeFract
  }

  def numericAlphabetString = MilesianNumeric.numericAlphabetString
  def toDouble: Double = 0.0

  def intStr = {

  }

/*
  String longStr = "ε' 𐅵 δ" + '"'
  String fract = '𐅵 δ"'

  String intStr = "ε'"
  String shortStr = "ε"
*/
  override def compare(that:GreekNumeric): Int = {
    this.toDouble compare that.toDouble
  }
}


/** Utility functions for working with definitions of the [[MilesianNumeric]]
* class's character encoding.
*/
@JSExportAll object MilesianNumeric extends MidOrthography  with LogSupport {

  /** Unicode codepoint for numeric tick mark, as a String. */
  val numericTick: String = "ʹ"

  /** Double quote character is valid syntax in a  MilesianNumeric string
  * for the fractional, or secondary, segment of a String. */
  val seconds: String = "\""

  /** All valid characters in the ASCII representation of this system
  * in their alphabetic order.
  */
  //val alphabetString ="""abgdezhqiklmncoprsΣtufxyw|()/\=+,:;.""" + " \n\r"
  // temporarily leave out grave to make Atom's formatting sane
  val numericAlphabetString = "abgdezhqiklmncoprsΣtufxyw.|()/=+,:;. \n\r"


  def validCP(cp: Int): Boolean = false
  def orthography: String = "Greek numeric value in Milesian notation"
  def tokenCategories: Vector[MidTokenCategory] = Vector.empty[MidTokenCategory]

  def tokenizeNode (cn: CitableNode) : Vector[MidToken]  = Vector.empty[MidToken]
  /** Alphabetically ordered Vector of vowel characters in `ascii` view.*/
  //val chars = Vector('a','b','g','d','e','SIX')



}
