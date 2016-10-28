
package io.github.neelsmith.greek


import org.specs2.mutable.Specification


class TestGreekStringN extends Specification {

  "Specification for normalized Greek string" >> {
    /*"which should offer a method for creating objects from ascii" >> {
        GreekStringN.fromGreek("mh=nin").unicodeView == "μῆνιν"
    }*/
    "which should provide readable display in Unicode" >> {
        GreekStringN("μῆνιν").unicodeView == "μῆνιν"
    }

    "which should fail on invalid characters" >> {

      GreekStringN("123") must throwA[java.lang.IllegalArgumentException]

    }

  }



}
