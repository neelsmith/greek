package edu.holycross.shot.greek

case class CodePointPair(ascii: String, ucode: String)




/** Manager for two-way mappings between ascii-based code point sequences
* and code points in the Greek and Coptic or Extended Greek blocks of Unicode.
*/
object CodePointTranscoder {



  /** Find a single Greek code point for a pair of ASCII
  * characters.
  */
  def ucodeCodePoint(asciiCodePoint: String) : String ={
    val matchingPairs = CodePointTranscoder.pairings.filter(_.ascii == asciiCodePoint)
    matchingPairs.size match {
      case 0 => "#"
      case 1 => matchingPairs(0).ucode
      case _ => throw GreekException("Found multiple ascii mappings for " + asciiCodePoint)
    }
  }
    def asciiCodePoint(ucodeCodePoint: String) : String = {
      val matchingPairs = CodePointTranscoder.pairings.filter(_.ucode == ucodeCodePoint)
      matchingPairs.size match {
        case 0 => "#"
        case 1 => matchingPairs(0).ascii
        case _ => throw GreekException("Found multiple unicode mappings for " + ucodeCodePoint)
      }
    }

  def swapPrecedingBreathings(s: String): String = {
    s.replaceAll(" ̓Ε", " " + ucodeCodePoint("*e)")).
    replaceAll(" ̔Ε", " " + ucodeCodePoint("*e("))

    // A + smmooth " ̓Α"
    // A + rough
  }

