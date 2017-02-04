package edu.holycross.shot


import scala.scalajs.js
import js.JSStringOps._

package object greek {

  def asciiOf(s: String) = {
    "TRANSCODE " + s
  }


  def ucodeOf(s: String) = {
    "UCODE OF " + s
  }
}
