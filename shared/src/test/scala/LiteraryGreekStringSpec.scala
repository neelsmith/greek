package edu.holycross.shot.greek
import org.scalatest.FlatSpec




class LiteraryGreekStringSpec extends FlatSpec {




  "A literary Greek string" should ", when constructed from an ASCII string, preserve the ASCII representation" in {
    val wrath = LiteraryGreekString("mh=nin")
    assert(wrath.ascii == "mh=nin")
  }

  it should ", when constructed from an ASCII string, create a representation in Unicode Greek" in pending /* {
    val wrath = LiteraryGreekString("mh=nin")
    assert(wrath.ucode == "μῆνιν")
  }*/


}
