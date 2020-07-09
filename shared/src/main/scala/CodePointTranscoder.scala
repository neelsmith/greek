package edu.holycross.shot.greek

import scala.scalajs.js.annotation._

import wvlet.log._

import scala.annotation.tailrec

/** A pairing of an ASCII string with a single Unicode code point.
*
* @param ascii A string of characters that can be used in the `ascii`
* encoding of a class implementing the [[GreekString]] trait.
* @param ucode A corresponding single Unicode codepoint, as a String.
*/
@JSExportAll case class CodePointPair(ascii: String, ucode: String)


/** Manager for two-way mappings between ascii-based character sequences
* and code points in the Greek and Coptic or Extended Greek blocks of Unicode.
*/
@JSExportAll  object CodePointTranscoder extends LogSupport {

  // Compute Vector of code point values from String
  def strToCps(s: String, cpVector: Vector[Int] = Vector.empty[Int], idx : Int = 0) : Vector[Int] = {
   if (idx >= s.length) {
     cpVector
   } else {
     val cp = s.codePointAt(idx)
     strToCps(s, cpVector :+ cp, idx + java.lang.Character.charCount(cp))
   }
  }

  // Compose a String from a Vector of code point values
  def cpsToString(v: Vector[Int]) = {
    val chs = v.map { cp =>
      new String(Character.toChars(cp))
    }
    chs.mkString
  }

  def sideBySide(s: String) =  {
    def cpList = strToCps(s)
    cpList.map(cp => cp + s" (${cpsToString(Vector(cp))})")
  }

  /** Find a single `ucode` code point, as a String, for a string of ASCII
  * characters.
  *
  * @param asciiCodePoint String of characters from `ascii` encoding
  * equated with a single `ucode` codepoint.
  */
  def ucodeCodePoint(asciiCodePoint: String) : String ={
    val matchingPairs = CodePointTranscoder.pairings.filter(_.ascii == asciiCodePoint)
    matchingPairs.size match {
      case 0 => {
        warn(s"CodePointTranscoder: no character matching ascii ${asciiCodePoint}")
        s"#${strToCps(asciiCodePoint)}#" //{  println("No character for " + asciiCodePoint); "#"}
      }
      case 1 => matchingPairs(0).ucode
      case _ => throw GreekException("Found multiple ascii mappings for " + asciiCodePoint)
    }
  }


  /** Find the `ascii` String encoding for a single `ucode` codepoint.
  *
  * @param ucodeCodePoint A String representing a single codepoint in the
  * `ucode` encoding.
  */
  def asciiCodePoint(ucodeCodePoint: String) : String = {
    val matchingPairs = CodePointTranscoder.pairings.filter(_.ucode == ucodeCodePoint)
    matchingPairs.size match {
      case 0 => {
        warn(s"CodePointTranscoder: no character matching unicode ${ucodeCodePoint}")
        //s"#${LiteraryGreekOrthography.sideBySide(ucodeCodePoint)}#"
        s"#${ucodeCodePoint}#"
      }
      case 1 => matchingPairs(0).ascii
      case _ => throw GreekException("Found multiple unicode mappings for " + ucodeCodePoint)
    }
  }

  def transcodableUcodeCP(cp: Int): Boolean = {
    val s = cpsToString(Vector(cp))
    val matchingPairs = CodePointTranscoder.pairings.filter(_.ucode == s)
    matchingPairs.size match {
      case 0 => false
      case 1 => true
      case _ => throw GreekException("Found multiple unicode mappings for " + s + " from code point " + cp)
    }
  }



