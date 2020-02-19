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
trait MilesianNumTrait extends GreekNumeric with  Ordered[GreekNumeric] with LogSupport {
  Logger.setDefaultLogLevel(LogLevel.INFO)


  def int: Option[MilesianInteger]
  def partial: Option[MilesianPartial]
  
}
