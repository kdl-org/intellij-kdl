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
        KdlElementTypes.IDENTIFIER -> IDENTIFIER_KEYS
        KdlElementTypes.COMMA -> COMMA_KEYS
        SEMI -> SEMICOLON_KEYS
        NULL_LITERAL, TRUE_LITERAL, FALSE_LITERAL -> KEYWORD_KEYS
        R_BRACE, L_BRACE -> BRACES_KEYS
        R_BRACK, L_BRACK -> BRACKETS_KEYS
        R_PAREN, L_PAREN -> PARENTHESES_KEYS
        MULTI_LINE_COMMENT -> BLOCK_COMMENT_KEYS
        SINGLE_LINE_COMMENT -> LINE_COMMENT_KEYS
        DECIMAL_LITERAL, HEX_LITERAL, OCTAL_LITERAL, BINARY_LITERAL -> NUMBER_KEYS
        STRING_LITERAL -> STRING_KEYS
        TokenType.BAD_CHARACTER -> BAD_CHAR_KEYS
        else -> EMPTY_KEYS
    }

    companion object {
        private val KEYWORD = createTextAttributesKey("KDL_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
        private val IDENTIFIER = createTextAttributesKey("KDL_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER)
        private val COMMA = createTextAttributesKey("KDL_COMMA", DefaultLanguageHighlighterColors.COMMA)
        private val SEMICOLON = createTextAttributesKey("KDL_SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON)
        private val PARENTHESES = createTextAttributesKey("KDL_PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES)
        private val BRACES = createTextAttributesKey("KDL_BRACES", DefaultLanguageHighlighterColors.BRACES)
        private val BRACKETS = createTextAttributesKey("KDL_BRACKETS", DefaultLanguageHighlighterColors.BRACKETS)
        private val BLOCK_COMMENT = createTextAttributesKey("KDL_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT)
        private val LINE_COMMENT = createTextAttributesKey("KDL_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        private val NUMBER = createTextAttributesKey("KDL_NUMBER", DefaultLanguageHighlighterColors.NUMBER)
        private val STRING = createTextAttributesKey("KDL_STRING", DefaultLanguageHighlighterColors.STRING)
        private val BAD_CHARACTER = createTextAttributesKey("KDL_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)

        private val BAD_CHAR_KEYS = arrayOf(BAD_CHARACTER)
        private val KEYWORD_KEYS = arrayOf(KEYWORD)
        private val IDENTIFIER_KEYS = arrayOf(IDENTIFIER)
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