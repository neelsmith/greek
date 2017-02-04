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



}
