package edu.holycross.shot.greek
import org.scalatest.FlatSpec




class AccentSpec extends FlatSpec {


  "A Literary Greek String" should "identify the first accent in a string" in pending /*{
    val s = LiteraryGreekString("mh=nin")
    s.accent match {
      case c: Some[Char] => assert (c.get == '=')
      case None => fail("Should have found an accent")
    }
  } */

  it should "be able to strip accents from a word" in {
    val s = LiteraryGreekString("mh=nin")
    assert (s.stripAccent.ascii == "mhnin")
  }

}
