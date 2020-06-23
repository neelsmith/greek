package edu.holycross.shot.greek
import org.scalatest.FlatSpec


class MilesianIntegerSpec extends FlatSpec {


  "A MilesianInteger"  should "convert betwen ASCII and Unicode forms" in {
    val milesianInteger = MilesianInteger("ra'")
    assert(milesianInteger.toInt == 101)
    println(milesianInteger.ucode)
  }

  it should "recognize the value for OUDEN" in {
      val ouden = MilesianInteger(MilesianNumeric.oudenString)
      assert(ouden.ascii == MilesianNumeric.oudenString)
      assert(ouden.ucode == MilesianNumeric.oudenString)
      assert(ouden.toInt == 0)
      assert(ouden.toDouble == 0.0)
  }
}
