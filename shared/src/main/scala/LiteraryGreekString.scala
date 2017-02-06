package edu.holycross.shot.greek

import scala.scalajs.js
import js.annotation.JSExport


@JSExport  case class LiteraryGreekString(str: String) extends GreekString {
  val ascii = literaryAsciiOf(str)
  val ucode = literaryUcodeOf(str)
}


object LiteraryGreekString {
  val vowels = Vector('a','e','h','i','o','u','w')
  val consonants = Vector('b','g','d','z','q','k','l','m','n','c','p',
    'r','s','t','f','x','y')
  val breathings = Vector(')', '(')
  val accents = Vector('=', '/', '\\')
  val comboChars = Vector('|','+')

  def isVowel (c: Character): Boolean = {vowels.contains(c)}
  def isConsonant (c: Character): Boolean = {consonants.contains(c)}
  def isAlpha(c: Character): Boolean = (isVowel(c) || isConsonant(c))
  def isAccent(c: Character): Boolean = {accents.contains(c)}
  def isBreathing(c: Character): Boolean = {breathings.contains(c)}
  def isCombining(c: Character): Boolean = {
    (comboChars.contains(c) || isAccent(c) || isBreathing(c))

  }


  def classOfChar(c: Character): String = {
    if (vowels.contains(c)) {
      "vowel"
    } else if (consonants.contains(c)) {
      "consonant"
    } else if (breathings.contains(c)) {
      "breathing"
    } else if (accents.contains(c)) {
      "accent"
    } else if (comboChars.contains(c)) {
      "combining"
    } else {
      "invalid"
    }
  }


  // extract run of ascii chars to put in
  // a single combined point
  def peekAhead(s: String, accumulator: String): String = {
    if (s.size < 2) {
      accumulator + s
    } else {
      if (isCombining(s(1))) {
        peekAhead(s.drop(1), accumulator + s.head)
      } else {
        accumulator + s.head.toString
      }
    }
  }
  def asciiToUcode(ascii: String, ucode: String): String = {
    if (ascii.size == 0 ) {
      ucode

    } else if (ascii.size == 1) {
      ucode + CodePointTranscoder.ucodeCodePoint(ascii)

    } else {
      val chunk = peekAhead(ascii,"")
      val newUcode = ucode + CodePointTranscoder.ucodeCodePoint(chunk)
      val newAscii = ascii.drop(chunk.size)
      asciiToUcode(newAscii, newUcode)
    }
  }
  //ucode must be already normalized to NFC
  def nfcToAscii(ucode: String, ascii: String): String = {
    if (ucode.size == 0 ) {
      ascii

    } else if (ucode.size == 1) {
      ascii +  CodePointTranscoder.asciiCodePoint(ucode)

    } else {
      val newUcode = ucode.drop(1)
      val newAscii = ascii + CodePointTranscoder.asciiCodePoint(ucode.head.toString)
      nfcToAscii(newUcode,newAscii )
    }
  }

}
