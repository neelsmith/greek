package io.github.neelsmith

package greek {

//   u = Normalizer.normalize(u, Form.NFC)
  case class GreekStringN(s: String) extends GreekString {

    val orthography = NormalizedOrthography

    // require all chars valid.
    // Check for bogus terminal sigma
    val greek = u2ascii.getString(s.toLowerCase.trim).toLowerCase

    println("Invalid chars: " + greek.filterNot(orthography.isValidChar(_)))
    require(greek.filterNot(orthography.isValidChar(_)).isEmpty)


    def unicodeView = {
      ascii2u.getString(greek)
    }
  }

  object GreekStringN {
    def fromGreek(s: String) = {
      println("Make Greek from "+ s + " with " + ascii2u.getString(s))
      GreekStringN(ascii2u.getString(s))
    }
  }

}
