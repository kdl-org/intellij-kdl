package dev.kdl.lang.highlighter

import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase

@TestDataPath("\$CONTENT_ROOT/testData/highlighter")
class KdlHighlighterTest : BasePlatformTestCase() {
    override fun getTestDataPath(): String = "src/test/testData/highlighter"

    fun testItemComment() = doHighlightingTest(checkInfos = true)

    fun testIllegalWhitespaces() = doHighlightingTest()
    fun testIllegalComment() = doHighlightingTest()

    private fun doHighlightingTest(checkWarnings: Boolean = true, checkInfos: Boolean = false, checkWeakWarnings: Boolean = true) {
        myFixture.testHighlighting(checkWarnings, checkInfos, checkWeakWarnings, "${getTestName(true)}.kdl")
    }
}