package dev.kdl.lang.highlighter

import dev.kdl.lang.lexer.KdlFlexAdapter
import dev.kdl.lang.psi.ext.KdlElementTypes
import dev.kdl.lang.psi.ext.KdlElementTypes.*
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType


class KdlSyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer = KdlFlexAdapter()

    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> = when (tokenType) {
        KdlElementTypes.BARE_IDENTIFIER -> BARE_IDENTIFIER_KEYS
        KdlElementTypes.COMMA -> COMMA_KEYS
        SEMI -> SEMICOLON_KEYS
        NULL_LITERAL, TRUE_LITERAL, FALSE_LITERAL -> KEYWORD_KEYS
        R_BRACE, L_BRACE -> BRACES_KEYS
        R_BRACK, L_BRACK -> BRACKETS_KEYS
        R_PAREN, L_PAREN -> PARENTHESES_KEYS
        MULTI_LINE_COMMENT -> BLOCK_COMMENT_KEYS
        SINGLE_LINE_COMMENT -> LINE_COMMENT_KEYS
        DECIMAL_LITERAL, HEX_LITERAL, OCTAL_LITERAL, BINARY_LITERAL -> NUMBER_KEYS
        STRING_LITERAL, RAW_STRING_LITERAL -> STRING_KEYS
        TokenType.BAD_CHARACTER -> BAD_CHAR_KEYS
        else -> EMPTY_KEYS
    }

    companion object {
        val KEYWORD = createTextAttributesKey("KDL_KEYWORD", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION)
        val BARE_IDENTIFIER = createTextAttributesKey("KDL_IDENTIFIER", DefaultLanguageHighlighterColors.KEYWORD)
        val COMMA = createTextAttributesKey("KDL_COMMA", DefaultLanguageHighlighterColors.COMMA)
        val SEMICOLON = createTextAttributesKey("KDL_SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON)
        val PARENTHESES = createTextAttributesKey("KDL_PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES)
        val BRACES = createTextAttributesKey("KDL_BRACES", DefaultLanguageHighlighterColors.BRACES)
        val BRACKETS = createTextAttributesKey("KDL_BRACKETS", DefaultLanguageHighlighterColors.BRACKETS)
        val BLOCK_COMMENT = createTextAttributesKey("KDL_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT)
        val LINE_COMMENT = createTextAttributesKey("KDL_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val NUMBER = createTextAttributesKey("KDL_NUMBER", DefaultLanguageHighlighterColors.NUMBER)
        val STRING = createTextAttributesKey("KDL_STRING", DefaultLanguageHighlighterColors.STRING)
        private val BAD_CHARACTER = createTextAttributesKey("KDL_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)

        private val BAD_CHAR_KEYS = arrayOf(BAD_CHARACTER)
        private val KEYWORD_KEYS = arrayOf(KEYWORD)
        private val BARE_IDENTIFIER_KEYS = arrayOf(BARE_IDENTIFIER)
        private val COMMA_KEYS = arrayOf(COMMA)
        private val SEMICOLON_KEYS = arrayOf(SEMICOLON)
        private val PARENTHESES_KEYS = arrayOf(PARENTHESES)
        private val BRACES_KEYS = arrayOf(BRACES)
        private val BRACKETS_KEYS = arrayOf(BRACKETS)
        private val BLOCK_COMMENT_KEYS = arrayOf(BLOCK_COMMENT)
        private val LINE_COMMENT_KEYS = arrayOf(LINE_COMMENT)
        private val NUMBER_KEYS = arrayOf(NUMBER)
        private val STRING_KEYS = arrayOf(STRING)
        private val EMPTY_KEYS = arrayOf<TextAttributesKey>()
    }
}