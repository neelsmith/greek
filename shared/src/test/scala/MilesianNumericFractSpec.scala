package edu.holycross.shot.greek
import org.scalatest.FlatSpec


class MilesianNumericFractSpec extends FlatSpec {

  "The MilesianNumeric object"  should "recognize symbols for half and third" in pending /*{
    val half = MilesianNumeric(MilesianNumeric.halfString)
    val twoThirds = MilesianNumeric(MilesianNumeric.twoThirdsString)
    println( Vector(half.ascii, half.ucode).mkString(" == ") )
    println (Vector(twoThirds.ascii, twoThirds.ucode).mkString(" == ") )
  }
  it should "really do this" in {
    val hemiolon = MilesianNumeric("a' ½"  + "\"")
    println( Vector(hemiolon.ascii, hemiolon.ucode).mkString(" == ") )
  }*/

  it should "convert fracts to doubles" in pending/* {
    val half = MilesianNumeric("b d\"")
    println(half + " == " + half.toDouble)
  }*/

  it should "accumulate unit fractions" in {
    val threeQuarters = MilesianNumeric("b d\"")
    assert(threeQuarters.toDouble == 0.75)
  }

  it should "accumulate unit fractions for two thirds" in {
    val twoThirds = MilesianNumeric("b " + MilesianNumeric.stigma + "\"")
    //assert(twoThirds.toDouble == 0.75)
    println(twoThirds.ucode + " = " + twoThirds.toDouble)
  }

  // split on ";"



}
