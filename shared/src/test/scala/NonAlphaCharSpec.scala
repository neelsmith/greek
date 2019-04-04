package edu.holycross.shot.greek
import org.scalatest.FlatSpec




class NonAlphaCharSpec extends FlatSpec {

//*i*l*i*a*s


  "A literary Greek string" should "recognize elision" in {
    val prep =  "meq'"
    val expected = "μεθ'"
    val lgs = LiteraryGreekString(prep)
    assert(lgs.ucode ==  expected)
  }

  it should "recognize curly quotes" in {
    val s =  "“ἵνα σύ γε” ἔφη “πειρᾷς”"
    val lgs = LiteraryGreekString(s)
    val expectedAscii = "“i(/na su/ ge” e)/fh “peira=|s”"
    assert (expectedAscii == lgs.ascii)
  }
}
