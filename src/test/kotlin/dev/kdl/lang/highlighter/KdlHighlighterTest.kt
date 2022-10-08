package dev.kdl.lang.highlighter

import com.intellij.lexer.Lexer
import dev.kdl.lang.lexer.LexerTestBase

class KdlHighlighterTest: LexerTestBase() {

    override fun createLexer(): Lexer = KdlSyntaxHighlighterLexer()

    override fun getDirPath(): String = "highlighter"

    fun test1() = doFileTest()
}