  /** Utility function to tidy up badly encoded Unicode when a combining
  * accent is erroneously placed on a preceding space by luddites equating
  * typesetting with encoding.
  *
  * @param s String to fix.
  */
  def swapPrecedingBreathings(s: String): String = {
    // initial vowel w smooth + grave
    s.replaceAll("""\u0313\u0300\u0399""" ," " + ucodeCodePoint("*i)\\")).

    // initial vowel with smooth + acute
    replaceAll("""\u0313\u0301\u0391""" ," " + ucodeCodePoint("*a)/")).
    replaceAll("""\u0313\u0301\u0395""", " " + ucodeCodePoint("*e)/")).
    replaceAll("""\u0313\u0301\u0397""", " " + ucodeCodePoint("*h)/")).
    replaceAll("""\u0313\u0301\u0399""", " " + ucodeCodePoint("*i)/")).
    replaceAll("""\u0313\u0301\u039f""", " " + ucodeCodePoint("*o)/")).

    // initial vowel with smooth + circumflex
    replaceAll("""\u0313\u0342\u0391""", " " + ucodeCodePoint("*a)=")).
    replaceAll("""\u0313\u0342\u0397""", " " + ucodeCodePoint("*h)=")).
    replaceAll("""\u0313\u0342\u0399""", " " + ucodeCodePoint("*i)=")).
    replaceAll("""\u0313\u0342\u03a9""", " " + ucodeCodePoint("*w)=")). //this is busted

    // initial with smooth breathing
    replaceAll("""\u0313\u0391""", " " + ucodeCodePoint("*a)")).
    replaceAll("""\u0313\u0395""", " " + ucodeCodePoint("*e)")).
    replaceAll("""\u0313\u0399""", " " + ucodeCodePoint("*i)")).
    replaceAll("""\u0313\u039f""", " " + ucodeCodePoint("*o)")).
    replaceAll("""\u0313\u03a9""", " " + ucodeCodePoint("*w)")). // so is this.  all omegas?

    // initial with rough + acute
    replaceAll("""\u0314\u0301\u0391""", " " + ucodeCodePoint("*a(/")).
    replaceAll("""\u0314\u0301\u0395""", " " + ucodeCodePoint("*e(/")).
    replaceAll("""\u0314\u0301\u0397""", " " + ucodeCodePoint("*h(/")).
    replaceAll("""\u0314\u0301\u0399""", " " + ucodeCodePoint("*i(/")).
    replaceAll("""\u0314\u0301\u039f""", " " + ucodeCodePoint("*o(/")).
    replaceAll("""\u0314\u0301\u03a5""", " " + ucodeCodePoint("*u(/")).

    // initial with rough + acute
    replaceAll("""\u0314\u0391""", " " + ucodeCodePoint("*a(")).
    replaceAll("""\u0314\u0395""", " " + ucodeCodePoint("*e(")).
    replaceAll("""\u0314\u0397""", " " + ucodeCodePoint("*h(")).
    replaceAll("""\u0314\u0399""", " " + ucodeCodePoint("*i(")).
    replaceAll("""\u0314\u039f""", " " + ucodeCodePoint("*o(")).
    replaceAll("""\u0314\u03a1""", " " + ucodeCodePoint("*r(")).
    replaceAll("""\u0314\u03a5""", " " + ucodeCodePoint("*u("))

  }

  /** Extract first series of characters from an ascii String
  * forming a single Unicode code point by recursively looking ahead
  * as long as following character is a combining character.
  *
  * @param s String to extract code point from.
  * @param accumulator String accumulasted so far.
  *
  */
  @tailrec def peekAhead(s: String, accumulator: String = "", combining: Vector[Char]): String = {
    if (s.size < 2) {
      accumulator + s
    } else {
      if (s(0) == '*') {
        if (s.size == 2) {
          accumulator + s
        } else if (combining.contains(s(2))) {
          peekAhead(s.drop(2), accumulator + s.take(2), combining)
        } else {
          accumulator + s.take(2)
        }

      } else if (combining.contains(s(1))) {
        peekAhead(s.drop(1), accumulator + s.head, combining)
      } else {
        accumulator + s.head.toString
      }
    }
  }


  // "Permitted markup" characters, in addition to ASCII
  // punctuation and white space:
  val leftCurly = "“"
  val rightCurly = "”"
  val emDash = "—"
  val numericTick = '\u0374'


  /** All recognized pairings of `ascii` String encodings
  * to `ucode` code points (as Strings) in the Greek and Extended Greek
  * blocks of Unicode.  Ascii values must be unique.
  */
  def pairings =

