package edu.holycross.shot.greek
import org.scalatest.FlatSpec


import java.text.Normalizer

class AtoUSpec extends FlatSpec {


  val alphabet = CodePointTranscoder.strToCps("himn=~")
  val passThrough = "~"
  val combos = Vector('=')

  "The package object's ucodeOf method"  should "use a supplied alphabet to covert ascii to unicode" in {
    val expected = "μῆνιν~"
    val actual = ucodeOf("mh=nin~", alphabet, combos, passThrough)
    assert(actual == expected)
  }

  it should "check pass-through list in converting ucode to ascii" in  {
    val s = "μῆνιν~"
    val expected = "mh=nin~"
    val actual = asciiOf(s, passThrough)
    assert(actual == expected)
  } 


}
