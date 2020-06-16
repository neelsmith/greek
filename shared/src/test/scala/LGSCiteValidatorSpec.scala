package edu.holycross.shot.greek

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._

import org.scalatest.FlatSpec




class LGSCiteValidatorSpec extends FlatSpec {

  "The Literary GreekString object" should "implement the verify method of the CiteValidator trait" in pending


  it should "validate the contents of a CitableNode" in {
    val urn = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1")
    val cn = CitableNode(urn, "Μῆνιν ἄειδε θεὰ Πηληϊάδεω Ἀχιλῆος")
    val validationResults = LiteraryGreekString.validate(cn)
    val expectedTotal = 5
    val expectedGood = 5
    assert(validationResults.size == expectedTotal)
    assert(validationResults.filter(_.success).size == expectedTotal)
    assert(validationResults.filterNot(_.success).isEmpty)
  }

  it should "identify invalid tokens" in {
    val urn = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1")
    val cn = CitableNode(urn, "Μῆνιν ἄειδε XXθεὰXX Πηληϊάδεω Ἀχιλῆος")
    val validationResults = LiteraryGreekString.validate(cn)
    val expectedGood = 4
    val expectedBad = 1
    assert(validationResults.filter(_.success).size == expectedGood)
    assert(validationResults.filterNot(_.success).size == expectedBad)
  }
}
