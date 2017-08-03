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

  it should "be scaffolded for unicode normalization" in  pending /*{
    val s = "string1"
    val normalized = s.normalize()  //normalize(UnicodeNormalizationForm.NFC

    assert (s == normalized)
  } */


  "A Literary Greek String" should "construct single-accented vowel in ancient Greek range" in {
    val rightAlpha = "μάλα"
    val wrongAlpha = "μάλα"

    val s1 = LiteraryGreekString("ma/la")
    val s2 = LiteraryGreekString(rightAlpha)
    assert(s1.ascii == s2.ascii)
    assert (s1 == s2)
    //assert (s1 == s3)
    // sadly, can't normalize in browser yet:

    val s3 = LiteraryGreekString(wrongAlpha)
    assert(s3.ascii.contains("#"))

  }
}
