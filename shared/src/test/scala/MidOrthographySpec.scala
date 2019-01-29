package edu.holycross.shot.greek
import org.scalatest.FlatSpec


import edu.holycross.shot.mid.validator._


class MidOrthographySpec extends FlatSpec {



  "The LiteraryGreekString object" should "implement the MidOrthography trait's labelling function" in {
    val expected = "Conventional modern orthography of literary Greek"
    assert(LiteraryGreekString.orthography == expected)
  }

  it should "implement the MidOrthography trait's validCP function" in pending
  it should "implement the MidOrthography trait's tokenCategories function" in {
    val expected = Vector(PunctuationToken, LexicalToken, NumericToken).toSet
    assert (LiteraryGreekString.tokenCategories.toSet == expected)
  }
  it should "implement the MidOrthography trait's tokenizeNode function" in pending
}
