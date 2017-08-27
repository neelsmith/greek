package edu.holycross.shot.greek


/** Simple object testing accessibility of [[LiteraryGreekString]]
* class in the JVM environment.
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
