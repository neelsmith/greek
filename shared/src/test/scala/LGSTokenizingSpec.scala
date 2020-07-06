package edu.holycross.shot.greek
import org.scalatest.FlatSpec


import edu.holycross.shot.mid.orthography._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._

class LGSTokenizingSpec extends FlatSpec {

  val urn = CtsUrn("urn:cts:greekLit:tlg0540.tlg001.omar:1.1")
  val txt = "περὶ πολλοῦ ἂν ποιησαίμην, ὦ ἄνδρες, "
  val cn = CitableNode(urn, txt)

  "The LiteraryGreekString object" should "split strings on trailing punctuation" in {
    val comma  = "δὲ,"
    val depunctuated = (LiteraryGreekString.depunctuate(comma))
    assert(depunctuated.size == 2)
    assert(depunctuated(1) == ",")
  }

  it should "recognize trailing quote as punctuation" in {
    val trailingQuote = """νῆας" """
    val depunctuated = (LiteraryGreekString.depunctuate(trailingQuote))
    assert(depunctuated.size == 2)
  }

  it should "do something  sane with bad characters" in {
    val massive = """ὅτι κεχώρικε τῶν τρώων τὸν Ἕκτορα ὡς κακεῖ , οὐ γὰρ ἔτ' Οἰνῆος μεγαλήτορος , οὐδέτ' αὐτὸς ἔην θάνε δὲ 	ξανθὸς Μενέλαος 									 . αλλως κεχώρηκε τῶν λοιπῶν Τρώων τὸν Ἕκτορα κατ εξοχὴν μετὰ δὲ τὴν Ἰλίου πόρθησιν Ἕκτωρ Πριάμου καὶ μετα τὸν θάνατον την ἀπο θν  εὐτύχησε τιμήν~ οἱ γὰρ ἐν Βοιωτία Θηβαίοι πιεζόμενοι κακωῖς  ἐμαντεύοντο περὶ ἀπαλλαγῆς~ χρησμος δὲ αὐτοῖς ἐδόθη παύσασθαι τὰ δεινὰ ἐὰν ἐξ ο φρυνίου τῆς Τρωάδος τὰ Ἕκτορος ὁστᾶ διακοσμισθῶσιν  εἰς τὸν παρα τοῖς καλουμένοις τόπον διὸς γονὰς , οἱ δὲ τοῦτο 		ποιήσαντες , καὶ τῶν κακῶν ἀπαλλαγέντες , τια  τιμῆς ἔσχον Ἕκτορα κατά τε τοὺς ἐπείγοντας καιροὺς ἐπικαλοῦνται 							τὴν ἐπιφάνειαν αὐτοῦ ἡ ϊστορία παρα Ἀριστοδήμω ⁑"""

    val u = CtsUrn("urn:cts:debug:w.g.v:1")
    val cn = CitableNode(u, massive)
    val tokens = LiteraryGreekString.tokenizeNode(cn)
    println(tokens.mkString("\n"))
  }

}
