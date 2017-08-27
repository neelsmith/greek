package edu.holycross.shot.greek


/** Representation of a string in ancient Greek in two
* parallel encodings:  a representation suitable for machine processing,
* using ASCII characters wherever possible, in which vowels and breathings
* are represented as distinct characters, and a representation suited to
* hard-copy or on-screen printing for human readers, using code points
* from the so-called "Greek and Coptic" or "Extended Greek" blocks of Unicode.
*/
trait GreekString  {

  /** Representation of a Greek string with vowels and breathings
  *  represented as distinct characters.  Implementations of this trait
  *  use ASCII characters wherever possible for this representation.
  */
  def ascii: String

  /** Representation of a Greek string using glyphs that are suitable for
  * display to human readers.  Implementations of this trait
  * use characters the so-called "Greek and Coptic" or "Extended Greek" blocks
  * of Unicode wherever possible for this representation.
  */
  def ucode: String

  /** Fixed definition of acute, grave and circumflex accents
  * in ASCII representational system.
  */
  val accents = Vector('/','\\','=')


  /** Convert uppercase characters to lowercase form if possible
  * in this encoding system.
  */
  def toUpper: GreekString

  /** Convert lowercase characters to uppercase form if possible
  * in this encoding system.
  */
  def toLower: GreekString

  /** True if c is an accent character.
  *
  * @param c Character to examine.
  */
  def isAccent(c: Char): Boolean = {
    accents.contains(c)
  }


  /** Get first accent, if any, in this string.
  */
  def accent: Option[Char] = {
    getAccent(ascii)
  }


  /** Recursively examine first character in s to
  * find the first accent character.
  *
  * @param s String to look for accent in.
  */
  private def getAccent(s: String = ascii): Option[Char] = {
    if (isAccent(s.head)) {
      Some(s.head)
    } else {
      s.size match {
        case 1 => None
        case _ => getAccent(s.tail)
      }
    }
  }


  /**  Create a new [[GreekString]] from this one with all accents
  * removed.
  */
  def stripAccent: GreekString

  /**
  */
  val alphabetString: String
  def sequenceOf(c: Char) =  {
    alphabetString.indexOf(c) match {
      case -1 => throw GreekException("Character " + c + " not in alphabet.")
      case idx => idx
    }
  }
  def asciiCompare(s1: String, s2: String): Int = {
    if (s1.isEmpty) {
      s2.isEmpty match {
        case true => 0
        case false => -1
      }
    } else {
      val cf = sequenceOf(s1.head) compare sequenceOf(s2.head)
      if (cf == 0) {
        asciiCompare(s1.drop(1), s2.drop(1))
      } else {
        cf
      }
    }
  }
  override def equals(that: Any): Boolean = {
    that match {
      case gs: GreekString => ((gs.ascii == this.ascii) && (gs.ucode == this.ucode))
      case _ => throw GreekException("Cannot compare " + that + " to a GreekString object")
    }
  }

}
