package edu.holycross.shot


import scala.scalajs.js
import js.JSStringOps._

package object greek {

  def literaryAsciiOf (s: String): String = {
    "ASCII OF " + s
  }

  def literaryUcodeOf(s: String) : String = {
    "UCODE OF " + s
  }

    def atticAsciiOf(s: String) : String = {
      "ATTIC ASCII OF  " + s
    }
    def atticUcodeOf(s: String) : String = {
      "ATTIC Ucode OF  " + s
    }
}
