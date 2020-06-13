package edu.holycross.shot.greek


//import js.JSStringOps._

/** Simple object verifying the accessibility of the [[LiteraryGreekString]]
* class in the Javascript environment.
*/
object Main {
  def main(args: Array[String]): Unit = {

    val litgs = LiteraryGreekString("mh=nin")
    println("Create greek string with ascii: ")
    println("\t" + litgs.ascii)
    println("and Greek unicode")
    println("\t" + litgs.ucode)

  }
}
