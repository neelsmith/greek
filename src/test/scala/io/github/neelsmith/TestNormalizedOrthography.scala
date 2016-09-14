
package io.github.neelsmith.greek

// Modelled on examples in this blog post:
//http://blog.scalac.io/2015/03/27/specs2-notes.html
//
// I've still got tons to learn about specs2.
//
import org.specs2.mutable.Specification


class TestNormalizedOrthography extends Specification {

  "Specification for normalized Greek orthography" >> {
    "which should recognize a consonant" >> {
        NormalizedOrthography.isConsonant('b') == true
    }
    "which should distinguish a non-consonant" >> {
        NormalizedOrthography.isConsonant('a') == false
    }
    "which should recognize a vowel" >> {
        NormalizedOrthography.isVowel('a') == true
    }
    "which should distinguish a non-vowel" >> {
        NormalizedOrthography.isVowel('b') == false
    }

    "which should recognize vowels as alphabetic characters" >> {
        NormalizedOrthography.isAlphabetic('a') == true
    }
    "which should recognize consonants as alphabetic characters" >> {
        NormalizedOrthography.isAlphabetic('b') == true
    }
    "which should distinguish non-alphabetic characters" >> {
        NormalizedOrthography.isAlphabetic('=') == false
    }

    "which should recognize accents" >> {
        NormalizedOrthography.isAccent('=') == true
    }
    "which should distinguish non-accents" >> {
        NormalizedOrthography.isAccent('a') == false
    }

    "which should recognize quantity marks" >> {
        NormalizedOrthography.isQuantity('^') == true
    }
    "which should distinguish non-quantity marks" >> {
        NormalizedOrthography.isQuantity('a') == false
    }

    "which should recognize the elision mark" >> {
        NormalizedOrthography.isElision('\'') == true
    }
    "which should distinguish from other characters" >> {
        NormalizedOrthography.isElision('a') == false
    }

    "which should recognize valid characters in this orthography" >> {
        NormalizedOrthography.isValidChar('a') == true
    }
    "which should distinguish from invalid characters" >> {
        NormalizedOrthography.isValidChar('A') == false
    }

    "which should recognize punctuation characters" >> {
        NormalizedOrthography.isPunctuation(';') == true
    }
    "which should distinguish from non-punctuation characters" >> {
        NormalizedOrthography.isPunctuation('A') == false
    }

  }




  // etc etc etc  Many units to implement...

}
