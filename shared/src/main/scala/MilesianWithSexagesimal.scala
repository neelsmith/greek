package edu.holycross.shot.greek

import scala.scalajs.js
import scala.scalajs.js.annotation._


import edu.holycross.shot.mid.orthography._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._

import wvlet.log._
import wvlet.log.LogFormatter.SourceCodeLogFormatter

/** Representation of a Greek string written in conventional literary orthography.
*
* @param str A string in either the ascii or ucode representation of the [[GreekNumeric]]
* system.
*/
@JSExportAll  case class MilesianWithSexagesimal(str: String) extends MilesianWithPartial with LogSupport {


  def intOpt : Option[MilesianInteger] = None

  def partialDouble: Double = 0.0
  def doublePrecision(digits: Int = 3): Option[Double]  = None
  def stringParts: (String, String) = ("","")
  def asciiPartial: String = ""
  def ucodePartial: String = ""
  def numericAlphabetString: String = ""
  def unicodeTickString : String = ""

  override def compare(that: GreekNumeric): Int = {
    this.toDouble compare that.toDouble
  }


}
