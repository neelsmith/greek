package edu.holycross.shot.greek
import org.scalatest.FlatSpec




class NfcToAsciiSpec extends FlatSpec {




  "A literary Greek string's unicode to ascii transcoding" should "map single ASCII characters to a codepoint" in {

    assert(asciiForString("μ",LiteraryGreekOrthography.cpList) == "m")
  }


  it should "map sequences of consonants to an equal number of codepoints" in {
    assert(asciiForString("τρ",LiteraryGreekOrthography.cpList) == "tr")
  }

  it should "map sequences of vowels to an equal number of codepoints" in {
    assert(asciiForString("ει",LiteraryGreekOrthography.cpList) == "ei")
  }

  it should "map vowel+breathing+vowel to two code points" in {
    assert(asciiForString("ἀα",LiteraryGreekOrthography.cpList) == "a" + ')' + "a","" )
  }

  it should "map vowel+breathing+accent to one code point" in {
      assert(asciiForString("ἄ",LiteraryGreekOrthography.cpList ) ==  "a" + ')' + "/","" )
  }

  it should "happily accept iota subscript as a vowel" in {
    val submitted = "μῆνιν ἄειδε θεὰ"
    val expected = "mh=nin a)/eide qea\\"
    assert (asciiForString(submitted,LiteraryGreekOrthography.cpList) == expected)
  }


}
