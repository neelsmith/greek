package edu.holycross.shot.greek

import scala.scalajs.js
import scala.scalajs.js.annotation._


import edu.holycross.shot.mid.validator._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._

import wvlet.log._
import wvlet.log.LogFormatter.SourceCodeLogFormatter


/** Utility functions for working with definitions of the [[MilesianNumeric]]
* class's character encoding.
*/
@JSExportAll object MilesianNumeric extends MidOrthography  with LogSupport {
  Logger.setDefaultLogLevel(LogLevel.INFO)

  /** Maximum integer value currently supported in numeric conversions.*/
  val MAX_INT = 999

  /** Unicode codepoint for numeric tick mark, as a String. */
  val numericTick: String = "ʹ"

  /** Double quote character is valid syntax in a  MilesianNumeric string
  * for the fractional, or secondary, segment of a String. */
  val seconds: String = "\""

  val ouden = 'Ο' // upper-case Omicron
  val oudenString = "Ο" // upper-case Omicron
  val myriadCP = '\u039c'
  val myriadString = myriadCP + ""
  val stigma = '\u03DB'
  val stigmaString = '\u03DB' + ""
  val upperStigma = '\u03DA'
  val qoppa = '\u03D9'
  val qoppaString = '\u03D9' + ""
  val upperQoppa = '\u03D8'
  val sampi = '\u03E1'
  val sampiString = '\u03E1' + ""
  val upperSampi = '\u03E0'

  val halfString = "𐅵"
  val twoThirdsString = "𐅷"

  /** All valid characters in the ASCII representation of this system
  * in their alphabetic order.
  */
  //val alphabetString ="""abgdezhqiklmncoprsΣtufxyw|()/\=+,:;.""" + " \n\r"
  // temporarily leave out grave to make Atom's formatting sane
  val numericAlphabetString = "abgdezhqiklmncoprsΣtufxyw.|()/=+,:;. \n\r"


  def validCP(cp: Int): Boolean = false
  def orthography: String = "Greek numeric value in Milesian notation"
  def tokenCategories: Vector[MidTokenCategory] = Vector.empty[MidTokenCategory]

  def tokenizeNode (cn: CitableNode) : Vector[MidToken]  = Vector.empty[MidToken]

  def toInt(s: String) : Option[Int] = {
    val cps = strToCps(s)
    debug("cps from s: " + cps + " from " + s)
    val opts = cps.map(toInt(_))
    val total = opts.flatten.sum
    // what are appropriate checks and responses on bad characters?
    total match {
      case 0 => None
      case _ => Some(total)
    }
  }


  def fract(s: String, digits: Int = 3): Option[Double] = {
    debug("FRACT FROM " + s)
    val pieces = s.split("[\\s]+").toVector
    val subTotals = pieces.map(piece => toInt(piece)).flatten
    debug("From str " + s + " subTotals " + subTotals)
    if (subTotals.isEmpty) {
      None

    } else {
      debug("subTotals : "  + subTotals)
      val total = subTotals.map(i => 1.0 / i)
      val rounded = BigDecimal(total.sum).setScale(digits, BigDecimal.RoundingMode.HALF_UP).toDouble
      Some(rounded)
    }
  }

  /** Convert a code point to an integer if possible.
  *
  * @param Code point to convert.
  */
  def toInt(cp: Int) : Option[Int] = {
    cp match {
      case 'a' => Some(1)
      case 'b' => Some(2)
      case 'g' => Some(3)
      case 'd' => Some(4)
      case 'e' => Some(5)
      case `stigma` => Some(6)
      case `upperStigma` => Some(6)
      case 'z' => Some(7)
      case 'h' => Some(8)
      case 'q' => Some(9)
      case 'i' => Some(10)
      case 'k' => Some(20)
      case 'l' => Some(30)
      case 'm' => Some(40)
      case 'n' => Some(50)
      case 'c' => Some(60)
      case 'o' => Some(70)
      case 'p' => Some(80)
      case `qoppa` => Some(90)
      case 'r' => Some(100)
      case 's' => Some(200)
      case 't' => Some(300)
      case 'u' => Some(400)
      case 'f' => Some(500)
      case 'x' => Some(600)
      case 'y' => Some(700)
      case 'w' => Some(800)
      case `sampi` => Some(900)
      case _ => None
    }
  }

}
