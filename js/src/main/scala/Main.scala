package edu.holycross.shot.greek

import scala.scalajs.js
import js.JSStringOps._

object Main extends js.JSApp {
  def main(): Unit = {

    val litgs = LiteraryGreekString("mh=nin")
    println("Create greek string with ascii: ")
    println("\t" + litgs.ascii)
    println("and Greek unicode")
    println("\t" + litgs.ucode)

  }
}
