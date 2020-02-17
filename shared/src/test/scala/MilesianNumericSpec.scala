package edu.holycross.shot.greek
import org.scalatest.FlatSpec


class MilesianNumericSpec extends FlatSpec {

  "The MilesianNumeric object"  should "define special characters for integers and fractional values" in {
    val expectedTick = "ʹ"
    assert(MilesianNumeric.numericTick == expectedTick)
    val expectedSeconds = "\""
    assert(MilesianNumeric.seconds == expectedSeconds)
  }

  "A MilesianNumeric" should "recognize a simple integer string" in {
    val simpleInt = MilesianNumeric("a")
    assert(simpleInt.intString == "a")
    assert(simpleInt.fractString.isEmpty)
  }
  it should "recognize a simple integer including numeric tick" in {
    val tickedInt = MilesianNumeric("a'")
    assert(tickedInt.intString == "a")
    assert(tickedInt.fractString.isEmpty)
  }
  it should "accept unicode tick point as tick mark" in {
    val tickedInt = MilesianNumeric(s"a${MilesianNumeric.numericTick}")
    assert(tickedInt.intString == "a")
    assert(tickedInt.fractString.isEmpty)
  }

  it should "correctly split integer and fractional components" in {
    val tickedInt = MilesianNumeric("a' γ")
    assert(tickedInt.intString == "a")
    assert(tickedInt.fractString == "γ")
  }

  it should "correctly split integer and fractional components including optional seconds marker" in {
    val tickedInt = MilesianNumeric("a' γ\"")
    assert(tickedInt.intString == "a")
    assert(tickedInt.fractString == "γ")
  }

  it should "correctly split integer and fractional components using unicode tick mark" in {
    val tickedInt = MilesianNumeric(s"a${MilesianNumeric.numericTick} γ")
    assert(tickedInt.intString == "a")
    assert(tickedInt.fractString == "γ")
  }

  it should "recognize a simple fractional value" in {
    val third = MilesianNumeric("γ\"")
    assert (third.intString.isEmpty)
    assert (third.fractString == "γ")
  }

  it should "produce an ascii encoding of an ASCII Milesian string for an integer" in{
    val one = MilesianNumeric("aʹ")
    val expected = "aʹ"
    assert(one.ascii == expected)
  }

  it  should "produce a unicode encoding of ASCII Milesian string for an integer" in {
    val one = MilesianNumeric("a'")
    val expected = s"α${MilesianNumeric.numericTick}"
    assert(one.ucode == expected)
  }

  it should "produce an integer value for a numeric string" in pending
  it should "produce a double value for a numeric string" in pending

}
