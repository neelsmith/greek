package edu.holycross.shot.greek
import org.scalatest.FlatSpec


class MilesianNumericFractSpec extends FlatSpec {

  "The MilesianNumeric object"  should "recognize symbols for half and third" in {
    val half = MilesianNumeric(MilesianNumeric.halfString + "\"")
    val twoThirds = MilesianNumeric(MilesianNumeric.twoThirdsString + "\"")
    println( Vector(half.ascii, half.ucode, half.toDouble).mkString(" == ") )
    println (Vector(twoThirds.ascii, twoThirds.ucode, twoThirds.toDouble).mkString(" == ") )
  }


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



}
