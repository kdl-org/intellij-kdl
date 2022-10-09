package dev.kdl.lang.escape

import com.intellij.psi.tree.IElementType
import java.io.StringReader
import java.util.stream.IntStream

fun unescapeString(rawValue: String): String {
    val lexer = KdlStringLexer(StringReader(rawValue))

    return tokenize(lexer)
        .joinToString(separator = "") {
            val (type, text) = it
            when (type) {
                EscapeType.NOT_ESCAPE -> text
                else -> decodeEscape(text)
            }
        }
}

fun escapeString(rawValue: String): String {
    val codepoints = rawValue.codePoints()
        .flatMap {
            when (it) {
                0x000A -> "\\n".codePoints() // LF
                0x000D -> "\\r".codePoints() // CR
                0x0009 -> "\\t".codePoints() // TAB
                0x005C -> "\\\\".codePoints() // BACKSLASH
                0x002F -> "\\/".codePoints() // FORWARDSLASH
                0x0022 -> "\\\"".codePoints() // QUOTE
                0x0008 -> "\\b".codePoints() // BACKSPACE
                0x000C -> "\\f".codePoints() // FORM_FEED
                else -> IntStream.of(it)
            }
        }
        .toArray()

    return String(codepoints, 0, codepoints.size)
}

private fun tokenize(lexer: KdlStringLexer): Sequence<Pair<IElementType, CharSequence>> =
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