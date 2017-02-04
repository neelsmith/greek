package io.github.neelsmith

package greek {
  // String-level features to implement:
  // - comparison and ordering.
  // SEE THIS STACKOVERFLOW: http://stackoverflow.com/questions/19345030/easy-idiomatic-way-to-define-ordering-for-a-simple-case-class
  // and THIS
  // http://www.artima.com/pins1ed/object-equality.html
  // - tokenization


  /** Abstract model for a GreekString */
  abstract class GreekString {
    val orthography: GreekOrthography
    val greek: String
    def unicodeView: String
  }

}
