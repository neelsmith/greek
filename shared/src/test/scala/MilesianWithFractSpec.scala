package edu.holycross.shot.greek
import org.scalatest.FlatSpec


class MilesianWithFractSpec extends FlatSpec {
// MilesianNumeric(ιβ' 𐅵 γ")

  "The MilesianNumeric object"  should "split strings into integer and fractional components" in {
    val s = "ιβ' 𐅵 γ\""
    val fract = MilesianWithFraction(s)
    val expected = ("ιβ", "β  γ")
    assert(fract.stringParts == expected)
  }

  it should "expand strings for short-hand characters" in {
    val half = MilesianWithFraction(MilesianNumeric.halfString + "\"")
    assert(half.expandedFractions == "β \"")
  }

  it should "trim white space in splitting into parts" in {
    val half = MilesianWithFraction(MilesianNumeric.halfString + "\"")
    assert(half.asciiPartial == "b\"")
    assert(half.ucodePartial == "β\"")

    val twoThirds = MilesianWithFraction(MilesianNumeric.twoThirdsString + "\"")
    assert(twoThirds.asciiPartial == "b ϛ\"")
    assert(twoThirds.ucodePartial == "β ϛ\"")
  }


  it should "recognize symbols for half and third" in  {
    val half = MilesianWithFraction(MilesianNumeric.halfString + "\"")
    val twoThirds = MilesianWithFraction(MilesianNumeric.twoThirdsString + "\"")
    println( Vector(half.ascii, half.ucode, half.toDouble).mkString(" == ") )
    println (Vector(twoThirds.ascii, twoThirds.ucode, twoThirds.toDouble).mkString(" == ") )

    assert(twoThirds.ucode == "β ϛ\"")
    assert(half.ucode == "β\"")
    assert(half.ascii == "b\"")
    assert(twoThirds.ascii == "b ϛ\"")

  }

/*
  it should "convert fracts to doubles" in {
    val half = MilesianNumeric("b d\"")
    println(half + " == " + half.toDouble)
  }

  it should "accumulate unit fractions" in {
    val threeQuarters = MilesianNumeric("b d\"")
    assert(threeQuarters.toDouble == 0.75)
  }

  it should "accumulate unit fractions for two thirds" in {
    val twoThirds = MilesianNumeric("b " + MilesianNumeric.stigma + "\"")
    //assert(twoThirds.toDouble == 0.75)
    println(twoThirds.ucode + " = " + twoThirds.toDouble)
  }

  it should "correctly combine int and fract parts" in {
    val hemiolon = MilesianNumeric("q' b d\"")
    assert(hemiolon.toDouble == 9.75)
  }

  it should "handle accepted unicode characters from BMP" in {
    val twelvePoint8 = MilesianNumeric("ιβ' 𐅵 γ\"")
    val expectedUcode = "ιβʹβ  γ\""

    println(twelvePoint8.expandedFractions)
    assert(twelvePoint8.ucode == expectedUcode)
  }
*/
}
