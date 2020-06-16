package edu.holycross.shot.greek

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.scm._


import edu.holycross.shot.mid.markupreader._
import edu.holycross.shot.mid.validator._
import org.homermultitext.edmodel._

import org.scalatest.FlatSpec




class LGSCiteValidateLibSpec extends FlatSpec {

  def readersMap : Map[String, Vector[MidMarkupReader]] = Map(
    "DiplomaticReader" ->   Vector(DiplomaticReader)
  )
  val lib = EditorsRepo("jvm/src/test/resources/iliad23-2020", readersMap).library
  val lgsv = LGSValidator(lib)

  "An LGSValidator" should  "apply the validate method of the CiteValidator trait to a library" in {
    val rslts = lgsv.validate(lib)
    val good = rslts.filter(_.success)
    val bad = rslts.filterNot(_.success)
    println("LIBRARY TOTAL: " + rslts.size + s"\nGood: ${good.size}\nBad ${bad.size}")
  }



  it should "validate a text corpus" in {
    val rslts = lgsv.validate(lib.textRepository.get.corpus)
    val good = rslts.filter(_.success).size
    val bad = rslts.filterNot(_.success).size
    println(s"Corpus total: ${good + bad}\nGood/bad: ${good}/${bad}")
  }

  it should "apply the validate method of the CiteValidator trait to a physical surface" in {
    val pg = Cite2Urn("urn:cite2:hmt:msB.v1:303r")
    val rslts = lgsv.validate(pg)
    val good = rslts.filter(_.success).size
    val bad = rslts.filterNot(_.success).size
    println(s"Surface ${pg} total: ${good + bad}\nGood/bad: ${good}/${bad}")
  }

  it should "validate the contents of a CitableNode" in {
    val urn = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1")
    val cn = CitableNode(urn, "Μῆνιν ἄειδε θεὰ Πηληϊάδεω Ἀχιλῆος")
    val validationResults = lgsv.validate(cn)
    val expectedTotal = 5
    val expectedGood = 5
    assert(validationResults.size == expectedTotal)
    assert(validationResults.filter(_.success).size == expectedTotal)
    assert(validationResults.filterNot(_.success).isEmpty)
  }

  it should "identify invalid tokens" in {
    val urn = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1")
    val cn = CitableNode(urn, "Μῆνιν ἄειδε XXθεὰXX Πηληϊάδεω Ἀχιλῆος")
    val validationResults = lgsv.validate(cn)
    val expectedGood = 4
    val expectedBad = 1
    assert(validationResults.filter(_.success).size == expectedGood)
    assert(validationResults.filterNot(_.success).size == expectedBad)
  }


}
