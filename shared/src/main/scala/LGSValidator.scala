package edu.holycross.shot.greek

import edu.holycross.shot.mid.orthography._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._
import edu.holycross.shot.citevalidator._
import edu.holycross.shot.scm._
import edu.holycross.shot.dse._

import wvlet.log._
import wvlet.log.LogFormatter.SourceCodeLogFormatter


import scala.scalajs.js
import scala.scalajs.js.annotation._
import scala.annotation.tailrec

/** Representation of a Greek string written in conventional literary orthography.
*
* @param str A string in either the ascii or ucode representation of the [[LiteraryGreekString]]
* system.
*/
@JSExportAll  case class LGSValidator(lib: CiteLibrary) extends CiteValidator[LiteraryGreekString] with LogSupport  {
  lazy val corpus = lib.textRepository.get.corpus

  // 4 methods required by CiteValidator.
  //
  // required by CiteValidator trait
  /** Label for validation.*/
  def label: String = "Validate orthography of LiteraryGreekStrings"

  // required by CiteValidator trait
  def validate(library: CiteLibrary) : Vector[TestResult[LiteraryGreekString]] = {
    val analyzedNodes = for (n <- library.textRepository.get.corpus.nodes) yield {
      validate(n)
    }
    analyzedNodes.flatten
  }

  // required by CiteValidator trait
  def validate(surface: Cite2Urn) : Vector[TestResult[LiteraryGreekString]] =  {
    val dsev = DseVector.fromCiteLibrary(lib)
    val surfaceDse = dsev.passages.filter(_.surface == surface)

    val rslts = for (dsePsg <- surfaceDse) yield {
      val subcorpus = corpus ~~ dsePsg.passage.dropSubref
      val nodeResults = for (n <- subcorpus.nodes)  yield {
          validate(n)
      }
      nodeResults.flatten
    }
    rslts.flatten
  }

  // required by CiteValidator trait
  def verify(surface: Cite2Urn) : String = "# VERIFICATION RESULTS GO HERE\n"


  def validate (c: Corpus): Vector[TestResult[LiteraryGreekString]]  = {
    val rslts = for (n <- c.nodes) yield {
      validate(n)
    }
    rslts.flatten
  }

  /** Validate text contents of a CitableNode.
  *
  * @param textNode Citable node with text contents
  * expected to follow GreekLiteraryString.
  */
  def validate(textNode: CitableNode) : Vector[TestResult[LiteraryGreekString]] = {
    val tokens = LiteraryGreekString.tokenizeNode(textNode)
    tokens.isEmpty match {
      case true => {
        warn("No tokens found from " + textNode)
        Vector.empty[TestResult[LiteraryGreekString]]
      }

      case false => {
        val lgsList = tokens.map(t => (t.urn, LiteraryGreekString(t.text)))

        for( (urn, lgs) <- lgsList) yield {
          LiteraryGreekString.validString(lgs.ascii) match {
            case true => TestResult(true, s"${lgs.ucode} (${urn}) valid.", lgs)
            case false => TestResult(false, s"${lgs.ucode} invalid (${urn}):  ${LiteraryGreekString.hiliteBadCps(lgs.ascii)} ", lgs)
          }
        }
      }
    }
  }


}
