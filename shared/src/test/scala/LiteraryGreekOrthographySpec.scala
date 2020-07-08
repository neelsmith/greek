package edu.holycross.shot.greek
import org.scalatest.FlatSpec


import edu.holycross.shot.mid.orthography._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._

class LiteraryGreekOrthographySpec extends FlatSpec {

  val urn = CtsUrn("urn:cts:greekLit:tlg0540.tlg001.omar:1.1")
  val txt = "περὶ πολλοῦ ἂν ποιησαίμην, ὦ ἄνδρες, "
  val cn = CitableNode(urn, txt)

  "The LiteraryGreekOrthography object" should "implement the MidOrthography trait's labelling function" in {
    val expected = "Conventional modern orthography of literary Greek"
    assert(LiteraryGreekOrthography.orthography == expected)
  }

  // validate code points for ascii versions
  it should "implement the MidOrthography trait's validCP function" in {
    assert(LiteraryGreekOrthography.validCP('a'.toInt))
  }

  it should "determine if an ASCII code point is valid" in  {
    assert(LiteraryGreekOrthography.validAsciiCP('a'.toInt))
    assert(LiteraryGreekOrthography.validAsciiCP('α'.toInt) == false)
  }

  it should "determine if a code point in the Unicode range is valid" in  {
    assert(LiteraryGreekOrthography.validCP('α'.toInt))
    assert(LiteraryGreekOrthography.validCP('a'.toInt))
  }

  it should "depunctuate" in {
    val comma  = "δὲ,"
    val depunctuated = (LiteraryGreekOrthography.depunctuate(comma))
    assert(depunctuated.size == 2)
    assert(depunctuated(1) == ",")
  }

}
