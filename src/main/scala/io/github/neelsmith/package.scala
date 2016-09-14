package io.github.neelsmith

/** A library for working with texts in polytonic ancient Greek
* language and scripts including epichoric alphabets.
*/
package object greek {

  import edu.unc.epidoc.transcoder.TransCoder

  val u2ascii = new TransCoder()
  u2ascii.setParser("Unicode")
  u2ascii.setConverter("BetaCode")

  val ascii2u = new TransCoder()
  ascii2u.setParser("BetaCode")
  ascii2u.setConverter("UnicodeC")

}
