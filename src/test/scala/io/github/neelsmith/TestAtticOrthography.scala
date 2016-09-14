
package io.github.neelsmith.greek

// Modelled on examples in this blog post:
//http://blog.scalac.io/2015/03/27/specs2-notes.html
//
// I've still got tons to learn about specs2.
//
import org.specs2.mutable.Specification


class TestAtticOrthography extends Specification {

  "Specification for Attic Greek orthography" >> {
    "which should recognize a consonant" >> {
        AtticOrthography.isConsonant('B') == true
    }
    "which should distinguish a non-consonant" >> {
        AtticOrthography.isConsonant('A') == false
    }
    "which should recognize a vowel" >> {
        AtticOrthography.isVowel('A') == true
    }
    "which should distinguish a non-vowel" >> {
        AtticOrthography.isVowel('B') == false
    }

    "which should recognize vowels as alphabetic characters" >> {
        AtticOrthography.isAlphabetic('A') == true
    }
    "which should recognize consonants as alphabetic characters" >> {
        AtticOrthography.isAlphabetic('B') == true
    }
    "which should distinguish non-alphabetic characters" >> {
        AtticOrthography.isAlphabetic('=') == false
    }

    "which should recognize accents" >> {
        AtticOrthography.isAccent('=') == true
    }
    "which should distinguish non-accents" >> {
        AtticOrthography.isAccent('A') == false
    }

    "which should recognize quantity marks" >> {
        AtticOrthography.isQuantity('^') == true
    }
    "which should distinguish non-quantity marks" >> {
        AtticOrthography.isQuantity('A') == false
    }

    "which should recognize the elision mark" >> {
        AtticOrthography.isElision('\'') == true
    }
    "which should distinguish from other characters" >> {
        AtticOrthography.isElision('A') == false
    }

    "which should recognize valid characters in this orthography" >> {
        AtticOrthography.isValidChar('A') == true
    }
    "which should distinguish from invalid characters" >> {
        AtticOrthography.isValidChar('C') == false
    }

  }




  // etc etc etc  Many units to implement...

}
