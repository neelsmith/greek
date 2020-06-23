package edu.holycross.shot.greek
import org.scalatest.FlatSpec


import edu.holycross.shot.mid.orthography._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._

class LGSTokenizationSpec extends FlatSpec {

  val urn = CtsUrn("urn:cts:greekLit:tlg0540.tlg001.omar:1.1")
  val txt = "περὶ πολλοῦ ἂν ποιησαίμην, ὦ ἄνδρες, "
  val cn = CitableNode(urn, txt)

  "The LiteraryGreekString object" should  "implement the MidOrthography trait's tokenCategories function" in {
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

  it should "correctly depunctuate strings" in pending

  it should "correctly identify numeric tokens" in pending


  //it should ""

}
