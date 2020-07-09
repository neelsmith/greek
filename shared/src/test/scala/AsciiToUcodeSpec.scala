package edu.holycross.shot.greek
import org.scalatest.FlatSpec




class AsciiToUcodeSpec extends FlatSpec {




  "A literary Greek string's ascii to unicode transcoding" should "map single ASCII characters to a codepoint" in {
    //assert(ucodeOf("m", LiteraryGreekOrthography.cpList) == "μ")
  }
/*
  it should "map sequences of consonants to an equal number of codepoints" in {
    assert(ucodeOf("tr", LiteraryGreekOrthography.cpList) == "τρ")
  }


  it should "map sequences of vowels to an equal number of codepoints" in {
    assert(ucodeOf("ei",LiteraryGreekOrthography.cpList) == "ει")
  }
*/
  it should "map vowel+breathing+vowel to two code points" in {
    assert(ucodeOf("a" + ')' + "a",LiteraryGreekOrthography.cpList) == "ἀα")
  }
/*
  it should "map vowel+breathing+accent to one code point" in {
      assert(ucodeOf("a" + ')' + "/",LiteraryGreekOrthography.cpList) == "ἄ")
  }

  it should "happily accept iota subscript as a vowel" in {
    assert (ucodeOf("dw/rw|",LiteraryGreekOrthography.cpList) == "δώρῳ")
  }

  it should "accept white space" in {
    val expected = "μῆνιν ἄειδε θεὰ"
    val submitted = "mh=nin a)/eide qea\\"
    assert (ucodeOf(submitted,LiteraryGreekOrthography.cpList) == expected)
  }

  it should "construct single-accented vowel in ancient Greek range" in {

    val rightAlpha = "μάλα"
    val  wrongAlpha = "μάλα"

    val s1 = LiteraryGreekString("ma/la")
    val s2 = LiteraryGreekString(rightAlpha)

    assert(s1.ascii == s2.ascii)
    assert (s1 == s2)

    val s3 = LiteraryGreekString(wrongAlpha)
    // This only works in JVM:
    //   assert (s1 == s3)
    // This happens in ScalaJS:
    //assert(s3.ascii.contains("#"))

  }
*/




}
