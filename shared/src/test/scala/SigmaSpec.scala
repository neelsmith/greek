package edu.holycross.shot.greek
import org.scalatest.FlatSpec




class SigmaSpec extends FlatSpec {

//*i*l*i*a*s


  "A literary Greek string's ascii to unicode transcoding" should "handle final sigma" in {

    assert(LiteraryGreekOrthography.asciiToUcode("*s","") == "Σ")
  }
  it should "handle that in longer words" in {
    val s = "*i*l*i*a*s"
    val expected = "ΙΛΙΑΣ"
    assert  (LiteraryGreekOrthography.asciiToUcode(s, "") == expected)
  }


  it should "correctly convert Unicode terminal sigma" in  {
    val wrath = LiteraryGreekString("μῆνις ")
    assert (wrath.ascii == "mh=nis ")

    val wrathish = LiteraryGreekString("μῆνισ ")
    assert (wrathish.ascii == "mh=nis ")
  }

  it should "map to terminal sigma when sigma followed by space" in {
    val wrath = LiteraryGreekString("mh=nis ")
    val wrath2 =  LiteraryGreekString("μῆνις ")
    assert (wrath.ascii == wrath2.ascii)
    assert (wrath.ucode == wrath2.ucode)
    assert(wrath == wrath2)
  }



}
