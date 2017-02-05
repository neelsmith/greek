package edu.holycross.shot.greek

import scala.scalajs.js
import js.annotation.JSExport


@JSExport  case class AtticGreekString(str: String) extends GreekString {
  val ascii = atticAsciiOf(str)
  val ucode = atticUcodeOf(str)
}
