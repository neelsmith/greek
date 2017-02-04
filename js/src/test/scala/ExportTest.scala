package edu.holycross.shot.greek

import org.scalatest._
import scala.scalajs.js
//import js.JSStringOps._


class ExportTest extends FlatSpec {

  "The greek library"  should "construct a LiteraryGreekString from a String"  in {
    val litgs = LiteraryGreekString("mh=nin")
    assert (litgs.ascii == "mh=nin")
  }

}
