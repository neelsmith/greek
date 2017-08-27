package edu.holycross.shot
import java.text.Normalizer


/** Package for representing text in Ancient Greek, since fundamental
* assumption of Unicode that script code points belong to a language is wrong.
*
*  ==Overview==
* Classes implementing the [[GreekString]] trait define an encoding system
* for writing some form of ancient Greek with an ordered set of
* characters drawn from the ASCII character set whenever possible.
* The [[CodePointTranscoder]] object provides a mapping of ASCII character
* sequence to glyphs taken primarily from the so-called "Greek and Coptic"
* and "Extended Greek" blocks of Unicode.  Using the concrete implementation of
* the [[GreekString]] trait's `asciiCompare` function, any class extending
* the [[GreekString]] trait can trivially extend Scala's `Ordered` trait as well.
*/
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
      /*
      if (s.head.toInt == 787){
        val asciified = CodePointTranscoder.asciiCodePoint(s.tail.head.toString) + ")" + literaryAsciiOf(s.tail.tail)
        val asciiLeader = CodePointTranscoder.asciiCodePoint(s.tail.head.toString)
        val leader = CodePointTranscoder.ucodeCodePoint(asciiLeader + ")")
        Normalizer.normalize(leader + s.tail.tail, Normalizer.Form.NFC)

      } else if (s.tail.head == 787 ) {
        //smoothUpperCase(s.tail.tail)
        Normalizer.normalize(s, Normalizer.Form.NFC)
      } else {*/
        Normalizer.normalize(s, Normalizer.Form.NFC)
      //}


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
