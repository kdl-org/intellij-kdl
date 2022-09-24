package dev.kdl.lang.parser

import com.intellij.testFramework.ParsingTestCase

class KdlParserTest : ParsingTestCase("", "kdl", true, KdlParserDefinition()) {
    override fun getTestDataPath(): String = "src/test/testData/parser"
    override fun includeRanges(): Boolean = true

    fun testNodeRecovery() = doTest(true)
}