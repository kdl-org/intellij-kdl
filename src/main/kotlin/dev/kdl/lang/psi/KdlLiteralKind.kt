package dev.kdl.lang.psi

import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import dev.kdl.lang.escape.unescapeString
import dev.kdl.lang.psi.ext.KdlElementTypes.*
import java.math.BigDecimal
import java.math.BigInteger

sealed class KdlLiteralKind(val node: ASTNode) {
    class BooleanLiteral(node: ASTNode) : KdlLiteralKind(node) {
        val value: Boolean = node.chars == "true"
    }

    class NumberLiteral(node: ASTNode) : KdlLiteralKind(node) {
        val value: BigDecimal? get() {
            val textValue = offsetsForNumber(node).substring(node.text)
            val (start, radix) = when (textValue.take(2)) {
                "0x" -> 2 to 16
                "0o" -> 2 to 8
                "0b" -> 2 to 2
                else -> 0 to 10
            }

            val cleanTextValue = textValue.substring(start).filter { it != '_' }
            return try {
                if (radix == 10) {
                    BigDecimal(cleanTextValue)
                } else {
                    BigInteger(cleanTextValue, radix).toBigDecimal()
                }
            } catch (e: NumberFormatException) {
                null
            }
        }
    }

    class StringLiteral(node: ASTNode) : KdlLiteralKind(node) {
        val value: String get() {
            val rawValue = offsetsForText(node).substring(node.text)

            return when (node.elementType) {
                RAW_STRING_LITERAL -> rawValue
                else -> unescapeString(rawValue)
            }
        }
    }

    class NullLiteral(node: ASTNode) : KdlLiteralKind(node)

    companion object {
        fun fromAstNode(node: ASTNode): KdlLiteralKind? = when (node.elementType) {
            TRUE_LITERAL, FALSE_LITERAL -> BooleanLiteral(node)
            DECIMAL_LITERAL, OCTAL_LITERAL, BINARY_LITERAL, HEX_LITERAL -> NumberLiteral(node)

            STRING_LITERAL, RAW_STRING_LITERAL -> StringLiteral(node)
            NULL_LITERAL -> NullLiteral(node)

            else -> null
        }
    }

}

fun offsetsForNumber(node: ASTNode): TextRange {
    val start = when (node.text.take(2)) {
        "0b" -> 2
        "0o" -> 2
        "0x" -> 2
        else -> 0
    }

    return TextRange.allOf(node.text.substring(start))
}

fun offsetsForText(node: ASTNode): TextRange {
    when (node.elementType) {
        RAW_STRING_LITERAL -> return offsetsForRawText(node)
    }

    return TextRange(1, node.textLength - 1)
}

private fun offsetsForRawText(node: ASTNode): TextRange {
    val text = node.text
    val textLength = node.textLength

    val prefixEnd = 1

    val hashes = run {
        var pos = prefixEnd
        while (pos < textLength && text[pos] == '#') {
            pos++
        }
        pos - prefixEnd
    }

    val openDelimEnd = doLocate(node, prefixEnd) {
        assert(textLength - it >= 1 + hashes && text[it] == '#' || text[it] == '"') { "expected open delim" }
        it + 1 + hashes
    }

    val valueEnd = doLocate(node, openDelimEnd, fun(start: Int): Int {
        text.substring(start).forEachIndexed { i, ch ->
            if (start + i + hashes < textLength &&
                ch == '"' &&
                text.subSequence(start + i + 1, start + i + 1 + hashes).all { it == '#' }) {
                return i + start
            }
        }
        return textLength
    })

    val closeDelimEnd = doLocate(node, valueEnd) {
        assert(textLength - it >= 1 + hashes && text[it] == '"') { "expected close delim" }
        it + 1 + hashes
    }

    return TextRange(openDelimEnd, valueEnd)
}

private inline fun doLocate(node: ASTNode, start: Int, locator: (Int) -> Int): Int =
    if (start >= node.textLength) start else locator(start)

