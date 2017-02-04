package io.github.neelsmith

package greek {

//   u = Normalizer.normalize(u, Form.NFC)
// taken care of by transcoder.

  /** GreekString in normalized orthography. */
  case class GreekStringN(s: String) extends GreekString {

    val orthography = NormalizedOrthography

    // Check for bogus terminal sigma
    val greek = u2ascii.getString(s.toLowerCase.trim).toLowerCase

    // require that all chars be valid:
    require(greek.filterNot(orthography.isValidChar(_)).isEmpty)

    def unicodeView = {
      ascii2u.getString(greek)
    }



    //def cf(s: String) {
      //cf(greek, s)
    //}
  }
/*
  object GreekStringN {
    def fromGreek(s: String) = {
      val ustring = ascii2u.getString(s)
      GreekStringN(ustring)
    }
  }*/

}
