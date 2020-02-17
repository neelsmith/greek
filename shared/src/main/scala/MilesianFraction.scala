package edu.holycross.shot.greek

import scala.scalajs.js
import scala.scalajs.js.annotation._


import edu.holycross.shot.mid.validator._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._

/** Representation of a Greek string written in conventional literary orthography.
*
* @param str A string in either the ascii or ucode representation of the [[GreekNumeric]]
* system.
*/
@JSExportAll  case class MilesianFraction(str: String) extends GreekNumeric with  Ordered[GreekNumeric] {


  def ascii: String = {
    milesianAsciiOf(str)
  }

  def ucode: String = {
    milesianUcodeOf(str)
  }


  /** All valid characters in the ASCII representation of this system
  * in their alphabetic order.
  */
  //val alphabetString ="""abgdezhqiklmncoprsΣtufxyw|()/\=+,:;.""" + " \n\r"
  // temporarily leave out grave to make Atom's formatting sane
  def numericAlphabetString = MilesianNumeric.numericAlphabetString


  def toDouble: Double = 0.0

  override def compare(that:GreekNumeric): Int = {
    this.toDouble compare that.toDouble
  }
}
