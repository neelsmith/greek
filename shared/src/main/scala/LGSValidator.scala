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


  // Vectors of canonically citable passages containing a Vector of tokens
  def tokensForUrn(psg: CtsUrn) : Vector[Vector[MidToken]]= {
    val subcorpus = corpus ~~ psg
    for (n <- subcorpus.nodes) yield {
      LiteraryGreekString.tokenizeNode(n)
    }
  }


  // Vectors of canonically citable passages containing a Vector of tokens
  def tokensForSurface(surface: Cite2Urn): Vector[Vector[MidToken]] = {
    val dsev = DseVector.fromCiteLibrary(lib)
    val surfaceDse = dsev.passages.filter(_.surface == surface)
    val allTokens = for (dsePsg <- surfaceDse) yield {
       tokensForUrn(dsePsg.passage.dropSubref)
    }
    allTokens.flatten
  }

  // required by CiteValidator trait
  def verify(surface: Cite2Urn) : String = {
    val tokensList = tokensForSurface(surface).flatten
    val badTokens = tokensList.filterNot(t => LiteraryGreekString.validString(t.text))
    debug("BAD\n" + badTokens.mkString("\n"))
    debug("TOTAL TOKENS: " + tokensList.size)
    debug("BAD TOKENS: " + badTokens.size)
    val grouped = badTokens.groupBy(t => t.text)
    debug("Groups: " + grouped.size)

    // Sort later...
    //val keysSorted = grouped.keySet.toVector.map ( k => (k, LiteraryGreekString(k))).sortBy(_._2)

    val msgs  = grouped.keySet.toVector.map ( tkn => {
        val plural = grouped(tkn).size match {
          case 1 => ""
          case _ => "s"
        }
        s"- **${tkn}**:  ${grouped(tkn).size} occurrence${plural}. (" + grouped(tkn).map(t => t.urn).mkString(", ") + ")"
      }
    )

    /*val msgs = keysSorted.map ( tkn =>
      s"- **${tkn}**:  ${grouped(tkn).size} occurrences. (" + grouped(tkn).map(t => t.urn).mkString(", ") + ")"
    )*/
    val hdr = s"# Verification of `LiteraryGreekString` orthography\n\nSurface: ${surface}\n\n"
    hdr + msgs.mkString("\n")
  }


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
