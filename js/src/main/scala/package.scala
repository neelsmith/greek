package edu.holycross.shot


import scala.scalajs.js
import js.JSStringOps._

package object greek {

  def asciiOf(s: String): String = {
    "TRANSCODE " + s
  }


  def ucodeOf(s: String): String = {
    "UCODE OF " + s
  }
}
