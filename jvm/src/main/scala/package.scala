package edu.holycross.shot
import java.text.Normalizer

package object greek {

  def literaryAsciiOf (s: String): String = {
    "ASCII OF " + s
  }

  def literaryUcodeOf(s: String) : String = {
    "UCODE OF " + s
  }
}