  def pairings =
    Vector(
      //CodePointPair("*e)"," ̓Ε"),
      CodePointPair(" ", " "),
      CodePointPair("\n", "\n"),
      CodePointPair("\r", "\r"),
      CodePointPair("\t", "\t"),

      CodePointPair(",", ","),
      CodePointPair(".", "."),
      CodePointPair(":", ":"),
      CodePointPair(";", ";"),
      CodePointPair("'", "'"),
      CodePointPair("—", "—"),
      CodePointPair("\"", "\""),

      //CodePointPair("","Ͱ"), //880
      //CodePointPair("","ͱ"), //881
      //CodePointPair("","Ͳ"), //882
      //CodePointPair("","ͳ"), //883
      CodePointPair("","ʹ"), //884
      CodePointPair("","͵"), //885
      //CodePointPair("","Ͷ"), //886
      //CodePointPair("","ͷ"), //887
      //CodePointPair("","͸"), //888
      //CodePointPair("","͹"), //889
      //CodePointPair("","ͺ"), //890
      //CodePointPair("","ͻ"), //891
      //CodePointPair("","ͼ"), //892
      //CodePointPair("","ͽ"), //893
      //CodePointPair("",";"), //894
      //CodePointPair("","Ϳ"), //895
      //CodePointPair("","΀"), //896
      //CodePointPair("","΁"), //897
      //CodePointPair("","΂"), //898
      //CodePointPair("","΃"), //899
      //CodePointPair("","΄"), //900
      //CodePointPair("","΅"), //901
      //CodePointPair("*a/","Ά"), //902
      //CodePointPair("","·"), //903
      //CodePointPair("*e/","Έ"), //904
      //CodePointPair("*h/","Ή"), //905
      //CodePointPair("*i/","Ί"), //906
      //CodePointPair("","΋"), //907
      //CodePointPair("*o/","Ό"), //908

      //CodePointPair("*u/","Ύ"), //910
      //CodePointPair("*w/","Ώ"), //911
      CodePointPair("i/+","ΐ"), //912 cf 8147
      CodePointPair("*a","Α"), //913
      CodePointPair("*b","Β"), //914
      CodePointPair("*g","Γ"), //915
      CodePointPair("*d","Δ"), //916
      CodePointPair("*e","Ε"), //917
      CodePointPair("*z","Ζ"), //918
      CodePointPair("*h","Η"), //919
      CodePointPair("*q","Θ"), //920
      CodePointPair("*i","Ι"), //921
      CodePointPair("*k","Κ"), //922
      CodePointPair("*l","Λ"), //923
      CodePointPair("*m","Μ"), //924
      CodePointPair("*n","Ν"), //925
      CodePointPair("*c","Ξ"), //926
      CodePointPair("*o","Ο"), //927
      CodePointPair("*p","Π"), //928
      CodePointPair("*r","Ρ"), //929
      //
      CodePointPair("*s","Σ"), //931
      CodePointPair("*t","Τ"), //932
      CodePointPair("*u","Υ"), //933
      CodePointPair("*f","Φ"), //934
      CodePointPair("*x","Χ"), //935
      CodePointPair("*y","Ψ"), //936
      CodePointPair("*w","Ω"), //937
      CodePointPair("*i+","Ϊ"), //938
      CodePointPair("*u+","Ϋ"), //939


      CodePointPair("a/","ά"), //940 cf 8049
      CodePointPair("e/","έ"), //941 cf 8051
      CodePointPair("h/","ή"), //942 cf 8053
      CodePointPair("i/","ί"), //943 cf 8055
      CodePointPair("u/+","ΰ"), //944 cf 8163
      CodePointPair("a","α"), //945
      CodePointPair("b","β"), //946
      CodePointPair("g","γ"), //947
      CodePointPair("d","δ"), //948
      CodePointPair("e","ε"), //949
      CodePointPair("z","ζ"), //950
      CodePointPair("h","η"), //951
      CodePointPair("q","θ"), //952
      CodePointPair("i","ι"), //953
      CodePointPair("k","κ"), //954
      CodePointPair("l","λ"), //955
      CodePointPair("m","μ"), //956
      CodePointPair("n","ν"), //957
      CodePointPair("c","ξ"), //958
      CodePointPair("o","ο"), //959
      CodePointPair("p","π"), //960
      CodePointPair("r","ρ"), //961
      CodePointPair("Σ","ς"), //962
      CodePointPair("s","σ"), //963
      CodePointPair("t","τ"), //964
      CodePointPair("u","υ"), //965
      CodePointPair("f","φ"), //966
      CodePointPair("x","χ"), //967
      CodePointPair("y","ψ"), //968
      CodePointPair("w","ω"), //969
      CodePointPair("i+","ϊ"), //970
      CodePointPair("u+","ϋ"), //971
      CodePointPair("o/","ό"), //972 cf 8057
      CodePointPair("u/","ύ"), //973 cf 8059
      CodePointPair("w/","ώ"), //974 cf 8061
      //CodePointPair("","Ϗ"), //975
      //CodePointPair("","ϐ"), //976
      //CodePointPair("","ϑ"), //977
      //CodePointPair("","ϒ"), //978
      //CodePointPair("","ϓ"), //979
      //CodePointPair("","ϔ"), //980
      //CodePointPair("","ϕ"), //981
      //CodePointPair("","ϖ"), //982
      //CodePointPair("","ϗ"), //983
      CodePointPair("","Ϙ"), //984
      CodePointPair("","ϙ"), //985
      CodePointPair("","Ϛ"), //986
      CodePointPair("","ϛ"), //987
      CodePointPair("","Ϝ"), //988
      CodePointPair("","ϝ"), //989
      CodePointPair("","Ϟ"), //990
      CodePointPair("","ϟ"), //991
      CodePointPair("","Ϡ"), //992
      CodePointPair("","ϡ"), //993

      //CodePointPair("","ϰ"), //1008
      //CodePointPair("","ϱ"), //1009
      //CodePointPair("","ϲ"), //1010
      //CodePointPair("","ϳ"), //1011
      //CodePointPair("","ϴ"), //1012
      //CodePointPair("","ϵ"), //1013
      //CodePointPair("","϶"), //1014
      //CodePointPair("","Ϸ"), //1015
      //CodePointPair("","ϸ"), //1016
      //CodePointPair("","Ϲ"), //1017
      CodePointPair("","Ϻ"), //1018
      CodePointPair("","ϻ"), //1019
      //CodePointPair("","ϼ"), //1020
      //CodePointPair("","Ͻ"), //1021
      //CodePointPair("","Ͼ"), //1022
      //CodePointPair("","Ͽ"), //1023

      CodePointPair("a)","ἀ"), //7936
      CodePointPair("a(","ἁ"), //7937
      CodePointPair("a)\\","ἂ"), //7938
      CodePointPair("a(\\","ἃ"), //7939
      CodePointPair("a)/","ἄ"), //7940
      CodePointPair("a(/","ἅ"), //7941
      CodePointPair("a)=","ἆ"), //7942
      CodePointPair("a(=","ἇ"), //7943

      CodePointPair("*a)","Ἀ"), //7944
      CodePointPair("*a(","Ἁ"), //7945
      CodePointPair("*a)\\","Ἂ"), //7946
      CodePointPair("*a(\\","Ἃ"), //7947
      CodePointPair("*a)/","Ἄ"), //7948
      CodePointPair("*a(/","Ἅ"), //7949
      CodePointPair("*a)=","Ἆ"), //7950
      CodePointPair("*a(=","Ἇ"), //7951

      CodePointPair("e)","ἐ"), //7952
      CodePointPair("e(","ἑ"), //7953
      CodePointPair("e)\\","ἒ"), //7954
      CodePointPair("e(\\","ἓ"), //7955
      CodePointPair("e)/","ἔ"), //7956
      CodePointPair("e(/","ἕ"), //7957


      CodePointPair("*e)","Ἐ"), //7960
      CodePointPair("*e(","Ἑ"), //7961
      CodePointPair("*e)\\","Ἒ"), //7962
      CodePointPair("*e(\\","Ἓ"), //7963
      CodePointPair("*e)/","Ἔ"), //7964
      CodePointPair("*e(/","Ἕ"), //7965


      CodePointPair("h)","ἠ"), //7968
      CodePointPair("h(","ἡ"), //7969
      CodePointPair("h)\\","ἢ"), //7970
      CodePointPair("h(\\","ἣ"), //7971
      CodePointPair("h)/","ἤ"), //7972
      CodePointPair("h(/","ἥ"), //7973
      CodePointPair("h)=","ἦ"), //7974
      CodePointPair("h(=","ἧ"), //7975

      CodePointPair("*h)","Ἠ"), //7976
      CodePointPair("*h(","Ἡ"), //7977
      CodePointPair("*h)\\","Ἢ"), //7978
      CodePointPair("*h(\\","Ἣ"), //7979
      CodePointPair("*h)/","Ἤ"), //7980
      CodePointPair("*h(/","Ἥ"), //7981
      CodePointPair("*h)=","Ἦ"), //7982
      CodePointPair("*h(=","Ἧ"), //7983

      CodePointPair("i)","ἰ"), //7984
      CodePointPair("i(","ἱ"), //7985
      CodePointPair("i)\\","ἲ"), //7986
      CodePointPair("i(\\","ἳ"), //7987
      CodePointPair("i)/","ἴ"), //7988
      CodePointPair("i(/","ἵ"), //7989
      CodePointPair("i)=","ἶ"), //7990
      CodePointPair("i(=","ἷ"), //7991

      CodePointPair("*i)","Ἰ"), //7992
      CodePointPair("*i(","Ἱ"), //7993
      CodePointPair("*i)\\","Ἲ"), //7994
      CodePointPair("*i(\\","Ἳ"), //7995
      CodePointPair("*i)/","Ἴ"), //7996
      CodePointPair("*i(/","Ἵ"), //7997
      CodePointPair("*i)=","Ἶ"), //7998
      CodePointPair("*i(=","Ἷ"), //7999

      CodePointPair("o)","ὀ"), //8000
      CodePointPair("o(","ὁ"), //8001
      CodePointPair("o)\\","ὂ"), //8002
      CodePointPair("o(\\","ὃ"), //8003
      CodePointPair("o)/","ὄ"), //8004
      CodePointPair("o(/","ὅ"), //8005


      CodePointPair("*o)","Ὀ"), //8008
      CodePointPair("*o(","Ὁ"), //8009
      CodePointPair("*o)\\","Ὂ"), //8010
      CodePointPair("*o(\\","Ὃ"), //8011
      CodePointPair("*o)/","Ὄ"), //8012
      CodePointPair("*o(/","Ὅ"), //8013


      CodePointPair("u)","ὐ"), //8016
      CodePointPair("u(","ὑ"), //8017
      CodePointPair("u)\\","ὒ"), //8018
      CodePointPair("u(\\","ὓ"), //8019
      CodePointPair("u)/","ὔ"), //8020
      CodePointPair("u(/","ὕ"), //8021
      CodePointPair("u)=","ὖ"), //8022
      CodePointPair("u(=","ὗ"), //8023

      CodePointPair("*u)","὘"), //8024
      CodePointPair("*u(","Ὑ"), //8025
      CodePointPair("*u)\\","὚"), //8026
      CodePointPair("*u(\\","Ὓ"), //8027
      CodePointPair("*u)/","὜"), //8028
      CodePointPair("*u(/","Ὕ"), //8029
      CodePointPair("*u)=","὞"), //8030
      CodePointPair("*u(=","Ὗ"), //8031

      CodePointPair("w)","ὠ"), //8032
      CodePointPair("w(","ὡ"), //8033
      CodePointPair("w)\\","ὢ"), //8034
      CodePointPair("w(\\","ὣ"), //8035
      CodePointPair("w)/","ὤ"), //8036
      CodePointPair("w(/","ὥ"), //8037
      CodePointPair("w)=","ὦ"), //8038
      CodePointPair("w(=","ὧ"), //8039

      CodePointPair("","Ὠ"), //8040
      CodePointPair("","Ὡ"), //8041
      CodePointPair("","Ὢ"), //8042
      CodePointPair("","Ὣ"), //8043
      CodePointPair("","Ὤ"), //8044
      CodePointPair("","Ὥ"), //8045
      CodePointPair("","Ὦ"), //8046
      CodePointPair("","Ὧ"), //8047

      CodePointPair("a\\","ὰ"), //8048
      //CodePointPair("a/","ά"), //8049
      CodePointPair("e\\","ὲ"), //8050
      //CodePointPair("e/","έ"), //8051
      CodePointPair("h\\","ὴ"), //8052
      //CodePointPair("h/","ή"), //8053
      CodePointPair("i\\","ὶ"), //8054
      //CodePointPair("i/","ί"), //8055
      CodePointPair("o\\","ὸ"), //8056
      //CodePointPair("o/","ό"), //8057
      CodePointPair("u\\","ὺ"), //8058
      //CodePointPair("u/","ύ"), //8059
      CodePointPair("w\\","ὼ"), //8060
      //CodePointPair("w/","ώ"), //8061


      CodePointPair("a)|","ᾀ"), //8064
      CodePointPair("a(|","ᾁ"), //8065
      CodePointPair("a)\\|","ᾂ"), //8066
      CodePointPair("a(\\|","ᾃ"), //8067
      CodePointPair("a)/|","ᾄ"), //8068
      CodePointPair("a(/|)","ᾅ"), //8069
      CodePointPair("a)=|","ᾆ"), //8070
      CodePointPair("a(=|","ᾇ"), //8071

      CodePointPair("","ᾈ"), //8072
      CodePointPair("","ᾉ"), //8073
      CodePointPair("","ᾊ"), //8074
      CodePointPair("","ᾋ"), //8075
      CodePointPair("","ᾌ"), //8076
      CodePointPair("","ᾍ"), //8077
      CodePointPair("","ᾎ"), //8078
      CodePointPair("","ᾏ"), //8079

      CodePointPair("h)|","ᾐ"), //8080
      CodePointPair("h(|","ᾑ"), //8081
      CodePointPair("h)\\|","ᾒ"), //8082
      CodePointPair("h(\\|","ᾓ"), //8083
      CodePointPair("h)/|","ᾔ"), //8084
      CodePointPair("h(/|","ᾕ"), //8085
      CodePointPair("h)=|","ᾖ"), //8086
      CodePointPair("h(=|","ᾗ"), //8087

      CodePointPair("","ᾘ"), //8088
      CodePointPair("","ᾙ"), //8089
      CodePointPair("","ᾚ"), //8090
      CodePointPair("","ᾛ"), //8091
      CodePointPair("","ᾜ"), //8092
      CodePointPair("","ᾝ"), //8093
      CodePointPair("","ᾞ"), //8094
      CodePointPair("","ᾟ"), //8095

      CodePointPair("w)|","ᾠ"), //8096
      CodePointPair("w(|","ᾡ"), //8097
      CodePointPair("w)\\|","ᾢ"), //8098
      CodePointPair("w(\\|","ᾣ"), //8099
      CodePointPair("w)/|","ᾤ"), //8100
      CodePointPair("w(/|","ᾥ"), //8101
      CodePointPair("w)=|","ᾦ"), //8102
      CodePointPair("w(=|","ᾧ"), //8103

      CodePointPair("*w)|","ᾨ"), //8104
      CodePointPair("*w(|","ᾩ"), //8105
      CodePointPair("*w)\\|","ᾪ"), //8106
      CodePointPair("*w(\\|","ᾫ"), //8107
      CodePointPair("*w)/|","ᾬ"), //8108
      CodePointPair("*w(/|","ᾭ"), //8109
      CodePointPair("*w)=|","ᾮ"), //8110
      CodePointPair("*w(=|","ᾯ"), //8111

      CodePointPair("","ᾰ"), //8112
      CodePointPair("","ᾱ"), //8113

      CodePointPair("a\\|","ᾲ"), //8114
      CodePointPair("a|","ᾳ"), //8115
      CodePointPair("a/|","ᾴ"), //8116
      //
      CodePointPair("a=","ᾶ"), //8118
      CodePointPair("a=|","ᾷ"), //8119

      CodePointPair("","Ᾰ"), //8120
      CodePointPair("","Ᾱ"), //8121

      CodePointPair("","Ὰ"), //8122
      CodePointPair("","Ά"), //8123
      CodePointPair("","ᾼ"), //8124
      CodePointPair("","᾽"), //8125
      CodePointPair("","ι"), //8126
      CodePointPair("","᾿"), //8127
      CodePointPair("","῀"), //8128
      CodePointPair("","῁"), //8129

      CodePointPair("h\\|","ῂ"), //8130
      CodePointPair("h|","ῃ"), //8131
      CodePointPair("h/|","ῄ"), //8132
      //
      CodePointPair("h=","ῆ"), //8134
      CodePointPair("h=|","ῇ"), //8135

      CodePointPair("","Ὲ"), //8136
      CodePointPair("","Έ"), //8137

      CodePointPair("","Ὴ"), //8138
      CodePointPair("","Ή"), //8139
      CodePointPair("*h|","ῌ"), //8140

      CodePointPair("","῍"), //8141
      CodePointPair("","῎"), //8142
      CodePointPair("","῏"), //8143

      CodePointPair("","ῐ"), //8144
      CodePointPair("","ῑ"), //8145

      CodePointPair("i\\+","ῒ"), //8146
      //CodePointPair("i/+","ΐ"), //8147
      //
      //
      CodePointPair("i=","ῖ"), //8150
      CodePointPair("i=+","ῗ"), //8151

      CodePointPair("","Ῐ"), //8152
      CodePointPair("","Ῑ"), //8153

      CodePointPair("","Ὶ"), //8154
      CodePointPair("","Ί"), //8155


      CodePointPair("","῝"), //8157
      CodePointPair("","῞"), //8158
      CodePointPair("","῟"), //8159

      CodePointPair("","ῠ"), //8160
      CodePointPair("","ῡ"), //8161

      CodePointPair("u\\+","ῢ"), //8162
      //CodePointPair("u/+","ΰ"), //8163

      CodePointPair("r)","ῤ"), //8164
      CodePointPair("r(","ῥ"), //8165

      CodePointPair("u=","ῦ"), //8166
      CodePointPair("u=+","ῧ"), //8167

      CodePointPair("","Ῠ"), //8168
      CodePointPair("","Ῡ"), //8169
      CodePointPair("","Ὺ"), //8170
      CodePointPair("","Ύ"), //8171

      CodePointPair("*r(","Ῥ"), //8172
      CodePointPair("","῭"), //8173
      CodePointPair("","΅"), //8174
      CodePointPair("","`"), //8175

      CodePointPair("w\\|","ῲ"), //8178
      CodePointPair("w|","ῳ"), //8179
      CodePointPair("w/|","ῴ"), //8180


      CodePointPair("w=","ῶ"), //8182
      CodePointPair("w=|","ῷ"), //8183

      CodePointPair("","Ὸ"), //8184
      CodePointPair("","Ό"), //8185
      CodePointPair("","Ὼ"), //8186
      CodePointPair("","Ώ"), //8187
      CodePointPair("*w|","ῼ"), //8188
      CodePointPair("","´"), //8189
      CodePointPair("","῾"), //8190
      CodePointPair("","῿") //8191
    )

