---
layout: page
title: "LiteraryGreekString: lower-case mappings"
---



| Unicode | ASCII |
|:--------|:------|
| α       | a     |
| β       | b     |
| γ       | g     |
| δ       | d     |
| ε       | e     |
| ζ       | z     |
| η       | h     |
| θ       | q     |
| ι       | i     |
| iota subscript | `|` ("pipe" character) |
| κ       | k     |
| λ       | l     |
| μ       | m     |
| ν       | n     |
| ξ       | c     |
| ο       | o     |
| π       | p     |
| ρ       | r     |
| σ       | s     |
| τ       | t     |
| υ       | u     |
| φ       | f     |
| χ       | x     |
| ψ       | y     |
| ω       | w     |


## A note on terminal sigma

Terminal sigma is a non-semantic presentational variant of the letter sigma determined by word position.  When a Greek string is constructed from Unicode code points, code point 962 (terminal sigma) is mapped to ASCII `s`.  When a Greek string is constructed from an ASCII representation, the sequence `s ` is mapped to `ς  `.  Otherwise, `s` is mapped to code point 963 (initial or medial sigma).
