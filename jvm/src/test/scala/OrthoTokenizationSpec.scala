package edu.holycross.shot.greek
import org.scalatest.FlatSpec

import edu.holycross.shot.scm._



class OrthoTokenizationSpec extends FlatSpec {

  val f = "jvm/src/test/resources/hmt-reduced.cex"
  val lib = CiteLibrarySource.fromFile(f)

  "An LiteraryGreekOrthography" should "tokenize a citable node" in {
    val corpus = lib.textRepository.get.corpus
    val tokens = LiteraryGreekOrthography.tokenizeNode(corpus.nodes(1))
  }


}
