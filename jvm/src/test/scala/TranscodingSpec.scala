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

  // ALL work in JVM:
  it should "construct single-accented vowel in ancient Greek range" in {
    val s1 = LiteraryGreekString("ma/la")
    val rightAlpha = "μάλα"
    val s2 = LiteraryGreekString(rightAlpha)
    val wrongAlpha = "μάλα"
    val s3 = LiteraryGreekString(wrongAlpha)

    assert(s1.ascii == s2.ascii)
    assert (s1 == s2)
    assert (s1 == s3)

  }

  it should "swap combining smooth breathing when it precedes a word" in {
    //ὡς ἐμοίχευεν ̓Ερατοσθένης τὴν γυναῖκα
      val eraucode = "Ἐρατοσθένης"
      val lgs = LiteraryGreekString(literaryUcodeOf(eraucode))

      assert(lgs.ascii == "*e)ratosqe/nhs")
      println("Via ucodeof = " + lgs.ascii)
      println("Direct: = " + LiteraryGreekString(eraucode).ascii)

  }


}
