package edu.holycross.shot.greek


/** Representation of a numeric string in ancient Greek in two
* parallel encodings:  a representation suitable for machine processing,
* using ASCII characters wherever possible, and a representation suited to
* hard-copy or on-screen printing for human readers, using code points
* from the so-called "Greek and Coptic" or "Extended Greek" blocks of Unicode.
*/
trait GreekNumeric  {

  /** Representation of a Greek numeric string in ASCII characters.
  */
  def ascii: String

  /** Representation of a Greek numeric string using glyphs that are
  * suitable for display to human readers.  Implementations of this trait
  * use characters the so-called "Greek and Coptic" or "Extended Greek" blocks
  * of Unicode wherever possible for this representation.
  */
  def ucode: String

  /** A single string listing all valid characters in the ASCII
  * representation of this system in their alphabetic order.
  */
  val numericAlphabetString: String

  def toInt: Int

  def toDouble: Double
}
