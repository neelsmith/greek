package edu.holycross.shot.greek
import org.scalatest.FlatSpec




class NfcToAsciiSpec extends FlatSpec {




  "A literary Greek string's unicode to ascii transcoding" should "map single ASCII characters to a codepoint" in {

    assert(asciiForString("μ") == "m")
  }


  it should "map sequences of consonants to an equal number of codepoints" in {
    assert(asciiForString("τρ") == "tr")
  }

  it should "map sequences of vowels to an equal number of codepoints" in {
    assert(asciiForString("ει") == "ei")
  }

  it should "map vowel+breathing+vowel to two code points" in {
    assert(asciiForString("ἀα") == "a" + ')' + "a","" )
  }

  it should "map vowel+breathing+accent to one code point" in {
      assert(asciiForString("ἄ" ) ==  "a" + ')' + "/","" )
  }

  it should "happily accept iota subscript as a vowel" in {
    val submitted = "μῆνιν ἄειδε θεὰ"
    val expected = "mh=nin a)/eide qea\\"
    assert (asciiForString(submitted) == expected)
  }


}
