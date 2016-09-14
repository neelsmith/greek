package io.github.neelsmith

package greek {


  trait GreekOrthography {
    val consonants: Vector[Char]
    def isConsonant(c: Char) = consonants.contains(c)

    val vowels: Vector[Char]
    def isVowel(c: Char) = vowels.contains(c)

    val breathings: Vector[Char]
    def isBreathing(c: Char) = breathings.contains(c)

    def isAlphabetic(c: Char) = isConsonant(c) || isVowel(c) || isBreathing(c)

    val accents = Vector('/','\\','=')
    def isAccent(c: Char) = accents.contains(c)

    val quantities = Vector('_', '^')
    def isQuantity(c: Char) = quantities.contains(c)

    val elision = '\''
    def isElision(c: Char) = c == elision

    val punctuation: Vector[Char]
    def isPunctuation(c: Char) = punctuation.contains(c)

    val whitespace = Vector(' ','\r','\n','\t')
    def isWhitespace(c: Char) = whitespace.contains(c)

    def isValidChar(c: Char) = isAlphabetic(c) || isAccent(c) || isQuantity(c) || isElision(c) || isPunctuation(c) || isWhitespace(c)

    // String-level features to implement:
    // - comparison and ordering
    // - tokenization
    // - conversion to Unicode output,

  }

}
