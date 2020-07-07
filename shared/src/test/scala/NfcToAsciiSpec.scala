package edu.holycross.shot.greek
import org.scalatest.FlatSpec




class NfcToAsciiSpec extends FlatSpec {




  "A literary Greek string's unicode to ascii transcoding" should "map single ASCII characters to a codepoint" in {

    assert(LiteraryGreekOrthography.nfcToAscii("μ","") == "m")
  }


  it should "map sequences of consonants to an equal number of codepoints" in {
    assert(LiteraryGreekOrthography.nfcToAscii("τρ","") == "tr")
  }

  it should "map sequences of vowels to an equal number of codepoints" in {
    assert(LiteraryGreekOrthography.nfcToAscii("ει","") == "ei")
  }

  it should "map vowel+breathing+vowel to two code points" in {
    assert(LiteraryGreekOrthography.nfcToAscii("ἀα","") == "a" + ')' + "a","" )
  }

  it should "map vowel+breathing+accent to one code point" in {
      assert(LiteraryGreekOrthography.nfcToAscii("ἄ","" ) ==  "a" + ')' + "/","" )
  }

  it should "happily accept iota subscript as a vowel" in {
    val submitted = "μῆνιν ἄειδε θεὰ"
    val expected = "mh=nin a)/eide qea\\"
    assert (LiteraryGreekOrthography.nfcToAscii(submitted,"") == expected)
  }


}
