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
trait MilesianWithPartial extends GreekNumeric with  Ordered[GreekNumeric] with LogSupport {
  Logger.setDefaultLogLevel(LogLevel.INFO)

  def partial: Option[MilesianPartial]

  /** Raw string value allowed in constructoin of Milesian values.*/
  def str: String


  override def compare(that: GreekNumeric): Int = {
    this.toDouble compare that.toDouble
  }

  def int: Option[MilesianInteger] = None


  /** Split string into integer versus partial components. */
  def stringParts: (String, String)
  /** Ascii representation of fractional component of this numeric.*/
  def asciiPartial: String
  /** Unicode representation of fractional component of this numeric.*/
  def ucodePartial: String

  def partialDouble: Option[Double] =  None

  /** Permit keyboard entry of single quote as numeric tick mark,
  * but replace with proper Unicode code point.*/
  def unicodeTickString : String 

  /** Integer component, if any, of raw String.
  * If no integer component, this value is an empty String.*/
  def intString = stringParts._1.trim

  /** Partial component, if any, of raw String.
  * If no partial component, this value is an empty String.*/
  def partialString = stringParts._2.trim

  /** Ascii representation of integer component of this numeric.*/
  def asciiInt = {
    if (intString.isEmpty) { "" } else {
      milesianAsciiOf(intString) + MilesianNumeric.numericTick
    }
  }

  /** Ascii representation of this numeric.*/
  def ascii: String = {
    asciiInt + asciiPartial
  }

  /** Unicode representation of integer component of this numeric.*/
  def  ucodeInt = {
    if (intString.isEmpty) { "" } else {
      milesianUcodeOf(intString) + MilesianNumeric.numericTick
    }
  }

  /** Unicode representation of this numeric.*/
  def ucode: String = {
      ucodeInt + ucodePartial
  }

  def toDouble: Double = {
    toInt.getOrElse(0) + partialDouble.getOrElse(0.0)
  }




  /** Int value, if any, for this Milesian numeric.*/
  def toInt : Option[Int] = {
    MilesianNumeric.toInt(asciiInt)
  }

}
