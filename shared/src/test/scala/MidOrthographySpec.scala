package edu.holycross.shot.greek
import org.scalatest.FlatSpec


import edu.holycross.shot.mid.validator._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._

class MidOrthographySpec extends FlatSpec {

  val urn = CtsUrn("urn:cts:greekLit:tlg0540.tlg001.omar:1.1")
  val txt = "περὶ πολλοῦ ἂν ποιησαίμην, ὦ ἄνδρες, "
  val cn = CitableNode(urn, txt)

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
  it should "implement the MidOrthography trait's tokenizeNode function" in {
    val tokens = LiteraryGreekString.tokenizeNode(cn)
    val expectedTotal = 8
    val expectedLexical = 6
    val expectedPunct = 2
    assert(expectedTotal == tokens.size)

    val punct = tokens.filter(_.tokenCategory.toString == "Some(PunctuationToken)")
    assert(punct.size == expectedPunct)

    val lexical = tokens.filter(_.tokenCategory.toString == "Some(LexicalToken)")
    assert(expectedLexical == lexical.size)
  }

  it should "determine if an ASCII code point is valid" in  {
    assert(LiteraryGreekString.validAsciiCP('a'.toInt))
    assert(LiteraryGreekString.validAsciiCP('α'.toInt) == false)
  }

  it should "determine if a code point in the Unicode range is valid" in  {
    assert(LiteraryGreekString.validCP('α'.toInt))
    assert(LiteraryGreekString.validCP('a'.toInt))
  }


  it should "correctly depunctuate strings" in pending
}
