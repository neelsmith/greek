package edu.holycross.shot.greek

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import org.scalatest._
//import scala.scalajs.js
import scala.scalajs.js.UnicodeNormalizationForm._
//import js.JSStringOps._


class JSTokenizationSpec extends FlatSpec {

  val urn = CtsUrn("urn:cts:greekLit:tlg0540.tlg001.omar:1.1")
  val txt = "περὶ πολλοῦ ἂν ποιησαίμην, ὦ ἄνδρες, "
  val cn = CitableNode(urn, txt)

  "A LiteraryGreekString"  should "tokenize a node"  in {
    println(txt)
    val lgs =  LiteraryGreekString(txt)
    println(lgs.ucode)
    println(lgs.ascii)

    val s1 = LiteraryGreekString("μῆνιν")
    val s2 = LiteraryGreekString("mh=nin")
    println(s1.ascii)
  }

  /*
  val urn = n.urn
  // initial chunking on white space
  val lgs = LiteraryGreekString(n.text)
  val units = lgs.ascii.split(" ").filter(_.nonEmpty)

  val classified = for (unit <- units.zipWithIndex) yield {
    val newPassage = urn.passageComponent + "." + unit._2
    val newVersion = urn.addVersion(urn.versionOption.getOrElse("") + LiteraryGreekString.exemplarId)
    val newUrn = CtsUrn(newVersion.dropPassage.toString + newPassage)

    val trimmed = unit._1.trim
    // Catch leading quotation?
    val tokensClassified: Vector[MidToken] = if (trimmed(0) == '"') {
        Vector(MidToken(newUrn, "\"", Some(PunctuationToken)))

    } else {
      val depunctuated = depunctuate(unit._1)
      val first =  MidToken(newUrn, depunctuated.head, lexicalCategory(depunctuated.head))

      val trailingPunct = for (punct <- depunctuated.tail zipWithIndex) yield {
        MidToken(CtsUrn(newUrn + "_" + punct._2), punct._1, Some(PunctuationToken))
      }
      first +: trailingPunct
    }
    tokensClassified
  }
  classified.toVector.flatten
  */
}
