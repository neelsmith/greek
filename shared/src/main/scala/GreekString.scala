package edu.holycross.shot.greek


trait GreekString  {
  def ascii: String
  def ucode: String


  override def equals(that: Any): Boolean = {
    that match {
      case gs: GreekString => ((gs.ascii == this.ascii) && (gs.ucode == this.ucode))
      case _ => throw GreekException("Cannot compare " + that + " to a GreekString object")
    }
  }
}
