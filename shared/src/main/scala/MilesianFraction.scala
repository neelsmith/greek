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
@JSExportAll  case class MilesianFraction(str: String) extends MilesianPartial with LogSupport {
  Logger.setDefaultLogLevel(LogLevel.INFO)


  // DETERMINE WHETHER TO SUB b or β
  def expandedFractions = if (isAscii(str)) {
    str.replaceAll(MilesianNumeric.halfString, "b ").replaceAll(MilesianNumeric.twoThirdsString, "b " + MilesianNumeric.stigma + " ")
  } else {
    str.replaceAll(MilesianNumeric.halfString, "β ").replaceAll(MilesianNumeric.twoThirdsString, "β " + MilesianNumeric.stigma + " ")
  }

  /** Ascii representation of this numeric.*/
  def ascii: String = {
    milesianAsciiOf(str) +"\""
  }

  /** Unicode representation of this numeric.*/
  def ucode: String = {
      milesianUcodeOf(str) +"\""
  }

  def toDouble: Double = {
    fract.getOrElse(0.0)
  }

  def fract: Option[Double] = {
    MilesianNumeric.fract(str)
  }

}
