package io.github.neelsmith

package greek {



  abstract class GreekString {
    val orthography: GreekOrthography
    val greekString: String
    def unicodeView: String
  }

}
