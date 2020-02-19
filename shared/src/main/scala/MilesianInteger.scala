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
@JSExportAll  case class MilesianInteger(str: String) extends  LogSupport {
  Logger.setDefaultLogLevel(LogLevel.INFO)

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
