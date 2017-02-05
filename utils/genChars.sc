
println("char\tdecimal\thex")
for (i <- 0x0370 to 0x03FF) {
  println(i.toChar + "\t" + i + "\t0x" + i.toHexString )
}


for (i <-0x10140 to 0x1018F) {
  println(i.toChar + "\t" + i + "\t0x" + i.toHexString)
}


for (i <- 0x1F00 to 0x1FFF) {
  println(i.toChar + "\t" + i + "\t0x" + i.toHexString)
}
