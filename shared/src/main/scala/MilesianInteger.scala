package edu.holycross.shot.greek

import scala.scalajs.js
import scala.scalajs.js.annotation._


import edu.holycross.shot.mid.orthography._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._

import wvlet.log._
import wvlet.log.LogFormatter.SourceCodeLogFormatter

/*
CodePointPair("ϙ","ϙ"), //985
//CodePointPair("","Ϛ"), //986 \x03da
CodePointPair("ϛ","ϛ"), //987
//CodePointPair("","Ϝ"), //988
//CodePointPair("","ϝ"), //989
//CodePointPair("","Ϟ"), //990
//CodePointPair("","ϟ"), //991
//CodePointPair("","Ϡ"), //992
CodePointPair("ϡ","ϡ"), //993
*/

/** Representation of a Greek string written in conventional literary orthography.
*
* @param str A string in either the ascii or ucode representation of the [[GreekNumeric]]
* system.
*/
@JSExportAll  case class MilesianInteger(str: String) extends GreekNumeric  with  Ordered[GreekNumeric]  with LogSupport {



  
  def ascii: String =  {
    if (unticked == MilesianNumeric.oudenString) {
      MilesianNumeric.oudenString
    } else {
      milesianAsciiOf(unticked, cpList) + MilesianNumeric.numericTick
    }

  }
  def ucode: String = {
    debug("EXAMINE UNTICKED FOR UCODE: " + unticked)
    if (unticked == MilesianNumeric.oudenString) {
      MilesianNumeric.oudenString
    } else {
      milesianUcodeOf(unticked, cpList) + MilesianNumeric.numericTick
    }
  }


  def toDouble: Double = {
    toInt.toDouble
  }

  def toInt: Int = {
    if (unticked ==   MilesianNumeric.oudenString) {
      0
    }  else {
      debug("MilesianInteger convert unticked form " + unticked + " to int.")
      val intified = MilesianNumeric.toInt(ascii) //.getOrElse(0)
      debug("It was " + intified)
      intified
    }

  }

  override def compare(that: GreekNumeric): Int = {
    this.toDouble compare that.toDouble
  }


  def unticked : String = {
    str.replaceFirst( MilesianNumeric.numericTick, "").replaceFirst("'", "")
  }

}
