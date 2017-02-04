package edu.holycross.shot.greek
import org.scalatest.FlatSpec


import java.text.Normalizer

class CodePointTranscodingSpec extends FlatSpec {




  "A code point transcoder object"  should "map individual ascii characters to unicode code points" in  {
    val asciiList = Vector("a","b","g","d","e")
    val ucodeList = Vector("α","β","γ","δ","ε")

    var count = 0
    for (a <- asciiList) {
      assert(ucodeList(count ) == ucodeCodePoint(a))
      count = count + 1
    }
  }

  it should "map individual code points in the Greek range to ascii strings" in {
    val asciiList = Vector("a","b","g","d","e")
    val ucodeList = Vector("α","β","γ","δ","ε")
    val normalUcode = ucodeList.map(s => Normalizer.normalize(s, Normalizer.Form.NFC))
    var count = 0
    for (u <- normalUcode) {
      assert(asciiList(count ) == asciiCodePoint(u))
      count = count + 1
    }

  }



}
