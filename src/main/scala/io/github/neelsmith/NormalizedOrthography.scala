package io.github.neelsmith

package greek {



  /**
  */
  object NormalizedOrthography extends GreekOrthography {
    val consonants = Vector('b','g','d','z','q','k','l','m','n','c','p','r','s','t','f','x','y')
    val vowels = Vector('a','e','h','i','o','u','w')
    val breathings = Vector('(', ')')
    val punctuation = Vector('.', ',', ':',';')
  }
}
