package edu.holycross.shot.greek

import scala.scalajs.js
import scala.scalajs.js.annotation._


@JSExportAll  case class AtticGreekString(str: String) extends GreekString {
  val ascii = atticAsciiOf(str)
  val ucode = atticUcodeOf(str)



  def toUpper: AtticGreekString = {
    if (ascii.head == '*') {
      AtticGreekString(ascii)
    } else {
      AtticGreekString(ascii.tail)
    }
  }
  def toLower: AtticGreekString = {
    if (ascii.head == '*') {
      AtticGreekString(ascii.tail)
    } else {
      AtticGreekString(ascii)
    }
  }

  def stripAccent: AtticGreekString = {
    stripAccs(ascii,"")
  }
  def stripAccs(src: String, accumulator: String): AtticGreekString = {
    if (src.isEmpty) {
      AtticGreekString(accumulator)

    } else {
      if (isAccent(src.head)) {
        stripAccs(src.tail, accumulator)
      } else {
        stripAccs(src.tail, accumulator + src.head)
      }
    }
  }
  
  val alphabetString ="abgdezqiklmnoprstufxh \n"

}
