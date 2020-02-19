package edu.holycross.shot.greek
import org.scalatest.FlatSpec


class MilesianNumericHardCPsSpec extends FlatSpec {

  "The MilesianNumeric object"  should "offer member values for hard-to-type Unicode characters" in {
    val six = MilesianWithFraction(MilesianNumeric.stigmaString)
    assert(six.toInt.get == 6)

    val ninety = MilesianWithFraction(MilesianNumeric.qoppaString)
    assert(ninety.toInt.get == 90)

    val ninehundred = MilesianWithFraction(MilesianNumeric.sampiString)
    assert(ninehundred.toInt.get == 900)
  }
}
