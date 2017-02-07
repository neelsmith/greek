package edu.holycross.shot
import java.text.Normalizer

package object greek {

  def literaryAsciiOf (s: String): String = {
    if (s.head.toInt > 127) {
      val normalized = Normalizer.normalize(s, Normalizer.Form.NFC)
      LiteraryGreekString.nfcToAscii(normalized,"")

    } else {
      // handle terminal sigma
      Normalizer.normalize(s, Normalizer.Form.NFC)
    }
  }

  def literaryUcodeOf(s: String) : String = {
    if (s.head.toInt > 127) {
      Normalizer.normalize(s, Normalizer.Form.NFC)
    } else {
      LiteraryGreekString.asciiToUcode(s,"")
    }
  }

  def atticAsciiOf(s: String) : String = {
    "ATTIC ASCII OF  " + s
  }
  def atticUcodeOf(s: String) : String = {
    "ATTIC Ucode OF  " + s
  }

}
