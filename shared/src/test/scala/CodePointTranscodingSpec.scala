package edu.holycross.shot.greek
import org.scalatest.FlatSpec




class CodePointTranscodingSpec extends FlatSpec {




  "A code point transcoder object"  should "map individual ascii characters to unicode code points" in  {
    val asciiList = Vector("a","b","g","d","e")
    val ucodeList = Vector("α","β","γ","δ","ε")

    var count = 0
    for (a <- asciiList) {
      assert(ucodeList(count ) == CodePointTranscoder.ucodeCodePoint(a))
      count = count + 1
    }
  }

  it should "map individual code points in the Greek range to ascii strings" in {
    val asciiList = Vector("a","b","g","d","e")
    val ucodeList = Vector("α","β","γ","δ","ε")

    var count = 0
    for (u <- ucodeList) {
      assert(asciiList(count ) == CodePointTranscoder.asciiCodePoint(u))
      count = count + 1
    }

  }

  it should "accept quotation marks and em dashes as valid 'markup' characters in either encoding" in {
    val markupChars = Vector(CodePointTranscoder.emDash, CodePointTranscoder.leftCurly, CodePointTranscoder.rightCurly)


    var count = 0
    for (c <- markupChars) {
      assert(markupChars(count ) == CodePointTranscoder.asciiCodePoint(c))
      assert(markupChars(count ) == CodePointTranscoder.ucodeCodePoint(c))
      count = count + 1
    }
  }

  it should "catch upper case omega" in {
    val s = "*w)="
    val expected = "Ὦ"
    assert( CodePointTranscoder.ucodeCodePoint(s) == expected)
  }

  it should "handle combination of vowel, accent and diaeresis" in {
    val s = "i+/e"
    println("What is ? " + CodePointTranscoder.ucodeCodePoint(s))
  }


}
