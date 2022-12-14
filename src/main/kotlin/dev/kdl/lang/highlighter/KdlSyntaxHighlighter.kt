package dev.kdl.lang.highlighter

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import dev.kdl.lang.escape.EscapeType.ESCAPE
import dev.kdl.lang.escape.EscapeType.NOT_ESCAPE
import dev.kdl.lang.psi.ext.KdlElementTypes
import dev.kdl.lang.psi.ext.KdlElementTypes.*


class KdlSyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer = KdlSyntaxHighlighterLexer()

    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> = when (tokenType) {
        KdlElementTypes.COMMA -> COMMA_KEYS
        SEMI -> SEMICOLON_KEYS
        NULL_LITERAL, TRUE_LITERAL, FALSE_LITERAL -> KEYWORD_KEYS
        R_BRACE, L_BRACE -> BRACES_KEYS
        R_BRACK, L_BRACK -> BRACKETS_KEYS
        R_PAREN, L_PAREN -> PARENTHESES_KEYS
        MULTI_LINE_COMMENT -> BLOCK_COMMENT_KEYS
        SINGLE_LINE_COMMENT -> LINE_COMMENT_KEYS
        DECIMAL_LITERAL, HEX_LITERAL, OCTAL_LITERAL, BINARY_LITERAL -> NUMBER_KEYS
        RAW_STRING_LITERAL, BARE_IDENTIFIER -> STRING_KEYS
        NOT_ESCAPE -> STRING_KEYS
        ESCAPE -> STRING_ESCAPE_SEQUENCE_KEYS
        TokenType.BAD_CHARACTER -> BAD_CHAR_KEYS
        else -> EMPTY_KEYS
    }

    companion object {
        val KEYWORD = createTextAttributesKey("KDL_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
        val IDENTIFIER = createTextAttributesKey("KDL_IDENTIFIER", DefaultLanguageHighlighterColors.INSTANCE_FIELD)
        val PROPERTY_NAME = createTextAttributesKey("KDL_PROPERTY_NAME", DefaultLanguageHighlighterColors.NUMBER)
        val TYPE = createTextAttributesKey("KDL_TYPE", DefaultLanguageHighlighterColors.NUMBER)
        val COMMA = createTextAttributesKey("KDL_COMMA", DefaultLanguageHighlighterColors.COMMA)
        val SEMICOLON = createTextAttributesKey("KDL_SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON)
        val PARENTHESES = createTextAttributesKey("KDL_PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES)
        val BRACES = createTextAttributesKey("KDL_BRACES", DefaultLanguageHighlighterColors.BRACES)
        val BRACKETS = createTextAttributesKey("KDL_BRACKETS", DefaultLanguageHighlighterColors.BRACKETS)
        val BLOCK_COMMENT = createTextAttributesKey("KDL_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT)
        val LINE_COMMENT = createTextAttributesKey("KDL_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val ITEM_COMMENT = createTextAttributesKey("KDL_ITEM_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val NUMBER = createTextAttributesKey("KDL_NUMBER", DefaultLanguageHighlighterColors.NUMBER)
        val STRING = createTextAttributesKey("KDL_STRING", DefaultLanguageHighlighterColors.STRING)
        val STRING_ESCAPE_SEQUENCE = createTextAttributesKey("KDL_STRING_ESCAPE_SEQUENCE", DefaultLanguageHighlighterColors.VALID_STRING_ESCAPE)
        private val BAD_CHARACTER = createTextAttributesKey("KDL_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)

        private val BAD_CHAR_KEYS = arrayOf(BAD_CHARACTER)
        private val KEYWORD_KEYS = arrayOf(KEYWORD)
        private val COMMA_KEYS = arrayOf(COMMA)
        private val SEMICOLON_KEYS = arrayOf(SEMICOLON)
        private val PARENTHESES_KEYS = arrayOf(PARENTHESES)
        private val BRACES_KEYS = arrayOf(BRACES)
        private val BRACKETS_KEYS = arrayOf(BRACKETS)
        private val BLOCK_COMMENT_KEYS = arrayOf(BLOCK_COMMENT)
        private val LINE_COMMENT_KEYS = arrayOf(LINE_COMMENT)
        private val NUMBER_KEYS = arrayOf(NUMBER)
        private val STRING_KEYS = arrayOf(STRING)
        private val STRING_ESCAPE_SEQUENCE_KEYS = arrayOf(STRING_ESCAPE_SEQUENCE)
        private val EMPTY_KEYS = arrayOf<TextAttributesKey>()
    }
}