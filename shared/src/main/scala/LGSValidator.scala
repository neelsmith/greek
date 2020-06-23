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
  //Logger.setDefaultLogLevel(LogLevel.DEBUG)
  // useful short hands
  lazy val corpus = lib.textRepository.get.corpus
  lazy val dsev = DseVector.fromCiteLibrary(lib)

  // 4 methods required by CiteValidator.
  //
  // 1. required by CiteValidator trait
  /** Label for validation.*/
  def label: String = "Validate orthography of LiteraryGreekStrings"

  // 2. required by CiteValidator trait
  def validate(library: CiteLibrary) : Vector[TestResult[LiteraryGreekString]] = {
    val analyzedNodes = for (n <- library.textRepository.get.corpus.nodes) yield {
      validate(n)
    }
    analyzedNodes.flatten
  }

  // 3. required by CiteValidator trait
  def validate(surface: Cite2Urn) : Vector[TestResult[LiteraryGreekString]] =  {

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

  // 4. required by CiteValidator trait
  def verify(surface: Cite2Urn) : String = {
    println("verify surface " + surface)
    val hdr = s"# Verification of `LiteraryGreekString` orthography\n\nSurface: ${surface}\n\n"

    val txtUrns = dsev.textsForTbs(surface)
    val grouped = txtUrns.groupBy(u => u.dropPassage)

    val msgs = for (group <- grouped.keySet) yield {
      val sectionHeader = s"**${group}**\n\n"

      val urns = grouped(group)
      val sorted = corpus.sortPassages(urns)
      val formatted = sorted.map { u =>

        val matched = corpus ~~ u

        val formattedNode = matched.nodes.size match {
          case 1 => {
            val cn = matched.nodes.head
            val lgs = LiteraryGreekString(cn.text)
            s"${cn.urn}\n\n${lgs.ucode}\n\n${lgs.xlit}\n\n"
          }
          case 0 => ""
          case _ => {
            println("too many matches?")
            val cn = matched.nodes.head
            val lgs = LiteraryGreekString(cn.text)
            s"${cn.urn}\n\n${lgs.ucode}\n\n${lgs.xlit}\n\n"
          }
        }
        formattedNode + "\n\n---\n\n"
        //u.toString + " " + matched.nodes.head + "\n\n---\n\n"
      }
      //sectionHeader + formatted + "---\n\n"
      sectionHeader + formatted.mkString("\n")
    }



    hdr + msgs.mkString("\n")
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
    //println("DSE MATCHED " + surfaceDse.size + " passages")
    debug("DSE MATCHED " + surfaceDse.size + " passages")

    val allTokens = for (dsePsg <- surfaceDse) yield {
        //println("DSE: " + dsePsg.passage.dropSubref)
       tokensForUrn(dsePsg.passage.dropSubref)
    }
    //println("ALL TKENS: \n" + allTokens.flatten.mkString("\n") )
    allTokens.flatten
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
