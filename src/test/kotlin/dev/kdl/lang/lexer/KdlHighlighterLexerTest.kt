package dev.kdl.lang.lexer

import com.intellij.lexer.Lexer
import dev.kdl.lang.highlighter.KdlSyntaxHighlighterLexer

class KdlHighlighterLexerTest: LexerTestBase() {

    override fun createLexer(): Lexer = KdlSyntaxHighlighterLexer()

    override fun getDirPath(): String = "lexer/highlighter"

    fun test1() = doFileTest()
}