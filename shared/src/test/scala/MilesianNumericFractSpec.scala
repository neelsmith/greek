package edu.holycross.shot.greek
import org.scalatest.FlatSpec


class MilesianNumericFractSpec extends FlatSpec {
// MilesianWithFraction(ιβ' 𐅵 γ")

  "The MilesianNumeric object"  should "recognize symbols for half and third" in {
    val half = MilesianWithFraction(MilesianNumeric.halfString + "\"")
    val twoThirds = MilesianWithFraction(MilesianNumeric.twoThirdsString + "\"")
    println( Vector(half.ascii, half.ucode, half.toDouble).mkString(" == ") )
    println (Vector(twoThirds.ascii, twoThirds.ucode, twoThirds.toDouble).mkString(" == ") )
  }


  it should "convert fracts to doubles" in {
    val half = MilesianWithFraction("b d\"")
    println(half + " == " + half.toDouble)
  }

  it should "accumulate unit fractions" in {
    val threeQuarters = MilesianWithFraction("b d\"")
    assert(threeQuarters.toDouble == 0.75)
  }

  it should "accumulate unit fractions for two thirds" in {
    val twoThirds = MilesianWithFraction("b " + MilesianNumeric.stigma + "\"")
    //assert(twoThirds.toDouble == 0.75)
    println(twoThirds.ucode + " = " + twoThirds.toDouble)
  }

  it should "correctly combine int and fract parts" in {
    val hemiolon = MilesianWithFraction("q' b d\"")
    assert(hemiolon.toDouble == 9.75)
  }

  it should "handle accepted unicode characters from BMP" in {
    val twelvePoint8 = MilesianWithFraction("ιβ' 𐅵 γ\"")
    val expectedUcode = "ιβʹβ  γ\""

    println(twelvePoint8.expandedFractions)
    assert(twelvePoint8.ucode == expectedUcode)
  }

}
