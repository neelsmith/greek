package edu.holycross.shot.greek
import org.scalatest.FlatSpec




class LiteraryGreekStringSpec extends FlatSpec {


  "A literary Greek string" should ", when constructed from an ASCII string, preserve the ASCII representation" in {
    val wrath = LiteraryGreekString("mh=nin")
    assert(wrath.ascii == "mh=nin")
  }

  it should ", when constructed from an ASCII string, create a representation in Unicode Greek" in   {
    val wrath = LiteraryGreekString("mh=nin")
    assert(wrath.ucode == "μῆνιν")
  }

  it should ", when constructed from a Greek Unicode string, preserve the Greek Unicode representation" in {
    val wrath = LiteraryGreekString("μῆνιν")
    assert(wrath.ucode == "μῆνιν")
  }

  it should ", when constructed from a Greek Unicode string, construct an ASCII representation" in {
    val wrath = LiteraryGreekString("μῆνιν")
    assert(wrath.ascii == "mh=nin")
  }

  it should "support meaningful equality comparison" in {
    val s1 = LiteraryGreekString("μῆνιν")
    val s2 = LiteraryGreekString("mh=nin")
    assert(s1 == s2)
  }


}
