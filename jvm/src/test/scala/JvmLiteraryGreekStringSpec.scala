package edu.holycross.shot.greek
import org.scalatest.FlatSpec


import java.text.Normalizer

class JvmLiteraryGreekStringSpec extends FlatSpec {




  "The JVM package object"  should "normalize the LiteraryGreekString's `ucode` view an ASCII string" in  {
    val s = "mh=nin"
    val expected = Normalizer.normalize("μῆνιν", Normalizer.Form.NFC)
    assert(literaryUcodeOf(s) == expected)
  }

  it should "normalize the LiteraryGreekString's `ucode` view of strings not in form NFC" in {
    val notNfc = Normalizer.normalize("μῆνιν", Normalizer.Form.NFD)
    // Accent is a separate character:
    assert (notNfc.size == 6)
    // Accent is combined with eta:
    val expected = Normalizer.normalize("μῆνιν", Normalizer.Form.NFC)
    assert (expected.size == 5)
    assert(literaryUcodeOf(notNfc) == expected)
  }

  it should "create the LiteraryGreekString's `ascii` view of an ASCII string"in {
    val s = "mh=nin"
    assert (literaryAsciiOf(s) == s)
  }

  it should "create the LiteraryGreekString's `ascii` view of NFC Unicode" in {
    val s = Normalizer.normalize("μῆνιν", Normalizer.Form.NFC)
    assert(s.size == 5)
    val expected = "mh=nin"
    assert(literaryAsciiOf(s) == expected)
  }

  it should "create the LiteraryGreekString's `ascii` view of non-NFC Unicode" in {
    val s = Normalizer.normalize("μῆνιν", Normalizer.Form.NFD)
    assert (s.size == 6)
    val expected = "mh=nin"
    assert(literaryAsciiOf(s) == expected)
  }

  it should "create the AtticGreekString's `ucode` view of strings not in form NFC" in  pending

  val s = "me=nin"
  val expected = Normalizer.normalize("μêνιν", Normalizer.Form.NFC)
  assert(literaryUcodeOf(s) == expected)

}