          // conversions broken beyond BMP
          /*
          CodePointPair("","ŀ"), //65856
          CodePointPair("","Ł"), //65857
          CodePointPair("","ł"), //65858
          CodePointPair("","Ń"), //65859
          CodePointPair("","ń"), //65860
          CodePointPair("","Ņ"), //65861
          CodePointPair("","ņ"), //65862
          CodePointPair("","Ň"), //65863
          CodePointPair("","ň"), //65864
          CodePointPair("","ŉ"), //65865
          CodePointPair("","Ŋ"), //65866
          CodePointPair("","ŋ"), //65867
          CodePointPair("","Ō"), //65868
          CodePointPair("","ō"), //65869
          CodePointPair("","Ŏ"), //65870
          CodePointPair("","ŏ"), //65871
          CodePointPair("","Ő"), //65872
          CodePointPair("","ő"), //65873
          CodePointPair("","Œ"), //65874
          CodePointPair("","œ"), //65875
          CodePointPair("","Ŕ"), //65876
          CodePointPair("","ŕ"), //65877
          CodePointPair("","Ŗ"), //65878
          CodePointPair("","ŗ"), //65879
          CodePointPair("","Ř"), //65880
          CodePointPair("","ř"), //65881
          CodePointPair("","Ś"), //65882
          CodePointPair("","ś"), //65883
          CodePointPair("","Ŝ"), //65884
          CodePointPair("","ŝ"), //65885
          CodePointPair("","Ş"), //65886
          CodePointPair("","ş"), //65887
          CodePointPair("","Š"), //65888
          CodePointPair("","š"), //65889
          CodePointPair("","Ţ"), //65890
          CodePointPair("","ţ"), //65891
          CodePointPair("","Ť"), //65892
          CodePointPair("","ť"), //65893
          CodePointPair("","Ŧ"), //65894
          CodePointPair("","ŧ"), //65895
          CodePointPair("","Ũ"), //65896
          CodePointPair("","ũ"), //65897
          CodePointPair("","Ū"), //65898
          CodePointPair("","ū"), //65899
          CodePointPair("","Ŭ"), //65900
          CodePointPair("","ŭ"), //65901
          CodePointPair("","Ů"), //65902
          CodePointPair("","ů"), //65903
          CodePointPair("","Ű"), //65904
          CodePointPair("","ű"), //65905
          CodePointPair("","Ų"), //65906
          CodePointPair("","ų"), //65907
          CodePointPair("","Ŵ"), //65908
          CodePointPair("","ŵ"), //65909
          CodePointPair("","Ŷ"), //65910
          CodePointPair("","ŷ"), //65911
          CodePointPair("","Ÿ"), //65912
          CodePointPair("","Ź"), //65913
          CodePointPair("","ź"), //65914
          CodePointPair("","Ż"), //65915
          CodePointPair("","ż"), //65916
          CodePointPair("","Ž"), //65917
          CodePointPair("","ž"), //65918
          CodePointPair("","ſ"), //65919
          CodePointPair("","ƀ"), //65920
          CodePointPair("","Ɓ"), //65921
          CodePointPair("","Ƃ"), //65922
          CodePointPair("","ƃ"), //65923
          CodePointPair("","Ƅ"), //65924
          CodePointPair("","ƅ"), //65925
          CodePointPair("","Ɔ"), //65926
          CodePointPair("","Ƈ"), //65927
          CodePointPair("","ƈ"), //65928
          CodePointPair("","Ɖ"), //65929
          CodePointPair("","Ɗ"), //65930
          CodePointPair("","Ƌ"), //65931
          CodePointPair("","ƌ"), //65932
          CodePointPair("","ƍ"), //65933
          CodePointPair("","Ǝ"), //65934
          CodePointPair("","Ə"), //65935
          */

}
