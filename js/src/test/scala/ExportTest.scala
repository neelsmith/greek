package edu.holycross.shot.greek

import org.scalatest._
import scala.scalajs.js
import scala.scalajs.js.UnicodeNormalizationForm._
import js.JSStringOps._


class ExportTest extends FlatSpec {

  "The greek library"  should "construct a LiteraryGreekString from a String"  in pending /*{
    val litgs = LiteraryGreekString("mh=nin")
    assert (litgs.ascii == "mh=nin")
  }*/

  it should "be scaffolded for unicode normalization" in {
    val s = "string1"
    val normalized = s.normalize()  //normalize(UnicodeNormalizationForm.NFC

    assert (s == normalized)
  }

}
