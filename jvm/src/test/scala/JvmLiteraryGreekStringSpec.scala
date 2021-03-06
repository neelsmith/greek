package edu.holycross.shot.greek
import org.scalatest.FlatSpec


import java.text.Normalizer

class JvmLiteraryGreekStringSpec extends FlatSpec {




  "The JVM package object"  should "normalize the LiteraryGreekString's `ucode` view an ASCII string" in  {
    val s = "mh=nin"
    val expected = Normalizer.normalize("μῆνιν", Normalizer.Form.NFC)
    assert(ucodeForString(s, LiteraryGreekOrthography.cpList, LiteraryGreekOrthography.combining) == expected)
  }

  it should "normalize the LiteraryGreekString's `ucode` view of strings not in form NFC" in {
    val notNfc = Normalizer.normalize("μῆνιν", Normalizer.Form.NFD)
    // Accent is a separate character:
    assert (notNfc.size == 6)
    // Accent is combined with eta:
    val expected = Normalizer.normalize("μῆνιν", Normalizer.Form.NFC)
    assert (expected.size == 5)

    val actual = ucodeOf(notNfc, LiteraryGreekOrthography.cpList, LiteraryGreekOrthography.combining)
    println(actual)
    assert(actual == expected)
  }


  it should "create the LiteraryGreekString's `ascii` view of an ASCII string"in {
    val s = "mh=nin"
    assert (asciiOf(s) == s)
  }

  it should "create the LiteraryGreekString's `ascii` view of NFC Unicode" in {
    val s = Normalizer.normalize("μῆνιν", Normalizer.Form.NFC)
    assert(s.size == 5)
    val expected = "mh=nin"
    val actual = asciiOf(s)
    println("actual " + actual)
    //assert(asciiOf(s, LiteraryGreekOrthography.cpList) == expected)
  }

  it should "create the LiteraryGreekString's `ascii` view of non-NFC Unicode" in {
    val s = Normalizer.normalize("μῆνιν", Normalizer.Form.NFD)
    assert (s.size == 6)
    val expected = "mh=nin"
    assert(asciiOf(s) == expected)
  }



}
