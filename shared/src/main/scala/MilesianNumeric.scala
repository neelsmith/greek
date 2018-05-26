package edu.holycross.shot.greek

import scala.scalajs.js
import scala.scalajs.js.annotation._

/** Representation of a Greek string written in conventional literary orthography.
*
* @param str A string in either the ascii or ucode representation of the [[GreekNumeric]]
* system.
*/
@JSExportAll  case class MilesianNumeric(str: String) extends GreekNumeric with  Ordered[GreekNumeric] {


  def ascii: String = {
    ""
  }

  def ucode: String = {
    ""
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


    override def compare(that:GreekNumeric): Int = {
      this.toInt compare that.toInt
    }
}

/** Utility functions for working with definitions of the [[MilesianNumeric]]
* class's character encoding.
*/
object MilesianNumeric {

  /** Alphabetically ordered Vector of vowel characters in `ascii` view.*/
  //val chars = Vector('a','b','g','d','e','SIX')

}
