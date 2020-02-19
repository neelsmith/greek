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
@JSExportAll  case class MilesianWithFraction(str: String) extends MilesianWithPartial with  Ordered[GreekNumeric] with LogSupport {
  Logger.setDefaultLogLevel(LogLevel.INFO)

  def partialDouble(digits: Int = 3): Option[Double] = {
    MilesianNumeric.fract(asciiPartial, digits)
  }

  def partial: Option[MilesianPartial] = None

  def numericAlphabetString = ""

  /** Ascii encoding of fractional component with
  * conventional "seconds" mark (double quote). */
  def asciiPartial: String = {
    debug("Encode as ascii: " + partialString)
    if (partialString.isEmpty) { "" } else {
      milesianAsciiOf(partialString) +"\""
    }
  }


  /** Encoding of fractional component using Unicode
  * Greek code points, with conventional "seconds" mark
  * (double quote).*/
  def ucodePartial: String = {
    if (partialString.isEmpty) { "" } else {
      milesianUcodeOf(partialString) +"\""
    }
  }

  override def compare(that: GreekNumeric): Int = {
    this.toDouble compare that.toDouble
  }

  /** Substitute expanded fractional representation for shorthand 1/2 and 2/3
  * characters. */
  def expandedFractions = if (isAscii(str)) {
    val trimmed = str.trim.replaceAll(MilesianNumeric.halfString, "b ").replaceAll(MilesianNumeric.twoThirdsString, "b " + MilesianNumeric.stigma + " ").trim
    debug("Trimmed ascii: #" + trimmed + "#")
    trimmed
  } else {
    val trimmed = str.trim.replaceAll(MilesianNumeric.halfString, "β ").replaceAll(MilesianNumeric.twoThirdsString, "β " + MilesianNumeric.stigma + " ").trim
    debug("Trimmed: #" + trimmed + "#")
    trimmed
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
          ("", segments(0).trim.replaceFirst("\"", "").trim)
        } else {
          (segments(0).trim, "")
        }
      }
      case 2 => (segments(0).trim, segments(1).trim.replaceFirst("\"", "").trim)
    }
  }

  def unicodeTickString : String = {
    expandedFractions.replaceFirst("'", MilesianNumeric.numericTick)
  }




}