    Vector(

      CodePointPair(leftCurly, leftCurly),
      CodePointPair(rightCurly, rightCurly),
      CodePointPair(emDash, emDash),
      CodePointPair(s"${numericTick}", s"${numericTick}"),

      CodePointPair(" ", " "),
      CodePointPair("\n", "\n"),
      CodePointPair("\r", "\r"),
      CodePointPair("\t", "\t"),

      CodePointPair(",", ","),
      CodePointPair(".", "."),
      CodePointPair(":", ":"),
      CodePointPair(";", ";"),
      CodePointPair("'", "'"),

      CodePointPair("\"", "\""),


      // For Attic Greek Strings
      CodePointPair("e=", "ê"),
      CodePointPair("o=", "ô"),


      // Beginning of Greek and Coptic block of Unicode is 880 x0370
      //CodePointPair("","Ͱ"), //880 x0370
      //CodePointPair("","ͱ"), //881
      //CodePointPair("","Ͳ"), //882
      //CodePointPair("","ͳ"), //883
      //CodePointPair("","ʹ"), //884
      //CodePointPair("","͵"), //885
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
      //CodePointPair("","΄"), //900 // modern Greek tonos
      //CodePointPair("","΅"), //901
      CodePointPair("*a/","Ά"), //902  // x0386
      //CodePointPair("","·"), //903
      CodePointPair("*e/","Έ"), //904
      CodePointPair("*h/","Ή"), //905
      CodePointPair("*i/","Ί"), //906
      //CodePointPair("","΋"), //907
      CodePointPair("*o/","Ό"), //908

      CodePointPair("*u/","Ύ"), //910
      CodePointPair("*w/","Ώ"), //911
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
      // the next series code points do not properly belong
      // in a semantic encoding, so commented out here:
      //CodePointPair("","Ϗ"), //975
      //CodePointPair("","ϐ"), //976
      //CodePointPair("","ϑ"), //977
      //CodePointPair("","ϒ"), //978
      //CodePointPair("","ϓ"), //979
      //CodePointPair("","ϔ"), //980
      //CodePointPair("","ϕ"), //981
      //CodePointPair("","ϖ"), //982
      //CodePointPair("","ϗ"), //983
      // Allow 3 code points used in case-insensitive
      // encoding of numbers to be passed through
      // unaltered:
      //CodePointPair("","Ϙ"), //984
      CodePointPair("ϙ","ϙ"), //985
      //CodePointPair("","Ϛ"), //986 \x03da
      CodePointPair("ϛ","ϛ"), //987
      //CodePointPair("","Ϝ"), //988
      //CodePointPair("","ϝ"), //989
      //CodePointPair("","Ϟ"), //990
      //CodePointPair("","ϟ"), //991
      //CodePointPair("","Ϡ"), //992
      CodePointPair("ϡ","ϡ"), //993
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
      //CodePointPair("","Ϻ"), //1018
      //CodePointPair("","ϻ"), //1019
      //CodePointPair("","ϼ"), //1020
      //CodePointPair("","Ͻ"), //1021
      //CodePointPair("","Ͼ"), //1022
      //CodePointPair("","Ͽ"), //1023




      // Greek Extended block of Unicode
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

      CodePointPair("*w)","Ὠ"), //8040
      CodePointPair("*w(","Ὡ"), //8041
      CodePointPair("*w)\\","Ὢ"), //8042
      CodePointPair("*w(\\)","Ὣ"), //8043
      CodePointPair("*w)/","Ὤ"), //8044
      CodePointPair("*w(/","Ὥ"), //8045
      CodePointPair("*w)=","Ὦ"), //8046
      CodePointPair("*w(=","Ὧ"), //8047

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

      CodePointPair("*a)|","ᾈ"), //8072 \x1f88
      CodePointPair("*a(|","ᾉ"), //8073
      CodePointPair("*a(\\|","ᾊ"), //8074
      //CodePointPair("*a","ᾋ"), //8075 using 913 x0391
      CodePointPair("*a(/|","ᾌ"), //8076
      CodePointPair("*a)/|","ᾍ"), //8077
      CodePointPair("*a)=|","ᾎ"), //8078
      CodePointPair("*a(/|","ᾏ"), //8079

      CodePointPair("h)|","ᾐ"), //8080
      CodePointPair("h(|","ᾑ"), //8081
      CodePointPair("h)\\|","ᾒ"), //8082
      CodePointPair("h(\\|","ᾓ"), //8083
      CodePointPair("h)/|","ᾔ"), //8084
      CodePointPair("h(/|","ᾕ"), //8085
      CodePointPair("h)=|","ᾖ"), //8086
      CodePointPair("h(=|","ᾗ"), //8087

      CodePointPair("*h)|","ᾘ"), //8088
      CodePointPair("*h(|","ᾙ"), //8089
      CodePointPair("*h)\\|","ᾚ"), //8090
      CodePointPair("*h(\\|","ᾛ"), //8091
      CodePointPair("*h)/|","ᾜ"), //8092
      CodePointPair("*h(/|","ᾝ"), //8093
      CodePointPair("*h)=|","ᾞ"), //8094
      CodePointPair("*h(=|","ᾟ"), //8095

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

      CodePointPair("a^","ᾰ"), //8112
      CodePointPair("a_","ᾱ"), //8113

      CodePointPair("a\\|","ᾲ"), //8114
      CodePointPair("a|","ᾳ"), //8115
      CodePointPair("a/|","ᾴ"), //8116
      //
      CodePointPair("a=","ᾶ"), //8118
      CodePointPair("a=|","ᾷ"), //8119

      CodePointPair("*a^","Ᾰ"), //8120
      CodePointPair("*a_","Ᾱ"), //8121

      CodePointPair("*a\\","Ὰ"), //8122
      CodePointPair("*a/","Ά"), //8123
      CodePointPair("*a|","ᾼ"), //8124

      //CodePointPair("","᾽"), //8125
      //CodePointPair("","ι"), //8126
      //CodePointPair("","᾿"), //8127
      //CodePointPair("","῀"), //8128
      //CodePointPair("","῁"), //8129

      CodePointPair("h\\|","ῂ"), //8130
      CodePointPair("h|","ῃ"), //8131
      CodePointPair("h/|","ῄ"), //8132
      //
      CodePointPair("h=","ῆ"), //8134
      CodePointPair("h=|","ῇ"), //8135

      CodePointPair("*d\\","Ὲ"), //8136
      CodePointPair("*e/","Έ"), //8137

      CodePointPair("*h\\","Ὴ"), //8138
      CodePointPair("*h//","Ή"), //8139
      CodePointPair("*h|","ῌ"), //8140

      //CodePointPair("","῍"), //8141
      //CodePointPair("","῎"), //8142
      //CodePointPair("","῏"), //8143

      CodePointPair("i^","ῐ"), //8144
      CodePointPair("i_","ῑ"), //8145

      CodePointPair("i\\+","ῒ"), //8146
      CodePointPair("i/\\+","ΐ"), //8147
      //
      //
      CodePointPair("i=","ῖ"), //8150
      CodePointPair("i=\\+","ῗ"), //8151

      CodePointPair("*i^","Ῐ"), //8152
      CodePointPair("*i_","Ῑ"), //8153

      CodePointPair("*i\\","Ὶ"), //8154
      CodePointPair("*i/","Ί"), //8155


      //CodePointPair("","῝"), //8157
      //CodePointPair("","῞"), //8158
      //CodePointPair("","῟"), //8159

      CodePointPair("u^","ῠ"), //8160
      CodePointPair("u_","ῡ"), //8161

      CodePointPair("u\\+","ῢ"), //8162
      CodePointPair("u/+","ΰ"), //8163

      CodePointPair("r)","ῤ"), //8164
      CodePointPair("r(","ῥ"), //8165

      CodePointPair("u=","ῦ"), //8166
      CodePointPair("u=+","ῧ"), //8167

      CodePointPair("*u^","Ῠ"), //8168
      CodePointPair("*u_","Ῡ"), //8169
      CodePointPair("*u\\","Ὺ"), //8170
      CodePointPair("*u/","Ύ"), //8171

      CodePointPair("*r(","Ῥ"), //8172
      //CodePointPair("","῭"), //8173
      //CodePointPair("","΅"), //8174
      //CodePointPair("","`"), //8175

      CodePointPair("w\\|","ῲ"), //8178
      CodePointPair("w|","ῳ"), //8179
      CodePointPair("w/|","ῴ"), //8180

      CodePointPair("w=","ῶ"), //8182
      CodePointPair("w=|","ῷ"), //8183

      CodePointPair("*o\\","Ὸ"), //8184
      CodePointPair("*o/","Ό"), //8185
      CodePointPair("*w\\","Ὼ"), //8186
      CodePointPair("*w/","Ώ"), //8187
      CodePointPair("*w|","ῼ"), //8188
      //CodePointPair("","´"), //8189
      //CodePointPair("","῾"), //8190
      //CodePointPair("","῿") //8191

      // The Ancient Greek Numbers block begins at 65856 = x10140.
      // Numeric values used in both ASCII and Unicode Milesian:
      //
      CodePointPair("½", MilesianNumeric.halfString),       // x10175 Greek one-half sign
      CodePointPair("⅓", MilesianNumeric.twoThirdsString),  // x10177 Greek two-thirds sign
      CodePointPair("¾", "\u10178"),  // x10178 Greek three-quarters sign
      CodePointPair("0", "\u1018a") // x1018A Greek zero sign

      // Encoded with Extended Greek block, above:
      //CodePointPair(MilesianNumeric.stigmaString, MilesianNumeric.stigmaString),
      //CodePointPair(MilesianNumeric.qoppaString, MilesianNumeric.qoppaString),
      //CodePointPair(MilesianNumeric.sampiString, MilesianNumeric.sampiString),
    )


}
