package dev.kdl.lang.escape

import java.io.StringReader

fun unescapeString(rawValue: String): String {
    val lexer = KdlEscapeLexer(StringReader(rawValue))

    return tokenize(lexer)
        .joinToString(separator = "") {
            val (type, text) = it
            when (type) {
                EscapeType.NOT_ESCAPE -> text
                else -> decodeEscape(text)
            }
        }
}

private fun tokenize(lexer: KdlEscapeLexer): Sequence<Pair<EscapeType, CharSequence>> =
    generateSequence {
        val escapeType = lexer.advance() ?: return@generateSequence null
        escapeType to lexer.yytext()
    }


private fun decodeEscape(esc: CharSequence): String = when (esc) {
    "\\n" -> "\n"
    "\\r" -> "\r"
    "\\t" -> "\t"
    "\\\\" -> "\\"
    "\\/" -> "/"
    "\\\"" -> "\""
    "\\b" -> "\u0008"
    "\\f" -> "\u000C"

    else -> {
        assert(esc.length >= 2)
        assert(esc[0] == '\\')
        when (esc[1]) {
            'u' -> decodeUnicodeEscape(esc)
            else -> error("unreachable")
        }
    }
}

private fun decodeUnicodeEscape(esc: CharSequence): String {
    val cleanTextValue = esc.substring(3, esc.length - 1)

    return Integer.parseInt(cleanTextValue, 16)
        .toChar()
        .toString()
}