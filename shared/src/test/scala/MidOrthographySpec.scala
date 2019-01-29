package edu.holycross.shot.greek
import org.scalatest.FlatSpec


import edu.holycross.shot.mid.validator._


class MidOrthographySpec extends FlatSpec {



  "The LiteraryGreekString object" should "implement the MidOrthography trait's labelling function" in {
    val expected = "Conventional modern orthography of literary Greek"
    assert(LiteraryGreekString.orthography == expected)
  }


  // validate code points for ascii versions
  it should "implement the MidOrthography trait's validCP function" in {

  }
  it should "implement the MidOrthography trait's tokenCategories function" in {
    val expected = Vector(PunctuationToken, LexicalToken, NumericToken).toSet
    assert (LiteraryGreekString.tokenCategories.toSet == expected)
  }
  it should "implement the MidOrthography trait's tokenizeNode function" in pending

  it should "determine if an ASCII code point is valid" in  {
    assert(LiteraryGreekString.validAsciiCP('a'.toInt))
    assert(LiteraryGreekString.validAsciiCP('α'.toInt) == false)
  }

  it should "determine if a code point in the Unicode range is valid" in  {
    assert(LiteraryGreekString.validCP('α'.toInt))
    assert(LiteraryGreekString.validCP('a'.toInt))
  }
}
