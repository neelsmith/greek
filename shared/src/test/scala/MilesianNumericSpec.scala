package edu.holycross.shot.greek
import org.scalatest.FlatSpec


class MilesianNumericSpec extends FlatSpec {

  "The MilesianNumeric object"  should "define special characters for integers and fractional values" in pending /*{
    val expectedTick = "ʹ"
    assert(MilesianNumeric.numericTick == expectedTick)
    val expectedSeconds = "\""
    assert(MilesianNumeric.seconds == expectedSeconds)
  }

  it should "define a character for myriad" in {
    val upperMu = 'Μ'
    assert(MilesianNumeric.myriadCP == upperMu)
  }

  it should "define a MAX_INT value greater than 0" in {
    assert(MilesianNumeric.MAX_INT > 0)
  }

  it should "offer member values for hard-to-type Unicode characters" in {
    val six = MilesianWithFraction(MilesianNumeric.stigmaString)
    //assert(six.toInt.get == 6)
    //qoppa
    //sampi
    //MilesianNumeric.cp
  }



  it should " construct values via sub classes" in {

    val route1 = MilesianNumeric.toInt(MilesianNumeric.stigmaString)
    assert(route1 == 6)

    val route2 = MilesianWithFraction(MilesianNumeric.stigmaString).toInt
    assert(route2 == 6)
  }

  "A MilesianNumeric" should "recognize a simple integer string" in {
    val simpleInt = MilesianWithFraction("a")
    assert(simpleInt.intString == "a")
    assert(simpleInt.partialString.isEmpty)
  }
  it should "recognize a simple integer including numeric tick" in {
    val tickedInt = MilesianWithFraction("a'")
    assert(tickedInt.intString == "a")
    assert(tickedInt.partialString.isEmpty)
  }
  it should "accept unicode tick point as tick mark" in {
    val tickedInt = MilesianWithFraction(s"a${MilesianNumeric.numericTick}")
    assert(tickedInt.intString == "a")
    assert(tickedInt.partialString.isEmpty)
  }

  it should "correctly split integer and fractional components" in {
    val tickedInt = MilesianWithFraction("a' γ")
    assert(tickedInt.intString == "a")
    assert(tickedInt.partialString == "γ")
  }

  it should "correctly split integer and fractional components including optional seconds marker" in {
    val tickedInt = MilesianWithFraction("a' γ\"")
    assert(tickedInt.intString == "a")
    assert(tickedInt.partialString == "γ")
  }

  it should "correctly split integer and fractional components using unicode tick mark" in {
    val tickedInt = MilesianWithFraction(s"a${MilesianNumeric.numericTick} γ")
    assert(tickedInt.intString == "a")
    assert(tickedInt.partialString == "γ")
  }

  it should "recognize a simple fractional value" in {
    val third = MilesianWithFraction("γ\"")
    assert (third.intString.isEmpty)
    assert (third.partialString == "γ")
  }
*/
  it should "produce an ascii encoding of an ASCII Milesian string for an integer" in{
    val one = MilesianWithFraction("aʹ")
    val expected = "aʹ"
    assert(one.ascii == expected)
  }
/*
  it  should "produce a unicode encoding of ASCII Milesian string for an integer" in {
    val one = MilesianWithFraction("a'")
    val expected = s"α${MilesianNumeric.numericTick}"
    assert(one.ucode == expected)
  }

  it should "produce an integer value for a single-digit numeric string" in {
    val one = MilesianWithFraction("a'")
    assert (one.toInt == 1)
  }
  it should "produce an integer value for a two-digit numeric string" in {
    val one = MilesianWithFraction("ia'")
    assert (one.toInt == 11)
  }
  it should "produce an integer value for a three-digit numeric string" in {
    val one = MilesianWithFraction("ra'")
    assert (one.toInt == 101)
  }

  it should "ignore fractional components in convertint to an integer value" in {
    val oneAndAThird = MilesianWithFraction("a' g")
    assert (oneAndAThird.toInt == 1)
  }

*/

}
