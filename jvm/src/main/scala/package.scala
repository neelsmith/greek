package edu.holycross.shot
import java.text.Normalizer

package object greek {

  def literaryAsciiOf (s: String): String = {
    // if (Character.UnicodeBlock.of(s.head) == Character.UnicodeBlock.BASIC_LATIN) {
    if (s.head.toInt > 127) {
      "TRANSCODE " + s + " to ASCII"
    } else {
      s
    }
  }

  def literaryUcodeOf(s: String) : String = {
    if (s.head.toInt > 127) {
      s
    } else {
      "TRANSCODE " + s + " to unicode Greek"
    }
  }

  def atticAsciiOf(s: String) : String = {
    "ATTIC ASCII OF  " + s
  }
  def atticUcodeOf(s: String) : String = {
    "ATTIC Ucode OF  " + s
  }

}
