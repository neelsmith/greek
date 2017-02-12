package edu.holycross.shot.greek


trait GreekString  {
  def ascii: String
  def ucode: String
  val accents = Vector('/','\\','=')

  def isAccent(c: Char): Boolean = {
    accents.contains(c)
  }
  def accent: Option[Char] = {
    getAccent(ascii)
  }

  def getAccent(s: String): Option[Char] = {
    if (isAccent(s.head)) { Some(s.head) }
    else {
      s.size match {
        case 1 => None
        case _ => getAccent(s.tail)
      }
    }
  }

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
