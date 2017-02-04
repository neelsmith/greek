package edu.holycross.shot
import java.text.Normalizer

package object greek {

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
      "ucode normalize " + s
    }
  }
}
