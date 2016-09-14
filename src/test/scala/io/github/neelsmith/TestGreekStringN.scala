
package io.github.neelsmith.greek


import org.specs2.mutable.Specification


class TestGreekStringN extends Specification {

  "Specification for normalized Greek string" >> {
    "which should provide readable display in Unicode" >> {
        GreekStringN("μῆνιν").unicodeView == "μῆνιν"
    }

  }



}
