package edu.holycross.shot.greek
import org.scalatest.FlatSpec


class OudenSpec extends FlatSpec {
// MilesianWithFraction(ιβ' 𐅵 γ")

  "A MilesianWithFraction"  should "recognize the value for OUDEN" in  {

    val ouden = MilesianWithFraction(MilesianNumeric.oudenString)
    assert(ouden.ascii == MilesianNumeric.oudenString)
    assert(ouden.ucode == MilesianNumeric.oudenString)
    assert(ouden.toInt == 0)
    assert(ouden.toDouble == 0.0)
  }

  "A MilesianInteger" should "recognize the value for OUDEN" in {
      val ouden = MilesianInteger(MilesianNumeric.oudenString)
      assert(ouden.ascii == MilesianNumeric.oudenString)
      assert(ouden.ucode == MilesianNumeric.oudenString)
      assert(ouden.toInt == 0)
      assert(ouden.toDouble == 0.0)
  }
}
