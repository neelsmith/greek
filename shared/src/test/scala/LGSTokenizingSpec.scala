package edu.holycross.shot.greek
import org.scalatest.FlatSpec


import edu.holycross.shot.mid.orthography._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._

class LGSTokenizingSpec extends FlatSpec {

  val urn = CtsUrn("urn:cts:greekLit:tlg0540.tlg001.omar:1.1")
  val txt = "περὶ πολλοῦ ἂν ποιησαίμην, ὦ ἄνδρες, "
  val cn = CitableNode(urn, txt)

  "The LiteraryGreekString object" should "split strings on trailing punctuation" in {
    val comma  = "δὲ,"
    val depunctuated = (LiteraryGreekString.depunctuate(comma))
    assert(depunctuated.size == 2)
    assert(depunctuated(1) == ",")
  }

  it should "recognize trailing quote as punctuation" in {
    val trailingQuote = """νῆας" """
    val depunctuated = (LiteraryGreekString.depunctuate(trailingQuote))
    assert(depunctuated.size == 2)
  }

}
