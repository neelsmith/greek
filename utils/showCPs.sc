def showCps (s: String, idx : Int) {
  if (idx >= s.length) {
    println("\ndone")
  } else {
    val cp = s.codePointAt(idx)

    println(s(idx) + " " + cp)
    showCps(s, idx + java.lang.Character.charCount(cp))
  }
}


val rightAlpha = "μάλα"
println("RIGHT alpha " + rightAlpha)
showCps(rightAlpha,0)



val wrongAlpha = "μάλα"
println("WRONG alpha " + wrongAlpha)
showCps(wrongAlpha,0)
