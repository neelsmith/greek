package edu.holycross.shot.greek


import java.text.Normalizer

case class LiteraryGreekString(str: String)  {
  val ascii = LiteraryGreekString.asciiOf(str)
  val ucode = LiteraryGreekString.ucodeOf(str)
}

object LiteraryGreekString {
  def asciiOf(s: String) = {
    if (Character.UnicodeBlock.of(s.head) == Character.UnicodeBlock.BASIC_LATIN) {
      s
    } else {
      "TRANSCODE " + s
    }
  }

  def ucodeOf(s: String) = {
    if (Character.UnicodeBlock.of(s.head) == Character.UnicodeBlock.BASIC_LATIN) {
      "UCODE OF " + s
    } else {
      "ucod normalize " + s
    }
  }

/*

  */
  val vowels = Vector('a','e','h','i','o','u','w')
  val consonants = Vector('b','g','d','z','q','k','l','m','n','c','p',
    'r','s','t','f','x','y')
  val breathings = Vector(')', '(')
  val accents = Vector('=', '/', '\\')


  def isVowel (c: Character): Boolean = {vowels.contains(c)}
  def isConsonant (c: Character): Boolean = {consonants.contains(c)}
  def isAlpha(c: Character): Boolean = (isVowel(c) || isConsonant(c))
  def classOfChar(c: Character): String = {
    if (vowels.contains(c)) {
      "vowel"
    } else if (consonants.contains(c)) {
      "consonant"
    } else if (breathings.contains(c)) {
      "breathing"
    } else if (accents.contains(c)) {
      "accent"
    } else {
      "invalid"
    }
  }

  def peekAhead(unencoded: String, encoded: String): String = {
    if (unencoded.size == 1) {
      encoded + unencoded

    } else {
      classOfChar(unencoded.head) match {
        case "consonant" => unencoded.head.toString
        case "vowel" => if (isAlpha(unencoded(1))) {
          unencoded.head.toString
        } else {
          peekAhead(unencoded.drop(1), encoded + unencoded(0))
        }
        case "breathing" => if (isAlpha(unencoded(1))) {
          encoded + unencoded.head.toString
        } else {
          peekAhead(unencoded.drop(1), encoded + unencoded(0))
        }
        case _ =>  encoded + unencoded.head.toString
      }
    }
  }

  def encode (s: String): String = {
    s match {
      case "a" => "α"
      case "b" => "β"
      case "g" => "γ"
      case "d" => "δ"
      case "e" => "ε"
      case "z" => "ζ"
      case "h" => "η"
    }
  }

}
