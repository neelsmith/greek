def showCps (s: String, idx : Int) {
  if (idx >= s.length) {
    println("\ndone")
  } else {
    val cp = s.codePointAt(idx)

    println(s(idx) + " " + cp)
    showCps(s, idx + java.lang.Character.charCount(cp))
  }
}

val analyze = "μάλα"
showCps(analyze,0)
