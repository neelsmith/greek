package edu.holycross.shot.greek
import org.scalatest.FlatSpec




class CodePointMilesianTranscodingSpec extends FlatSpec {




  "A code point transcoder object"  should "transcode funny characters properly" in  {
    val raw = "ιβ' 𐅵 γ\""

    val mil = MilesianNumeric(raw)
  }






}
