package edu.holycross.shot
import java.text.Normalizer

package object greek {


    def asciiCodePoint(s: String) : String = {
      val matchingPairs = CodePointTranscoder.pairings.filter(_.ascii == s)
      matchingPairs.size match {
        case 0 => "#"
        case 1 => matchingPairs(0).ucode
        case _ => throw GreekException("Found multiple unicode mappings for " + s)
      }
    }



    def ucodeCodePoint(s: String) : String ={
      val matchingPairs = CodePointTranscoder.pairings.filter(_.ucode == s)
      matchingPairs.size match {
        case 0 => "#"
        case 1 => matchingPairs(0).ascii
        case _ => throw GreekException("Found multiple ascii mappings for " + s)
      }
    }


    def ucodeOf (s: String): String = {
      "UCODE OF " + s
    }

    def asciiOf (s: String): String = {
      "ASCII OF " + s
    }

}
