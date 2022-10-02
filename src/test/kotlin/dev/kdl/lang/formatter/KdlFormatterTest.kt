package dev.kdl.lang.formatter

import com.intellij.psi.formatter.FormatterTestCase
import com.intellij.testFramework.TestDataPath
import org.junit.Assert.*


@TestDataPath("\$CONTENT_ROOT/testData")
class KdlFormatterTest : FormatterTestCase() {
    override fun getTestDataPath(): String = "src/test/testData"
    override fun getBasePath(): String = "formatter"

    override fun getFileExtension(): String = "kdl"

    fun test1() = doTest()
    fun test2() = doTest()
}