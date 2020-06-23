package edu.holycross.shot.greek
import org.scalatest.FlatSpec




class ValidCharSpec extends FlatSpec {


  "A Literary Greek String" should "identify a valid string" in {
    val s = LiteraryGreekString("mh=nin")
    assert (s.valid)
  }

  it should "identify strings with invalid characters" in {
    val s = LiteraryGreekString("mh=nin2")
    assert (s.valid == false)
  }

}
