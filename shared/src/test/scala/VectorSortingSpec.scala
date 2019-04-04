package edu.holycross.shot.greek
import org.scalatest.FlatSpec


import java.text.Normalizer._


class VectorSortingSpec extends FlatSpec {


  "A Vector of Literary Greek Strings" should "sort alphabetically" in {
    // strings carefully crafted in Unicode form NFC, the only
    // form that works in JavaScript.
    val nfcWords = Vector("περὶ", "πολλοῦ", "ἂν", "ποιησαίμην", ",", "ὦ", "ἄνδρες")
    val gls = nfcWords.map(wd => LiteraryGreekString(wd))
    val sortedWords = gls.sortWith(_ < _)

    val expected = Vector(LiteraryGreekString("ἂν"), LiteraryGreekString("ἄνδρες"), LiteraryGreekString("περὶ"), LiteraryGreekString("ποιησαίμην"), LiteraryGreekString("πολλοῦ"), LiteraryGreekString("ὦ"), LiteraryGreekString(","))
    assert(sortedWords == expected)
    /*
    for (w <- words)  {
        println("DIRECT " + w + ". Valid? " + LiteraryGreekString(w).valid + " literaryUcode " + literaryUcodeOf(w) + " literaryAscii " + literaryAsciiOf(w))
      }*/

  }

}
