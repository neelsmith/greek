package edu.holycross.shot.greek
import org.scalatest.FlatSpec




class AsciiToUcodeSpec extends FlatSpec {




  "A literary Greek string's ascii to unicode transcoding" should "map single ASCII characters to a codepoint" in {

    assert(LiteraryGreekString.asciiToUcode("m","") == "μ")
  }

  it should "map sequences of consonants to an equal number of codepoints" in {
    assert(LiteraryGreekString.asciiToUcode("tr","") == "τρ")
  }

  it should "map sequences of vowels to an equal number of codepoints" in {
    assert(LiteraryGreekString.asciiToUcode("ei","") == "ει")
  }

  it should "map vowel+breathing+vowel to two code points" in {
    assert(LiteraryGreekString.asciiToUcode("a" + ')' + "a","") == "ἀα")
  }




}
