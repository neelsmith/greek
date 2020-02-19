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
@JSExportAll  case class MilesianSexagesimal(str: String) extends MilesianPartial  with LogSupport {
  Logger.setDefaultLogLevel(LogLevel.INFO)

  /** Integer component, if any, of submitted String.
  * If no integer component, this value is an empty String.*/
  def partialString = str.trim

  /** Ascii representation of this numeric.*/
  def ascii: String = {
      milesianAsciiOf(str) +"\""
  }

  /** Unicode representation of this numeric.*/
  def ucode: String = {
    milesianUcodeOf(str) +"\""
  }

  def toDouble: Double = {
    0.0
  }

}
