package edu.holycross.shot.greek
import org.scalatest.FlatSpec


class MilesianNumericObjectSpec extends FlatSpec {

  "The MilesianNumeric object"  should "define special characters for integers and fractional values" in {
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


  it should " construct values via sub classes" in {

    val route1 = MilesianNumeric.toInt(MilesianNumeric.stigmaString)
    assert(route1 == 6)

    val route2 = MilesianWithFraction(MilesianNumeric.stigmaString).toInt
    assert(route2 == 6)
  }

}
