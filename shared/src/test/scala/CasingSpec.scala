package edu.holycross.shot.greek
import org.scalatest.FlatSpec




class CasingSpec extends FlatSpec {


  "A Literary Greek String" should "create an upper-case string" in {
      val s = LiteraryGreekString("*a")
      assert (s.ucode == "Α")
  }

  it should "upper case a string" in {
    val s = LiteraryGreekString("ιλιας")
    assert (s.toUpper.ucode == "ΙΛΙΑΣ")
    assert (s.toUpper.ascii == "*i*l*i*a*s")
  }

/*
  it should "lower case a string" in {
    val s = LiteraryGreekString("ΙΛΙΑΣ")
    assert (s.toLower.ucode == "ιλιας")
    assert (s.toLower.ascii == "ilias")
  }

  it should "capitalize a string" in {
    val s = LiteraryGreekString("i)/lias")
    assert (s.capitalize.ascii == "*i)/lias")
    assert(s.capitalize.ucode == "Ἴλιας")
  }

  it should "camel-case a string" in {
    val s = LiteraryGreekString("peri\\ paronu/mwn")
    assert (s.camelCase.ascii == "*peri\\ *paronu/mwn")
    assert(s.camelCase.ucode == "Περὶ Παρονύμων")
  }
  */
}
