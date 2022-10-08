package dev.kdl.lang.lexer

import com.intellij.lexer.Lexer

class KdlLexerTest: LexerTestBase() {

    override fun createLexer(): Lexer = KdlFlexAdapter()

    override fun getDirPath(): String = "lexer/base"

    fun test1() = doFileTest()

}