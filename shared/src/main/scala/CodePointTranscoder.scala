package edu.holycross.shot.greek

import java.text.Normalizer


case class CodePointPair(ucode: String, ascii: String)

object CodePointTranscoder {
  def pairings =
    Vector(
      CodePointPair("a","α"),
      CodePointPair("b", "β"),
      CodePointPair("g", "γ"),
      CodePointPair("d", "δ"),
      CodePointPair("e", "ε"),
      CodePointPair("z", "ζ"),
      CodePointPair("h", "η")
    )


  def asciiToUcode (s: String): String = {
    val matchingPairs = pairings.filter(_.ascii == s)
    matchingPairs.size match {
      case 0 => "#"
      case 1 => matchingPairs(0).ucode
      case _ => throw GreekException("Found multiple unicode mappings for " + s)
    }
  }


  def ucodeToAscii (s: String): String = {
    val matchingPairs = pairings.filter(_.ucode == s)
    matchingPairs.size match {
      case 0 => "#"
      case 1 => matchingPairs(0).ascii
      case _ => throw GreekException("Found multiple ascii mappings for " + s)
    }
  }

}
