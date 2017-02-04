package edu.holycross.shot.greek
import org.scalatest.FlatSpec


import java.text.Normalizer

class TranscodingSpec extends FlatSpec {




  "A code point transcoder object"  should "derive a Unicode Greek string when instantiated with an ASCII string" in  {
    val lgs = LiteraryGreekString("mh=nin")
    val expected = Normalizer.normalize("μῆνιν", Normalizer.Form.NFC)
    assert(lgs.ascii == "mh=nin")
    assert(lgs.ucode == expected)
  }



}
