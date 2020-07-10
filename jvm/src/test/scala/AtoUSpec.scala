package edu.holycross.shot.greek
import org.scalatest.FlatSpec


import java.text.Normalizer

class AtoUSpec extends FlatSpec {


  val alphabet = CodePointTranscoder.strToCps("abct~")
  val combos = Vector.empty[Char]

  "The package object's ucodeOf method"  should "use a supplied alphabet to covert ascii to unicode" in {
    println(ucodeOf("baba~", alphabet, combos))
  }

  it should "convert ucode to ascii" in {
    val s = "μῆνιν"
    val s2 = "μῆνιν~"
    println(asciiOf(s, alphabet))
    println(asciiOf(s2, alphabet))
  }


}
