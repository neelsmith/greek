package edu.holycross.shot.greek
import org.scalatest.FlatSpec




class CasingSpec extends FlatSpec {


  "A Literary Greek String" should "create an upper-case string" in {
      val s = LiteraryGreekString("*a")
      assert (s.ucode == "Α")
  }

  it should "lower case a string" in {
    val s = LiteraryGreekString("ιλιας")
    assert (s.toUpper.ucode == "ΙΛΙΑΣ")
    assert (s.toUpper.ascii == "*i*l*i*a*s")
  }
}
