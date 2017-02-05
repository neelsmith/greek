import java.text.Normalizer

val s = "μῆνιν ἄειδε θεὰ"

println(Normalizer.normalize(s, Normalizer.Form.NFC))
