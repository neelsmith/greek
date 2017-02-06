package edu.holycross.shot.greek
import org.scalatest.FlatSpec




class SortingSpec extends FlatSpec {


  "A Literary Greek String" should "compare identical individual  Ascii characters as equal" in {
    val s1 = LiteraryGreekString("z")
    val s2 = LiteraryGreekString("z")
    assert(s1.asciiCompare(s1.ascii, s2.ascii) == 0)
  }

  it should "recognize Greek alphabetic ordering of individual characters" in {
    val s1 = LiteraryGreekString("a")
    val s2 = LiteraryGreekString("b")
    assert (s1.asciiCompare(s1.ascii, s2.ascii) < 0)

    val s3 = LiteraryGreekString("z")
    val s4 = LiteraryGreekString("w")
    assert (s1.asciiCompare(s3.ascii, s4.ascii) < 0)
  }

  it should "apply ascii comparison to object-level comparison" in {
    val s1 = LiteraryGreekString("z")
    val s2 = LiteraryGreekString("w")
    assert (s1 < s2)
  }

  it should "order matching substrings before continuing superstrings" in {
    val s1 = LiteraryGreekString("ei)s")
    val s2 = LiteraryGreekString("ei)spra/ttw")
    assert (s1 < s2)
  }

}
