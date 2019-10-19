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
@JSExportAll  case class MilesianNumeric(str: String) extends GreekNumeric with  Ordered[GreekNumeric] {


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
  val numericAlphabetString = "abgdezhqiklmncoprsΣtufxyw.|()/=+,:;. \n\r"



  def toInt: Int = {
    0
  }

  def toDouble: Double = {
    0.0
  }

  override def compare(that:GreekNumeric): Int = {
    this.toDouble compare that.toDouble
  }
}

/** Utility functions for working with definitions of the [[MilesianNumeric]]
* class's character encoding.
*/
object MilesianNumeric extends MidOrthography {
  def validCP(cp: Int): Boolean = false
  def orthography: String = "Greek numeric value in Milesian notation"
  def tokenCategories: Vector[MidTokenCategory] = Vector.empty[MidTokenCategory]

  def tokenizeNode (cn: CitableNode) : Vector[MidToken]  = Vector.empty[MidToken]
  /** Alphabetically ordered Vector of vowel characters in `ascii` view.*/
  //val chars = Vector('a','b','g','d','e','SIX')



}
