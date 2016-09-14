package io.github.neelsmith

package greek {


  trait GreekOrthography {
    val consonants: Vector[Char]
    def isConsonant(c: Char) = consonants.contains(c)

    val vowels: Vector[Char]
    def isVowel(c: Char) = vowels.contains(c)

    def isAlphabetic(c: Char) = isConsonant(c) || isVowel(c)

    val accents = Vector('/','\\','=')
    def isAccent(c: Char) = accents.contains(c)

    val quantities = Vector('_', '^')
    def isQuantity(c: Char) = quantities.contains(c)

    val elision = '\''
    def isElision(c: Char) = c == elision

    def isValidChar(c: Char) = isAlphabetic(c) || isAccent(c) || isQuantity(c) || isElision(c)
  }

}
