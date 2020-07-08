package edu.holycross.shot.greek

import edu.holycross.shot.mid.orthography._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._
import edu.holycross.shot.citevalidator._
import edu.holycross.shot.scm._
import edu.holycross.shot.dse._

import wvlet.log._
import wvlet.log.LogFormatter.SourceCodeLogFormatter


import scala.scalajs.js
import scala.scalajs.js.annotation._
import scala.annotation.tailrec

/** Representation of a Greek string written in conventional literary orthography.
*
* @param str A string in either the ascii or ucode representation of the [[LiteraryGreekString]]
* system.
*/
case class LiteraryGreekString(str: String)  extends LGSTrait with GreekString with Ordered[GreekString] with LogSupport  {

  require(str.nonEmpty, "Cannot create LiteraryGreekString from empty String")

  //////////////////////////////////////////////
  /////////////// REQUIRED BY LGSTrait
  //
  /** All valid characters in the ASCII representation of this system
  * in their alphabetic order in Greek.
  */
  def alphabetString =  LiteraryGreekOrthography.alphabetString  //"*abgdezhqiklmncoprstufxyw'.|()/\\=+,:;.— \n\r"

  def punctuationString: String = LiteraryGreekOrthography.punctuationString

  /** Alphabetically ordered Vector of vowel characters in `ascii` view.*/
  def vowels = LiteraryGreekOrthography.vowels //Vector('a','e','h','i','o','u','w')

  /** Alphabetically ordered Vector of consonant characters in `ascii` view.*/
  def consonants = LiteraryGreekOrthography.consonants // ('b','g','d','z','q','k','l','m','n','c','p',
  //'r','s','t','f','x','y') //,'Σ')

  def accents =  LiteraryGreekOrthography.accents

  /** Breathing characters. */
  def breathings = LiteraryGreekOrthography.breathings // Vector(')', '(')
  /** Accent characters. */
  Vector('=', '/', '\\')

  /** */
  def comboChars = LiteraryGreekOrthography.comboChars //Vector('|','+')

  // use concrete encoding in LGSTrait
  def combos: String = fixedCombos(str)


  /////////////// REQUIRED BY GreekString trait
  //
  def stripAccent: LiteraryGreekString = LiteraryGreekString(stripAccentString)

  def stripBreathing: LiteraryGreekString = LiteraryGreekString(stripBreathingString)

  def stripBreathingAccent: LiteraryGreekString = LiteraryGreekString(stripBreathingAccentString)

  def flipGrave: LiteraryGreekString = LiteraryGreekString(flipGraveString)

  def toLower: LiteraryGreekString = LiteraryGreekString(lowerCase)

  def toUpper: LiteraryGreekString = LiteraryGreekString(upperCase)


  //// OTHER FORMATTING METHODS
  def capitalize: LiteraryGreekString = LiteraryGreekString(capitalizeString)

  def depunctuate: LiteraryGreekString = LiteraryGreekString(LiteraryGreekOrthography.depunctuate(ascii).mkString(" "))

}
