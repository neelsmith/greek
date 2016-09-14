package io.github.neelsmith

package greek {

//   u = Normalizer.normalize(u, Form.NFC)
  case class GreekStringN(s: String) extends GreekString {
    val orthography = NormalizedOrthography

    // LC; require all chars valid:
    val greekString = u2ascii.getString(s)

    def unicodeView = {
      ascii2u.getString(greekString)
    }
  }

}
