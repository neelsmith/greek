package edu.holycross.shot.greek

import scala.scalajs.js
import js.annotation.JSExport


@JSExport  case class AtticGreekString(str: String) extends GreekString {
  val ascii = atticAsciiOf(str)
  val ucode = atticUcodeOf(str)
  val alphabetString ="abgdezqiklmnoprstufxh"


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
}
