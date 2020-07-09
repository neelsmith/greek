package edu.holycross.shot.greek
import org.scalatest.FlatSpec
import scala.io.Source
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._

class WhiteSpaceOrthographySpec extends FlatSpec {


  val f = "jvm/src/test/resources/evil1.txt"
  val evilDataColumns = Source.fromFile(f).getLines.toVector.head.replaceAll("~",":").split("#")
  val u = CtsUrn(evilDataColumns(0))
  val cn = CitableNode(u, evilDataColumns(1))

  "A LiteraryGreekOrthography"  should  "do something reasonable with badly spaced input" in {

    val lgs = LiteraryGreekString(cn.text)
    //println(lgs.ascii)
    //println(lgs.ucode)
    val tokens =  LiteraryGreekOrthography.tokenizeNode(cn)
  }

  it should "tokenize normal ucode Greek" in {
    val because = "ὅτι κεχώρικε τῶν τρώων τὸν Ἕκτορα ὡς κακεῖ , οὐ γὰρ ἔτ' Οἰνῆος μεγαλήτορος , οὐδέτ' αὐτὸς ἔην θάνε δὲ 										ξανθὸς Μενέλαος 									 ."
    val u = CtsUrn("urn:cts:debug:t.g.v:x")
    val cn = CitableNode(u, because)
    //val tokens =  LiteraryGreekString.tokenizeNode(cn)
    //println("Short list tokenized to " + tokens.size + " tokens")
    //println(LiteraryGreekString.sideBySide(because).mkString(", "))
  }

}
