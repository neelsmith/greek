package edu.holycross.shot
package greek {

  case class GreekException(message: String = "", cause: Option[Throwable] = None) extends Exception(message) {
    cause.foreach(initCause)
  }

}
