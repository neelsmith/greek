package edu.holycross.shot.greek
import org.scalatest.FlatSpec

import edu.holycross.shot.scm._
import edu.holycross.shot.cite._
import edu.holycross.shot.citevalidator._



class LSGValidationSpec extends FlatSpec {

  val f = "jvm/src/test/resources/hmt-reduced.cex"
  val lib = CiteLibrarySource.fromFile(f)
  val corpus = lib.textRepository.get.corpus
  val validator  = LGSValidator(lib)
  val pg = Cite2Urn("urn:cite2:hmt:msA.v1:292v")

  "An LGSValidator" should "validate a citable node" in {
      val cn =  corpus.nodes(3)

      val rslts = validator.validate(cn)
      val tg = TestResultGroup("Validation of LiteraryGreekString orthography", rslts)
      println(tg.markdown)
      //println("TEXT TO VALIDATE: " + cn.text)
      //println(" GOOD / TOTAL: " + rslts.filter(_.success).size + " / " + rslts.size)
      //println("BAD: " + rslts.filterNot(_.success))
      //println("MADE a validator with corpu sof " + corpus.size + " nodes")

  }

  it should "validate a CiteLibary" in pending

  it should "validate a DSE surface" in {
    val rslts = validator.validate(pg)
  }

  it should "format a verification report for a surface" in {
    val verify = validator.verify(pg)
    println(verify)
  }

}
