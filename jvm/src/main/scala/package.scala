package edu.holycross.shot
import java.text.Normalizer

package object greek {

  def literaryAsciiOf (s: String): String = {
    // if (Character.UnicodeBlock.of(s.head) == Character.UnicodeBlock.BASIC_LATIN) {
    if (s.head.toInt > 127) {
      "TRANSCODE " + s
    } else {
      s
    }
  }

  def literaryUcodeOf(s: String) : String = {
    "UCODE OF " + s
  }

  def atticAsciiOf(s: String) : String = {
    "ATTIC ASCII OF  " + s
  }
  def atticUcodeOf(s: String) : String = {
    "ATTIC Ucode OF  " + s
  }

}
