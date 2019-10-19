package edu.holycross.shot.greek
import org.scalatest.FlatSpec


class MilesianNumericSpec extends FlatSpec {


  "A MilesianNumeric" should "produce an ascii encoding of an ASCII Milesian string for an integer" in {
    val one = MilesianNumeric("a")
    val expected = "aʹ"
    assert(one.ascii == expected)
  }
  it  should "produce a unicode encoding of ASCII Milesian string for an integer" in {
    val one = MilesianNumeric("a")
    val expected = "αʹ"
    assert(one.ucode == expected)
  }
  it should "produce an integer value for a numeric string" in pending
  it should "produce a double value for a numeric string" in pending

}
