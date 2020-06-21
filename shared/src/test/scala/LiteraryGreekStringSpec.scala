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

  it should "accept quotation mark and em dash as 'markup' characters" in {
    val s1 = LiteraryGreekString("""mh=nin — "a)/eide" —""")
    val expected = """μῆνιν — "ἄειδε" —"""
    assert(s1.ucode == expected)
  }

  it should "be able to strip off breathings" in {
    val s1 = LiteraryGreekString("e)/sti")
    val expected = "e/sti"
    assert(s1.stripBreathing.ascii == expected)
  }

  it should "be able to strip off breathings and accents in a single function" in {
    val s1 = LiteraryGreekString("e)/sti")
    val expected = "esti"
    assert(s1.stripBreathingAccent.ascii == expected)
  }

  it should "offer a transliteration for easy reading" in {
    val s1 = LiteraryGreekString("mh=nis")
    val expected = "mênis"
    assert(s1.xlit == expected)
  }

  it should "respect case in transliterating" in {
    val s1 = LiteraryGreekString("Μῆνις")
    val expected = "Mênis"
    assert(s1.xlit == expected)
  }

}
