import java.text.Normalizer

val s = "μῆνιν"

println(Normalizer.normalize(s, Normalizer.Form.NFC))
