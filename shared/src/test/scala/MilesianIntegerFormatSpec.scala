package edu.holycross.shot.greek
import org.scalatest.FlatSpec


class MilesianIntegerFormatSpec extends FlatSpec {


  "A MilesianNumeric" should "recognize a simple integer string" in {
    val simpleInt = MilesianWithFraction("a")
    assert(simpleInt.intString == "a")
    assert(simpleInt.partialString.isEmpty)
  }

  it  should "produce a unicode encoding of ASCII Milesian string for an integer" in {
    val one = MilesianWithFraction("a'")
    val expected = s"α${MilesianNumeric.numericTick}"
    assert(one.ucode == expected)
  }

}
