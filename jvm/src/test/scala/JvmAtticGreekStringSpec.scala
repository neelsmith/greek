package edu.holycross.shot.greek
import org.scalatest.FlatSpec


import java.text.Normalizer

class JvmAtticGreekStringSpec extends FlatSpec {




  "The JVM package object"  should "create the AtticGreekString's `ucode` view of strings not in form NFC" in  {
    val s = "me=nin"
    val expected = Normalizer.normalize("μêνιν", Normalizer.Form.NFC)
    assert(atticUcodeOf(s) == expected)
  }

}
