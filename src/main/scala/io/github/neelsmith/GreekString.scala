package io.github.neelsmith

package greek {



  abstract class GreekString {
    val orthography: GreekOrthography
    val greek: String
    def unicodeView: String
  }

}
